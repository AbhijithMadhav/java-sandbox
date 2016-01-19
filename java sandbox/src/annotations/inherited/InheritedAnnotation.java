package annotations.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // Think why this is necessary? Hint: Look at Main#main
@Inherited
public @interface InheritedAnnotation {

}
