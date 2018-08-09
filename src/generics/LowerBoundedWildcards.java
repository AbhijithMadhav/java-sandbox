package generics;

import java.util.Arrays;
import java.util.List;

public class LowerBoundedWildcards {

	static void addNumbers(List<? super Integer> list) {
		
		for (int i = 0; i < 10 ; i++)
			list.add(i);
	}
	
	public static void main(String[] args) {
		
		List<Integer> integers = Arrays.asList(1, 2, 3);
		List<Number> numbers = Arrays.asList(1, 2, 3, 4);
		List<Object> objects = Arrays.asList(0);
		addNumbers(integers);
		addNumbers(numbers);
		addNumbers(objects);
	}
}
