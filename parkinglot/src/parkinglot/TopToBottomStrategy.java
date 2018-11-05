package parkinglot;

public class TopToBottomStrategy extends Strategy {

	int startFloor;
	int endFloor;
	ParkingSlot emptySlot;
	ParkingBuilding building;

	public TopToBottomStrategy(int startFloorNum, int endFloorNum, ParkingBuilding building) {
		startFloor = startFloorNum;
		endFloor = endFloorNum;
		this.building= building;
		emptySlot=  this.building.parkingFloors.get(0).parkingSlots[0];
		
	}

	void parkMyCar(Vehicle vehicle) {
		
		
	}
	
	

}
