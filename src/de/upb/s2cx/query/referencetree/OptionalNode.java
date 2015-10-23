package de.upb.s2cx.query.referencetree;

import de.upb.s2cx.query.QueryTree;

public class OptionalNode extends ReferenceTreeNode {

	private QueryTree query;

	public OptionalNode(QueryTree query) {
		super("?");
		this.query = query;

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OptionalNode [query=");
		builder.append(query);
		builder.append("]");
		return builder.toString();
	}

	public QueryTree getQuery() {
		return query;
	}

}
