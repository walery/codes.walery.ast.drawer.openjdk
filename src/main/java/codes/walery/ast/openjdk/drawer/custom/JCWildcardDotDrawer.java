package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCWildcard;

public class JCWildcardDotDrawer extends DotDrawer<JCWildcard> {

	public JCWildcardDotDrawer(final JCWildcard node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("getKind()", node.getKind()) //
		);
	}
}
