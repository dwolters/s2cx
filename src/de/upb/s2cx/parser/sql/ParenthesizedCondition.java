package de.upb.s2cx.parser.sql;

public class ParenthesizedCondition implements Condition {
	private Condition condition;

	public ParenthesizedCondition(Condition condition) {
		super();
		this.condition = condition;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(builder);
		return builder.toString();
	}

	@Override
	public void toString(StringBuilder builder) {
		builder.append("(");
		condition.toString(builder);
		builder.append(")");

	}

}
