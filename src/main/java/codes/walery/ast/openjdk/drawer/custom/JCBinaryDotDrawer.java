package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCBinary;

public class JCBinaryDotDrawer extends DotDrawer<JCBinary> {

	public JCBinaryDotDrawer(final JCBinary node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("getKind()", node.getKind()), //
				prop("getTag()", node.getTag()), //
				prop("operator", node.operator) //
		);
	}
}
