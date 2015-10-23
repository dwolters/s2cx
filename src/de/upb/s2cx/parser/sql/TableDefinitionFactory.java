package de.upb.s2cx.parser.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TableDefinitionFactory {

	private static Connection connection;

	private static Map<String, TableDefinition> defs = new HashMap<String, TableDefinition>();

	public static void setDatabaseConnection(Connection connection) {
		TableDefinitionFactory.connection = connection;
		defs.clear();
	}

	public static TableDefinition createTableDefinition(Table t) throws SQLException {
		String name = t.getName();
		name = name.substring(1, name.length() - 1);
		if (defs.containsKey(name)) {
			return defs.get(name);
		}
		if (connection == null) {
			throw new SQLException("No Database connection set.");
		}
		if (connection.isClosed()) {
			throw new SQLException("Database connection is closed.");
		}
		TableDefinition td = new TableDefinition();
		ResultSet rsColumns = null;
		DatabaseMetaData meta = connection.getMetaData();
		rsColumns = meta.getColumns(null, null, name, null);
		while (rsColumns.next()) {
			String colName = rsColumns.getString("COLUMN_NAME");
			td.addType(colName, rsColumns.getString("TYPE_NAME"));
			int nullable = rsColumns.getInt("NULLABLE");
			if (DatabaseMetaData.columnNullableUnknown == nullable) {
				throw new SQLException("Could not determine nullable status of " + colName);
			} else if (DatabaseMetaData.columnNullable == nullable) {
				td.addNullableColumn(colName);
			}
		}
		rsColumns = meta.getPrimaryKeys(null, null, name);
		while (rsColumns.next()) {
			td.addKey(rsColumns.getString("COLUMN_NAME"));
		}
		defs.put(name, td);
		return td;
	}
}
