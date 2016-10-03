package codes.walery.ast.openjdk.drawer.custom;

import java.io.OutputStream;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree.JCNewClass;

public class JCNewClassDotDrawer extends DotDrawer<JCNewClass> {

	public JCNewClassDotDrawer(final JCNewClass node, final String astPath, final OutputStream output) {
		super(node, astPath, output);
	}

	@Override
	protected String drawNodeProps(final EndPosTable ept) {
		return props( //
				prop("constructor", node.constructor), //
				prop("varargsElement", node.varargsElement), //
				prop("constructorType", node.constructorType), //
				super.drawNodeProps(ept) //
		);
	}
}
