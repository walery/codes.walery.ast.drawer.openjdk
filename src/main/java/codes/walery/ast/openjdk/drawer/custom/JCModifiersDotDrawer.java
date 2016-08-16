package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCModifiers;

public class JCModifiersDotDrawer extends DotDrawer<JCModifiers> {

	public JCModifiersDotDrawer(final JCModifiers node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("flags", node.flags), //
				prop("flagSet", Flags.asFlagSet(node.flags)), //
				super.drawNodeProps(ept) //
		);
	}
}
