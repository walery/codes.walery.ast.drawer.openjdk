package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCPrimitiveTypeTree;

public class JCPrimitiveTypeTreeDotDrawer extends DotDrawer<JCPrimitiveTypeTree> {

	public JCPrimitiveTypeTreeDotDrawer(final JCPrimitiveTypeTree node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("typetag", node.typetag), //
				prop("getPrimitiveTypeKind()", node.getPrimitiveTypeKind()) //
		);
	}
}
