package lambda_expressions.aggregate_operations;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lambda_expressions.aggregate_operations.Person.Sex;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		// reduce
		List<Person> roster = Person.createRoster();

		roster.stream().filter(p -> p.getGender() == Sex.MALE).forEach(p -> System.out.println(p.getName() + " " + p.getAge()));

		//System.out.println(roster.stream().filter(p -> p.getGender() == Sex.MALE).mapToInt(Person::getAge).sum());
		
		System.out.println("Sum of ages of males(Reducing the hardcore way) : " + roster.stream()
				.filter(p -> p.getGender() == Sex.MALE)
				.mapToInt(Person::getAge)
				.reduce(0, (a, b) -> a + b)
		);
		System.out.println("Sum of ages of males(Using 'IntStream#sum') : " + roster.stream()
				.filter(p -> p.getGender() == Sex.MALE)
				.mapToInt(Person::getAge)
				.sum()
		);


		// collect

		System.out.println(
				ImmutableList.of(1.1, 2.2, 3.3).stream()
						.collect(Collectors.averagingDouble(Double::valueOf))
		);
		System.out.println(
				ImmutableList.of("1", "2", "3", "4").stream()
						.collect(Collectors.averagingInt(Integer::valueOf))
		);
		System.out.println(
				ImmutableList.of("1", "2", "3", "4").stream()
						.collect(Collectors.averagingLong(Long::valueOf))
		);
		System.out.println(
				ImmutableSet.of("abg", "asdf", "adf", "asdgasdg").stream()
						.collect(
								Collectors.collectingAndThen
										(Collectors.toList(), Collections::unmodifiableList)
						).toString()
		);
		System.out.println(
				ImmutableList.of("1", "2", "3", "4").stream()
						.collect(Collectors.counting())
		);
		System.out.println(
				ImmutableList.of(new Employee("price", "F"), new Employee("hula", "F"), new Employee("roy", "M")).stream()
						.collect(Collectors.groupingBy(Employee::getSex))
		);
		System.out.println(
				ImmutableList.of(new Employee("price", "F", 10000L), new Employee("hula", "F", 20000L), new Employee("roy", "M", 15000L)).stream()
						.collect(Collectors.groupingBy(Employee::getSex, Collectors.summingLong(Employee::getSalary)))
		);
	}
}


class Employee {
	private String name;
	private String sex;
	private Long salary;

	Employee(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}

	Employee(String name, String sex, Long salary) {
		super();
		this.name = name;
		this.sex = sex;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public Long getSalary() {
		return salary;
	}
}