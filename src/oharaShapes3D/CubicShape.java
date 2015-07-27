package oharaShapes3D;

/* The CubicShape class is an abstract class that contains fields and methods
 * that are used for all shapes that are defined in terms of length, width,
 * and height.
 */
public abstract class CubicShape extends Shape {

	// The length of the shape
	private double length;
	// The width of the shape
	private double width;
	// The height of the shape
	private double height;

	// Default constructor will create a shape with all zero dimensions
	public CubicShape() {
		this(0, 0, 0);
	}

	// Construct a cubic shape
	public CubicShape(double side) {
		this(side, side, side);
	}

	// Construct a shape with a square base and unique height
	public CubicShape(double side, double height) {
		this(side, side, height);
	}

	// Construct a shape with unique length, width, and height
	public CubicShape(double l, double w, double h) {
		this.length = l;
		this.width = w;
		this.height = h;
	}

	// Construct a shape with the same dimensions as the given object
	public CubicShape(CubicShape other) {
		this(other.getLength(), other.getWidth(), other.getHeight());
	}

	// Get the length of the shape
	public double getLength() {
		return this.length;
	}

	// Get the width of the shape
	public double getWidth() {
		return this.width;
	}

	// Get the height of the shape
	public double getHeight() {
		return this.height;
	}

	// Set the length of the shape
	public void setLength(double newLength) {
		this.length = newLength;
	}

	// Set the width of the shape
	public void setWidth(double newWidth) {
		this.width = newWidth;
	}

	// Set the height of the shape
	public void setHeight(double newHeight) {
		this.height = newHeight;
	}

	// Set all the dimensions of the shape to a single value
	public void setAllDimensions(double newDimension) {
		this.setLength(newDimension);
		this.setHeight(newDimension);
		this.setWidth(newDimension);
	}

	// Set the length and width only to a given value
	public void setSideLength(double newSide) {
		this.setLength(newSide);
		this.setWidth(newSide);
	}

	// Get the volume of the shape, assuming it's a rectangular prism
	@Override
	public double getVolume() {
		return this.length * this.width * this.height;
	}

	// Get the surface area of the shape, assuming it's a rectangular prism
	@Override
	public double getSurfaceArea() {
		return (2 * this.length * this.width) + (2 * this.length * this.height)
				+ (2 * this.width * this.height);
	}

	/*
	 * Commented: method in superclass works better // Compare equality of each dimension
	 * of two shapes public boolean equals(Object other) { if (!(other instanceof
	 * CubicShape)) throw new
	 * IllegalArgumentException("Compared object must be a CubicShape"); else { CubicShape
	 * cs = (CubicShape) other; return this.getLength() == cs.getLength() &&
	 * this.getWidth() == cs.getWidth() && this.getHeight() == cs.getHeight(); } }
	 */

	// Get the String representation of the object
	@Override
	public String toString() {
		return " with" + this.lengthToString() + "," + this.widthToString() + this.heightToString();
	}

	// Get the String representation of the length of the object
	public String lengthToString() {
		return " length of " + String.format("%.1f", this.getLength());
	}

	// Get the String representation of the width of the object
	public String widthToString() {
		return " width of " + String.format("%.1f", this.getWidth());
	}

	// Get the String representation of the height of the object
	public String heightToString() {
		return " and height of " + String.format("%.1f", this.getHeight());
	}

}
