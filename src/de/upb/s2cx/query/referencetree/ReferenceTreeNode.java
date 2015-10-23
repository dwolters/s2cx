package de.upb.s2cx.query.referencetree;

public abstract class ReferenceTreeNode {

	private ReferenceTreeNode firstChild = null;

	private ReferenceTreeNode nextSibling = null;

	private String label;

	private int grammarId;

	public ReferenceTreeNode(String label) {
		super();
		this.label = label;
	}

	public ReferenceTreeNode getFirstChild() {
		return firstChild;
	}

	public void setFirstChild(ReferenceTreeNode firstChild) {
		this.firstChild = firstChild;
	}

	public ReferenceTreeNode getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(ReferenceTreeNode nextSibling) {
		this.nextSibling = nextSibling;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getGrammarId() {
		return grammarId;
	}

	public void setGrammarId(int grammarId) {
		this.grammarId = grammarId;
	}

	public boolean isKleene() {
		return "*".equals(label);
	}

	public boolean isOptional() {
		return "?".equals(label);
	}

	public String printTree() {
		StringBuilder sb = new StringBuilder();
		printTree(sb, "");
		return sb.toString();
	}

	private void printTree(StringBuilder sb, String indent) {
		sb.append(indent);
		sb.append(toString());
		sb.append("\n");
		if (firstChild != null) {
			firstChild.printTree(sb, indent + "\t");
		}
		if (nextSibling != null) {
			nextSibling.printTree(sb, indent);
		}
	}

	@Override
	public abstract String toString();
}
