package oharaShapes3D;

// An abstract superclass for shapes with a circular cross-section

public abstract class CircularShape extends Shape {
	
	// The radius of the shape
	private double radius;
	
	// Construct a shape with a radius of zero
	public CircularShape() {
		this(0);
	}
	
	// Construct a shape with a given radius
	public CircularShape(double radius) {
		this.radius = radius;
	}
	
	// Construct a shape with the same radius as the given shape
	public CircularShape(CircularShape other) {
		this(other.getRadius());
	}
	
	// Get the radius of the shape
	public double getRadius() {
		return radius;
	}
	
	// Get the diameter of the shape
	public double getDiameter() {
		return 2 * radius;
	}
	
	// Set the radius of the shape
	public void setRadius(double newRadius) {
		this.radius = newRadius;
	}
	
	// Get the area of the cross-section area of the shape
	public double getCrossSectionArea() {
		return Math.PI * radius * radius;
	}
	
	// Get the perimeter of the cross-section of the shape
	public double getCrossSectionPerimeter() {
		return Math.PI * getDiameter();
	}
	
	// Get the volume of the shape, assuming the shape is a sphere
	@Override
	public double getVolume() {
		return 4 * Math.PI * Math.pow(this.getRadius(), 3) / 3;
	}
	
	// Get the surface area of the shape, assuming the shape is a sphere
	@Override
	public double getSurfaceArea() {
		return 4 * this.getCrossSectionArea();
	}
	
	/*
	 * Commented: method in superclass works better // Compare equality of each
	 * dimension of two shapes public boolean equals(Object other) { if (!(other
	 * instanceof CircularShape)) throw new
	 * IllegalArgumentException("Compared object must be of type CircularShape"
	 * ); else { CircularShape cs = (CircularShape) other; return
	 * this.getRadius() == cs.getRadius(); } }
	 */
	
	// Get the String representation of the object
	public String toString() {
		return " with radius of " + String.format("%.1f", this.getRadius());
	}
}
