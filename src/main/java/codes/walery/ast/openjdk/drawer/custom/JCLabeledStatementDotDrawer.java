package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCLabeledStatement;

public class JCLabeledStatementDotDrawer extends DotDrawer<JCLabeledStatement> {

	public JCLabeledStatementDotDrawer(final JCLabeledStatement node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("label", node.label) //
		);
	}
}
