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

/* A RectangularPrism object represents a shape in 3D space that is composed
 * of a unique length, width, and height.
 */

public class RectangularPrism extends CubicShape {

	// Construct a prism will all sides of zero
	public RectangularPrism() {
		super();
	}

	// Construct a prism with given length, width, and height
	public RectangularPrism(double l, double w, double h) {
		super(l, w, h);
	}

	// Construct a prism with the same dimensions as a given prism
	public RectangularPrism(RectangularPrism other) {
		super(other);
	}

	// Get the String representation of the prism
	@Override
	public String toString() {
		return "Rectanglular Prism" + super.toString();
	}

}
