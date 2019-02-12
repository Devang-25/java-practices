package logisticsystem.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import logisticsystem.domain.Location;
import logisticsystem.domain.Order;
import logisticsystem.domain.OrderStatus;
import logisticsystem.domain.User;
import logisticsystem.domain.Vehicle;


//The main class (LogisticsSystem) which stores all the information of users, orders and vehicles. It has the methods like takeAnOrder(), processOrder() etc.
public class LogisticsSystem {
	
	private List<Order> orders;
	private List<Vehicle> vehicles;
	private List<User> users;
	
	public LogisticsSystem() {
		this.orders = new ArrayList<Order>();
		this.vehicles = new ArrayList<Vehicle>();
		this.users = new ArrayList<User>();
	}
	public LogisticsSystem(List<Order> orders, List<Vehicle> vehicles, List<User> users) {
		super();
		this.orders = orders;
		this.vehicles = vehicles;
		this.users = users;
	}
	
	public void takeAnOrder(Order order) {
		orders.add(order);
	}
	
	public void processOrder(Order order) {
		
	}
	
	public void cancelOrder(Order order) {
		Order od= orders.stream().filter(o->order.getOrderId()== o.getOrderId()).collect(Collectors.toList()).get(0);
		od.setCurrentStatus(OrderStatus.CANCELLED);
	}
	
	public Location trackAnOrder(Order order) {
		return order.getVehicleOfOrder().getVehiclePosition();
	}
	
	public void registerNewUser(User user) {
		users.add(user);
	}

	public void registerNewVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
