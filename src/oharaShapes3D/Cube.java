package oharaShapes3D;

/* A Cube object represents a cube in 3D space, with identical length, width, and height
 */

public class Cube extends CubicShape {

	// Construct a cube with side length of zero
	public Cube() {
		super();
	}

	// Construct a cube with a given side length
	public Cube(double side) {
		super(side);
	}

	// Construct a cube with the same side length as another cube
	public Cube(Cube other) {
		super(other);
	}

	/*
	 * The setLength, setWidth, and setHeight methods are all overridden from CubicShape so
	 * that they can be redirected to the method setAllDimensions. This way, updating any
	 * dimension individually will retain the cubic nature of the shape.
	 */
	@Override
	public void setLength(double newLength) {
		this.setAllDimensions(newLength);
	}

	@Override
	public void setWidth(double newWidth) {
		this.setAllDimensions(newWidth);
	}

	@Override
	public void setHeight(double newHeight) {
		this.setAllDimensions(newHeight);
	}

	// Get the String representation of the object
	@Override
	public String toString() {
		return "Cube with length, width" + this.heightToString();
		// this.heightToString() will evaluate to " and height of " +
		// this.getHeight()
	}

}
