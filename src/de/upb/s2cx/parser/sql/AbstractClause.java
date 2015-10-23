package de.upb.s2cx.parser.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractClause<T extends StringAppendable> implements StringAppendable {

	protected List<T> items;

	protected final String name;

	public AbstractClause(String name) {
		items = new ArrayList<T>();
		this.name = name;
	}

	public boolean add(T e) {
		return items.add(e);
	}

	public boolean remove(T o) {
		return items.remove(o);
	}

	public List<T> getItems() {
		return items;
	}

	public boolean addAll(Collection<? extends T> c) {
		return items.addAll(c);
	}

	@Override
	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		toString(strBuild);
		return strBuild.toString();
	}

	@Override
	public void toString(StringBuilder strBuild) {
		strBuild.append(" ");
		strBuild.append(name);
		strBuild.append("\n\t");
		for (int i = 0; i < items.size(); i++) {
			if (i != 0) {
				strBuild.append(",\n\t");
			}
			items.get(i).toString(strBuild);
		}
	}
}
