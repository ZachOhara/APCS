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

public class Line {

	private Point p1;
	private Point p2;

	// Constructs a new line from the given two points
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	// Constructs a new line from the four given integers, representing two
	// two-dimentional points
	public Line(int x1, int y1, int x2, int y2) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
	}

	// Default constructor: constructs a line going from the origin to the
	// origin
	public Line() {
		this.p1 = new Point();
		this.p2 = new Point();
	}

	// Return the line's first endpoint
	public Point getP1() {
		return this.p1;
	}

	// Return the line's second endpoint
	public Point getP2() {
		return this.p2;
	}

	// Returns a string representing a line
	@Override
	public String toString() {
		return "[" + this.p1 + "," + this.p2 + "]";
	}

	// Returns the slope of the line
	public Double getSlope() {
		if (this.p1.isVertical(this.p2)) {
			return null;
		} else {
			return this.p1.slope(this.p2);
		}
	}

	// Returns the length of the line
	public double getLength() {
		return this.p1.distance(this.p2);
	}
}
