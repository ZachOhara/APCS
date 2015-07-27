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

// An abstract superclass for shapes with a circular cross-section
// that extends over some height

public abstract class CircularShapeWithHeight extends CircularShape {

	private double height;

	// Construct a shape with all dimensions of zero
	public CircularShapeWithHeight() {
		this(0, 0);
	}

	// Construct a shape with a given radius and height
	public CircularShapeWithHeight(double radius, double height) {
		super(radius);
		this.height = height;
	}

	// Construct a shape with the same dimensions as the given shape
	public CircularShapeWithHeight(CircularShapeWithHeight other) {
		this(other.getRadius(), other.getHeight());
	}

	// Get the height of the shape
	public double getHeight() {
		return this.height;
	}

	// Set the height of the shape
	public void setHeight(double newHeight) {
		this.height = newHeight;
	}

	// Get the volume of the shape, assuming the shape is cylindrical
	@Override
	public double getVolume() {
		return this.getCrossSectionArea() * this.getHeight();
	}

	// Get the suraface area of the smape, assuming the shape is cylindrical
	@Override
	public double getSurfaceArea() {
		return this.getCrossSectionPerimeter() * this.getHeight() + 2 * this.getCrossSectionArea();
	}

	/*
	 * Commented: method in superclass works better // Compare equality of each dimension
	 * of two shapes public boolean equals(Object other) { if (!(other instanceof
	 * CircularShapeWithHeight)) throw new IllegalArgumentException(
	 * "Compared object must be of type CircularObjectWithHeight"); else {
	 * CircularShapeWithHeight cswh = (CircularShapeWithHeight) other; return
	 * this.getHeight() == cswh.getHeight() && super.equals(cswh); } }
	 */

	// Get the String representation of the shape
	@Override
	public String toString() {
		return super.toString() + " and height of " + String.format("%.1f", this.getHeight());
	}
}
