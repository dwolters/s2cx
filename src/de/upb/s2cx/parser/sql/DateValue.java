package de.upb.s2cx.parser.sql;

import de.upb.s2cx.parser.sql.xml.XmlValue;

public class DateValue implements XmlValue {

	private String date;

	public DateValue(String date) {
		super();
		this.date = date;
	}

	@Override
	public String getValue() {
		return "DATE " + date;
	}

	@Override
	public String toString() {
		return getValue();
	}

	@Override
	public void toString(StringBuilder sb) {
		sb.append(toString());

	}

}
