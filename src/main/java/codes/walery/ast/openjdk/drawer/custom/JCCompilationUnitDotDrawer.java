package codes.walery.ast.openjdk.drawer.custom;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;

public class JCCompilationUnitDotDrawer extends DotDrawer<JCCompilationUnit> {

	public JCCompilationUnitDotDrawer(final JCCompilationUnit node) {
		super(node);
	}

}
