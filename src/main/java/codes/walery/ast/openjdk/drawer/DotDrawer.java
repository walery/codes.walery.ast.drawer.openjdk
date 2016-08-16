package codes.walery.ast.openjdk.drawer;

import static java.util.stream.Collectors.joining;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import codes.walery.ast.openjdk.drawer.custom.JCAnnotationDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCAssignOpDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCBinaryDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCBlockDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCBreakDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCClassDeclDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCCompilationUnitDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCContinueDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCFieldAccessDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCIdentDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCImportDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCLabeledStatementDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCLambdaDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCLiteralDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCMemberReferenceDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCMethodDeclDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCMethodInvocationDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCModifiersDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCNewArrayDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCNewClassDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCPrimitiveTypeTreeDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCTryDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCTypeParameterDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCUnaryDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCVariableDeclDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.JCWildcardDotDrawer;
import codes.walery.ast.openjdk.drawer.custom.TypeBoundKindDotDrawer;

import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCAssignOp;
import com.sun.tools.javac.tree.JCTree.JCBinary;
import com.sun.tools.javac.tree.JCTree.JCBlock;
import com.sun.tools.javac.tree.JCTree.JCBreak;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.tree.JCTree.JCContinue;
import com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import com.sun.tools.javac.tree.JCTree.JCIdent;
import com.sun.tools.javac.tree.JCTree.JCImport;
import com.sun.tools.javac.tree.JCTree.JCLabeledStatement;
import com.sun.tools.javac.tree.JCTree.JCLambda;
import com.sun.tools.javac.tree.JCTree.JCLiteral;
import com.sun.tools.javac.tree.JCTree.JCMemberReference;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCMethodInvocation;
import com.sun.tools.javac.tree.JCTree.JCModifiers;
import com.sun.tools.javac.tree.JCTree.JCNewArray;
import com.sun.tools.javac.tree.JCTree.JCNewClass;
import com.sun.tools.javac.tree.JCTree.JCPrimitiveTypeTree;
import com.sun.tools.javac.tree.JCTree.JCTry;
import com.sun.tools.javac.tree.JCTree.JCTypeParameter;
import com.sun.tools.javac.tree.JCTree.JCUnary;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.JCTree.JCWildcard;
import com.sun.tools.javac.tree.JCTree.TypeBoundKind;

public abstract class DotDrawer<T extends JCTree> implements Drawer {
	protected final T node;
	private final PrintStream output;

	public DotDrawer(final T node) {
		this(node, System.out);
	}

	public DotDrawer(final T node, final OutputStream output) {
		this.node = node;

		if (output instanceof PrintStream) {
			this.output = (PrintStream) output;
		} else {
			this.output = new PrintStream(output);
		}
	}

	@Override
	public void draw() {
		draw(null);
	}

	@Override
	public void draw(final EndPosTable ept) {
		drawNode(ept);
		drawChildren(ept);
	}

	protected void drawNode(final EndPosTable ept) {
		final String templateWithoutStyle = "o%s [shape = box, label = \"%s%s\"]%n";
		final String templateWithStyle = "o%s [shape = box, label = \"%s%s\", color = \"%s\"]%n";

		String template = templateWithoutStyle;

		String props = drawNodeProps(ept);
		final String color = getColor();

		if (props != null && !props.isEmpty()) {
			props = "\\n" + props;
		}

		if (color != null && !color.isEmpty()) {
			template = templateWithStyle;
		}

		output.printf(template, node.hashCode(), node.getClass().getSimpleName(), props, color);
	}

	// protected String drawNodeProps() {
	// return drawNodeProps(null);
	// }

	protected String drawNodeProps(final EndPosTable ept) {
		if (ept == null) {
			return "";
		} else {
			return props( //
					prop("start", node.getStartPosition()), //
					prop("end", node.getEndPosition(ept)) //
					);
		}
		// return drawNodePropsFromFields();
		// return drawNodePropsFromMethods();
	}

	protected final String props(final String... props) {
		return Arrays.asList(props).stream().collect(joining("\\n"));
	}

	protected final String prop(final String name, final Object value) {
		return name + ": " + escapeValue(value);
	}

	private String escapeValue(final Object value) {
		if (value == null) {
			return "null";
		}

		return value.toString().replaceAll("\"", "\\\\\"");
	}

	protected String drawNodePropsFromFields() {
		return Arrays.stream(node.getClass().getFields()) //
				.filter(e -> !DotDrawerHelper.isListOfTypeOfSuperclassOfJCTree(e)) //
				.filter(e -> !DotDrawerHelper.isSuperclassOfJCTree(e)) //
				.map(e -> {
					try {
						return e.getName() + ": " + e.get(node);
					} catch (Exception exep) {
						return "ERROR ON FIELD " + e.getName();
					}
				}) //
				.collect(joining("\\n"));
	}

	protected String drawNodePropsFromMethods() {
		return Arrays.stream(node.getClass().getMethods()) //
				.filter(e -> e.getDeclaringClass() == node.getClass()) //
				.filter(e -> {
					// quick and dirty fix
					return e.getReturnType() != com.sun.tools.javac.util.List.class;
				}) //
				.filter(e -> {
					// quick and dirty fix
					return e.getReturnType() != java.util.List.class;
				}) //
				.filter(e -> !DotDrawerHelper.isSuperclassOfJCTree(e.getReturnType())) //
				.filter(e -> !e.getReturnType().equals(Void.TYPE)) //
				.filter(e -> e.getParameterCount() == 0) //
				.filter(e -> !Modifier.isStatic(e.getModifiers())) //
				.map(e -> {
					try {
						return e.getName() + ": " + e.invoke(node);
					} catch (Exception exep) {
						return "ERROR ON INVOKE MEHOTD " + e.getName();
					}
				}) //
				.collect(joining("\\n"));
	}

	protected String getColor() {
		return "";
	}

	protected void drawChildren(final EndPosTable ept) {
		for (Field f : node.getClass().getFields()) {
			try {
				if (DotDrawerHelper.isSuperclassOfJCTree(f)) {
					drawChild((JCTree) f.get(node), f.getName(), ept);
				} else if (DotDrawerHelper.isListOfTypeOfSuperclassOfJCTree(f)) {
					drawChildren((List<JCTree>) f.get(node), f.getName(), ept);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void drawChildren(final List<? extends JCTree> children) {
		drawChildren(children, null, null);
	}

	protected void drawChildren(final List<? extends JCTree> children, final String label, final EndPosTable ept) {
		if (children == null) {
			return;
		}

		for (final JCTree n : children) {
			drawChild(n, label, ept);
		}
	}

	protected void drawChild(final JCTree child) {
		drawChild(child, null, null);
	}

	protected void drawChild(final JCTree child, final String label, final EndPosTable ept) {
		if (child == null) {
			return;
		}

		getDrawer(child).draw(ept);
		if (label == null) {
			drawEdge(child);
		} else {
			drawEdge(child, label);
		}
	}

	protected void drawEdge(final JCTree child) {
		output.printf("o%s -> o%s%n", node.hashCode(), child.hashCode());
	}

	protected void drawEdge(final JCTree child, final String label) {
		output.printf("o%s -> o%s [label = \"%s\"]%n", node.hashCode(), child.hashCode(), label);
	}

	// TODO refactor
	protected Drawer getDrawer(final JCTree node) {
		final String treeType = node.getClass().getSimpleName();

		switch (treeType) {
		case "JCCompilationUnit":
			return new JCCompilationUnitDotDrawer((JCCompilationUnit) node, output);
		case "JCAnnotation":
			return new JCAnnotationDotDrawer((JCAnnotation) node, output);
		case "JCImport":
			return new JCImportDotDrawer((JCImport) node, output);
		case "JCModifiers":
			return new JCModifiersDotDrawer((JCModifiers) node, output);
		case "JCBlock": // 5
			return new JCBlockDotDrawer((JCBlock) node, output);
		case "JCPrimitiveTypeTree":
			return new JCPrimitiveTypeTreeDotDrawer((JCPrimitiveTypeTree) node, output);
		case "JCMethodInvocation":
			return new JCMethodInvocationDotDrawer((JCMethodInvocation) node, output);
		case "JCLiteral":
			return new JCLiteralDotDrawer((JCLiteral) node, output);
		case "JCBinary":
			return new JCBinaryDotDrawer((JCBinary) node, output);
		case "JCNewClass": // 10
			return new JCNewClassDotDrawer((JCNewClass) node, output);
		case "JCLambda":
			return new JCLambdaDotDrawer((JCLambda) node, output);
		case "JCMemberReference":
			return new JCMemberReferenceDotDrawer((JCMemberReference) node, output);
		case "JCAssignOp":
			return new JCAssignOpDotDrawer((JCAssignOp) node, output);
		case "JCUnary":
			return new JCUnaryDotDrawer((JCUnary) node, output);
		case "JCWildcard": // 15
			return new JCWildcardDotDrawer((JCWildcard) node, output);
		case "TypeBoundKind":
			return new TypeBoundKindDotDrawer((TypeBoundKind) node, output);
		case "JCTry":
			return new JCTryDotDrawer((JCTry) node, output);
		case "JCNewArray":
			return new JCNewArrayDotDrawer((JCNewArray) node, output);
		case "JCFieldAccess":
			return new JCFieldAccessDotDrawer((JCFieldAccess) node, output);
		case "JCIdent": // 20
			return new JCIdentDotDrawer((JCIdent) node, output);
		case "JCClassDecl":
			return new JCClassDeclDotDrawer((JCClassDecl) node, output);
		case "JCMethodDecl":
			return new JCMethodDeclDotDrawer((JCMethodDecl) node, output);
		case "JCVariableDecl":
			return new JCVariableDeclDotDrawer((JCVariableDecl) node, output);
		case "JCTypeParameter":
			return new JCTypeParameterDotDrawer((JCTypeParameter) node, output);
		case "JCLabeledStatement": // 25
			return new JCLabeledStatementDotDrawer((JCLabeledStatement) node, output);
		case "JCBreak":
			return new JCBreakDotDrawer((JCBreak) node, output);
		case "JCContinue": // 27
			return new JCContinueDotDrawer((JCContinue) node, output);
		default:
			return new JCDefaultDotDrawer(node, output);
		}
	}
}
