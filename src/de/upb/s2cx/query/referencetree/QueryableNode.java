package de.upb.s2cx.query.referencetree;

import de.upb.s2cx.parser.sql.Queryable;

public class QueryableNode extends ReferenceTreeNode {

	private Queryable queryable;

	public QueryableNode(Queryable q) {
		super("");
		this.queryable = q;
	}

	@Override
	public String getLabel() {
		return queryable.getAlias();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryableNode [queryable=");
		builder.append(queryable);
		builder.append("]");
		return builder.toString();
	}

	public Queryable getQueryable() {
		return queryable;
	}

}
