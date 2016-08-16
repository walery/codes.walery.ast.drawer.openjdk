package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCTypeParameter;

public class JCTypeParameterDotDrawer extends DotDrawer<JCTypeParameter> {

	public JCTypeParameterDotDrawer(final JCTypeParameter node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("name", node.name), //
				super.drawNodeProps(ept) //
		);
	}
}
