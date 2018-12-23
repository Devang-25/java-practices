package coffeemachine;

public class Configuration {
	private int quantityCoffee;
	private int quantityWater;
	public Configuration(int quantityCoffee, int quantityWater) {
		super();
		this.quantityCoffee = quantityCoffee;
		this.quantityWater = quantityWater;
	}
	public int getQuantityCoffee() {
		return quantityCoffee;
	}
	public int getQuantityWater() {
		return quantityWater;
	}

	
}
