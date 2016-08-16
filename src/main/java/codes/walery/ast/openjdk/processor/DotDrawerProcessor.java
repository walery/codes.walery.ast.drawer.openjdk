package codes.walery.ast.openjdk.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

			try (OutputStream output = getOutput(e)) {
				DotCreater.printAstAsDot((JCCompilationUnit) compilationUnit, output);
			} catch (Exception exec) {
				// no idea
			}
		});

		return true;
	}

	private OutputStream getOutput(final Element e) {
		String fileToWrite = e.getAnnotation(DrawAst.class).value();
		OutputStream output;
		if (fileToWrite.isEmpty()) {
			output = System.out;
		} else {
			// TODO error handling
			try {
				output = new FileOutputStream(new File(fileToWrite));
			} catch (Exception exec) {
				output = System.out;
			}
		}

		return output;
	}
}
