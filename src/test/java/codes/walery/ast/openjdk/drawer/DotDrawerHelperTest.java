package codes.walery.ast.openjdk.drawer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import codes.walery.ast.openjdk.drawer.DotDrawerHelperTest.IsListOfTypeOfSuperclassOfJCTreeTest;
import codes.walery.ast.openjdk.drawer.DotDrawerHelperTest.IsSuperclassOfJCTreeTest;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCIf;
import com.sun.tools.javac.tree.JCTree.JCLambda;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.tree.JCTree.JCTypeUnion;

@RunWith(Suite.class)
@SuiteClasses({ //
	IsSuperclassOfJCTreeTest.class, //
	IsListOfTypeOfSuperclassOfJCTreeTest.class //
})
public class DotDrawerHelperTest {

	@RunWith(Parameterized.class)
	public static class IsSuperclassOfJCTreeTest {

		@Parameters(name = "{0}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] { //
					{ "jctree", true }, //
					{ "directSuperclass", true }, //
					{ "indirectSuperclass", true }, //
					{ "notSuperclass", false }, //
					{ "justObject", false } //
			});
		}

		@Parameter(0)
		public String field;

		@Parameter(1)
		public boolean expected;

		@Test
		public void should_recognize_field_of_superclass_of_JCTree() throws Exception {
			Field toCheck = ExampleClass.class.getField(field);

			boolean actual = DotDrawerHelper.isSuperclassOfJCTree(toCheck);

			assertThat(actual, is(expected));
		}

		@Test
		public void should_recognize__superclass_of_JCTree() throws Exception {
			Class<?> toCheck = ExampleClass.class.getField(field).getType();

			boolean actual = DotDrawerHelper.isSuperclassOfJCTree(toCheck);

			assertThat(actual, is(expected));
		}
	}

	@RunWith(Parameterized.class)
	public static class IsListOfTypeOfSuperclassOfJCTreeTest {

		@Parameters(name = "{0}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] { //
					{ "listOfJCTree", true }, //
					{ "listOfDirectSuperclassOfJCTree", true }, //
					{ "listOfExtendsDirectSuperclassOfJCTree", true }, //
					{ "listOfIndirectSuperclassOfJCTree", true }, //
					{ "listOfExtendsIndirectSuperclassOfJCTree", true }, //
					{ "listOfNotSuperclassOfJCTree", false }, //
					{ "listOfExtendsNotSuperclassOfJCTree", false }, //
					{ "notSuperclass", false } //
			});
		}

		@Parameter(0)
		public String field;

		@Parameter(1)
		public boolean expected;

		@Test
		public void should_check_if_field_of_list_type_is_superclass_of_JCTree() throws Exception {
			Field toCheck = ExampleClass.class.getField(field);

			boolean actual = DotDrawerHelper.isListOfTypeOfSuperclassOfJCTree(toCheck);

			assertThat(actual, is(expected));
		}
	}

	private static class ExampleClass {
		public JCTree jctree;
		public JCExpression directSuperclass;
		public JCIf indirectSuperclass;
		public CompilationUnitTree notSuperclass;
		public Object justObject;

		public List<JCTree> listOfJCTree;
		public List<JCStatement> listOfDirectSuperclassOfJCTree;
		public List<? extends JCCompilationUnit> listOfExtendsDirectSuperclassOfJCTree;
		public List<JCLambda> listOfIndirectSuperclassOfJCTree;
		public List<? extends JCTypeUnion> listOfExtendsIndirectSuperclassOfJCTree;
		public List<String> listOfNotSuperclassOfJCTree;
		public List<? extends CompilationUnitTree> listOfExtendsNotSuperclassOfJCTree;
	}
}
