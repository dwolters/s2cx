package de.upb.s2cx.schema;

import java.util.List;
import java.util.Set;

public class Element extends ComplexElement {

	private String name;

	private List<String> attributes;

	private Set<String> requiredAttributes;

	public Element(String name, List<String> attributes, Set<String> requiredAttributes, List<ComplexElement> children) {
		super();
		this.attributes = attributes;
		this.requiredAttributes = requiredAttributes;
		this.name = name;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public List<ComplexElement> getChildren() {
		return children;
	}

	@Override
	public Type analyseType() {
		Type type = super.analyseType();
		if (attributes != null && !attributes.isEmpty()) {
			if (type == Type.STRING) {
				return Type.COMPLEX_STRING;
			} else if (type == Type.EMPTY) {
				return Type.COMPLEX;
			}
		}
		return type;
	}

	@Override
	public void buildSchema(StringBuilder sb) {
		if (getType() == Type.EMPTY) {
			sb.append("<xsd:element name=\"");
			sb.append(name);
			sb.append("\"><xsd:complexType /></xsd:element>");
		}
		if (getType() == Type.STRING) {
			sb.append("<xsd:element name=\"");
			sb.append(name);
			sb.append("\" type=\"xsd:string\" />");
		}
		if (getType() == Type.COMPLEX || getType() == Type.COMPLEX_MIXED || getType() == Type.COMPLEX_STRING) {
			sb.append("<xsd:element name=\"");
			sb.append(name);
			sb.append("\">");
			if (getType() == Type.COMPLEX_STRING) {
				sb.append("<xsd:complexType><xsd:extension base=\"xsd:string\"><xsd:simpleContent>");
				addAttributes(sb);
				sb.append("</xsd:simpleContent></xsd:extension>");
			} else {
				if (getType() == Type.COMPLEX_MIXED) {
					sb.append("<xsd:complexType mixed=\"true\">");
				} else {
					sb.append("<xsd:complexType>");
				}
				if (children != null && !children.isEmpty()) {
					boolean addSequence = false;
					int sequenceCount = 0;
					for (int i = 0; i < children.size() && !addSequence; i++) {
						if (children.get(i) instanceof Element) {
							addSequence = true;
						} else if (children.get(i) instanceof Sequence) {
							if (((Sequence) children.get(i)).containsSingleElement()) {
								addSequence = true;
							} else {
								sequenceCount++;
							}
						}
					}
					addSequence = addSequence || sequenceCount > 1;
					if (addSequence) {
						sb.append("<xsd:sequence>");
					}
					for (ComplexElement compEl : children) {
						if (!(compEl instanceof TextElement)) {
							compEl.buildSchema(sb);
						}
					}
					if (addSequence) {
						sb.append("</xsd:sequence>");
					}
				}
				addAttributes(sb);
			}
			sb.append("</xsd:complexType></xsd:element>");
		}
	}

	private void addAttributes(StringBuilder sb) {
		if (attributes != null && !attributes.isEmpty()) {
			for (String attr : attributes) {
				sb.append("<xsd:attribute name=\"");
				sb.append(attr);
				sb.append("\" type=\"xsd:string\" use=\"");
				if (requiredAttributes.contains(attr)) {
					sb.append("required");
				} else {
					sb.append("optional");
				}
				sb.append("\" />");
			}
		}
	}
}
