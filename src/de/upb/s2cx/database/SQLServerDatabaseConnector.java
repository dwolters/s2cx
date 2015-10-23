package de.upb.s2cx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerDatabaseConnector implements IDatabaseConnector {

	@Override
	public Connection createDatabaseConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public Connection createDatabaseConnection(String host, String port, String database, String user, String password)
			throws SQLException, ClassNotFoundException {
		String url = "jdbc:jtds:sqlserver://" + host + ":" + port + ";database=" + database;
		return createDatabaseConnection(url, user, password);
	}
}
