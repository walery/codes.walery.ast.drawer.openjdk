package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCTry;

public class JCTryDotDrawer extends DotDrawer<JCTry> {

	public JCTryDotDrawer(final JCTry node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("finallyCanCompleteNormally", node.finallyCanCompleteNormally) //
		);
	}
}
