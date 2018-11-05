
public class DistanceCalculator {

	static long haversine(Location loc1, Location loc2) {

	    int R = 6371000 ; // radius of Earth in meters
	    double lat1= loc1.getLatitude();
	    double lat2= loc2.getLatitude();
	    double lon1= loc1.getLongitude();
	    double lon2= loc2.getLongitude();
	    
	    double phi_1 = Math.toRadians(lat1);
	    double phi_2 = Math.toRadians(lat2);

	    double delta_phi =  Math.toRadians(lat2 - lat1);
	    double delta_lambda = Math.toRadians(lon2 - lon1);

	    double a = Math.pow( Math.sin(delta_phi / 2.0) , 2 )+ Math.cos(phi_1) * Math.cos(phi_2) * Math.pow(Math.sin(delta_lambda / 2.0), 2);
	    
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    double meters = R * c ; // output distance in meters
	    double km = meters / 1000.0;  // output distance in kilometers

	    long distInMeters= Math.round(meters);
	    return distInMeters;
	 
	}
}
