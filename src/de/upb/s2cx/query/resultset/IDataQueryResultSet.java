package de.upb.s2cx.query.resultset;

import java.sql.SQLException;

import de.upb.s2cx.parser.sql.Queryable;
import de.upb.s2cx.query.QueryTree;

public interface IDataQueryResultSet {

	boolean hasData(QueryTree q) throws SQLException;

	void next(QueryTree q) throws SQLException;

	String getValue(QueryTree q, Queryable c) throws SQLException;

	void cache(QueryTree q) throws SQLException;

	void clearCache(QueryTree q);

}
