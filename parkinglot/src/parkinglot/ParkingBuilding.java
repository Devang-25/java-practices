package parkinglot;

import java.util.ArrayList;

public class ParkingBuilding {
	int floors;
	ArrayList<ParkingFloor> parkingFloors;
	
	ParkingBuilding(int floors){
		this.floors= floors;
		parkingFloors= new ArrayList<ParkingFloor>();
	}

}
