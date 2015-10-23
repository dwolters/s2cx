package de.upb.s2cx.schema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.upb.s2cx.query.referencetree.ElementNode;
import de.upb.s2cx.query.referencetree.KleeneNode;
import de.upb.s2cx.query.referencetree.OptionalNode;
import de.upb.s2cx.query.referencetree.ReferenceTreeNode;

public class SchemaBuilder {

	private ComplexElement root;

	private ReferenceTreeNode refTreeRoot;

	public SchemaBuilder(ReferenceTreeNode refTreeRoot) {
		this.refTreeRoot = refTreeRoot;
	}

	public String getSchema() {
		this.root = getRoot();
		StringBuilder sb = new StringBuilder();
		sb.append("<xsd:schema xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
		this.root.buildSchema(sb);
		sb.append("</xsd:schema>");
		return sb.toString();
	}

	private ComplexElement getRoot() {
		if (root == null) {
			root = process(findActualRoot(refTreeRoot), true);
		}
		return root;
	}

	private ReferenceTreeNode findActualRoot(ReferenceTreeNode node) {
		if (node == refTreeRoot) {
			return findActualRoot(node.getFirstChild());
		}
		if (node instanceof OptionalNode) {
			return findActualRoot(node.getFirstChild());
		}
		return node;

	}

	private ComplexElement process(ReferenceTreeNode node, boolean next) {
		if (!next && node.getNextSibling() != null) {
			Sequence seq = new Sequence(false);
			ReferenceTreeNode current = node;
			while (current != null) {
				seq.addChild(process(current, true));
				current = current.getNextSibling();
			}
			return seq;
		}

		if (node instanceof OptionalNode) {
			return wrapSequence(process(node.getFirstChild(), false), false, true);
		} else if (node instanceof KleeneNode) {
			return wrapSequence(process(node.getFirstChild(), false), true, true);
		} else if (node instanceof ElementNode) {
			return processElementNode((ElementNode) node);
		} else {
			return new TextElement();
		}
	}

	private ComplexElement wrapSequence(ComplexElement e, boolean unbounded, boolean optional) {
		Sequence seq = new Sequence(unbounded);
		if (!unbounded) {
			seq.setOptional(optional);
		}
		seq.addChild(e);
		return seq;
	}

	private Element processElementNode(ElementNode node) {
		String name = node.getLabel();
		List<ComplexElement> children = new ArrayList<ComplexElement>();
		ReferenceTreeNode child = node.getFirstChild();
		List<String> attributes = new ArrayList<String>();
		Set<String> requiredAttributes = new HashSet<String>();
		boolean processedAttr = false;
		while (child != null) {
			if (processedAttr) {
				children.add(process(child, true));
				child = child.getNextSibling();
			} else if (child.isOptional() && child.getFirstChild().getLabel().startsWith("@")) {
				String attr = child.getFirstChild().getLabel().substring(1);
				attributes.add(attr);
				child = child.getNextSibling();
			} else if (child.getLabel().startsWith("@")) {
				String attr = child.getLabel().substring(1);
				attributes.add(attr);
				requiredAttributes.add(attr);
				child = child.getNextSibling();
			} else {
				processedAttr = true;
			}
		}
		Element e = new Element(name, attributes, requiredAttributes, children);
		return e;
	}
}
