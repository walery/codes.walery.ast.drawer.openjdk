package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCImport;

public class JCImportDotDrawer extends DotDrawer<JCImport> {

	public JCImportDotDrawer(final JCImport node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("staticImport", node.staticImport), //
				super.drawNodeProps(ept) //
		);
	}
}
