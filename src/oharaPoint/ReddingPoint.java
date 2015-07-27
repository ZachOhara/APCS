package oharaPoint;

/* A Point object represented by a pair of
 (x,y) coordinates
 by Mrs. Redding
 */

public class ReddingPoint {

	// instance variable or fields
	private int x;
	private int y;

	public ReddingPoint() {
		this(0, 0);
		// x = 0;
		// y = 0;
	}

	// Constructs a new point at the given (x,y)
	// Precondition: x and y are >= 0
	public ReddingPoint(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("value < 0");
		}
		this.x = x;
		this.y = y;
	}

	// instance method
	// "getter method" - gets an instance variable
	// returns the x value
	// accessor method
	public int getX() {
		return this.x;
	}

	// instance method
	// "getter method" - gets an instance variable
	// returns the y value
	// accessor method
	public int getY() {
		return this.y;
	}

	// instance method
	// "setter method" - sets an instnce variable
	// sets the x value
	// mutator method
	public void setX(int newX) {
		this.x = newX;
	}

	// instance method
	// "setter method" - sets an instnce variable
	// sets the y value
	// mutator method
	public void setY(int newY) {
		this.y = newY;
	}

	// shifts this point's location a given amount
	// precondition: x + dx >= 0; y + dy >= 0;
	// mutator method
	public void translate(int dx, int dy) {
		if (this.x + dx < 0 || this.y + dy < 0) {
			throw new IllegalArgumentException();
		}
		this.x = this.x + dx;
		this.y = this.y + dy;
	}

	// return the distance between this
	// Point and (0,0)
	// accessor method
	public double distanceFromOrigin() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	// returns a String representation of this point
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
}
