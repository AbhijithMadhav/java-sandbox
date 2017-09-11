package lambda_expressions.aggregate_operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import lambda_expressions.aggregate_operations.Person.Sex;

public class Main {

	public static void main(String[] args) {
		List<Person> roster = Person.createRoster();

		// roster.stream().forEach(p -> System.out.println(p.name));

		roster.stream().filter(p -> p.getGender() == Sex.MALE).forEach(p -> System.out.println(p.getName() + " " + p.getAge()));

		System.out.println(roster.stream().filter(p -> p.getGender() == Sex.MALE).mapToInt(Person::getAge).sum()
				);
		
		System.out.println(roster.stream()
		.filter(p -> p.getGender() == Sex.MALE)
		.mapToInt(Person::getAge)
		.reduce(0, (a, b) -> a + b));
		
		System.out.println(Collections.emptyList().add(null));
	}
}
