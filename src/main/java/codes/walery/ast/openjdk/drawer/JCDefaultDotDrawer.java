package codes.walery.ast.openjdk.drawer;

import com.sun.tools.javac.tree.JCTree;

public class JCDefaultDotDrawer extends DotDrawer<JCTree> {

	public JCDefaultDotDrawer(final JCTree node) {
		super(node);
	}
}
