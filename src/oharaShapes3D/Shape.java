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

 package oharaShapes3D;

/* The shape class is a generic class that all other shapes are indirectly derived
 * from. It only contains methods implemented from Comparable<>, and it passes on
 * the implementation of the methods in Shape3D
 */
public abstract class Shape implements Shape3D, Comparable<Shape> {

	@Override
	public int compareTo(Shape other) {
		return Shape.compare(this, other);
	}

	public static int compare(Shape a, Shape b) {
		double aVol = a.getVolume();
		double bVol = b.getVolume();
		if (aVol < bVol) {
			return -1;
		} else if (aVol > bVol) {
			return 1;
		} else {
			double aArea = a.getSurfaceArea();
			double bArea = b.getSurfaceArea();
			if (aArea < bArea) {
				return -1;
			} else if (aArea > bArea) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public boolean equals(Object other) {
		if ( !(other instanceof Shape)) {
			throw new IllegalArgumentException("Compared object must be of type Shape");
		} else {
			return this.compareTo((Shape) other) == 0;
		}
	}

}
