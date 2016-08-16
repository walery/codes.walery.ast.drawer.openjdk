package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCPrimitiveTypeTree;

public class JCPrimitiveTypeTreeDotDrawer extends DotDrawer<JCPrimitiveTypeTree> {

	public JCPrimitiveTypeTreeDotDrawer(final JCPrimitiveTypeTree node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("typetag", node.typetag), //
				prop("getPrimitiveTypeKind()", node.getPrimitiveTypeKind()), //
				super.drawNodeProps(ept) //
		);
	}
}
