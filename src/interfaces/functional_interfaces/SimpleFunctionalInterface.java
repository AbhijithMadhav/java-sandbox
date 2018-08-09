package interfaces.functional_interfaces;

@FunctionalInterface
public interface SimpleFunctionalInterface {

	void foo();
	
	// Allowed in a SAM interface as this is a method inherited from 'Object'
	// But what's the use????
	String toString();
	
	// Can have a second method in a SAM(Single Abstract Method) interface
	//void boo();
}

