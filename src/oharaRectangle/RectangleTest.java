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

 package oharaRectangle;

import oharaPoint.Point;

public class RectangleTest {

	public static void main(String[] args) {
		Rectangle r = new Rectangle(0, 10, 10, 10);
		System.out.println(r);
		System.out.println(r.coordinates());
		System.out.println(r.getX2()); // should be 10
		System.out.println(r.getY2()); // should be 0
		System.out.println(r.contains(5, 5)); // should be true
		System.out.println(r.contains(new Point(1, 9))); // should be true
		Rectangle r2 = new Rectangle(5, 15, 10, 10);
		System.out.println(r2.getX2()); // should be 15
		System.out.println(r2.getY2()); // should be 5
		System.out.println(r2);
		System.out.println(r2.coordinates()); // should be [5,15,15,5]
		System.out.println(r.union(r2).coordinates()); // should be [0, 15, 15,
		// 0]
		System.out.println(r.intersection(r2).coordinates()); // should be
		// [5,10, 5, 10]
	}

}
