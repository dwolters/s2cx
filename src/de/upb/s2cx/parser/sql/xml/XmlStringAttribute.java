package de.upb.s2cx.parser.sql.xml;

public class XmlStringAttribute extends AbstractXmlAttribute {

	private String value;

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public XmlStringAttribute(String name, String value) {
		super(name);
		this.value = value;
	}

}
