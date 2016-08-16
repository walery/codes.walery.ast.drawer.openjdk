package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCMemberReference;

public class JCMemberReferenceDotDrawer extends DotDrawer<JCMemberReference> {

	public JCMemberReferenceDotDrawer(final JCMemberReference node, final OutputStream output) {
		super(node, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {

		return props( //
				prop("mode", node.mode), //
				prop("kind", node.kind), //
				prop("name", node.name), //
				prop("sym", node.sym), //
				prop("varargsElement", node.varargsElement), //
				prop("refPolyKind", node.refPolyKind), //
				prop("ownerAccessible", node.ownerAccessible), //
				prop("overloadKind", node.overloadKind), //
				prop("targets", node.targets), //
				prop("polyKind", node.polyKind), //
				super.drawNodeProps(ept) //
		);
	}
}
