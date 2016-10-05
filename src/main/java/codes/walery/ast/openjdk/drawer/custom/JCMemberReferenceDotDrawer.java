package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.JCMemberReference;

public class JCMemberReferenceDotDrawer extends DotDrawer<JCMemberReference> {

	public JCMemberReferenceDotDrawer(final JCMemberReference node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {

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
				prop("polyKind", node.polyKind) //
		);
	}
}
