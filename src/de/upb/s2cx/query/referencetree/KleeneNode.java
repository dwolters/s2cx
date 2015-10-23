package de.upb.s2cx.query.referencetree;

import de.upb.s2cx.query.QueryTree;

public class KleeneNode extends ReferenceTreeNode {

	private QueryTree query;

	public KleeneNode(QueryTree q) {
		super("*");
		query = q;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KleeneNode [query=");
		builder.append(query);
		builder.append("]");
		return builder.toString();
	}

	public QueryTree getQuery() {
		return query;
	}

}
