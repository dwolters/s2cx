package de.upb.s2cx.query.referencetree;

public class ElementNode extends ReferenceTreeNode {

	private boolean mixed;

	public ElementNode(String label) {
		super(label);
	}

	public boolean isMixed() {
		return mixed;
	}

	public void setMixed(boolean mixed) {
		this.mixed = mixed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ElementNode [label=");
		builder.append(getLabel());
		builder.append(", mixed=");
		builder.append(mixed);
		builder.append("]");
		return builder.toString();
	}

}
