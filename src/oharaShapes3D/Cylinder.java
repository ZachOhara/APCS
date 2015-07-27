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

/* A Cylinder object represents a cylinder in 3D space
 */

public class Cylinder extends CircularShapeWithHeight {

	// Construct a cylinder with radius and height of zero
	public Cylinder() {
		super();
	}

	// Construct a cylinder with a given radius and height
	public Cylinder(double r, double h) {
		super(r, h);
	}

	// Construct a cylinder with the same dimensions as the given cyclinder
	public Cylinder(Cylinder other) {
		super(other);
	}

	// Get the String representation of the cylinder
	@Override
	public String toString() {
		return "Cylinder" + super.toString();
	}

}
