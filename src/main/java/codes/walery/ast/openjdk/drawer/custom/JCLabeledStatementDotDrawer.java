package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCLabeledStatement;

public class JCLabeledStatementDotDrawer extends DotDrawer<JCLabeledStatement> {

	public JCLabeledStatementDotDrawer(final JCLabeledStatement node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("label", node.label), //
				super.drawNodeProps(ept) //
		);
	}
}
