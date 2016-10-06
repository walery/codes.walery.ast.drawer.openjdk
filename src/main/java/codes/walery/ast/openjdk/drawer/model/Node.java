package codes.walery.ast.openjdk.drawer.model;

import java.util.Map;

import lombok.Data;

@Data
public class Node {
	private int id;
	private String typeName;
	private int start;
	private int end;
	private Map<String, ?> props;
	private Map<String, Node> children;
}
