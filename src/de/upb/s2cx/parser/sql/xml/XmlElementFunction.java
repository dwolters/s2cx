package de.upb.s2cx.parser.sql.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlElementFunction extends AbstractXmlFunction<XmlValue> {

	private List<AbstractXmlAttribute> attributes;

	private String name = null;

	public XmlElementFunction() {
		attributes = new ArrayList<AbstractXmlAttribute>();
	}

	public boolean addAttribute(AbstractXmlAttribute e) {
		return attributes.add(e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractXmlAttribute> getAttributes() {
		return attributes;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		return builder.toString();
	}

	@Override
	public void toString(StringBuilder builder) {
		builder.append("XMLELEMENT( NAME ");
		builder.append(name);
		if (!attributes.isEmpty()) {
			builder.append(",\n\tXMLATTRIBUTES(");
			for (int i = 0; i < attributes.size(); i++) {
				if (i != 0) {
					builder.append(",\n\t");
				}
				attributes.get(i).toString(builder);
			}
			builder.append(")");
		}
		if (!parameters.isEmpty()) {
			builder.append(",\n\t");
			super.toString(builder);
		}
		builder.append(")");
	}

}
