package codes.walery.ast.openjdk.drawer.visjs.model;

import java.util.Map;

import lombok.Data;

// TODO ignore null when convert to json
// TODO rename?
@Data
public class NodeProps {
	private int start;
	private int end;
	private Map<String, ?> otherProps;
}
