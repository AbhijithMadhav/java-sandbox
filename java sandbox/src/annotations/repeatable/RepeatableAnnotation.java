package annotations.repeatable;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ContainerAnnotation.class) // Why was this required? For compatibility reasons
public @interface RepeatableAnnotation {

}

@Retention(RetentionPolicy.RUNTIME)
@interface ContainerAnnotation {
	RepeatableAnnotation[] value();
}