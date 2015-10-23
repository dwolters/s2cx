package de.upb.s2cx.parser.sql.xml;

import de.upb.s2cx.parser.sql.OrderByClause;

public class XmlAggFunction implements XmlValue {

	private OrderByClause orderByClause;

	private XmlValue parameter;

	public OrderByClause getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(OrderByClause orderByClause) {
		this.orderByClause = orderByClause;
	}

	public XmlValue getParameter() {
		return parameter;
	}

	public void setParameter(XmlValue parameter) {
		this.parameter = parameter;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(builder);
		return builder.toString();
	}

	@Override
	public void toString(StringBuilder builder) {
		builder.append("XMLAGG(");
		parameter.toString(builder);
		if (orderByClause != null) {
			builder.append("\n\t");
			orderByClause.toString(builder);
		}
		builder.append(")");
	}
}
