package coffeemachine;

public class GroundCoffee {
	
	private String name;
	private int quantity;

	public GroundCoffee(String name, int quantity) {
		this.quantity= quantity;
		this.name= name;
	}

	public int getQuantity() {
		return quantity;
	}

	public Object getName() {
		return name;
	}

	public void setQuantity(int quantity) {
		this.quantity= quantity;
		
	}

}
