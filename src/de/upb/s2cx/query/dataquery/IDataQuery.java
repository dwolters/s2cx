package de.upb.s2cx.query.dataquery;

import java.sql.Connection;
import java.sql.SQLException;

import de.upb.s2cx.query.resultset.IDataQueryResultSet;

public interface IDataQuery {

	public enum QueryBuilderType {

		CTE, LEFTJOIN, UNION;

		public static QueryBuilderType getByName(String name) {
			for (QueryBuilderType c : values()) {
				if (name.toLowerCase().equals(c.toString().toLowerCase())) {
					return c;
				}
			}
			throw new RuntimeException("Name not found.");
		}
	}

	String getSQLQuery();

	IDataQueryResultSet execute(Connection db) throws SQLException;
}
