package oharaRectangle;

import oharaPoint.Point;

/* Constructs a new Rectangle whose top-left corner is specified by the given coordinates
 * and with the given width and height. Throw an IllegalArgumentException on a negative
 * width or height.
 */

public class Rectangle {
	
	private int x = 0; // left
	private int y = 0; // top
	private int width = 0;
	private int height = 0;
	private int x2 = 0; // right
	private int y2 = 0; // bottom
	
	public Rectangle(int x, int y, int width, int height) {
		if (width < 0 || height < 0)
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.x2 = x + width;
		this.y2 = y - height;
	}
	
	public Rectangle(Point p, int width, int height) {
		this(p.getX(), p.getY(), width, height);
	}
	
	// Default constructor
	public Rectangle() {
		// variables are already initialized
	}
	
	// Returns this rectangle's height
	public int getHeight() {
		return this.height;
	}
	
	// Returns this rectangle's width
	public int getWidth() {
		return this.width;
	}
	
	// Returns this rectangle's x-coordinate
	public int getX() {
		return this.x;
	}
	
	// Returns this rectangle's y-coordinate
	public int getY() {
		return this.y;
	}
	
	// Returns this rectangle's second x-coordinate
	public int getX2() {
		return this.x2;
	}
	
	// Returns this rectangle's second y-coordinate
	public int getY2() {
		return this.y2;
	}
	
	// Sets this rectangle's height
	public void setHeight(int height) {
		this.height = height;
	}
	
	// Sets this rectangle's width
	public void setWidth(int width) {
		this.width = width;
	}
	
	// Sets this rectangle's x-coordinate
	public void setX(int x) {
		this.x = x;
	}
	
	// Sets this rectangle's y-coordinate
	public void setY(int y) {
		this.y = y;
	}
	
	// Returns whether the given coordinates lie inside the bounds of this
	// rectangle
	public boolean contains(int x, int y) {
		return (this.x < x && x < this.x2 && this.y2 < y && y < this.y);
	}
	
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}
	
	// Returns the tighest possible bounding box that fully contains this
	// rectangle and the given rectangle
	public Rectangle union(Rectangle r) {
		int unX1 = Math.min(this.x, r.x); // left bound
		int unY1 = Math.max(this.y, r.y); // upper bound
		int unX2 = Math.max(this.x2, r.x2); // right bound
		int unY2 = Math.min(this.y2, r.y2); // lower bound
		return new Rectangle(unX1, unY1, unX2 - unX1, unY1 - unY2);
	}
	
	// Returns a new rectangle representing the area occupied by both this
	// rectangle and the given rectangle
	public Rectangle intersection(Rectangle r) {
		int unX1 = Math.max(this.x, r.x); // left bound
		int unY1 = Math.min(this.y, r.y); // upper bound
		int unX2 = Math.min(this.x2, r.x2); // right bound
		int unY2 = Math.max(this.y2, r.y2); // lower bound
		try {
			return new Rectangle(unX1, unY1, unX2 - unX1, unY1 - unY2);
		} catch (IllegalArgumentException e) {
			// the two rectangles given do not intersect at all
			return null;
		}
	}
	
	// Returns a String representation of this rectangle
	public String toString() {
		return "Rectangle [x=" + this.x + ", y=" + this.y + ", width="
				+ this.width + ", height=" + this.height + "]";
	}
	
	public String coordinates() {
		return "[" + this.x + ", " + this.y + ", " + this.x2 + ", " + this.y2
				+ "]";
	}
}
