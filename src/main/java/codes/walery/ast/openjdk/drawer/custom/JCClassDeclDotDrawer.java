package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCClassDecl;

public class JCClassDeclDotDrawer extends DotDrawer<JCClassDecl> {

	public JCClassDeclDotDrawer(final JCClassDecl node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("name", node.name), //
				prop("sym", node.sym), //
				prop("getKind()", node.getKind()) //
		);
	}
}
