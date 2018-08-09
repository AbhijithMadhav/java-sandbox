package interfaces;

public class InterfaceImpl implements Interface{
	
	@Override
	public void foo() {
		// Not possible
		// SOME_CONSTANT = "Changing the value of a constant";
	}

	public static void main(String[] args) {
		new InterfaceImpl().foo();
	}
}
