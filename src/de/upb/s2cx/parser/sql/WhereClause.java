package de.upb.s2cx.parser.sql;

public class WhereClause implements StringAppendable {

	private Condition condition;

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (condition != null) {
			return "WHERE\n\t" + condition.toString();
		}
		return "";
	}

	@Override
	public void toString(StringBuilder sb) {
		if (condition != null) {
			sb.append("WHERE\n\t");
			condition.toString(sb);
		}
	}
}
