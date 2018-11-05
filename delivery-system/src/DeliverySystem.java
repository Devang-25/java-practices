import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DeliverySystem implements Comparator<DEAssignment> {

	List<Order> Orders;

	public int compare(DEAssignment arg0, DEAssignment arg1)  {
		if (arg0.dist != arg1.dist) {
			return arg0.dist - arg1.dist > 0 ? 1 : -1;
		} else {
			SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");   
			try {
			long time1 = formatter.parse(arg0.lastDeliveredTime).getTime();
			long time2 = formatter.parse(arg1.lastDeliveredTime).getTime();
			
			if (time1 != time2) {
				return time1 - time2 > 0 ? 1 : -1;
			} else {
				long otime1 = new Date(arg0.orderedTime).getTime();
				long otime2 = new Date(arg1.orderedTime).getTime();
				if (time1 != time2) {
					return otime1 - otime2 > 0 ? 1 : -1;
				}
			}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	static ArrayList<Assignment> getListOfAssignments(List<Order> orders, List<DeliveryExecutive> DelExs) {
		// Restaurant id vs DEs location dist list map from restaurant
		Map<Integer, ArrayList<DEAssignment>> orderDEsMap = new HashMap<Integer, ArrayList<DEAssignment>>();

		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		for (Order order : orders) {
			for (DeliveryExecutive de : DelExs) {
				String[] oparts = order.restaurant_location.split(",");
				Location resTLoc = new Location(Double.parseDouble(oparts[0]), Double.parseDouble(oparts[1]));

				String[] dparts = de.current_location.split(",");
				Location deLoc = new Location(Double.parseDouble(dparts[0]), Double.parseDouble(dparts[1]));
				long dist = DistanceCalculator.haversine(resTLoc, deLoc);
				DEAssignment deAss = new DEAssignment(de.id, order.id, dist, de.last_order_delivered_time,
						order.order_time);
				ArrayList<DEAssignment> as = orderDEsMap.get(order.id);
				if (as == null) {
					as = new ArrayList<DEAssignment>();
				}
				as.add(deAss);

				orderDEsMap.put(order.id, as);
			}
		}
		for (Integer order : orderDEsMap.keySet()) {
			ArrayList<DEAssignment> as = orderDEsMap.get(order);
			Collections.sort(as, new DeliverySystem());
			Assignment a = new Assignment(order, as.get(0).deId);
			assignments.add(a);
		}
		return assignments;
	}

	static void print(ArrayList<Assignment> as) {
		for (Assignment a : as) {
			System.out.println(a.orderId + ": " + a.deliveryExecId);
		}
	}

	public static void main(String[] args) {
		List<Order> orders = new ArrayList<Order>();
		String dateS1=  "28-Jul-2018 13:00:00"; 
		String dateS2=  "28-Jul-2018 13:10:00"; 
		Order o1 = new Order("12.9081, 77.6476", dateS1, 123);
		Order o2 = new Order("12.9279, 77.6271", dateS2, 321);
		orders.add(o1);
		orders.add(o2);

		String dateS3=  "28-Jul-2018 13:25:00"; 
		String dateS4=  "28-Jul-2018 13:20:00"; 
		String dateS5=  "28-Jul-2018 13:30:00"; 
		List<DeliveryExecutive> DelExs = new ArrayList<DeliveryExecutive>();
		DeliveryExecutive de1 = new DeliveryExecutive(567, "12.9081, 77.6400", dateS4);
		DeliveryExecutive de2 = new DeliveryExecutive(765, "12.9081, 77.6400", dateS5);
		DeliveryExecutive de3 = new DeliveryExecutive(766, "12.9081, 77.6400", dateS5);
		DelExs.add(de1);
		DelExs.add(de2);
		DelExs.add(de3);
		ArrayList<Assignment> assignments = getListOfAssignments(orders, DelExs);
		print(assignments);
	}

}

class DEAssignment {
	int deId;
	int orderId;
	long dist;
	String lastDeliveredTime;
	String orderedTime;

	DEAssignment() {

	}

	DEAssignment(int deId, int orderId, long dist, String lastDeliveredTime, String orderedTime) {
		this.deId = deId;
		this.orderId = orderId;
		this.dist = dist;
		this.lastDeliveredTime = lastDeliveredTime;
		this.orderedTime = orderedTime;
	}
}
