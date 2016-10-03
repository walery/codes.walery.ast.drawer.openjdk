package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;
import java.util.List;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCNewArray;

public class JCNewArrayDotDrawer extends DotDrawer<JCNewArray> {

	public JCNewArrayDotDrawer(final JCNewArray node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected void drawChildren(final EndPosTable ept) {
		super.drawChildren(ept);

		for (int i = 0; i < node.dimAnnotations.length(); i++) {
			List<JCAnnotation> c = node.dimAnnotations.get(i);
			drawChildren(c, "dimAnnotations[" + i + "]", ept);
		}
	}
}
