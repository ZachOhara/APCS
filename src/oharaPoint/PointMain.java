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

 package oharaPoint;

public class PointMain {

	public static void main(String[] args) {

		/*
		 * Point p3 = new Point(); System.out.println(p3);
		 *
		 * // create 2 points Point p1 = new Point(7, 2); Point p2 = new Point(4, 3);
		 *
		 * System.out.println("p1 is (" + p1.getX() + "," + p1.getY() + ")");
		 * System.out.println("p2 is (" + p2.getX() + "," + p2.getY() + ")");
		 *
		 * p1.setX(5);
		 *
		 * p1.translate(11, 6);
		 *
		 * System.out.println(p1.distanceFromOrigin());
		 *
		 * System.out.println(p1); System.out.println(p2);
		 */

		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 2);
		Point p3 = new Point(2, 2);

		// testing Point.distance()
		System.out.println(p1.distance(p2)); // should be 1
		System.out.println(p2.distance(p3)); // should be 1
		System.out.println(p1.distance(p3)); // should be sqrt(2)
		System.out.println();

		// testing Point.manhattanDistance()
		System.out.println(p1.manhattanDistance(p2)); // should be 1
		System.out.println(p2.manhattanDistance(p3)); // should be 1
		System.out.println(p1.manhattanDistance(p3)); // should be 2
		System.out.println();

		// testing Point.isVertical()
		System.out.println(p1.isVertical(p2)); // should be true
		System.out.println(p2.isVertical(p3)); // should be false
		System.out.println(p1.isVertical(p3)); // should be false
		System.out.println();

		// testing Point.slope()
		System.out.println(p1.slope(p3)); // should be 1
		System.out.println(p2.slope(p3)); // should be 0

	}

}
