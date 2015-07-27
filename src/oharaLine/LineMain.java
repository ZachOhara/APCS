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

 package oharaLine;

import oharaPoint.Point;

public class LineMain {

	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(10, 10);
		Point p3 = new Point(0, 5);

		Line l1 = new Line(p1, p2);
		Line l2 = new Line(p2, p3);

		System.out.println(l1);
		System.out.println(l2);
		System.out.println(l1.getP1() + " " + l2.getP2());
		System.out.println();

		System.out.println(l1.getLength());
		System.out.println(l2.getLength());
		System.out.println();

		System.out.println(l1.getSlope());
		System.out.println(l2.getSlope());
	}

}
