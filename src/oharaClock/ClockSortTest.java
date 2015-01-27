package oharaClock;

import java.util.ArrayList;
import java.util.Collections;

public class ClockSortTest {

	public static void main(String[] args) {
		
		ArrayList<ClockTime> timelist = new ArrayList<ClockTime>();
		timelist.add(new ClockTime(12, 0, "am"));
		timelist.add(new ClockTime(11, 59, "pm"));
		timelist.add(new ClockTime(11, 58, "pm"));
		timelist.add(new ClockTime(3, 0, "pm"));
		timelist.add(new ClockTime(10, 0, "am"));
		timelist.add(new ClockTime(4, 0, "am"));
		
		System.out.println("Order before sorting:");
		System.out.println(timelist);
		System.out.println();
		
		Collections.sort(timelist);
		
		System.out.println("Order after sorting:");
		System.out.println(timelist);
		
	}

}
