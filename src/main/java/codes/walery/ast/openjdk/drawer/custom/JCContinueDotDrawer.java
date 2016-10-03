package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCContinue;

public class JCContinueDotDrawer extends DotDrawer<JCContinue> {

	public JCContinueDotDrawer(final JCContinue node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("label", node.label), //
				super.drawNodeProps(ept) //
		);
	}
}