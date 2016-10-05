package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCVariableDecl;

public class JCVariableDeclDotDrawer extends DotDrawer<JCVariableDecl> {

	public JCVariableDeclDotDrawer(final JCVariableDecl node) {
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
