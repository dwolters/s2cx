package de.upb.s2cx.parser.sql.xml;

public class XmlForestFunction extends AbstractXmlFunction<XmlElementWrapper> {

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
		builder.append("XMLFOREST(");
		super.toString(builder);
		builder.append(")");
	}

}
