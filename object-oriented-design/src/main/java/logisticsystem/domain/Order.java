package logisticsystem.domain;

import java.util.Date;
import java.util.List;

//The Order class contains all the information of an order. All fields are self-explanatory.

public class Order {
	private int orderId;
	private OrderPriority orderPriority;
	private User sender;
	private Location deliveryAddress;
	private PaymentDetails paymentDetail;
	private List<Item> items;
	private double totalWeight;
	private OrderStatus currentStatus;
	private Date TimeOfOrderPlacement;
	private Date TimeOfOrderDelivery;
	private Vehicle vehicleOfOrder;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public OrderPriority getOrderPriority() {
		return orderPriority;
	}
	public void setOrderPriority(OrderPriority orderPriority) {
		this.orderPriority = orderPriority;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public Location getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(Location deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public PaymentDetails getPaymentDetail() {
		return paymentDetail;
	}
	public void setPaymentDetail(PaymentDetails paymentDetail) {
		this.paymentDetail = paymentDetail;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public OrderStatus getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(OrderStatus currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Date getTimeOfOrderPlacement() {
		return TimeOfOrderPlacement;
	}
	public void setTimeOfOrderPlacement(Date timeOfOrderPlacement) {
		TimeOfOrderPlacement = timeOfOrderPlacement;
	}
	public Date getTimeOfOrderDelivery() {
		return TimeOfOrderDelivery;
	}
	public void setTimeOfOrderDelivery(Date timeOfOrderDelivery) {
		TimeOfOrderDelivery = timeOfOrderDelivery;
	}
	public Vehicle getVehicleOfOrder() {
		return vehicleOfOrder;
	}
	public void setVehicleOfOrder(Vehicle vehicleOfOrder) {
		this.vehicleOfOrder = vehicleOfOrder;
	}
	
	
}
