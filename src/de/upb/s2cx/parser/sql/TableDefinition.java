package de.upb.s2cx.parser.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gnu.trove.set.hash.THashSet;

public class TableDefinition {

	private List<String> keys;

	private Map<String, String> types;

	private Set<String> nullableColumns;

	public TableDefinition() {
		this.keys = new ArrayList<String>();
		this.types = new HashMap<String, String>();
		nullableColumns = new THashSet<String>();

	}

	public List<String> getKeys() {
		return keys;
	}

	public String getType(String colName) {
		return types.get(colName);
	}

	public boolean isNullable(String colName) {
		return nullableColumns.contains(colName);
	}

	public void addNullableColumn(String column) {
		nullableColumns.add(column);
	}

	public void addType(String colName, String type) {
		types.put(colName, type);
	}

	public void addKey(String key) {
		keys.add(key);
	}

}
