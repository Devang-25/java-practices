package inputoutput;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		String jsonInput = "{\"id\":0,\"firstName\":\"Robin\",\"lastName\":\"Wilson\"}";
		Person q = mapper.readValue(jsonInput, Person.class);
		System.out.println("Read and parsed Person from JSON: " + q);

		Person p = new Person("Roger", "Rabbit");
		System.out.print("Person object " + p + " as JSON = ");
		mapper.writeValue(System.out, p);
	}
}

class Person {
	private int id;
	private String firstName;
	private String lastName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Person(){}
	public Person(String firstName, String lastName){
		id =1;
		this.firstName= firstName;
		this.lastName= lastName;
	}
}