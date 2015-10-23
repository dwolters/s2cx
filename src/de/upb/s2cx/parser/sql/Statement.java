package de.upb.s2cx.parser.sql;

import de.upb.s2cx.parser.sql.xml.XmlValue;

public class Statement extends AbstractClause<Value>implements XmlValue {

	private OrderByClause orderByClause;

	private GroupByClause groupByClause;

	private FromClause fromClause;

	private WhereClause whereClause;

	private byte xmlAggDepth = 0;

	public Statement() {
		super("SELECT");
	}

	public XmlValue getXmlValue() {
		if (items.get(0) instanceof XmlValue) {
			return (XmlValue) items.get(0);
		} else {
			throw new RuntimeException("First value is not an XML value");
		}
	}

	public OrderByClause getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(OrderByClause orderByClause) {
		this.orderByClause = orderByClause;
	}

	public GroupByClause getGroupByClause() {
		return groupByClause;
	}

	public void setGroupByClause(GroupByClause groupByClause) {
		this.groupByClause = groupByClause;
	}

	public FromClause getFromClause() {
		return fromClause;
	}

	public void setFromClause(FromClause fromClause) {
		this.fromClause = fromClause;
	}

	public WhereClause getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(WhereClause whereClause) {
		this.whereClause = whereClause;
	}

	public byte getXmlAggDepth() {
		return xmlAggDepth;
	}

	public void setXmlAggDepth(byte xmlAggDepth) {
		this.xmlAggDepth = xmlAggDepth;
	}

	@Override
	public String getValue() {
		return ""; // TODO implement
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		toString(str);
		return str.toString();
	}

	@Override
	public void toString(StringBuilder str) {
		super.toString(str);
		str.append("\n");
		fromClause.toString(str);
		if (whereClause != null) {
			str.append("\n");
			whereClause.toString(str);
		}
		if (groupByClause != null) {
			str.append("\n");
			groupByClause.toString(str);
		}
		if (orderByClause != null) {
			str.append("\n");
			orderByClause.toString(str);
		}
	}

}
