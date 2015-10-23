package de.upb.s2cx.query;

import java.sql.SQLException;
import java.util.Stack;

import de.upb.s2cx.helper.StringFunctions;
import de.upb.s2cx.parser.sql.Column;
import de.upb.s2cx.parser.sql.Condition;
import de.upb.s2cx.parser.sql.ConditionTree;
import de.upb.s2cx.parser.sql.ParanthesizedValue;
import de.upb.s2cx.parser.sql.Queryable;
import de.upb.s2cx.parser.sql.SortColumn;
import de.upb.s2cx.parser.sql.SqlFunction;
import de.upb.s2cx.parser.sql.Statement;
import de.upb.s2cx.parser.sql.StringValue;
import de.upb.s2cx.parser.sql.Table;
import de.upb.s2cx.parser.sql.Term;
import de.upb.s2cx.parser.sql.xml.AbstractXmlAttribute;
import de.upb.s2cx.parser.sql.xml.XmlAggFunction;
import de.upb.s2cx.parser.sql.xml.XmlColumnAttribute;
import de.upb.s2cx.parser.sql.xml.XmlConcatFunction;
import de.upb.s2cx.parser.sql.xml.XmlElementFunction;
import de.upb.s2cx.parser.sql.xml.XmlElementWrapper;
import de.upb.s2cx.parser.sql.xml.XmlForestFunction;
import de.upb.s2cx.parser.sql.xml.XmlFunctionAttribute;
import de.upb.s2cx.parser.sql.xml.XmlStringAttribute;
import de.upb.s2cx.parser.sql.xml.XmlValue;
import de.upb.s2cx.query.referencetree.ElementNode;
import de.upb.s2cx.query.referencetree.KleeneNode;
import de.upb.s2cx.query.referencetree.OptionalNode;
import de.upb.s2cx.query.referencetree.QueryableNode;
import de.upb.s2cx.query.referencetree.ReferenceTreeNode;
import de.upb.s2cx.query.referencetree.StringNode;
import de.upb.s2cx.schema.ElementState;

public class TreeBuilder {

	private QueryTree qtRoot = null;

	private QueryTree current;

	private ElementNode referenceTreeRoot;

	private Stack<ElementLevelInformation> elLevelStack;

	private Stack<QueryLevelInformation> qLevelStack;

	private int depth = 0;

	private int maxDepth = 0;

	private class QueryLevelInformation {

		protected byte xmlAggCount = 0;

		protected QueryTree query;

		protected byte xmlAggDepth = 0;

		protected boolean nullable = true;

		public QueryLevelInformation(QueryTree query) {
			super();
			this.query = query;
		}
	}

	private class ElementLevelInformation {

		protected ElementState state = ElementState.UNDEFINED;

		protected boolean nullable = true;
	}

	public TreeBuilder(Statement stRoot) throws SQLException {
		super();
		elLevelStack = new Stack<ElementLevelInformation>();
		qLevelStack = new Stack<QueryLevelInformation>();
		referenceTreeRoot = onElementBegin("root");
		referenceTreeRoot.setFirstChild(processStatement(stRoot));
		onElementEnd(referenceTreeRoot);

		qtRoot.setMaxDepth(maxDepth);
		qtRoot.bubbleUpColumns();

		qtRoot.finalizeQuery();
	}

	public QueryTree getQueryTreeRoot() {
		return qtRoot;
	}

	public ReferenceTreeNode getReferenceTree() {
		return referenceTreeRoot;
	}

	private ReferenceTreeNode processStatement(Statement s) throws SQLException {
		incDepth();
		current = new QueryTree(current, true);
		qLevelStack.push(new QueryLevelInformation(current));
		for (Table t : s.getFromClause().getItems()) {
			current.addTable(t, false);
		}
		if (s.getGroupByClause() != null) {
			current.setGroupByClause(s.getGroupByClause());
			for (Column c : s.getGroupByClause().getItems()) {
				current.addColumn(c);
			}
		}
		current.setWhereClause(s.getWhereClause());
		if (current.getWhereClause() != null) {
			processCondition(current.getWhereClause().getCondition());
		}
		qLevelStack.peek().xmlAggDepth = s.getXmlAggDepth();

		if (qtRoot == null) {
			qtRoot = current;
		}
		ReferenceTreeNode node = processXmlValue(s.getXmlValue());
		if (qLevelStack.peek().xmlAggDepth == 0) {
			node = wrapKleene(node);
		} else if (qLevelStack.peek().nullable || qLevelStack.peek().xmlAggDepth == 1 && current.isGrouped()) {
			// node = wrapOptional(node, qLevelStack.peek().xmlAggDepth == 0 || qLevelStack.peek().xmlAggDepth == 1 && current.isGrouped());
		}
		if (current.getOrderByClause() != null) {
			for (SortColumn sc : current.getOrderByClause().getItems()) {
				if (!current.getQueryables().contains(sc.getColumn())) {
					current.addQueryable(sc.getColumn());
				}
			}
		}
		current.getId();
		current = current.getParent();
		qLevelStack.pop();
		decDepth();
		return node;
	}

	private ReferenceTreeNode processXmlValue(XmlValue value) throws SQLException {
		if (value instanceof ParanthesizedValue) {
			return processXmlValue(((ParanthesizedValue) value).getXmlValue());
		} else if (value instanceof Statement) {
			return processStatement((Statement) value);
		} else if (value instanceof XmlAggFunction) {
			return processXmlAggFunction((XmlAggFunction) value);
		} else if (value instanceof XmlConcatFunction) {
			return processXmlConcat(value);
		} else if (value instanceof XmlForestFunction) {
			return processXmlForest(value);
		} else if (value instanceof XmlElementFunction) {
			return processXmlElement((XmlElementFunction) value);
		} else if (value instanceof Term) {
			encounteredString();
			current.addQueryable((Queryable) value);
			processTerm(value, false);
			return new QueryableNode((Queryable) value);
		} else if (value instanceof Column) {
			if (!((Column) value).isNullable()) {
				elLevelStack.peek().nullable = false;
				qLevelStack.peek().nullable = false;
			}
			encounteredString();
			foundColumn((Column) value, true);
			return new QueryableNode((Queryable) value);
		} else if (value instanceof StringValue) {
			elLevelStack.peek().nullable = false;
			qLevelStack.peek().nullable = false;
			encounteredString();
			return new StringNode(value.getValue());
		} else if (value instanceof SqlFunction) {
			elLevelStack.peek().nullable = false;
			qLevelStack.peek().nullable = false;
			encounteredString();
			current.addQueryable((Queryable) value);
			processTerm(value, false);
			return new QueryableNode((Queryable) value);
		}
		throw new RuntimeException("Shoud not happen");
	}

	private ReferenceTreeNode processXmlConcat(XmlValue value) throws SQLException {
		ReferenceTreeNode subtree = null;
		ReferenceTreeNode node = null;
		for (XmlValue v : ((XmlConcatFunction) value).getParameters()) {
			if (node == null) {
				node = processXmlValue(v);
				subtree = node;
			} else {
				node.setNextSibling(processXmlValue(v));
			}
			while (node.getNextSibling() != null) {
				node = node.getNextSibling();
			}
		}
		return subtree;
	}

	private ReferenceTreeNode processXmlElement(XmlElementFunction value) throws SQLException {
		encounteredElement();
		elLevelStack.peek().nullable = false;
		qLevelStack.peek().nullable = false;
		ElementNode elNode = onElementBegin(StringFunctions.unquote(value.getName()));
		ReferenceTreeNode node = null;
		for (AbstractXmlAttribute att : value.getAttributes()) {
			ElementNode attNode = new ElementNode("@" + StringFunctions.unquote(att.getName()));
			if (att instanceof XmlColumnAttribute) {
				attNode.setFirstChild(new QueryableNode(((XmlColumnAttribute) att).getColumn()));
				if (((XmlColumnAttribute) att).getColumn().isNullable()) {
					ReferenceTreeNode optNode = wrapOptional(attNode, false);
					if (node == null) {
						node = optNode;
						elNode.setFirstChild(optNode);
					} else {
						node.setNextSibling(optNode);
						node = optNode;
					}
				} else {
					if (node == null) {
						node = attNode;
						elNode.setFirstChild(attNode);
					} else {
						node.setNextSibling(attNode);
						node = attNode;
					}
				}
				foundColumn(((XmlColumnAttribute) att).getColumn(), true);
			} else if (att instanceof XmlStringAttribute) {
				attNode.setFirstChild(new StringNode(att.getValue()));
				if (node == null) {
					node = attNode;
					elNode.setFirstChild(attNode);
				} else {
					node.setNextSibling(attNode);
					node = attNode;
				}
			} else if (att instanceof XmlFunctionAttribute) {
				attNode.setFirstChild(new QueryableNode(((XmlFunctionAttribute) att).getFunction()));
				if (node == null) {
					node = attNode;
					elNode.setFirstChild(attNode);
				} else {
					node.setNextSibling(attNode);
					node = attNode;
				}
				processXmlValue(((XmlFunctionAttribute) att).getFunction());
			}
		}
		for (XmlValue v : value.getParameters()) {
			ReferenceTreeNode param = processXmlValue(v);
			if (node == null) {
				node = param;
				elNode.setFirstChild(param);
			} else {
				node.setNextSibling(param);
			}
			while (node.getNextSibling() != null) {
				node = node.getNextSibling();
			}
		}
		onElementEnd(elNode);
		return elNode;
	}

	private ReferenceTreeNode processXmlForest(XmlValue value) throws SQLException {
		encounteredElement();
		ReferenceTreeNode subtree = null;
		ReferenceTreeNode node = null;
		for (XmlElementWrapper e : ((XmlForestFunction) value).getParameters()) {
			ElementNode elNode = onElementBegin(StringFunctions.unquote(e.getName()));
			elNode.setFirstChild(processXmlValue(e.getXmlValue()));
			if (onElementEnd(elNode)) {
				ReferenceTreeNode optNode = wrapOptional(elNode, false);
				if (node == null) {
					node = optNode;
					subtree = node;
				} else {
					node.setNextSibling(optNode);
					node = optNode;
				}
			} else {
				elLevelStack.peek().nullable = false;
				qLevelStack.peek().nullable = false;
				if (node == null) {
					node = elNode;
					subtree = node;
				} else {
					node.setNextSibling(elNode);
					while (node.getNextSibling() != null) {
						node = node.getNextSibling();
					}
				}
			}
		}
		return subtree;
	}

	private void processTerm(XmlValue value, boolean add) {
		if (value instanceof Term) {
			processTerm(((Term) value).getLeft(), add);
			processTerm(((Term) value).getRight(), add);
		} else if (value instanceof SqlFunction) {
			processTerm(((SqlFunction) value).getXmlValue(), add);
		} else if (value instanceof Column) {
			foundColumn((Column) value, add);
		}

	}

	@SuppressWarnings("rawtypes")
	private void processCondition(Condition c) {
		if (c instanceof ConditionTree) {
			processCondition(((ConditionTree) c).getLeft());
			processCondition(((ConditionTree) c).getRight());
		} else if (c instanceof Column) {
			foundColumn((Column) c, true);
		} else if (c instanceof Term) {
			processTerm((XmlValue) c, true);
		} else if (c instanceof SqlFunction) {
			processTerm((XmlValue) c, true);
		} else if (c instanceof Statement) {
			if (((Statement) c).getWhereClause() != null) {
				processCondition(((Statement) c).getWhereClause().getCondition());
			}
		}

	}

	private void foundColumn(Column c, boolean add) {
		if (add) {
			qLevelStack.peek().query.addToResponsible(c);
		}
	}

	private ReferenceTreeNode processXmlAggFunction(XmlAggFunction f) throws SQLException {
		ReferenceTreeNode node = null;
		qLevelStack.peek().xmlAggCount++;
		boolean qLevelNullable = qLevelStack.peek().nullable;
		boolean elLevelNullable = elLevelStack.peek().nullable;
		if (!current.isGrouped()) {
			node = processFirstLevelXmlAggFunction(f);
		} else {
			if (qLevelStack.peek().xmlAggDepth == 1) {
				if (current.isGrouped()) {
					node = processSecondLevelXmlAggFunction(f);
				} else {
					node = processFirstLevelXmlAggFunction(f);
				}
			} else if (qLevelStack.peek().xmlAggDepth == 2) {
				if (qLevelStack.peek().xmlAggCount == 1) {
					node = processFirstLevelXmlAggFunction(f);
				} else {
					node = processSecondLevelXmlAggFunction(f);
				}
			} else {
				throw new RuntimeException("XMLAGG depth must be 1 or 2 if an XMLAGG occured.");
			}
		}
		qLevelStack.peek().nullable = qLevelNullable;
		elLevelStack.peek().nullable = elLevelNullable;
		qLevelStack.peek().xmlAggCount--;
		return node;
	}

	private ReferenceTreeNode processFirstLevelXmlAggFunction(XmlAggFunction f) throws SQLException {
		current.setKleene();
		ReferenceTreeNode node = wrapKleene(processXmlValue(f.getParameter()));
		if (f.getOrderByClause() != null) {
			current.setOrderByClause(f.getOrderByClause());
			for (SortColumn sc : f.getOrderByClause().getItems()) {
				current.addColumn(sc.getColumn());
			}
		}
		return node;
	}

	private ReferenceTreeNode wrapKleene(ReferenceTreeNode fc) {
		ReferenceTreeNode node = new KleeneNode(current);
		node.setFirstChild(fc);
		return node;
	}

	private ReferenceTreeNode wrapOptional(ReferenceTreeNode fc, boolean addQuery) {
		ReferenceTreeNode node;
		if (addQuery) {
			node = new OptionalNode(current);
		} else {
			node = new OptionalNode(null);
		}
		node.setFirstChild(fc);
		return node;
	}

	private ReferenceTreeNode processSecondLevelXmlAggFunction(XmlAggFunction f) throws SQLException {
		incDepth();
		QueryTree parent = current;
		current = new QueryTree(current, true);
		current.setKleene();
		current.setGroupByProvider(true);
		for (Table t : parent.getTables().values()) {
			current.addTable(t, true);
		}
		current.setOrderByClause(f.getOrderByClause());
		if (f.getOrderByClause() != null) {
			current.setOrderByClause(f.getOrderByClause());
			for (SortColumn sc : f.getOrderByClause().getItems()) {
				current.addColumn(sc.getColumn());
			}
		}
		qLevelStack.push(new QueryLevelInformation(current));
		qLevelStack.peek().xmlAggDepth = 0;
		ReferenceTreeNode node = wrapKleene(processXmlValue(f.getParameter()));
		if (current.getOrderByClause() != null) {
			for (SortColumn sc : current.getOrderByClause().getItems()) {
				if (!current.getQueryables().contains(sc.getColumn())) {
					current.addQueryable(sc.getColumn());
				}
			}
		}
		current.getId();
		current = current.getParent();
		qLevelStack.pop();
		decDepth();
		return node;
	}

	private void encounteredString() {
		if (elLevelStack.peek().state == ElementState.UNDEFINED) {
			elLevelStack.peek().state = ElementState.STRING;
		} else if (elLevelStack.peek().state == ElementState.NESTED) {
			elLevelStack.peek().state = ElementState.MIXED;
		}
	}

	private void encounteredElement() {
		if (elLevelStack.peek().state == ElementState.UNDEFINED) {
			elLevelStack.peek().state = ElementState.NESTED;
		} else if (elLevelStack.peek().state == ElementState.STRING) {
			elLevelStack.peek().state = ElementState.MIXED;
		}
	}

	private ElementNode onElementBegin(String name) {
		elLevelStack.push(new ElementLevelInformation());
		return new ElementNode(name);
	}

	private boolean onElementEnd(ElementNode elNode) {
		boolean nullable = elLevelStack.peek().nullable;
		if (elLevelStack.peek().state == ElementState.MIXED) {
			elNode.setMixed(true);
		}
		elLevelStack.pop();
		return nullable;
	}

	private void incDepth() {
		depth++;
		if (depth > maxDepth) {
			maxDepth = depth;
		}
	}

	private void decDepth() {
		depth--;
	}
}
