package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCBinary;

public class JCBinaryDotDrawer extends DotDrawer<JCBinary> {

	public JCBinaryDotDrawer(final JCBinary node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("getKind()", node.getKind()), //
				prop("getTag()", node.getTag()), //
				prop("operator", node.operator), //
				super.drawNodeProps(ept) //
		);
	}
}
