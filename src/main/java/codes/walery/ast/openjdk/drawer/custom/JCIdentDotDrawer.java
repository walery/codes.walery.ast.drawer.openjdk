package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCIdent;

public class JCIdentDotDrawer extends DotDrawer<JCIdent> {

	public JCIdentDotDrawer(final JCIdent node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("name", node.name), //
				prop("sym", node.sym), //
				super.drawNodeProps(ept) //
		);
	}

}
