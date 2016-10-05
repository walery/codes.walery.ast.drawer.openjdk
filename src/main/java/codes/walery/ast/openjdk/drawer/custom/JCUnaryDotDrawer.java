package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCUnary;

public class JCUnaryDotDrawer extends DotDrawer<JCUnary> {

	public JCUnaryDotDrawer(final JCUnary node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("operator", node.operator), //
				prop("getKind()", node.getKind()), //
				prop("getTag()", node.getTag()) //
		);
	}
}
