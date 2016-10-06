package codes.walery.ast.openjdk.drawer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AbstractSourceTree extends Node {
	private String src;

	public AbstractSourceTree(final Node node) {
		setId(node.getId());
		setTypeName(node.getTypeName());
		setStart(node.getStart());
		setEnd(node.getEnd());
		setProps(node.getProps());
		setChildren(node.getChildren());
	}

}
