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

public class Point extends ReddingPoint {

	protected int x;
	protected int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
		this(0, 0);
	}

	public double distance(Point other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}

	public int manhattanDistance(Point other) {
		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
	}

	public boolean isVertical(Point other) {
		return this.x == other.x;
	}

	public double slope(Point other) {
		if (this.isVertical(other)) {
			throw new IllegalArgumentException("Vertial line: slope is unefined");
		} else {
			return (double) (this.y - other.y) / (this.x - other.x);
		}
	}

	// A simpler way to do this, now that there's more generic code that can be
	// used
	@Override
	public double distanceFromOrigin() {
		return this.distance(new Point()); // put the origin in a point object
		// and take the distance from that;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	public Point copyData() {
		return new Point(this.x, this.y);
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
