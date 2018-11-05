package com.elasticsearch.winterbe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

	// In the example filter, map and sorted are intermediate operations
	// whereas forEach is a terminal operation

	/*
	 * Most stream operations accept some kind of lambda expression parameter, a
	 * functional interface specifying the exact behavior of the operation. Most of
	 * those operations must be both non-interfering and stateless.
	 * 
	 * A function is non-interfering when it does not modify the underlying data
	 * source of the stream, e.g. in the above example no lambda expression does
	 * modify myList by adding or removing elements from the collection.
	 * 
	 * A function is stateless when the execution of the operation is deterministic,
	 * e.g. in the above example no lambda expression depends on any mutable
	 * variables or states from the outer scope which might change during execution.
	 */

	public static void streamProcesses() {
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);
	}

	/*
	 * Different kind of streams# Streams can be created from various data sources,
	 * especially collections. Lists and Sets support new methods stream() and
	 * parallelStream() to either create a sequential or a parallel stream. Parallel
	 * streams are capable of operating on multiple threads and will see later. We
	 * focus on sequential streams for now:
	 * 
	 */

	public static void streamKinds() {

		Arrays.asList("a1", "a2", "a3").stream().findFirst().ifPresent(System.out::println); // a1

		Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println); // a1

		IntStream.range(1, 4).forEach(System.out::println);

		Arrays.stream(new int[] { 1, 2, 3 }).map(n -> 2 * n + 1).average().ifPresent(System.out::println); // 5.0

		Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).max()
				.ifPresent(System.out::println); // 3

		IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(System.out::println);

		Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).forEach(System.out::println);

	}

	/*
	 * An important characteristic of intermediate operations is laziness. Look at
	 * this sample where a terminal operation is missing: e extend the example by
	 * the terminal operation forEach:
	 * 
	 * The order of the result might be surprising. A naive approach would be to
	 * execute the operations horizontally one after another on all elements of the
	 * stream. But instead each element moves along the chain vertically. The first
	 * string "d2" passes filter then forEach, only then the second string "a2" is
	 * processed.
	 * 
	 * This behavior can reduce the actual number of operations performed on each
	 * element, as we see in the next example:
	 * 
	 */
	public static void processOrder() {

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return true;
		});

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return true;
		}).forEach(s -> System.out.println("forEach: " + s));

		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).anyMatch(s -> {
			System.out.println("anyMatch: " + s);
			return s.startsWith("A");
		});

	}

	// We can greatly reduce the actual number of executions if we change the order
	// of the operations
	public static void orderMatters() {
		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("A");
		}).forEach(s -> System.out.println("forEach: " + s));

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("forEach: " + s));

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).sorted((s1, s2) -> {
			System.out.printf("sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("forEach: " + s));

	}
	/*
	 * Calling noneMatch after anyMatch on the same stream results in the following
	 * exception: java.lang.IllegalStateException:
	 * 
	 * To overcome this limitation we have to to create a new stream chain for every
	 * terminal operation we want to execute, e.g. we could create a stream supplier
	 * to construct a new stream with all intermediate operations already set up:
	 * 
	 * Each call to get() constructs a new stream on which we are save to call the
	 * desired terminal operation.
	 * 
	 */

	public static void reusingStream() {
		Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));

		stream.anyMatch(s -> true); // ok
		stream.noneMatch(s -> true); // exception

		Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));

		streamSupplier.get().anyMatch(s -> true); // ok
		streamSupplier.get().noneMatch(s -> true); // ok
	}

	/*
	 * let's dive deeper into the more complex operations collect, flatMap and
	 * reduce.
	 * 
	 * Need a set instead of list - just use Collectors.toSet().
	 * 
	 * If you're interested in more comprehensive statistics, the summarizing
	 * collectors return a special built-in summary statistics object. So we can
	 * simply determine min, max and arithmetic average age of the persons as well
	 * as the sum and count.
	 * 
	 * The next example joins all persons into a single string: The join collector
	 * accepts a delimiter as well as an optional prefix and suffix.
	 */

	public static void advancedOperationsCollect() {
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));

		List<Person> filtered = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());

		System.out.println(filtered); // [Peter, Pamela]

		Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));

		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

		Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));

		System.out.println(averageAge); // 19.0

		IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.age));

		System.out.println(ageSummary);
		// IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}

		String phrase = persons.stream().filter(p -> p.age >= 18).map(p -> p.name)
				.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

		System.out.println(phrase);
		// In Germany Max and Peter and Pamela are of legal age.

		Map<Integer, String> map = persons.stream()
				.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + ";" + name2));

		System.out.println(map);
		// {18=Max, 23=Peter;Pamela, 12=David}

	}
	/*
	 * let's try to build our own special collector. We want to transform all
	 * persons of the stream into a single string consisting of all names in upper
	 * letters separated by the | pipe character. In order to achieve this we create
	 * a new collector via Collector.of(). We have to pass the four ingredients of a
	 * collector: a supplier, an accumulator, a combiner and a finisher.
	 * 
	 * Since strings in Java are immutable, we need a helper class like StringJoiner
	 * to let the collector construct our string. The supplier initially constructs
	 * such a StringJoiner with the appropriate delimiter. The accumulator is used
	 * to add each persons upper-cased name to the StringJoiner. The combiner knows
	 * how to merge two StringJoiners into one. In the last step the finisher
	 * constructs the desired String from the StringJoiner.
	 * 
	 * 
	 * 
	 */

	public static void customCollector() {
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));

		Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner(" | "), // supplier
				(j, p) -> j.add(p.name.toUpperCase()), // accumulator
				(j1, j2) -> j1.merge(j2), // combiner
				StringJoiner::toString); // finisher

		String names = persons.stream().collect(personNameCollector);

		System.out.println(names); // MAX | PETER | PAMELA | DAVID
	}

	/**
	 * FlatMap is also available for the Optional class introduced in Java 8.
	 * Optionals flatMap operation returns an optional object of another type. So it
	 * can be utilized to prevent nasty null checks.
	 * 
	 * Think of a highly hierarchical structure like this:
	 * 
	 * In order to resolve the inner string foo of an outer instance you have to add
	 * multiple null checks to prevent possible NullPointerExceptions:
	 * 
	 * The same behavior can be obtained by utilizing optionals flatMap operation:
	 * 
	 * Each call to flatMap returns an Optional wrapping the desired object if
	 * present or null if absent.
	 * 
	 */

	public static void advanceOperationFlatMap() {
		List<Foo> foos = new ArrayList<>();

		// create foos
		IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));

		// create bars
		foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

		foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));

		// Finally, the above code example can be simplified into a single pipeline of
		// stream operations:

		IntStream.range(1, 4).mapToObj(i -> new Foo("Foo" + i)).peek(
				f -> IntStream.range(1, 4).mapToObj(i -> new Bar("Bar" + i + " <- " + f.name)).forEach(f.bars::add))
				.flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));

		Outer outer = new Outer();
		if (outer != null && outer.nested != null && outer.nested.inner != null) {
			System.out.println(outer.nested.inner.foo);
		}

		Optional.of(new Outer()).flatMap(o -> Optional.ofNullable(o.nested)).flatMap(n -> Optional.ofNullable(n.inner))
				.flatMap(i -> Optional.ofNullable(i.foo)).ifPresent(System.out::println);
	}

	/*
	 * The third reduce method accepts three parameters: an identity value, a
	 * BiFunction accumulator and a combiner function of type BinaryOperator. Since
	 * the identity values type is not restricted to the Person type, we can utilize
	 * this reduction to determine the sum of ages from all persons:
	 * 
	 * the accumulator function does all the work. It first get called with the
	 * initial identity value 0 and the first person Max. In the next three steps
	 * sum continually increases by the age of the last steps person up to a total
	 * age of 76.
	 * 
	 * Executing this stream in parallel results in an entirely different execution
	 * behavior. Now the combiner is actually called. Since the accumulator is
	 * called in parallel, the combiner is needed to sum up the separate accumulated
	 * values.
	 */

	public static void advanceOperationReduce() {
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));
		persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println); // Pamela

		Person result = persons.stream().reduce(new Person("", 0), (p1, p2) -> {
			p1.age += p2.age;
			p1.name += p2.name;
			return p1;
		});

		System.out.format("name=%s; age=%s", result.name, result.age);
		// name=MaxPeterPamelaDavid; age=76

		Integer ageSum = persons.stream().reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

		System.out.println(ageSum); // 76
	}

	/*
	 * Streams can be executed in parallel to increase runtime performance on large
	 * amount of input elements. Parallel streams use a common ForkJoinPool
	 * available via the static ForkJoinPool.commonPool() method. The size of the
	 * underlying thread-pool uses up to five threads - depending on the amount of
	 * available physical CPU cores:
	 * 
	 * On my machine the common pool is initialized with a parallelism of 3 per
	 * default. This value can be decreased or increased by setting the following
	 * JVM parameter: -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
	 * 
	 * Collections support the method parallelStream() to create a parallel stream
	 * of elements. Alternatively you can call the intermediate method parallel() on
	 * a given stream to convert a sequential stream to a parallel counterpart.
	 * 
	 * In order to understate the parallel execution behavior of a parallel stream
	 * the next example prints information about the current thread to sout:
	 * 
	 * you can see the parallel stream utilizes all available threads from the
	 * common ForkJoinPool for executing the stream operations. The output may
	 * differ in consecutive runs because the behavior which particular thread is
	 * actually used is non-deterministic.
	 */
	public static void parallelStream() {

		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		System.out.println(commonPool.getParallelism()); // 3

		// -Djava.util.concurrent.ForkJoinPool.common.parallelism=5

		Arrays.asList("a1", "a2", "b1", "c2", "c1").parallelStream().filter(s -> {
			System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		}).map(s -> {
			System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		}).forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
	}

	/*
	 * It seems that sort is executed sequentially on the main thread only.
	 * Actually, sort on a parallel stream uses the new Java 8 method
	 * Arrays.parallelSort() under the hood. As stated in Javadoc this method
	 * decides on the length of the array if sorting will be performed sequentially
	 * or in parallel:
	 * 
	 * If the length of the specified array is less than the minimum granularity,
	 * then it is sorted using the appropriate Arrays.sort method.
	 */
	public static void parallelStreamSort() {

		Arrays.asList("a1", "a2", "b1", "c2", "c1").parallelStream().filter(s -> {
			System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		}).map(s -> {
			System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		}).sorted((s1, s2) -> {
			System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
			return s1.compareTo(s2);
		}).forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
	}

	/*
	 * Coming back to the reduce example from the last section. We already found out
	 * that the combiner function is only called in parallel but not in sequential
	 * streams. Let's see which threads are actually involved:
	 * 
	 * The console output reveals that both the accumulator and the combiner
	 * functions are executed in parallel on all available threads:
	 * 
	 * In summary, it can be stated that parallel streams can bring be a nice
	 * performance boost to streams with a large amount of input elements. But keep
	 * in mind that some parallel stream operations like reduce and collect need
	 * additional computations (combine operations) which isn't needed when executed
	 * sequentially.
	 * 
	 * Furthermore we've learned that all parallel stream operations share the same
	 * JVM-wide common ForkJoinPool. So you probably want to avoid implementing slow
	 * blocking stream operations since that could potentially slow down other parts
	 * of your application which rely heavily on parallel streams.
	 */

	public static void parallelStreamReduce() {
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));

		persons.parallelStream().reduce(0, (sum, p) -> {
			System.out.format("accumulator: sum=%s; person=%s [%s]\n", sum, p, Thread.currentThread().getName());
			return sum += p.age;
		}, (sum1, sum2) -> {
			System.out.format("combiner: sum1=%s; sum2=%s [%s]\n", sum1, sum2, Thread.currentThread().getName());
			return sum1 + sum2;
		});
	}

	public static void main(String[] args) {
		streamProcesses();
	}

}

class Person {
	String name;
	int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return name;
	}
}

class Foo {
	String name;
	List<Bar> bars = new ArrayList<>();

	Foo(String name) {
		this.name = name;
	}
}

class Bar {
	String name;

	Bar(String name) {
		this.name = name;
	}
}

class Outer {
	Nested nested;
}

class Nested {
	Inner inner;
}

class Inner {
	String foo;
}
