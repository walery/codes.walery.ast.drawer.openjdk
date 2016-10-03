package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;

public class JCAnnotationDotDrawer extends DotDrawer<JCAnnotation> {

	public JCAnnotationDotDrawer(final JCAnnotation node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("kind", node.getKind()), //
				prop("tag", node.getKind()), //
				prop("attribute", node.attribute), //
				super.drawNodeProps(ept) //
		);
	}
}
