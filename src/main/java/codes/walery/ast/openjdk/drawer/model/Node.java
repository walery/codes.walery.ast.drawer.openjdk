package codes.walery.ast.openjdk.drawer.model;

import java.util.Map;

// TODO lombok
public class Node {
	private int id;
	private String typeName;
	private int start;
	private int end;
	private Map<String, ?> props;
	private Map<String, Node> children;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	public int getStart() {
		return start;
	}

	public void setStart(final int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(final int end) {
		this.end = end;
	}

	public Map<String, ?> getProps() {
		return props;
	}

	public void setProps(final Map<String, ?> props) {
		this.props = props;
	}

	public Map<String, Node> getChildren() {
		return children;
	}

	public void setChildren(final Map<String, Node> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Node [typeName=" + typeName + ", start=" + start + ", end=" + end + ", props=" + props + ", children=" + children + "]";
	}

}
