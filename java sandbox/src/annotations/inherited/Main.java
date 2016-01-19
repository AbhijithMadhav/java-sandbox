package annotations.inherited;

import java.lang.annotation.Annotation;

public class Main {

	public static void main(String[] args) {
		System.out.println("Annotations of Parent");
		for (Annotation annotation : Parent.class.getAnnotations())
			System.out.println(annotation); // retention policy = runtime
		System.out.println();
		
		System.out.println("Annotations of Child");
		for (Annotation annotation : Child.class.getAnnotations())
			System.out.println(annotation); // retention policy = runtime
	}
}

@UnInheritedAnnotation
@InheritedAnnotation
class Parent {
	
}

class Child extends Parent {
	
}