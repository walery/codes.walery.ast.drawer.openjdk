package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCLambda;

public class JCLambdaDotDrawer extends DotDrawer<JCLambda> {

	public JCLambdaDotDrawer(final JCLambda node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("canCompleteNormally", node.canCompleteNormally), //
				prop("paramKind", node.paramKind), //
				prop("getBodyKind()", node.getBodyKind()), //
				prop("targets", node.targets), //
				prop("polyKind", node.polyKind) //
		);
	}
}
