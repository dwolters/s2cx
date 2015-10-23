package de.upb.s2cx.parser.sql;

import java.sql.SQLException;
import java.util.List;

public class Table implements StringAppendable {

	private String name;

	private String alias;

	private TableDefinition def;

	public Table(String name, String alias) {
		super();
		this.name = name;
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTableDecl() {
		return name + " " + alias;
	}

	@Override
	public String toString() {
		return getTableDecl();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Table other = (Table) obj;
		if (alias == null) {
			if (other.alias != null) {
				return false;
			}
		} else if (!alias.equals(other.alias)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public TableDefinition queryTableDefinition() throws SQLException {
		if (def == null) {
			def = TableDefinitionFactory.createTableDefinition(this);
		}
		return def;
	}

	public List<String> getKeys() {
		if (def == null) {
			throw new RuntimeException("No Table definition avaiable. You have to call queryTableDefinition before calling this function");
		}
		return def.getKeys();
	}

	public String getType(Column c) {
		if (def == null) {
			throw new RuntimeException("No Table definition avaiable. You have to call queryTableDefinition before calling this function");
		}
		return def.getType(c.getColumnName().substring(1, c.getColumnName().length() - 1));
	}

	public boolean isNullable(Column c) {
		if (def == null) {
			throw new RuntimeException("No Table " + toString() + " definition avaiable. You have to call queryTableDefinition before calling this function");
		}
		return def.isNullable(c.getColumnName().substring(1, c.getColumnName().length() - 1));
	}

	@Override
	public void toString(StringBuilder sb) {
		sb.append(toString());
	}
}
