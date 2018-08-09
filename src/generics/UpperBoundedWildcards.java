package generics;

import java.util.Arrays;
import java.util.List;

public class UpperBoundedWildcards {

	
	double sum1(List<? extends Number> list) {
		double s = 0.0;
		for (Number number : list) {
			s += number.doubleValue();
		}
		return s;
	}
	
	double sum2(List<Number> list) {
		double s = 0.0;
		for (Number number : list) {
			s += number.doubleValue();
		}
		return s;
	}
		
	public static void main(String[] args) {
		List<Number> numbers = Arrays.asList(1, 2.2, 3);
		List<Integer> integers = Arrays.asList(1, 2, 3);
		List<Double> doubles = Arrays.asList(2.1, 1.2, 1.0);
		
		System.out.println(new UpperBoundedWildcards().sum1(numbers));
		System.out.println(new UpperBoundedWildcards().sum1(integers));
		System.out.println(new UpperBoundedWildcards().sum1(doubles));
		
		 
		System.out.println(new UpperBoundedWildcards().sum2(numbers));
		// Not possible as List<subtype of Number> is not a sub-type of List<Number>
		// Hence we need wildcard type parameter 
		//System.out.println(new UpperBounds().sum2(integers);
		//System.out.println(new UpperBounds().sum2(doubles));
	}
}
