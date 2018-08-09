package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Documented
@interface Header {

	String AUTHOR = "This space is supposed to be contain the author name";
	String creator() default AUTHOR;
	String description();
	String created();
	String lastModified();
	String[] contributers() default {};
	Class<?> className();
}
