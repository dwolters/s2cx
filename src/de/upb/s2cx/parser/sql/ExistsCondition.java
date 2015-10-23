package de.upb.s2cx.parser.sql;

public class ExistsCondition implements Condition {
	private Statement s;

	public ExistsCondition(Statement s) {
		super();
		this.s = s;
	}

	public Statement getS() {
		return s;
	}

	public void setS(Statement s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return "EXISTS (" + s.toString() + ")";
	}

	@Override
	public void toString(StringBuilder sb) {
		sb.append("EXISTS (");
		s.toString(sb);
		sb.append(")");
	}

}
