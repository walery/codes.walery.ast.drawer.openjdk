package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.JCTree.TypeBoundKind;

public class TypeBoundKindDotDrawer extends DotDrawer<TypeBoundKind> {

	public TypeBoundKindDotDrawer(final TypeBoundKind node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("kind", node.kind) //
		);
	}
}
