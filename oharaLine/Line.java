package oharaLine;

import oharaPoint.Point;

public class Line {
	
	private Point p1;
	private Point p2;
	
	// Constructs a new line from the given two points
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	// Constructs a new line from the four given integers, representing two two-dimentional points
	public Line(int x1, int y1, int x2, int y2) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
	}
	
	// Default constructor: constructs a line going from the origin to the origin
	public Line() {
		this.p1 = new Point();
		this.p2 = new Point();
	}
	
	// Return the line's first endpoint
	public Point getP1() {
		return this.p1;
	}
	
	// Return the line's second endpoint
	public Point getP2() {
		return this.p2;
	}
	
	// Returns a string representing a line
	public String toString() {
		return "[" + this.p1 + "," + this.p2 + "]";
	}
	
	// Returns the slope of the line
	public Double getSlope() {
		if (p1.isVertical(p2))
			return null;
		else
			return p1.slope(p2);
	}
	
	// Returns the length of the line
	public double getLength() {
		return p1.distance(p2);
	}
}
