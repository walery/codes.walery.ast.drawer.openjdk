package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCAnnotation;

public class JCAnnotationDotDrawer extends DotDrawer<JCAnnotation> {

	public JCAnnotationDotDrawer(final JCAnnotation node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("kind", node.getKind()), //
				prop("tag", node.getKind()), //
				prop("attribute", node.attribute) //
		);
	}
}
