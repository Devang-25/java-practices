
public class Location {
	Double latitute;
	Double longititude;
	String location;
	Location(double lat, double lon){
		latitute=lat;
		longititude=lon;
		location= latitute.toString()+ ","+ longititude.toString();
	}
	
	double getLatitude() {
		return this.latitute;
	}
	
	double getLongitude() {
		return this.longititude;
	}
}
