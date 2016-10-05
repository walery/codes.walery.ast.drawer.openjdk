package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCMethodDecl;

public class JCMethodDeclDotDrawer extends DotDrawer<JCMethodDecl> {

	public JCMethodDeclDotDrawer(final JCMethodDecl node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("name", node.name), //
				prop("sym", node.sym) //
		);
	}
}
