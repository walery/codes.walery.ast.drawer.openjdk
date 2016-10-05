package codes.walery.ast.openjdk.drawer.custom;

import java.util.List;
import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;
import codes.walery.ast.openjdk.drawer.model.Node;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCNewArray;

public class JCNewArrayDotDrawer extends DotDrawer<JCNewArray> {

	public JCNewArrayDotDrawer(final JCNewArray node) {
		super(node);
	}

	@Override
	protected Map<String, Node> drawChildren(final EndPosTable ept) {
		Map<String, Node> children = super.drawChildren(ept);

		for (int i = 0; i < node.dimAnnotations.length(); i++) {
			List<JCAnnotation> c = node.dimAnnotations.get(i);

			Map<String, Node> dimChildren = drawChildren(c, "dimAnnotations[" + i + "]", ept);

			children.putAll(dimChildren);
		}

		return children;
	}
}
