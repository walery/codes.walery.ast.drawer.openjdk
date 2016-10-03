package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCLiteral;

public class JCLiteralDotDrawer extends DotDrawer<JCLiteral> {

	public JCLiteralDotDrawer(final JCLiteral node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("typetag", node.typetag), //
				prop("getKind()", node.getKind()), //
				prop("value", node.value), //
				prop("getValue()", node.getValue()), //
				super.drawNodeProps(ept) //
		);
	}
}
