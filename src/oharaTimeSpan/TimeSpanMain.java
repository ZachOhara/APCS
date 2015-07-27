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

 package oharaTimeSpan;

import java.awt.Point;

public class TimeSpanMain {

	public static void main(String[] args) {
		TimeSpan t1 = new TimeSpan(3, 45);
		System.out.println("t1: " + t1 + " is " + t1.getTotalHours() + " hours");

		t1.add(2, 30);
		System.out.println("t1: " + t1 + " is " + t1.getTotalHours() + " hours");

		TimeSpan t2 = new TimeSpan(1, 55);
		System.out.println("t2: " + t2 + " is " + t2.getTotalHours() + " hours");

		t1.add(t2);
		System.out.println("t1 + t2: " + t1 + " is " + t1.getTotalHours() + " hours");

		TimeSpan t3 = new TimeSpan(5, 70);
		System.out.println("t3: " + t3 + " is " + t3.getTotalHours() + " hours");

		TimeSpan t4 = new TimeSpan(5, 70);
		System.out.println("t4: " + t4 + " is " + t4.getTotalHours() + " hours");

		if (t3.equals(t4)) {
			System.out.println("Time " + t3 + " equals Time " + t4);
		} else {
			System.out.println("Time " + t3 + " does not equal Time " + t4);
		}
		if (t1.equals(t2)) {
			System.out.println("Time " + t1 + " equals Time " + t2);
		} else {
			System.out.println("Time " + t1 + " does not equal Time " + t2);
		}
		if (t3.equals("Time")) {
			System.out.println("Time " + t3 + " equals Time");
		} else {
			System.out.println("Time " + t3 + " does not equal Time");
		}

		Point p = new Point(10, 20);
		if (t3.equals(p)) {
			System.out.println("Time " + t3 + " equals " + p);
		} else {
			System.out.println("Time " + t3 + " does not equal " + p);
		}
	}
}
