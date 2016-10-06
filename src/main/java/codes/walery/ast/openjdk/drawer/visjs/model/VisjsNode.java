package codes.walery.ast.openjdk.drawer.visjs.model;

import lombok.Data;

// TODO ignore null when convert to json
@Data
public class VisjsNode<T> {
	private T id;
	private String label;
	private NodeProps nodeProps;

	// TODO color
}
