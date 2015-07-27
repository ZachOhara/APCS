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
