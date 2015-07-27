/* Copyright (C) 2014-2015 Zach Ohara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
