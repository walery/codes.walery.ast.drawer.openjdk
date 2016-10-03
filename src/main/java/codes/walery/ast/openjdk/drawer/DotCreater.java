package codes.walery.ast.openjdk.drawer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import codes.walery.ast.openjdk.drawer.custom.JCCompilationUnitDotDrawer;

import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;

public class DotCreater {
	public static void printAstAsDot(final JCCompilationUnit node) {
		printAstAsDot(node, System.out);
	}

	public static void printAstAsDot(final JCCompilationUnit node, final OutputStream out) {
		PrintStream output;
		if (out instanceof PrintStream) {
			output = (PrintStream) out;
		} else {
			writeHtml(out, node);
			output = new PrintStream(out);
		}

		node.toString();

		output.println("digraph G {");
		new JCCompilationUnitDotDrawer(node, output).draw(node.endPositions);
		output.println("}");
	}

	// TODO pfush, pfush, pfush! Alles pfush!!!
	private static void writeHtml(final OutputStream out, final JCCompilationUnit node) {
		if (!(out instanceof FileOutputStream)) {
			return;
		}

		try {
			Field pathField = FileOutputStream.class.getDeclaredField("path");
			pathField.setAccessible(true);
			String path = (String) pathField.get(out);

			Path htmlTemplateBefore = Paths.get(path).getParent().resolve("svg-html-template-before.html");
			Path htmlTemplateAfter = Paths.get(path).getParent().resolve("svg-html-template-after.html");

			Files.write(htmlTemplateBefore, getBefore(node));
			Files.write(htmlTemplateAfter, getAfter());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static byte[] getAfter() {
		String after = "</div>" + //
				"</div>" + //
				"</body>" + //
				"</html>";
		return after.getBytes();
	}

	private static byte[] getBefore(final JCCompilationUnit node) throws IOException {
		String srcCode = getSrcCode(node).replaceAll("\n", "\\\\n").replaceAll("'", "\\\\'");

		String before = "<html>\n" + //
				"<head>\n" + //
				"	<style>\n" + //
				".javasrc {\n" + //
				"   display: inline;\n" + //
				"   font-family: monospace;\n" + //
				"   white-space: pre;\n" + //
				"   margin: 1em 0px 1em;\n" + //
				"}\n" + //
				"\n" + //
				".marked {\n" + //
				"   background-color: yellow;\n" + //
				"}\n" + //
				"\n" + //
				"#all {\n" + //
				"   width: 100%;\n" + //
				"   display: flex;\n" + //
				"   height: 100%;\n" + //
				"}\n" + //
				"\n" + //
				"#code {\n" + //
				"   overflow: scroll;\n" + //
				"   width: 200px;\n" + //
				"   resize: horizontal;\n" + //
				"   margin-top: 30px;\n" + //
				"   border-right-style: solid;\n" + //
				"   border-right-color: gray;\n" + //
				"}\n" + //
				"\n" + //
				"#ast {\n" + //
				"   overflow: scroll;\n" + //
				"   flex: 1 1 0;\n" + //
				"}\n" + //
				"\n" + //
				"#controlls {\n" + //
				"   position: absolute;\n" + //
				"}\n" + //
				"\n" + //
				"		</style>\n" + //
				"<script>\n" + //
				"var code = '" + srcCode + "';\n" + //
				"\n" + //
				"var highlightCode = function(start, end) {\n" + //
				"    var spanPreMark = document.createElement('span');\n" + //
				"    spanPreMark.className = 'javasrc';\n" + //
				"    spanPreMark.innerText = code.substr(0, parseInt(start, 10));\n" + //
				"\n" + //
				"    var spanMarked = document.createElement('span');\n" + //
				"    spanMarked.className = 'javasrc marked';\n" + //
				"    spanMarked.innerText = code.substr(parseInt(start, 10), (parseInt(end, 10) - parseInt(start, 10)));\n" + //
				"\n" + //
				"    var spanPostMark = document.createElement('span');\n" + //
				"    spanPostMark.className = 'javasrc';\n" + //
				"    spanPostMark.innerText = code.substr(parseInt(end, 10), code.length);\n" + //
				"\n" + //
				"    var divMarkedCode = document.createElement('div');\n" + //
				"    divMarkedCode.appendChild(spanPreMark);\n" + //
				"    divMarkedCode.appendChild(spanMarked);\n" + //
				"    divMarkedCode.appendChild(spanPostMark);\n" + //
				"\n" + //
				"    document.querySelector('#code').innerHTML = divMarkedCode.innerHTML;\n" + //
				"};\n" + //
				"\n" + //
				"var unhighlightCode = function() { highlightCode(0, 0); };\n" + //
				"\n" + //
				"window.onload = function () {[].forEach.call(" + //
				"  document.querySelectorAll('.node'), function(n) {\n" + //
				"      var start = -1;\n" + //
				"      var end = -1;\n" + //
				"\n" + //
				"      [].forEach.call(n.querySelectorAll('text'), function(e) {\n" + //
				"          if(e.innerHTML.startsWith('start: ')) {\n" + //
				"              start = parseInt(e.innerHTML.substr(7), 10);\n" + //
				"          }\n" + //
				"          if(e.innerHTML.startsWith('end: ')){\n" + //
				"              end = parseInt(e.innerHTML.substr(5), 10);\n" + //
				"          }\n" + //
				"      });\n" + //
				"      if(start !== -1 && end !== -1) {\n" + //
				"          n.onmouseenter = function(e) {\n" + //
				"              [].forEach.call(document.querySelectorAll('polygon'), function(p) {\n" + //
				"                  p.setAttribute('fill', 'none');\n" + //
				"              });\n" + //
				"\n" + //
				"              e.target.querySelector('polygon').setAttribute('fill', 'yellow');\n" + //
				"              highlightCode(start, end);\n" + //
				"          };\n" + //
				"      } else {\n" + //
				"          n.onmouseenter = function(e) {\n" + //
				"              [].forEach.call(document.querySelectorAll('polygon'), function(p) {\n" + //
				"                  p.setAttribute('fill', 'none');\n" + //
				"              });\n" + //
				"\n" + //
				"              e.target.querySelector('polygon').setAttribute('fill', 'orange');\n" + //
				"              unhighlightCode();\n" + //
				"          };\n" + //
				"      }\n" + //
				"  });" + //
				"\n" + //
				"  document.getElementById('srcPlus').onclick = function() {\n" + //
				"	  var e = document.getElementById('code');\n" + //
				"\n" + //
				"	  if(e.style.fontSize === '') {\n" + //
				"	    e.style.fontSize = '13px';\n" + //
				"	  }\n" + //
				"	  e.style.fontSize = (parseInt(e.style.fontSize) + 1) + 'px';\n" + //
				"	};\n" + //
				"\n" + //
				"  document.getElementById('srcMinus').onclick = function() {\n" + //
				"	  var e = document.getElementById('code');\n" + //
				"\n" + //
				"	  if(e.style.fontSize === '') {\n" + //
				"	    e.style.fontSize = '13px';\n" + //
				"	  }\n" + //
				"	  e.style.fontSize = (parseInt(e.style.fontSize) - 1) + 'px';\n" + //
				"	};\n" + //
				"\n" + //
				"  document.getElementById('astPlus').onclick = function() {\n" + //
				"     var e = document.querySelector('#ast > svg');\n" + //
				"	  e.setAttribute('width', (parseInt(e.getAttribute('width')) * 1.05) + 'pt');\n" + //
				"	  e.setAttribute('height', (parseInt(e.getAttribute('height')) * 1.05) + 'pt');\n" + //
				"  };\n" + //
				"\n" + //
				"  document.getElementById('astMinus').onclick = function() {\n" + //
				"     var e = document.querySelector('#ast > svg');\n" + //
				"     e.setAttribute('width', (parseInt(e.getAttribute('width')) * 0.95) + 'pt');\n" + //
				"     e.setAttribute('height', (parseInt(e.getAttribute('height')) * 0.95) + 'pt');\n" + //
				"  };\n" + //
				"\n" + //
				"unhighlightCode()\n" + //
				"\n" + //
				"};\n" + //
				"\n" + //
				"</script>\n" + //
				"	</head>\n" + //
				"	<body>\n" + //
				"		<div id=\"all\">\n" + //
				"			<div id=\"controlls\">" + //
				"				<span>code </span><button id=\"srcPlus\">+</button><button id=\"srcMinus\">-</button>" + //
				"				<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>" + //
				"				<span>ast </span><button id=\"astPlus\">+</button><button id=\"astMinus\">-</button>" + //
				"			</div>" + //
				"			<div id=\"code\"></div>\n" + //
				"			<div id=\"ast\">\n";

		return before.getBytes();
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
