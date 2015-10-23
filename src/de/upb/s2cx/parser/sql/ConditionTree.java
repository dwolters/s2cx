package de.upb.s2cx.parser.sql;

public class ConditionTree<L extends Condition, R extends Condition> implements Condition {

	private String operator;

	private L left;

	private R right;

	private boolean negated;

	private boolean parenthesized;

	public ConditionTree() {

	}

	public ConditionTree(String operator, L left, R right) {
		super();
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public L getLeft() {
		return left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}

	public boolean isNegated() {
		return negated;
	}

	public void setNegated(boolean negated) {
		this.negated = negated;
	}

	public boolean isParenthesized() {
		return parenthesized;
	}

	public void setParenthesized(boolean parenthesized) {
		this.parenthesized = parenthesized;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb);
		return sb.toString();
	}

	@Override
	public void toString(StringBuilder sb) {
		if (negated) {
			sb.append(" NOT ");
		}
		sb.append("(");
		left.toString(sb);
		if ("AND".equals(operator) || "OR".equals(operator)) {
			sb.append("\n\t");
		} else {
			sb.append(" ");
		}
		sb.append(operator);
		sb.append(" ");
		right.toString(sb);
		sb.append(")");
	}
}
