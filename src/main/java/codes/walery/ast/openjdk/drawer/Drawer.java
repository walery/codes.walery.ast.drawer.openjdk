package codes.walery.ast.openjdk.drawer;

import com.sun.tools.javac.tree.EndPosTable;

public interface Drawer {
	void draw();

	void draw(final EndPosTable ept);
}
