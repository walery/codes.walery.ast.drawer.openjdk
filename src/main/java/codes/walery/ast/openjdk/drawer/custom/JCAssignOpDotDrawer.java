package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCAssignOp;

public class JCAssignOpDotDrawer extends DotDrawer<JCAssignOp> {

	public JCAssignOpDotDrawer(final JCAssignOp node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("getKind()", node.getKind()), //
				prop("operator", node.operator), //
				prop("getTag()", node.getTag()) //
		);
	}
}
