package codes.walery.ast.openjdk.drawer;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.joining;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import codes.walery.ast.openjdk.drawer.model.Node;

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

	public DotDrawer(final T node) {
		this.node = node;
	}

	@Override
	public Node draw() {
		return draw(null);
	}

	@Override
	public Node draw(final EndPosTable ept) {
		Node node = drawNode(ept);
		node.setChildren(drawChildren(ept));

		return node;
	}

	protected Node drawNode(final EndPosTable ept) {
		Node res = new Node();
		res.setId(node.hashCode());
		res.setTypeName(node.getClass().getSimpleName());
		res.setProps(drawNodeProps());

		if (ept != null) {
			res.setStart(node.getStartPosition());
			res.setEnd(node.getEndPosition(ept));
		}

		return res;
	}

	protected Map<String, ?> drawNodeProps() {
		return emptyMap();
	}

	protected final Map<String, ?> props(final Prop... props) {
		Map<String, Object> res = new HashMap<>();

		Arrays.asList(props).stream() //
		.forEach(prop -> res.put(prop.getName(), prop.getValue()));

		return res;
	}

	protected final Prop prop(final String name, final Object value) {
		return new Prop(name, value);
	}

	// TODO move somewhere
	// TODO use lombok
	public static class Prop {
		private final String name;
		private final Object value;

		private Prop(final String name, final Object value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}
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

	protected NodeState getState() {
		return NodeState.DEFAULT;
	}

	// TODO rename
	// TODO move somewhere else
	public static enum NodeState {
		DEFAULT, NOT_IMPLEMENTED, NOT_FINISHED, NOT_IN_SRC
	}

	protected Map<String, Node> drawChildren(final EndPosTable ept) {
		Map<String, Node> children = new HashMap<>();

		for (Field f : node.getClass().getFields()) {
			try {
				String childName = f.getName();

				if (DotDrawerHelper.isSuperclassOfJCTree(f)) {
					Node child = drawChild((JCTree) f.get(node), childName, ept);
					children.put(childName, child);
				} else if (DotDrawerHelper.isListOfTypeOfSuperclassOfJCTree(f)) {
					Map<String, Node> child = drawChildren((List<JCTree>) f.get(node), childName, ept);
					children.putAll(child);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return children;
	}

	protected Map<String, Node> drawChildren(final List<? extends JCTree> children, final String childName, final EndPosTable ept) {
		if (children == null) {
			return emptyMap();
		}

		Map<String, Node> res = new HashMap<>();

		for (int i = 0; i < children.size(); i++) {
			String childNameIndexed = childName + "[" + i + "]";

			Node child = drawChild(children.get(i), childNameIndexed, ept);

			res.put(childNameIndexed, child);
		}

		return res;
	}

	protected Node drawChild(final JCTree child, final String childName, final EndPosTable ept) {
		if (child == null) {
			return null;
		}

		return getDrawer(child, childName).draw(ept);
	}

	// TODO refactor
	protected Drawer getDrawer(final JCTree node, final String childName) {
		final String treeType = node.getClass().getSimpleName();

		switch (treeType) {
		case "JCCompilationUnit":
			return new JCCompilationUnitDotDrawer((JCCompilationUnit) node);
		case "JCAnnotation":
			return new JCAnnotationDotDrawer((JCAnnotation) node);
		case "JCImport":
			return new JCImportDotDrawer((JCImport) node);
		case "JCModifiers":
			return new JCModifiersDotDrawer((JCModifiers) node);
		case "JCBlock": // 5
			return new JCBlockDotDrawer((JCBlock) node);
		case "JCPrimitiveTypeTree":
			return new JCPrimitiveTypeTreeDotDrawer((JCPrimitiveTypeTree) node);
		case "JCMethodInvocation":
			return new JCMethodInvocationDotDrawer((JCMethodInvocation) node);
		case "JCLiteral":
			return new JCLiteralDotDrawer((JCLiteral) node);
		case "JCBinary":
			return new JCBinaryDotDrawer((JCBinary) node);
		case "JCNewClass": // 10
			return new JCNewClassDotDrawer((JCNewClass) node);
		case "JCLambda":
			return new JCLambdaDotDrawer((JCLambda) node);
		case "JCMemberReference":
			return new JCMemberReferenceDotDrawer((JCMemberReference) node);
		case "JCAssignOp":
			return new JCAssignOpDotDrawer((JCAssignOp) node);
		case "JCUnary":
			return new JCUnaryDotDrawer((JCUnary) node);
		case "JCWildcard": // 15
			return new JCWildcardDotDrawer((JCWildcard) node);
		case "TypeBoundKind":
			return new TypeBoundKindDotDrawer((TypeBoundKind) node);
		case "JCTry":
			return new JCTryDotDrawer((JCTry) node);
		case "JCNewArray":
			return new JCNewArrayDotDrawer((JCNewArray) node);
		case "JCFieldAccess":
			return new JCFieldAccessDotDrawer((JCFieldAccess) node);
		case "JCIdent": // 20
			return new JCIdentDotDrawer((JCIdent) node);
		case "JCClassDecl":
			return new JCClassDeclDotDrawer((JCClassDecl) node);
		case "JCMethodDecl":
			return new JCMethodDeclDotDrawer((JCMethodDecl) node);
		case "JCVariableDecl":
			return new JCVariableDeclDotDrawer((JCVariableDecl) node);
		case "JCTypeParameter":
			return new JCTypeParameterDotDrawer((JCTypeParameter) node);
		case "JCLabeledStatement": // 25
			return new JCLabeledStatementDotDrawer((JCLabeledStatement) node);
		case "JCBreak":
			return new JCBreakDotDrawer((JCBreak) node);
		case "JCContinue": // 27
			return new JCContinueDotDrawer((JCContinue) node);
		default:
			return new JCDefaultDotDrawer(node);
		}
	}
}
