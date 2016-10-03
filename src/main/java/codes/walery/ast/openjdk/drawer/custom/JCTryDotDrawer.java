package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCTry;

public class JCTryDotDrawer extends DotDrawer<JCTry> {

	public JCTryDotDrawer(final JCTry node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("finallyCanCompleteNormally", node.finallyCanCompleteNormally), //
				super.drawNodeProps(ept) //
		);
	}
}
