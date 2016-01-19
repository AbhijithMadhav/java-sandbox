package interfaces.functional_interfaces;

@FunctionalInterface
public interface ComplexFunctionalInterface extends SimpleFunctionalInterface {

	// Can't do this as this is also a SAM interface
	// void coo();
	
	// This is ok
	default void coo() {
		System.out.println("This is a coo");
	}
}
