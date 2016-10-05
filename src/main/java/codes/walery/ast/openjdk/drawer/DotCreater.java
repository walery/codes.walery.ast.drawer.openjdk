package codes.walery.ast.openjdk.drawer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import codes.walery.ast.openjdk.drawer.custom.JCCompilationUnitDotDrawer;
import codes.walery.ast.openjdk.drawer.model.Node;
import codes.walery.ast.openjdk.drawer.visjs.VisjsConverter;
import codes.walery.ast.openjdk.drawer.visjs.model.VisjsData;

import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;

public class DotCreater {
	public static void printAstAsDot(final JCCompilationUnit node) {
		Node res = new JCCompilationUnitDotDrawer(node).draw(node.endPositions);
		VisjsData<String> visjs = VisjsConverter.convert(res, new VisjsData<String>());

		try {
			visjs.setSrc(getSrcCode(node));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(visjs);
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
