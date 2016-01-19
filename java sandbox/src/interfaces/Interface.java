package interfaces;

public interface Interface {

	String SOME_CONSTANT = "This is a constant value";
	
	/**
	 * @deprecated Deprecating this method
	 */
	@Deprecated
	void foo();
}
