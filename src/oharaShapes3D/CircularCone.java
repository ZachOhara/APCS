package oharaShapes3D;

/* A CircularCone object represents a cone in 3D space
 */

public class CircularCone extends CircularShapeWithHeight {

	// Construct a cone with radius and height of zero
	public CircularCone() {
		super();
	}

	// Construct a cone with a given radius and height
	public CircularCone(double radius, double height) {
		super(radius, height);
	}

	// Construct a cone with the same dimensions as the given cone
	public CircularCone(CircularCone other) {
		super(other);
	}

	// Get the volume of the cone
	@Override
	public double getVolume() {
		return super.getVolume() / 3;
	}

	// Get the surface area of the cone
	@Override
	public double getSurfaceArea() {
		return Math.PI * this.getRadius()
				* Math.sqrt(Math.pow(this.getRadius(), 2) + Math.pow(this.getHeight(), 2))
				+ this.getCrossSectionArea();
	}

	// Get the String represntation of the cone
	@Override
	public String toString() {
		return "Circular Cone" + super.toString();
	}

}
