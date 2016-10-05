package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCImport;

public class JCImportDotDrawer extends DotDrawer<JCImport> {

	public JCImportDotDrawer(final JCImport node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("staticImport", node.staticImport) //
		);
	}
}
