package de.upb.s2cx.parser.sql;

import de.upb.s2cx.parser.sql.xml.XmlValue;

public class ParanthesizedValue implements XmlValue {
	private XmlValue xmlValue;

	public ParanthesizedValue(XmlValue xmlValue) {
		super();
		this.xmlValue = xmlValue;
	}

	@Override
	public String getValue() {
		return null;
	}

	public XmlValue getXmlValue() {
		return xmlValue;
	}

	public void setXmlValue(XmlValue xmlValue) {
		this.xmlValue = xmlValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(builder);
		return builder.toString();
	}

	@Override
	public void toString(StringBuilder sb) {
		sb.append("(");
		xmlValue.toString(sb);
		sb.append(")");

	}

}
