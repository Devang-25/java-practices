package parkinglot;

public class BottomToTopStrategy  extends Strategy{
	int startFloor;
	int endFloor;

	public BottomToTopStrategy(int startFloorNum, int endFloorNum) {
		startFloor = startFloorNum;
		endFloor = endFloorNum;
	}


	@Override
	void parkMyCar(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}
}
