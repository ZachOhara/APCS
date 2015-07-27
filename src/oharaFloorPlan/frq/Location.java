package oharaFloorPlan.frq;

// Exam 3 Question 4

public class Location {

	private int row, col;
	private int direction;

	public Location(int r, int c) {
		this.row = r;
		this.col = c;
	}

	public Location(int r, int c, int dir) {
		this.row = r;
		this.col = c;
		this.direction = dir;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public int getDirection() {
		return this.direction;
	}

	@Override
	public String toString() {
		return "(" + this.row + ", " + this.col + " | " + this.direction + ")";
	}
}
