package de.upb.s2cx.parser.sql;

import de.upb.s2cx.parser.sql.xml.XmlValue;

public class SqlFunction implements XmlValue, Queryable {

	private String functionName;

	private XmlValue value;

	private String alias;

	public SqlFunction(String functionName, XmlValue value) {
		this.functionName = functionName;
		this.value = value;
	}

	@Override
	public String getValue() {
		return toString();
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

	public XmlValue getXmlValue() {
		return value;
	}

	@Override
	public void toString(StringBuilder builder) {
		builder.append(functionName);
		builder.append("(");
		value.toString(builder);
		builder.append(")");

	}
}
