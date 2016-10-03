package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCUnary;

public class JCUnaryDotDrawer extends DotDrawer<JCUnary> {

	public JCUnaryDotDrawer(final JCUnary node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("operator", node.operator), //
				prop("getKind()", node.getKind()), //
				prop("getTag()", node.getTag()), //
				super.drawNodeProps(ept) //
		);
	}
}
