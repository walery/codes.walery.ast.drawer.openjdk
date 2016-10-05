package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCContinue;

public class JCContinueDotDrawer extends DotDrawer<JCContinue> {

	public JCContinueDotDrawer(final JCContinue node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("label", node.label) //
		);
	}
}
