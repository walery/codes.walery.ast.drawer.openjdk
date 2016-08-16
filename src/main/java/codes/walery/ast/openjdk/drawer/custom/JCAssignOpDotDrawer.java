package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCAssignOp;

public class JCAssignOpDotDrawer extends DotDrawer<JCAssignOp> {

	public JCAssignOpDotDrawer(final JCAssignOp node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("getKind()", node.getKind()), //
				prop("operator", node.operator), //
				prop("getTag()", node.getTag()), //
				super.drawNodeProps(ept) //
		);
	}
}
