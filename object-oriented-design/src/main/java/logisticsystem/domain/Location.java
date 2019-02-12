package logisticsystem.domain;

//Location class simply represents the address.

public class Location { 
  
    private Double longitude; 
    private Double latitude; 
  
    public Double getLongitude() 
    { 
        return longitude; 
    } 
  
    public void setLongitude(Double longitude) 
    { 
        this.longitude = longitude; 
    } 
  
    public Double getLatitude() 
    { 
        return latitude; 
    } 
  
    public void setLatitude(Double latitude) 
    { 
        this.latitude = latitude; 
    } 
} 
