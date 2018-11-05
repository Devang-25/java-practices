package parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkUtil {
	int floors;
	int startFloorNum;
	int endFloorNum;
	StrategyType st;
	Map<Integer, ParkingFloor> floorSlots;

	ParkingBuilding building;
	Strategy strategy;

	ParkUtil(int floors, int start, int end, StrategyType st, Map<Integer, ParkingFloor> slots) {
		this.floors = floors;
		building = new ParkingBuilding(floors);
		startFloorNum = start;
		endFloorNum = end;
		this.st = st;
		this.floorSlots = slots;
		for (Integer slot : floorSlots.keySet()) {
			building.parkingFloors.add(floorSlots.get(slot));
		}
		if (st == StrategyType.TopToBottom) {
			strategy = new TopToBottomStrategy(startFloorNum, endFloorNum, building);
		} else if (st == StrategyType.BottomToTop) {
			strategy = new TopToBottomStrategy(startFloorNum, endFloorNum, building);
		}

	}

	void parkCar(Vehicle vh) {
		if (st == StrategyType.TopToBottom) {
			strategy.parkMyCar(vh);
		} else if (st == StrategyType.BottomToTop) {
			strategy.parkMyCar(vh);
		}
	}

	public static void main(String[] args) {
		// int slots[]= new int[] {10, 20, 10, 20, 30};
		Map<Integer, ParkingFloor> slots = new HashMap<Integer, ParkingFloor>();
		ParkingFloor f1 = new ParkingFloor(5, 10);
		ParkingFloor f2 = new ParkingFloor(5, 5);
		slots.put(0, f1);
		slots.put(1, f2);
		// slots.put(key, value)
		ParkUtil parkUtil = new ParkUtil(2, 0, 1, StrategyType.TopToBottom, slots);
		Vehicle car = new Vehicle("abcd123", Color.Black, Brand.Hundai);
		parkUtil.parkCar(car);

	}

}
