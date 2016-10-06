package codes.walery.ast.openjdk.drawer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import codes.walery.ast.openjdk.drawer.custom.JCCompilationUnitDotDrawer;
import codes.walery.ast.openjdk.drawer.model.AbstractSourceTree;

import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;

public class DotCreater {
	public static AbstractSourceTree getAst(final JCCompilationUnit node) {
		AbstractSourceTree ast = new JCCompilationUnitDotDrawer(node).drawAsAst(node.endPositions);

		try {
			ast.setSrc(getSrcCode(node));
		} catch (IOException e) {
			// TODO handle correct
			e.printStackTrace();
		}

		return ast;
	}

	private static String getSrcCode(final JCCompilationUnit node) throws IOException {
		InputStream srcIs = node.getSourceFile().openInputStream();

		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = srcIs.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString("UTF-8");
	}

}
