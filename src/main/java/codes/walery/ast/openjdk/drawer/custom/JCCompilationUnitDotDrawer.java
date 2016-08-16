package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;

public class JCCompilationUnitDotDrawer extends DotDrawer<JCCompilationUnit> {

	public JCCompilationUnitDotDrawer(final JCCompilationUnit node, final OutputStream output) {
		super(node, output);
	}

}
