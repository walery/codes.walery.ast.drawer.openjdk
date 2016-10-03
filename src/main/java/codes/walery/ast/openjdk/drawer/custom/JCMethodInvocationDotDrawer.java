package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCMethodInvocation;

public class JCMethodInvocationDotDrawer extends DotDrawer<JCMethodInvocation> {

	public JCMethodInvocationDotDrawer(final JCMethodInvocation node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("varargsElement", node.varargsElement), //
				super.drawNodeProps(ept) //
		);
	}
}
