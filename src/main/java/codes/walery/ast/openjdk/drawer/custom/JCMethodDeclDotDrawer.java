package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;

public class JCMethodDeclDotDrawer extends DotDrawer<JCMethodDecl> {

	public JCMethodDeclDotDrawer(final JCMethodDecl node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("name", node.name), //
				prop("sym", node.sym), //
				super.drawNodeProps(ept) //
		);
	}
}
