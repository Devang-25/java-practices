package parkinglot;

public class ParkingFloor {
	int rows;
	int cols;
	ParkingSlot[] parkingSlots;
	boolean status[];

	ParkingFloor(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		parkingSlots = new ParkingSlot[rows * cols];
		status = new boolean[rows*cols];
	}
}
