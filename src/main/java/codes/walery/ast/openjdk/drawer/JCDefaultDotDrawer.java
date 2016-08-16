package codes.walery.ast.openjdk.drawer;

import java.io.OutputStream;

import com.sun.tools.javac.tree.JCTree;

public class JCDefaultDotDrawer extends DotDrawer<JCTree> {

	public JCDefaultDotDrawer(final JCTree node, final OutputStream output) {
		super(node, output);
	}
}
