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

public class ClockMain {

	public static void main(String[] args) {
		ArrayList<ClockTime> times = new ArrayList<ClockTime>();

		times.add(new ClockTime(12, 00, "AM"));

		times.add(new ClockTime(12, 59, "AM"));
		times.add(new ClockTime(1, 00, "AM"));

		times.add(new ClockTime(11, 59, "AM"));
		times.add(new ClockTime(12, 00, "PM"));

		times.add(new ClockTime(12, 59, "PM"));
		times.add(new ClockTime(1, 00, "PM"));

		times.add(new ClockTime(11, 59, "PM"));

		for (int i = 0; i < times.size(); i++) {
			System.out.println("index: " + i + ", time: " + times.get(i));
		}
		System.out.println();

		for (ClockTime time1 : times) {
			for (ClockTime time2 : times) {
				System.out.print(time1 + " compared to " + time2 + " is: ");
				System.out.println(time1.compareTo(time2));
			}
			System.out.println();
		}

	}

}
