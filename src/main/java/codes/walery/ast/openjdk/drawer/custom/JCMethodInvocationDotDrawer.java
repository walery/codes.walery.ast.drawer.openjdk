package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCMethodInvocation;

public class JCMethodInvocationDotDrawer extends DotDrawer<JCMethodInvocation> {

	public JCMethodInvocationDotDrawer(final JCMethodInvocation node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("varargsElement", node.varargsElement) //
		);
	}
}
