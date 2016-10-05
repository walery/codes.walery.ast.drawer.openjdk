package codes.walery.ast.openjdk.drawer.visjs;

import codes.walery.ast.openjdk.drawer.model.Node;
import codes.walery.ast.openjdk.drawer.visjs.model.NodeProps;
import codes.walery.ast.openjdk.drawer.visjs.model.VisjsData;
import codes.walery.ast.openjdk.drawer.visjs.model.VisjsEdge;
import codes.walery.ast.openjdk.drawer.visjs.model.VisjsNode;

public class VisjsConverter {
	public static VisjsData<String> convert(final Node source, final VisjsData<String> target) {
		if (target == null) {
			throw new NullPointerException("target must not be null.");
		}

		if (source == null) {
			return null;
		}

		NodeProps props = new NodeProps();
		props.setStart(source.getStart());
		props.setEnd(source.getEnd());
		props.setOtherProps(source.getProps());

		VisjsNode<String> root = new VisjsNode<>();
		root.setId(source.getId() + "");
		root.setLabel(source.getTypeName());
		root.setNodeProps(props);

		target.getNodes().add(root);

		source.getChildren().forEach((k, v) -> {
			if (v == null) {
				return;
			}
			VisjsEdge<String> edge = new VisjsEdge<>();
			edge.setFrom(root.getId());
			edge.setTo("" + v.getId());
			edge.setLabel(k);

			target.getEdges().add(edge);

			convert(v, target);
		});

		return target;
	}
}
