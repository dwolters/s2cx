package de.upb.s2cx.parser.sql;

import java.util.HashMap;
import java.util.Map;

public final class TableFactory {

	private static Map<String, Table> aliasTableMap = new HashMap<String, Table>();

	private TableFactory() {

	}

	public static Table createTable(String name, String alias) {
		if (aliasTableMap.containsKey(alias)) {
			Table t = aliasTableMap.get(alias);
			if (t.getName().isEmpty()) {
				t.setName(name);
			} else if (!t.getName().equals(name)) {
				throw new RuntimeException("Two different tables have the same alias.");
			}
			return t;
		} else {
			Table t = new Table(name, alias);
			aliasTableMap.put(alias, t);
			return t;
		}
	}

	public static Table getTableByAlias(String alias) {
		if (aliasTableMap.containsKey(alias)) {
			return aliasTableMap.get(alias);
		} else {
			return createTable("", alias);
		}
	}

	public static void reset() {
		aliasTableMap.clear();
	}

}
