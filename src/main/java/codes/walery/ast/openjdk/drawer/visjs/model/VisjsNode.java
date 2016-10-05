package codes.walery.ast.openjdk.drawer.visjs.model;

// TODO lombok
// TODO ignore null when convert to json
public class VisjsNode<T> {
	private T id;
	private String label;
	private NodeProps nodeProps;

	// TODO color

	public T getId() {
		return id;
	}

	public void setId(final T id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public NodeProps getNodeProps() {
		return nodeProps;
	}

	public void setNodeProps(final NodeProps nodeProps) {
		this.nodeProps = nodeProps;
	}
}
