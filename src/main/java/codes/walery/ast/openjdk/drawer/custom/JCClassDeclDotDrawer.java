package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;

public class JCClassDeclDotDrawer extends DotDrawer<JCClassDecl> {

	public JCClassDeclDotDrawer(final JCClassDecl node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("name", node.name), //
				prop("sym", node.sym), //
				prop("getKind()", node.getKind()), //
				super.drawNodeProps(ept) //
		);
	}
}
