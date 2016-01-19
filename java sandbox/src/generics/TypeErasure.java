package generics;

import java.util.ArrayList;

public class TypeErasure {

	public static void main(String[] args) {
		ArrayList<Integer> li = new ArrayList<Integer>();
		ArrayList<Float> lf = new ArrayList<Float>();
		if (li.getClass() == lf.getClass()) { // evaluates to true
		    System.out.println("Equal");
		}
	}
}


// -------------------------------------------------------
// Not possible due to type erasure
// class GenericException<T> extends Throwable {}

// This is why. Due to type erasure not able to determine into which catch block the control should do inot
//try {
//    throw new GenericException<Integer>();
//}
//catch(GenericException<Integer> e) {
//    System.err.println("Integer");
//}
//catch(GenericException<String> e) {
//    System.err.println("String");
//}
//-------------------------------------------------------------------

//----------------------------------------------------------------------
class GenericsClass<T> {
	
	// Not possible due to generics
	// The JVM contains just one copy of the generics class for all instances
	//static T fieldName;
	
	void foo(T item) {
		// not possible due to type erasure
		//T tmp = new T();
	}
}
// -----------------------------------------------------------------------

//-----------------------------------------------------------------------------

//-----------------------------------------------------------------------------