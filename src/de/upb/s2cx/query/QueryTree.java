package de.upb.s2cx.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.upb.s2cx.helper.StringFunctions;
import de.upb.s2cx.parser.sql.Column;
import de.upb.s2cx.parser.sql.ColumnFactory;
import de.upb.s2cx.parser.sql.GroupByClause;
import de.upb.s2cx.parser.sql.OrderByClause;
import de.upb.s2cx.parser.sql.Queryable;
import de.upb.s2cx.parser.sql.Table;
import de.upb.s2cx.parser.sql.WhereClause;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.hash.THashMap;
import gnu.trove.procedure.TIntProcedure;

public class QueryTree {

	private TIntList idList;

	private String id = "";

	private Map<String, Table> tables;

	private List<Queryable> queryables;

	private List<Column> keys;

	private OrderByClause orderByClause;

	private QueryTree parent;

	private List<QueryTree> childs;

	private boolean kleene;

	private WhereClause whereClause;

	private GroupByClause groupByClause;

	private boolean groupByProvider = false;

	private int maxDepth;

	public QueryTree(QueryTree parent, boolean register) {
		idList = new TIntArrayList();
		if (parent == null) {
			idList.add(1);
		} else {
			idList.addAll(parent.idList);
			idList.add(parent.childs.size() + 1);
		}
		tables = new HashMap<String, Table>();
		queryables = new ArrayList<Queryable>();
		childs = new ArrayList<QueryTree>();
		this.parent = parent;
		if (parent != null && register) {
			this.parent.registerChild(this);
		}
		kleene = false;
		keys = new ArrayList<Column>();
	}

	public void setOrderByClause(OrderByClause orderBy) {
		this.orderByClause = orderBy;
	}

	public boolean responsibleFor(Column c) {
		return tables.containsValue(c.getTable());
	}

	public QueryTree getParent() {
		return parent;
	}

	public void setColumns(List<Queryable> columns) {
		this.queryables = columns;
	}

	public void setWhereClause(WhereClause where) {
		this.whereClause = where;
	}

	public void setGroupByClause(GroupByClause groupByClause) {
		this.groupByClause = groupByClause;
	}

	private void registerChild(QueryTree q) {
		childs.add(q);
	}

	public Table addTable(Table t, boolean justKeys) throws SQLException {
		t.queryTableDefinition();
		if (!tables.containsKey(t.getAlias())) {
			if (!justKeys) {
				tables.put(t.getAlias(), t);
			}
			for (String key : t.getKeys()) {
				keys.add(ColumnFactory.createColumn(StringFunctions.doubleQuote(key), t));
			}
		} else {
			return tables.get(t.getAlias());
		}
		return t;
	}

	public void setKleene() {
		kleene = true;
	}

	public Queryable addQueryable(Queryable value) {
		for (Queryable v : queryables) {
			if (v.equals(value)) {
				return v;
			}
		}
		queryables.add(value);
		return value;
	}

	public void addColumn(Column c) {
		for (Queryable v : queryables) {
			if (v.equals(c) && v instanceof Column) {
				c.setRedirect((Column) v);
				return;
			}
		}
		queryables.add(c);
	}

	public boolean isAncesterOrSelf(QueryTree q) {
		if (parent != null) {
			return q == this || parent.isAncesterOrSelf(q);
		}
		return q == this;
	}

	public Map<String, Table> getTables() {
		return tables;
	}

	public Column getColumn(Column col) {
		for (Queryable v : queryables) {
			if (v instanceof Column && v.equals(col)) {
				return (Column) v;
			}
		}
		if (parent == null) {
			return null;
		} else {
			return parent.getColumn(col);
		}
	}

	public void addToResponsible(Column c) {
		if (tables.containsKey(c.getTable().getAlias()) || groupByProvider && parent.tables.containsKey(c.getTable().getAlias())) {
			if (!isGrouped() || groupByClause.getItems().contains(c)) {
				this.addColumn(c);
			}
		} else if (parent != null) {
			parent.addToResponsible(c);
		} else {
			throw new RuntimeException("Responsible query not found for Column: " + c.toString());
		}
	}

	public String getId() {
		if (id.isEmpty()) {
			final StringBuilder sb = new StringBuilder();
			idList.forEach(new TIntProcedure() {
				private boolean first = true;

				@Override
				public boolean execute(int value) {
					if (first) {
						first = false;
					} else {
						sb.append(".");
					}
					sb.append(value);
					return true;
				}
			});
			id = sb.toString();
		}
		return id;
	}

	public void finalizeQuery() {
		for (Queryable q : queryables) {
			q.setAlias("COL_" + this.getId() + "-" + (queryables.indexOf(q) + 1));
		}
		if (groupByClause != null) {
			keys.clear();
			keys.addAll(groupByClause.getItems());
		}

		if (orderByClause == null) {
			orderByClause = new OrderByClause();
		}
		int i = 1;
		for (Column c : keys) {
			if (c.getAlias().isEmpty()) {
				c.setAlias("COL_" + this.getId() + "-K" + i);
				queryables.add(c);
				i++;
			}
			orderByClause.add(ColumnFactory.createSortColumn(c, true));
		}
		for (QueryTree c : childs) {
			c.finalizeQuery();
		}
	}

	public QueryTree createSibling() {
		QueryTree s = new QueryTree(parent, false);
		s.idList.set(s.idList.size() - 1, idList.get(idList.size() - 1) + 1);
		s.tables = new THashMap<String, Table>(this.tables);
		if (this.groupByClause != null) {
			s.groupByClause = new GroupByClause();
			for (Column c : this.groupByClause.getItems()) {
				s.groupByClause.add(c);
			}
		}
		if (this.whereClause != null) {
			s.whereClause = this.whereClause;
		}
		return s;
	}

	public void bubbleUpColumns() {
		for (QueryTree c : childs) {
			c.bubbleUpColumns();
		}
		ArrayList<Column> removals = new ArrayList<Column>();
		for (Queryable c : queryables) {
			if (c instanceof Column && !tables.containsKey(((Column) c).getTable().getAlias())) {
				removals.add((Column) c);
			}
		}
		if (parent == null && !removals.isEmpty()) {
			throw new RuntimeException("Missing Table Information");
		}
		for (Column c : removals) {
			bubbleUpColumn(c);
		}
	}

	public void bubbleUpColumn(Column c) {
		if (!parent.isGrouped() || parent.getGroupByClause().getItems().contains(c)) {
			this.queryables.remove(c);
			this.parent.addQueryable(c);
		}
	}

	public boolean isGrouped() {
		return groupByClause != null;
	}

	public boolean isKleene() {
		return kleene;
	}

	public OrderByClause getOrderByClause() {
		return orderByClause;
	}

	public List<QueryTree> getChildren() {
		return childs;
	}

	public WhereClause getWhereClause() {
		return whereClause;
	}

	public GroupByClause getGroupByClause() {
		return groupByClause;
	}

	public List<Queryable> getQueryables() {
		return queryables;
	}

	public boolean isGroupByProvider() {
		return groupByProvider;
	}

	public void setGroupByProvider(boolean groupByProvider) {
		this.groupByProvider = groupByProvider;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int depth) {
		this.maxDepth = depth;
	}

	public int getDepth() {
		return idList.size();
	}

	public List<Column> getKeys() {
		return keys;
	}

	public boolean isRoot() {
		return parent == null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryTree [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		QueryTree other = (QueryTree) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
