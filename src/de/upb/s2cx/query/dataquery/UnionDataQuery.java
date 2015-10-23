package de.upb.s2cx.query.dataquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.upb.s2cx.parser.sql.Column;
import de.upb.s2cx.parser.sql.Queryable;
import de.upb.s2cx.parser.sql.SortColumn;
import de.upb.s2cx.parser.sql.SqlFunction;
import de.upb.s2cx.parser.sql.Table;
import de.upb.s2cx.parser.sql.Term;
import de.upb.s2cx.query.QueryTree;
import de.upb.s2cx.query.resultset.IDataQueryResultSet;
import de.upb.s2cx.query.resultset.UnionQueryResultSet;
import gnu.trove.set.hash.THashSet;

public class UnionDataQuery implements IDataQuery {

	private StringBuilder sb;

	protected QueryTree root;

	private boolean first = true;

	private QueryTree current;

	private Set<Column> orderCols;

	public UnionDataQuery(QueryTree root) throws SQLException {
		this.root = root;
		orderCols = new THashSet<Column>();
	}

	@Override
	public String getSQLQuery() {
		sb = new StringBuilder();
		current = root;
		process();
		sb.append("\nORDER BY ");
		getOverallOrderByString(root);
		return sb.toString();
	}

	private void process() {
		QueryTree backup = current;
		processQueryTree(current);
		for (QueryTree q : current.getChildren()) {
			current = q;
			process();
		}
		current = backup;
	}

	private void processQueryTree(QueryTree q) {
		if (first) {
			first = false;
		} else {
			sb.append("\nUNION ALL \n");
		}
		sb.append("SELECT '");
		sb.append(q.getId());
		sb.append("' AS \"QueryID\"");
		getColumnString(root, q);
		sb.append("\nFROM ");
		getTableString(q);
		getWhereClause(q);
		getGroupByClause(q);
	}

	private void getGroupByClause(QueryTree q) {
		if (q.getGroupByClause() != null) {
			sb.append("\nGROUP BY ");
			boolean first = true;
			List<Column> cols = new ArrayList<Column>();
			if (!q.isRoot()) {
				addParentColumns(q.getParent(), cols);
			}
			cols.addAll(q.getGroupByClause().getItems());
			for (Column c : cols) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(c.toString());
			}
		}
	}

	private void addParentColumns(QueryTree q, List<Column> cols) {
		if (!q.isRoot()) {
			addParentColumns(q.getParent(), cols);
		}
		for (Queryable c : q.getQueryables()) {
			if (c instanceof Column) {
				cols.add((Column) c);
			}
		}
	}

	private void getOverallOrderByString(QueryTree q) {
		if (q.getOrderByClause() != null) {
			for (SortColumn s : q.getOrderByClause().getItems()) {
				if (!orderCols.contains(s.getColumn())) {
					sb.append(s.toString());
					sb.append(",");
					orderCols.add(s.getColumn());
				}
			}
		}
		if (q.isGrouped()) {
			sb.append("\"COL_");
			sb.append(q.getId());
			sb.append("-Sort\", ");
		}
		for (QueryTree c : q.getChildren()) {
			getOverallOrderByString(c);
		}

		if (q.getParent() == null) {
			sb.append("\"QueryID\"");
		}
	}

	private void getColumnString(QueryTree current, QueryTree q) {

		getColumnString(current, !q.isAncesterOrSelf(current), q != current);

		for (QueryTree c : current.getChildren()) {
			getColumnString(c, q);
		}
	}

	private void getColumnString(QueryTree q, boolean nullRows, boolean excludeFunctions) {
		for (Queryable c : q.getQueryables()) {
			sb.append(",\n");
			if (nullRows || excludeFunctions && (c instanceof Term || c instanceof SqlFunction)) {
				sb.append("null");
			} else {
				sb.append(c.getName());
			}
			sb.append("  AS \"");
			sb.append(c.getAlias());
			sb.append("\"");
		}
		if (q.isGrouped()) {
			if (excludeFunctions) {
				sb.append(", 1 AS \"COL_");
			} else {
				sb.append(", 0 AS \"COL_");
			}
			sb.append(q.getId());
			sb.append("-Sort\"");
		}
	}

	private boolean getWhereClause(QueryTree q) {
		boolean parentConditions = false;
		if (q.getParent() != null) {
			parentConditions = getWhereClause(q.getParent());
		}
		if (q.getWhereClause() != null) {
			if (parentConditions) {
				sb.append(" AND ");
			} else {
				sb.append("\nWHERE ");
			}
			sb.append("(");
			sb.append(q.getWhereClause().getCondition().toString());
			sb.append(")");
			return true;
		} else {
			return parentConditions;
		}
	}

	private void getTableString(QueryTree q) {
		boolean setComma = false;
		if (q.getParent() != null) {
			getTableString(q.getParent());
			setComma = true;
		}
		for (Table t : q.getTables().values()) {
			if (setComma) {
				sb.append(", ");
			} else {
				setComma = true;
			}
			sb.append(t.toString());
		}
	}

	@Override
	public IDataQueryResultSet execute(Connection connection) throws SQLException {
		String sql = getSQLQuery();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		return new UnionQueryResultSet(statement, rs);
	}
}
