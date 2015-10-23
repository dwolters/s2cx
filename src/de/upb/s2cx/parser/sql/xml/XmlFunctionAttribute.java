package de.upb.s2cx.parser.sql.xml;

import de.upb.s2cx.parser.sql.SqlFunction;

public class XmlFunctionAttribute extends AbstractXmlAttribute {

	private SqlFunction value;

	@Override
	public String getValue() {
		return value.getValue();
	}

	public SqlFunction getFunction() {
		return value;
	}

	public void setValue(SqlFunction value) {
		this.value = value;
	}

	public XmlFunctionAttribute(String name, SqlFunction value) {
		super(name);
		this.value = value;
	}

}
