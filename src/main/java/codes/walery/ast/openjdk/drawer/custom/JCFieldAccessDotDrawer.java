package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCFieldAccess;

public class JCFieldAccessDotDrawer extends DotDrawer<JCFieldAccess> {

	public JCFieldAccessDotDrawer(final JCFieldAccess node) {
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
