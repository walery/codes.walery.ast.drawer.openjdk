package codes.walery.ast.openjdk.drawer.custom;

import java.util.Map;

import codes.walery.ast.openjdk.drawer.DotDrawer;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree.JCBlock;

public class JCBlockDotDrawer extends DotDrawer<JCBlock> {

	public JCBlockDotDrawer(final JCBlock node) {
		super(node);
	}

	@Override
	protected Map<String, ?> drawNodeProps() {
		return props( //
				prop("flags", node.flags), //
				prop("flagSet", Flags.asFlagSet(node.flags)) //
		);
	}
}
