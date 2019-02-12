package logisticsystem.domain;

//The truck has only 100 unit of capacity (10 times more than bike).

public class Truck extends Vehicle { 
  
    private final static int capacityofTruck = 100; 
  
    public Truck(int id, String vehicleNo) 
    { 
        super(id, vehicleNo, capacityofTruck); 
    } 
} 
