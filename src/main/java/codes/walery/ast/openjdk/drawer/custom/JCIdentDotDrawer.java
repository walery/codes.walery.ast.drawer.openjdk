package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCIdent;

public class JCIdentDotDrawer extends DotDrawer<JCIdent> {

	public JCIdentDotDrawer(final JCIdent node) {
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
