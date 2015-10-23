package de.upb.s2cx.query.referencetree;

public class StringNode extends ReferenceTreeNode {

	public StringNode(String label) {
		super(label);

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StringNode [label=");
		builder.append(getLabel());
		builder.append("]");
		return builder.toString();
	}

}
