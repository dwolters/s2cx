package de.upb.s2cx.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseConnector {

	Connection createDatabaseConnection(String host, String port, String database, String user, String password) throws SQLException, ClassNotFoundException;

	Connection createDatabaseConnection(String url, String user, String password) throws SQLException, ClassNotFoundException;
}
