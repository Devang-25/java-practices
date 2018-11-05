package test;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class CalendarTest {

	static String getTimeDiff(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		String time = "";
		int hour = cal2.get(Calendar.HOUR) - cal1.get(Calendar.HOUR);
		int min = cal2.get(Calendar.MINUTE) - cal1.get(Calendar.MINUTE);
		if (min < 0) {
			min = 60 - min;
			hour--;
		}

		time += hour < 10 ? "0" + hour : hour;
		time += min < 10 ? ":0" + min : ":" + min;

		return time;

	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance(); // GregorianCalendar
		System.out.println("Calendar's toString() is : " + cal + "\n");
		System.out.println("Time zone is: " + cal.getTimeZone() + "\n");

		// An Easier way to print the timestamp by getting a Date instance
		Date date = cal.getTime();
		System.out.println("Current date and time in Date's toString() is : " + date + "\n");
		
		String diff= getTimeDiff(new Date(), new Date("Fri Jul 27 2018 14:51:51 GMT+0530 (India Standard Time)"));
		System.out.println("------------diff-----"+ diff);
		// Print Calendar's field
		System.out.println("Year  : " + cal.get(Calendar.YEAR));
		System.out.println("Month : " + cal.get(Calendar.MONTH));
		System.out.println("Day of Month : " + cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("Day of Week  : " + cal.get(Calendar.DAY_OF_WEEK));
		System.out.println("Day of Year  : " + cal.get(Calendar.DAY_OF_YEAR));
		System.out.println("Week of Year : " + cal.get(Calendar.WEEK_OF_YEAR));
		System.out.println("Week of Month : " + cal.get(Calendar.WEEK_OF_MONTH));
		System.out.println("Day of the Week in Month : " + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println("Hour  : " + cal.get(Calendar.HOUR));
		System.out.println("AM PM : " + cal.get(Calendar.AM_PM));
		System.out.println("Hour of the Day : " + cal.get(Calendar.HOUR_OF_DAY));
		System.out.println("Minute : " + cal.get(Calendar.MINUTE));
		System.out.println("Second : " + cal.get(Calendar.SECOND));
		System.out.println();

		// Manipulating Dates
		Calendar calTemp;
		calTemp = (Calendar) cal.clone();
		calTemp.add(Calendar.DAY_OF_YEAR, -365);
		System.out.println("365 days ago, it was: " + calTemp.getTime());

		calTemp = (Calendar) cal.clone();
		calTemp.add(Calendar.HOUR_OF_DAY, 11);
		System.out.println("After 11 hours, it will be: " + calTemp.getTime());

		// Roll
		calTemp = (Calendar) cal.clone();
		calTemp.roll(Calendar.HOUR_OF_DAY, 11);
		System.out.println("Roll 11 hours, it will be: " + calTemp.getTime());
		System.out.println();
	}
}
