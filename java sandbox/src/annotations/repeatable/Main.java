package annotations.repeatable;

import java.lang.annotation.Annotation;


@RepeatableAnnotation
@RepeatableAnnotation
public class Main {

	public static void main(String[] args) {
		System.out.println(Main.class.getAnnotationsByType(ContainerAnnotation.class).length);
		
		for(Annotation repeatableAnnotation : ContainerAnnotation.class.getAnnotations()){
			System.out.println(repeatableAnnotation);
		}
	}
}
