package codes.walery.ast.openjdk.drawer;

import codes.walery.ast.openjdk.drawer.model.Node;

import com.sun.tools.javac.tree.EndPosTable;

public interface Drawer {
	Node draw();

	Node draw(final EndPosTable ept);
}
