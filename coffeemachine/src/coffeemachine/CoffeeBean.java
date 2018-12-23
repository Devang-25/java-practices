package coffeemachine;

public class CoffeeBean {
	private String name;
	private int quantity;
	public CoffeeBean(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		
	}
	
	
}
