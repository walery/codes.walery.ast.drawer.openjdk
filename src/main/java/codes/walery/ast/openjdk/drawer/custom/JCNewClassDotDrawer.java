package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCNewClass;

public class JCNewClassDotDrawer extends DotDrawer<JCNewClass> {

	public JCNewClassDotDrawer(final JCNewClass node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("constructor", node.constructor), //
				prop("varargsElement", node.varargsElement), //
				prop("constructorType", node.constructorType) //
		);
	}
}
