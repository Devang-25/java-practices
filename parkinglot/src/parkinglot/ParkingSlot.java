package parkinglot;

public class ParkingSlot {
	int floorNumber;
	int row;
	int column;
	boolean status;
	String carId;
	
	ParkingSlot(int floor, int row, int col){
		floorNumber=floor;
		this.row=row;
		column=col;
	}

}
