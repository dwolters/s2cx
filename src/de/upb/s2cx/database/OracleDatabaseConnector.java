package de.upb.s2cx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDatabaseConnector implements IDatabaseConnector {

	@Override
	public Connection createDatabaseConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public Connection createDatabaseConnection(String host, String port, String database, String user, String password)
			throws SQLException, ClassNotFoundException {
		String url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + database;
		return createDatabaseConnection(url, user, password);
	}
}
