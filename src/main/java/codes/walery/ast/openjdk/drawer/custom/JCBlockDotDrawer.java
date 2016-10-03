package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCBlock;

public class JCBlockDotDrawer extends DotDrawer<JCBlock> {

	public JCBlockDotDrawer(final JCBlock node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("flags", node.flags), //
				prop("flagSet", Flags.asFlagSet(node.flags)), //
				prop("endpos??", node.endpos), // TODO endpos?
				super.drawNodeProps(ept) //
		);
	}
}
