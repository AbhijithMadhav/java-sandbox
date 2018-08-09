package generics;

// class has to be first and can then be followed by multiple interfaces
public class MultipleBounds<T extends B & A> {
}


interface A {
}

class B {
	
}


