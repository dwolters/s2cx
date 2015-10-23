package de.upb.s2cx.parser.sql;

import de.upb.s2cx.parser.sql.xml.XmlValue;

public class Column implements XmlValue, Queryable {

	private String name;

	private String alias;

	private Table table;

	private Column redirect;

	private String type;

	private Boolean nullable;

	public Column(String name) {
		this(name, "", null);
	}

	public Column(String name, String alias) {
		this(name, alias, null);
	}

	public Column(Column col) {
		if (col != null) {
			this.name = col.name;
			this.alias = col.alias;
			this.table = col.table;
		}
	}

	public Column(String name, String alias, Table table) {
		this.name = name;
		this.alias = alias;
		this.table = table;
	}

	@Override
	public String getName() {
		if (table == null) {
			return name;
		} else {
			return table.getAlias() + "." + name;
		}
	}

	public void setName(String name) {
		if (redirect == null) {
			this.name = name;
		} else {
			redirect.setName(name);
		}
	}

	@Override
	public String getAlias() {
		if (redirect == null) {
			return alias;
		} else {
			return redirect.alias;
		}
	}

	@Override
	public void setAlias(String alias) {
		if (redirect == null) {
			this.alias = alias;
		} else {
			redirect.setAlias(alias);
		}
	}

	public Table getTable() {
		if (redirect == null) {
			return table;
		} else {
			return redirect.table;
		}
	}

	public void setTable(Table table) {
		if (redirect == null) {
			this.table = table;
		} else {
			redirect.setTable(table);
		}
	}

	public String getColumnName() {
		if (redirect == null) {
			return name;
		} else {
			return redirect.name;
		}
	}

	public void setRedirect(Column redirect) {
		if (this != redirect) {
			this.redirect = redirect;
		}
	}

	public String getType() {
		if (type == null) {
			type = getTable().getType(this);
		}
		return type;
	}

	public boolean isNullable() {
		if (nullable == null) {
			nullable = getTable().isNullable(this);
		}
		return nullable;
	}

	@Override
	public String toString() {
		if (redirect == null) {
			return getName();
		} else {
			return redirect.getName();
		}
	}

	@Override
	public String getValue() {
		if (redirect == null) {
			return getName();
		} else {
			return redirect.getName();
		}
	}

	@Override
	public int hashCode() {
		if (redirect != null) {
			return redirect.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (redirect != null) {
			return redirect.equals(obj);
		}
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Column other = (Column) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (table == null) {
			if (other.table != null) {
				return false;
			}
		} else if (!table.equals(other.table)) {
			return false;
		}
		return true;
	}

	@Override
	public void toString(StringBuilder sb) {
		sb.append(toString());
	}
}
