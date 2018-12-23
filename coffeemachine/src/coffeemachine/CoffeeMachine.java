package coffeemachine;

public interface CoffeeMachine {
	
    Coffee brewCoffee(CoffeeSelection selection) throws CoffeeException;
}
