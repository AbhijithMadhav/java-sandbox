package interfaces.functional_interfaces;

public class Main {

	static void func(SimpleFunctionalInterface sfi) {
		sfi.foo();
	}
	
	static void cfi(ComplexFunctionalInterface cfi) {
		cfi.foo();
		cfi.coo();
	}
	
	public static void main(String[] args) {
		
		func(new SimpleFunctionalInterface() {
			
			@Override
			public void foo() {
				System.out.println("Not using lambda expressions");
			}
			
			public String toString() {
				return "I have no state";
			}
		});
		
		// can't override toString() here
		func(() -> System.out.println("Using lambda expressions"));
		
		cfi(() -> System.out.println("Using lambda expressions"));
		
	}
}
