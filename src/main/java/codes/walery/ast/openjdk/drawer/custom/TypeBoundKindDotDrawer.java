package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.TypeBoundKind;

public class TypeBoundKindDotDrawer extends DotDrawer<TypeBoundKind> {

	public TypeBoundKindDotDrawer(final TypeBoundKind node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("kind", node.kind), //
				super.drawNodeProps(ept) //
		);
	}
}
