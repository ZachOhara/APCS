package oharaShapes3D;

// An abstract superclass for shapes with a circular cross-section
// that extends over some height

public abstract class CircularShapeWithHeight extends CircularShape {

	private double height;

	// Construct a shape with all dimensions of zero
	public CircularShapeWithHeight() {
		this(0,0);
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
		return height;
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
		return this.getCrossSectionPerimeter() * this.getHeight()
				+ 2 * this.getCrossSectionArea();
	}
	
	// Compare equality of each dimension of two shapes
	public boolean equals(Object other) {
		if (!(other instanceof CircularShapeWithHeight))
			throw new IllegalArgumentException("Compared object must be of type CircularObjectWithHeight");
		else {
			CircularShapeWithHeight cswh = (CircularShapeWithHeight) other;
			return this.getHeight() == cswh.getHeight() && super.equals(cswh);
		}
	}
	
	// Get the String representation of the shape
	public String toString() {
		return super.toString() + " and height of " + String.format("%.1f", this.getHeight());
	}
}
