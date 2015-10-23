package de.upb.s2cx.parser.sql.xml;

import de.upb.s2cx.parser.sql.Column;

public class XmlColumnAttribute extends AbstractXmlAttribute {

	private Column column;

	public XmlColumnAttribute(String name, Column column) {
		super(name);
		this.column = column;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	@Override
	public String getValue() {
		return column.getName();
	}

}
