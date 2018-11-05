package parkinglot;

public class Vehicle {
	String registrationNumber;
	Color color;
	Brand brand; 
	Vehicle(String regNo, Color c, Brand b){
		registrationNumber= regNo;
		color=c;
		brand=b;
	}

}

enum Color{
	Black, Blue, White, Orange, Red, Grey, Silver
}

enum Brand {
	BMW, Toyota, Honda, Hundai, Tata, MarutiSuzuki
}