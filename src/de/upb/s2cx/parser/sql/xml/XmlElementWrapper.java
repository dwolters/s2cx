package de.upb.s2cx.parser.sql.xml;

public class XmlElementWrapper implements XmlValue {

	private XmlValue value;

	private String name;

	public XmlElementWrapper(XmlValue value, String name) {
		super();
		this.value = value;
		this.name = name;
	}

	public XmlValue getXmlValue() {
		return value;
	}

	public void setValue(XmlValue value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		value.toString(builder);
		builder.append(" AS ");
		builder.append(name);
	}
}
