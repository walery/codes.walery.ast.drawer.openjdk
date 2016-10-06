package codes.walery.ast.openjdk.drawer.visjs.model;

import lombok.Data;

// TODO ignore null when convert to json
@Data
public class VisjsEdge<T> {
	private T from;
	private T to;
	private String label;
}
