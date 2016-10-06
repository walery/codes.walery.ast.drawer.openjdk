package codes.walery.ast.openjdk.processor;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import codes.walery.ast.openjdk.drawer.DotCreater;
import codes.walery.ast.openjdk.drawer.model.AbstractSourceTree;
import codes.walery.ast.openjdk.drawer.visjs.VisjsConverter;
import codes.walery.ast.openjdk.drawer.visjs.model.VisjsData;

import com.google.gson.Gson;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;

@SupportedAnnotationTypes("codes.walery.ast.openjdk.processor.DrawAst")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class DotDrawerProcessor extends AbstractProcessor {

	private Trees trees;

	@Override
	public synchronized void init(final ProcessingEnvironment processingEnv) {
		super.init(processingEnv);

		trees = Trees.instance(processingEnv);
	}

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		Set<? extends Element> toDraw = roundEnv.getElementsAnnotatedWith(DrawAst.class);

		toDraw.stream().forEach(e -> {
			CompilationUnitTree compilationUnit = trees.getPath(e).getCompilationUnit();

			AbstractSourceTree ast = DotCreater.getAst((JCCompilationUnit) compilationUnit);
			VisjsData<String> visjs = VisjsConverter.convert(ast, new VisjsData<String>());

			getOutput(e, visjs);
		});

		return true;
	}

	private void getOutput(final Element e, final VisjsData<String> visjs) {
		String fileToWrite = e.getAnnotation(DrawAst.class).value();
		try (Writer w = new FileWriter(fileToWrite)) {
			Gson gson = new Gson();
			gson.toJson(visjs, w);
		} catch (Exception exec) {
			// TODO handle correct
			exec.printStackTrace();
		}
	}
}
