package parkinglot_v2;

import java.util.ArrayList;

enum Order{
	TopToBottom, BottomToTop;
}
public class ParkingUtil {
	int floors;
	int[] floorSlots;
	int[] freeSlots;
	ArrayList<Integer> parkingSlots;
	Order order;
	ParkingUtil(int floors, int [] floorSlots , Order order){
		this.floors= floors;
		this.floorSlots= floorSlots;
		this.freeSlots= floorSlots; 
		this.order= order;
	}
	
	void parkCar(Vehicle v) {
		if(order== Order.TopToBottom) {
			int i= floors-1;
			for(; i>=0;i--) {
				if(freeSlots[i]>0) {
					freeSlots[i] -=1;
					System.out.println("parked at floor: "+ i);
					break;
				}
			}
			if(i<0) {
				System.out.println("Parking is full");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int floors=4;
		int[] slots= {10,20,20,30};
		ParkingUtil p= new ParkingUtil(floors, slots, Order.TopToBottom);
		Vehicle v= new Vehicle("B123", Color.Black, Brand.Hundai);
		p.parkCar(v);
	
	}

}
