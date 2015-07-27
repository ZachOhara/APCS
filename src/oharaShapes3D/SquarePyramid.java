package oharaShapes3D;

/* A SquarePyramid object represents a Shape in 3D space with a square base, and
 * four iscoseles triangles extending from the sides of the base and coming to a
 * point at the top.
 */

public class SquarePyramid extends CubicShape {

	// Construct a pyramid with all dimensions of zero
	public SquarePyramid() {
		super();
	}

	// Construct a pyramid with a given base side length and a height
	public SquarePyramid(double side, double height) {
		super(side, height);
	}

	// Construct a pyramid with the same dimensions as another pyramid
	public SquarePyramid(SquarePyramid other) {
		super(other);
	}

	/*
	 * The setLength and setWidth methods are all overridden from CubicShape so that they
	 * can be redirected to the method setSideLength. This way, updating either base
	 * dimension individually will retain the square nature of the base of the pyramid.
	 */
	@Override
	public void setLength(double newLength) {
		this.setSideLength(newLength);
	}

	@Override
	public void setWidth(double newWidth) {
		this.setSideLength(newWidth);
	}

	// Get the volume of the pyramid
	// It is one-third the volume of the rectangular shape the pyramid occupies
	@Override
	public double getVolume() {
		return super.getVolume() / 3;
	}

	// Get the surface area of the pyramid
	@Override
	public double getSurfaceArea() {
		return this.getLength()
				* (this.getLength() + Math.sqrt(Math.pow(this.getLength(), 2) + 4
						* Math.pow(this.getHeight(), 2)));
	}

	// Get the String representation of the pyramid
	@Override
	public String toString() {
		return "Square Pyramid with base" + this.lengthToString() + this.heightToString();
	}

}
