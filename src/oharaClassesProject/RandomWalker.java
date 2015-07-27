package oharaClassesProject;

public class RandomWalker {

	private int x = 0;
	private int y = 0;
	private int steps = 0;

	public RandomWalker(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public RandomWalker() {
		// default constructor
	}

	public void move() {
		this.steps++;
		switch ((int) (Math.random() * 4)) {
			case 0:
				this.x++;
				break;
			case 1:
				this.x--;
				break;
			case 2:
				this.y++;
				break;
			case 3:
				this.y--;
				break;
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getSteps() {
		return this.steps;
	}

	@Override
	public String toString() {
		return "A toString method wasn't on the assignment, and I don't feel like writing one";
	}
}
