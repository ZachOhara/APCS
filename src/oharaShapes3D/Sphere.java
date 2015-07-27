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

/* A Sphere object represents a sphere in 3D space
 */
public class Sphere extends CircularShape {

	// Construct a sphere with a radius of 0
	public Sphere() {
		super();
	}

	// Construct a sphere with the given radius
	public Sphere(double r) {
		super(r);
	}

	// Construct a sphere with the same radius as a given sphere
	public Sphere(Sphere other) {
		super(other);
	}

	// Get the String representation of the sphere
	@Override
	public String toString() {
		return "Sphere" + super.toString();
	}

}
