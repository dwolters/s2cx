package de.upb.s2cx.parser.sql.xml;

public class XmlConcatFunction extends AbstractXmlFunction<XmlValue> {

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
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
		builder.append("XMLCONCAT(");
		super.toString(builder);
		builder.append(")");
	}

}
