package logisticsystem.domain;


//The Vehicle class represents the vehicle which will be used to ship/deliver an order.
//It will be of two types: 1. Bike and 2. Truck

public class Vehicle {
	private int id;
	private String vehicleNum;
	private double capacity;
	private VehicleStatus status;
	private Location vehiclePosition;
	
	public Vehicle(int id, String vehicleNum, double capacity) {
		super();
		this.id = id;
		this.vehicleNum = vehicleNum;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	public Location getVehiclePosition() {
		return vehiclePosition;
	}

	public void setVehiclePosition(Location vehiclePosition) {
		this.vehiclePosition = vehiclePosition;
	}
	
	

}
