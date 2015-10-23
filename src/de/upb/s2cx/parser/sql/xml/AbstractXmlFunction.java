package de.upb.s2cx.parser.sql.xml;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractXmlFunction<T extends XmlValue> implements XmlValue {

	protected List<T> parameters;

	public AbstractXmlFunction() {
		parameters = new ArrayList<T>();
	}

	public boolean add(T e) {
		return parameters.add(e);
	}

	public List<T> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		toString(strBuild);
		return strBuild.toString();
	}

	@Override
	public void toString(StringBuilder strBuild) {
		for (int i = 0; i < parameters.size(); i++) {
			if (i != 0) {
				strBuild.append(",\n\t");
			}
			parameters.get(i).toString(strBuild);
		}
	}
}
