package de.upb.s2cx.parser.sql;

import de.upb.s2cx.parser.sql.xml.XmlValue;

public class Term implements XmlValue, Queryable {

	private boolean negated;

	private XmlValue left;

	private XmlValue right;

	private String operator;

	private String alias;

	@Override
	public String getValue() {
		return null;
	}

	public boolean isNegated() {
		return negated;
	}

	public void setNegated(boolean negated) {
		this.negated = negated;
	}

	public XmlValue getLeft() {
		return left;
	}

	public void setLeft(XmlValue left) {
		this.left = left;
	}

	public XmlValue getRight() {
		return right;
	}

	public void setRight(XmlValue right) {
		this.right = right;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(builder);
		return builder.toString();
	}

	@Override
	public String getName() {
		return toString();
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public void setAlias(String alias) {
		this.alias = alias;

	}

	@Override
	public void toString(StringBuilder builder) {
		left.toString(builder);
		builder.append(" ");
		builder.append(operator);
		builder.append(" ");
		right.toString(builder);
	}

}
