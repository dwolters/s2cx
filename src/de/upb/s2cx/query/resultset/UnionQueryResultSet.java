package de.upb.s2cx.query.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

import de.upb.s2cx.parser.sql.Column;
import de.upb.s2cx.parser.sql.Queryable;
import de.upb.s2cx.query.QueryTree;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;

public class UnionQueryResultSet implements IDataQueryResultSet {

	private ResultSet rs;

	private boolean ended;

	private String current;

	private Map<QueryTree, Cache> cache;

	private Set<QueryTree> ignoreNext;

	private Set<QueryTree> groupBy;

	private Statement statement;

	private class Cache extends THashMap<Queryable, String> {

		private QueryTree query;

		public Cache(QueryTree query) {
			super();
			this.query = query;
		}

		public void cache() throws SQLException {
			for (Queryable q : query.getQueryables()) {
				put(q, rs.getString(q.getAlias()));
			}
		}
	}

	public UnionQueryResultSet(Statement statement, ResultSet rs) throws SQLException {
		this.statement = statement;
		this.rs = rs;
		ended = !rs.next();
		cache = new THashMap<QueryTree, Cache>();
		ignoreNext = new THashSet<QueryTree>();
		groupBy = new THashSet<QueryTree>();
	}

	@Override
	public boolean hasData(QueryTree query) throws SQLException {
		if (ended) {
			return false;
		}
		current = rs.getString("QueryID");
		if (query.isGroupByProvider() && current.equals(query.getParent().getId())) {
			if (groupBy.contains(query)) {
				for (QueryTree q : query.getParent().getChildren()) {
					if (q.isGroupByProvider()) {
						groupBy.remove(q);
					}
				}
			} else {
				for (QueryTree q : query.getParent().getChildren()) {
					if (q.isGroupByProvider()) {
						groupBy.add(q);
					}
				}
				next(query);
				if (ended) {
					return false;
				}
				current = rs.getString("QueryID");
				ignoreNext.add(query.getParent());
			}
		}
		if (current.startsWith(query.getId())) {
			cache(query);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void next(QueryTree q) throws SQLException {
		if (ignoreNext.contains(q)) {
			ignoreNext.remove(q);
		} else {
			ended = !rs.next();
			if (ended) {
				rs.close();
				statement.close();
			}

		}
		clearCache(q);
	}

	@Override
	public String getValue(QueryTree q, Queryable c) throws SQLException {
		if (c instanceof Column) {
			return rs.getString(c.getAlias());
		} else {
			return cache.get(q).get(c);
		}
	}

	@Override
	public void cache(QueryTree q) throws SQLException {
		if (!ended) {
			Cache c = cache.get(q);
			if (c == null) {
				c = new Cache(q);
				cache.put(q, c);
			}
			c.cache();
		}
	}

	@Override
	public void clearCache(QueryTree q) {
		Cache c = cache.get(q);
		if (c != null) {
			c.clear();
		}
	}
}
