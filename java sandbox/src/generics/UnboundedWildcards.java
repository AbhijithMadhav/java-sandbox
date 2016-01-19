package generics;

import java.util.Arrays;
import java.util.List;

public class UnboundedWildcards {

	static void printList(List<?> list) {
		for (Object item : list)
			System.out.println(item);
	}
	
	static void printList2(List<Object> list) {
		for (Object item : list)
			System.out.println(item);
	}
	
	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(1, 2, 3);
		List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
		
		printList(integers);
		printList(doubles);
		
		// Not possible
		// printList2(integers);
		// printList2(doubles);
	}
}
