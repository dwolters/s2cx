package de.upb.s2cx.parser.sql;

public final class ColumnFactory {

	public static SortColumn createSortColumn(Column col, boolean ascendant) {
		return new SortColumn(col, ascendant);
	}

	public static Column createColumn(String name, Table table) {
		Column c = new Column(name, "", table);
		return c;
	}

}
