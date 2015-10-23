package de.upb.s2cx.parser.sql;

import java.util.ArrayList;
import java.util.List;

import de.upb.s2cx.parser.sql.xml.XmlValue;

public class ArrayValue implements XmlValue {

	private List<String> entries;

	public ArrayValue() {
		entries = new ArrayList<String>();
	}

	@Override
	public String getValue() {
		return toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(builder);
		return builder.toString();
	}

	public boolean add(String e) {
		return entries.add(e);
	}

	@Override
	public void toString(StringBuilder builder) {
		builder.append("(");
		boolean first = true;
		for (String s : entries) {
			if (first) {
				first = false;
			} else {
				builder.append(",");
			}
			builder.append(s);
		}
		builder.append(")");
	}
}
