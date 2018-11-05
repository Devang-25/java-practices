package com.elasticsearch.winterbe;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Java8features {
	List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

	public void defaultMethod() {
		Formula formula = new Formula() {
			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};

		formula.calculate(100); // 100.0
		formula.sqrt(16);
	}

	public void sort() {

		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});

	}

	public void sortLambda() {
		Collections.sort(names, (String a, String b) -> {
			return b.compareTo(a);
		});
		
		Collections.sort(names, (String a, String b) -> b.compareTo(a));
		
		Collections.sort(names, (a, b) -> b.compareTo(a));
	}
	
	public void functionalIntefaceStaticMethod() {
		Converter<String, Integer> converter1 = (from) -> Integer.valueOf(from);
		Integer converted1 = converter1.convert("123");
		System.out.println(converted1);    // 123
		
		Converter<String, Integer> converter2 = Integer::valueOf;
		Integer converted2 = converter2.convert("123");
		System.out.println(converted2);   // 123
	}
	
	public void functionalInterfaceObjectMethos() {
		Something something = new Something();
		Converter<String, String> converter = something::startsWith;
		String converted = converter.convert("Java");
		System.out.println(converted);  
	}
	
	public void createUserWithFactoryInterface() {
		UserFactory<User> personFactory = User::new;
		User person = personFactory.create("Peter", "Parker");
	}
}

interface Formula {
	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

class User {
    String firstName;
    String lastName;

    User() {}

    User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

interface UserFactory<P extends User> {
    P create(String firstName, String lastName);
}