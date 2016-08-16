package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCWildcard;

public class JCWildcardDotDrawer extends DotDrawer<JCWildcard> {

	public JCWildcardDotDrawer(final JCWildcard node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("getKind()", node.getKind()), //
				super.drawNodeProps(ept) //
		);
	}
}
