package de.upb.s2cx.parser.sql;

public interface Queryable {
	String getName();

	String getAlias();

	void setAlias(String alias);
}
