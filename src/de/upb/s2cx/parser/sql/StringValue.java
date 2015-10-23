package de.upb.s2cx.parser.sql;

import de.upb.s2cx.parser.sql.xml.XmlValue;

public class StringValue implements XmlValue {
	private String value;

	public StringValue(String value) {
		super();
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public void toString(StringBuilder sb) {
		sb.append(toString());

	}

}
