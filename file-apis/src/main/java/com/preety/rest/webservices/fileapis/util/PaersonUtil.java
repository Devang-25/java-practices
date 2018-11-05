package com.preety.rest.webservices.fileapis.util;

public class PaersonUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println(person);
	}

}

class Person {
	String firstName;
	String lastName;

	Person() {
	}

	Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	 
}
// Next we specify a person factory interface to be used for creating new
// persons:

interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}

// Instead of implementing the factory manually, we glue everything together via
// constructor references:
