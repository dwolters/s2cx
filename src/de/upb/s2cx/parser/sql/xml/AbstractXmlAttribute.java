package de.upb.s2cx.parser.sql.xml;

public abstract class AbstractXmlAttribute implements XmlValue {

	private String name;

	public AbstractXmlAttribute(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(builder);
		return builder.toString();
	}

	@Override
	public void toString(StringBuilder builder) {
		builder.append(getValue());
		builder.append(" AS ");
		builder.append(name);
	}
}
