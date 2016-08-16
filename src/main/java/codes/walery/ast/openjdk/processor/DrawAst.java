package codes.walery.ast.openjdk.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface DrawAst {

	/**
	 * File where to write result. If value is empty string then result is
	 * printed to {@link System#out}. Currently the file must exist, otherwise
	 * result is printed to {@link System#out}.
	 *
	 * @return Path to exstent file where to write result. If given file doesn't
	 *         exists result is printed to {@link System#out}.
	 */
	public String value() default "";
}
