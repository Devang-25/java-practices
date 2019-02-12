package logisticsystem.domain;


//The bike has only 10 unit of capacity.
public class Bike extends Vehicle { 
	  
    private final static int capacityofBike = 10; 
  
    public Bike(int id, String vehicleNo) 
    { 
  
        super(id, vehicleNo, capacityofBike); 
    } 
} 
