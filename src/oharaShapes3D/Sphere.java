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
	public String toString() {
		return "Sphere" + super.toString();
	}
	
}
