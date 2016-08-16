package codes.walery.ast.openjdk.drawer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

import com.sun.tools.javac.tree.JCTree;

public final class DotDrawerHelper {
	private DotDrawerHelper() {
		// helper class
	}

	public static boolean isSuperclassOfJCTree(final Field toCheck) {
		return isSuperclassOfJCTree(toCheck.getType());
	}

	public static boolean isSuperclassOfJCTree(final Class<?> toCheck) {
		return JCTree.class.isAssignableFrom(toCheck);
	}

	public static boolean isListOfTypeOfSuperclassOfJCTree(final Field toCheck) {
		if (!(toCheck.getGenericType() instanceof ParameterizedType)) {
			return false;
		}

		Type[] typeArguments = ((ParameterizedType) toCheck.getGenericType()).getActualTypeArguments();

		return hasTypeOfSuperclassOfJCTree(typeArguments);
	}

	private static boolean hasTypeOfSuperclassOfJCTree(final Type[] types) {
		if (types.length != 1) {
			return false;
		}

		Type type = types[0];

		if (type instanceof Class<?>) {
			return isSuperclassOfJCTree((Class<?>) type);
		}

		if (type instanceof WildcardType) {
			return hasTypeOfSuperclassOfJCTree(((WildcardType) type).getUpperBounds());
		}

		return false;
	}
}
