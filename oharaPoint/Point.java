package oharaPoint;

public class Point extends ReddingPoint {
	
	protected int x;
	protected int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this(0,0);
	}
	
	public double distance(Point other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}
	
	public int manhattanDistance(Point other) {
		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
	}
	
	public boolean isVertical(Point other) {
		return this.x == other.x;
	}
	
	public double slope(Point other) {
		if (this.isVertical(other)) // yess, efficiency!
			throw new IllegalArgumentException("Vertial line: slope is unefined");
		else
			return (double)(this.y - other.y) / (this.x - other.x);
	}
	
	// A simpler way to do this, now that there's more generic code that can be used
	@Override
	public double distanceFromOrigin() {
		return this.distance(new Point()); // put the origin in a point object and take the distance from that;
	}
	
	public Point copyData() {
		return new Point(this.x, this.y);
	}
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
}
