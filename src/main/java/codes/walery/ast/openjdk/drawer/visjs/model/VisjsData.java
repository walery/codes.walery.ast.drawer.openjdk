package codes.walery.ast.openjdk.drawer.visjs.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class VisjsData<T> {
	private List<VisjsNode<T>> nodes = new ArrayList<>();
	private List<VisjsEdge<T>> edges = new ArrayList<>();
	private String src;
}
