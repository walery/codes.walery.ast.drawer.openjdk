package codes.walery.ast.openjdk.drawer.visjs.model;

import java.util.ArrayList;
import java.util.List;

// TODO lombok
public class VisjsData<T> {
	private List<VisjsNode<T>> nodes = new ArrayList<>();
	private List<VisjsEdge<T>> edges = new ArrayList<>();
	private String src;

	public List<VisjsNode<T>> getNodes() {
		return nodes;
	}

	public void setNodes(final List<VisjsNode<T>> nodes) {
		this.nodes = nodes;
	}

	public List<VisjsEdge<T>> getEdges() {
		return edges;
	}

	public void setEdges(final List<VisjsEdge<T>> edges) {
		this.edges = edges;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(final String src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return "VisjsData [nodes=" + nodes + ", edges=" + edges + ", src=" + src + "]";
	}
}
