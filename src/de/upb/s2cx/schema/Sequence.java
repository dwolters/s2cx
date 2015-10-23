package de.upb.s2cx.schema;

public class Sequence extends ComplexElement {

	private boolean unbounded = false;

	private boolean optional = false;

	public Sequence(boolean unbounded) {
		super();
		this.setUnbounded(unbounded);
		optional = false;
	}

	public void setUnbounded(boolean unbounded) {
		this.unbounded = unbounded;
		optional = false;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
		unbounded = false;
	}

	public boolean isUnbounded() {
		return unbounded;
	}

	public boolean containsSingleElement() {
		return children.size() == 1 && !unbounded && !optional;
	}

	@Override
	public void buildSchema(StringBuilder sb) {
		if (containsSingleElement()) {
			children.get(0).buildSchema(sb);
		} else {
			sb.append("<xsd:sequence ");
			if (unbounded) {
				sb.append("minOccurs=\"0\" maxOccurs=\"unbounded\"");
			} else if (optional) {
				sb.append("minOccurs=\"0\" maxOccurs=\"1\"");
			}
			sb.append(">");
			for (ComplexElement compEl : children) {
				if (compEl != null) {
					compEl.buildSchema(sb);
				}
			}
			sb.append("</xsd:sequence>");
		}
	}
}
