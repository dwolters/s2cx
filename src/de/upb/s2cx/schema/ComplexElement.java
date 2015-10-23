package de.upb.s2cx.schema;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexElement {

	protected List<ComplexElement> children;

	private Type type;

	public Type getType() {
		if (type == null) {
			type = this.analyseType();
		}
		return type;
	}

	public ComplexElement() {
		children = new ArrayList<ComplexElement>();
	}

	public boolean addChild(ComplexElement e) {
		return children.add(e);
	}

	public Type analyseType() {
		if (children == null || children.isEmpty()) {
			return Type.EMPTY;
		}
		boolean foundTextElement = false;
		boolean allTextElements = true;
		for (ComplexElement e : children) {
			if (e instanceof TextElement) {
				foundTextElement = true;
			} else {
				allTextElements = false;
			}
		}
		if (foundTextElement && allTextElements) {
			return Type.STRING;
		}
		if (foundTextElement) {
			return Type.COMPLEX_MIXED;
		}
		return Type.COMPLEX;
	}

	public abstract void buildSchema(StringBuilder sb);
}
