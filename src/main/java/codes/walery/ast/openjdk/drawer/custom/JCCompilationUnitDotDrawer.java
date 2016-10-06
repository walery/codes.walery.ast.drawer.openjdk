package codes.walery.ast.openjdk.drawer.custom;

import codes.walery.ast.openjdk.drawer.DotDrawer;
import codes.walery.ast.openjdk.drawer.model.AbstractSourceTree;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;

public class JCCompilationUnitDotDrawer extends DotDrawer<JCCompilationUnit> {

	public JCCompilationUnitDotDrawer(final JCCompilationUnit node) {
		super(node);
	}

	public AbstractSourceTree drawAsAst(final EndPosTable ept) {
		return new AbstractSourceTree(super.draw(ept));
	}
}
