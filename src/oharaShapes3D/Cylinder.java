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
