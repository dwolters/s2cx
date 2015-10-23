package de.upb.s2cx.parser.sql;

public class SortColumn implements StringAppendable {

	private boolean ascendant;

	private Column column;

	public SortColumn(Column col, boolean ascendant) {
		column = col;
		this.ascendant = ascendant;
	}

	public boolean isAscendant() {
		return ascendant;
	}

	public void setAscendant(boolean order) {
		this.ascendant = order;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb);
		return sb.toString();
	}

	public Column getColumn() {
		return column;
	}

	@Override
	public void toString(StringBuilder sb) {
		String alias = column.getAlias();
		if (alias != null && !alias.isEmpty()) {
			sb.append("\"");
			sb.append(alias);
			sb.append("\" ");
		} else {
			sb.append(column.getName());
		}
		sb.append((ascendant ? "ASC" : "DESC"));

	}
}
