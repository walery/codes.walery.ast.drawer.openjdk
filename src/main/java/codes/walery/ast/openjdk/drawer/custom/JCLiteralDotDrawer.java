package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCLiteral;

public class JCLiteralDotDrawer extends DotDrawer<JCLiteral> {

	public JCLiteralDotDrawer(final JCLiteral node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("typetag", node.typetag), //
				prop("getKind()", node.getKind()), //
				prop("value", node.value), //
				prop("getValue()", node.getValue()) //
		);
	}
}
