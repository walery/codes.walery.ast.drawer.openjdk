package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCLambda;

public class JCLambdaDotDrawer extends DotDrawer<JCLambda> {

	public JCLambdaDotDrawer(final JCLambda node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("canCompleteNormally", node.canCompleteNormally), //
				prop("paramKind", node.paramKind), //
				prop("getBodyKind()", node.getBodyKind()), //
				prop("targets", node.targets), //
				prop("polyKind", node.polyKind), //
				super.drawNodeProps(ept) //
		);
	}
}
