package oharaPoint;

public class PointMain {

	public static void main(String[] args) {

		/*
		 * Point p3 = new Point(); System.out.println(p3);
		 *
		 * // create 2 points Point p1 = new Point(7, 2); Point p2 = new Point(4, 3);
		 *
		 * System.out.println("p1 is (" + p1.getX() + "," + p1.getY() + ")");
		 * System.out.println("p2 is (" + p2.getX() + "," + p2.getY() + ")");
		 *
		 * p1.setX(5);
		 *
		 * p1.translate(11, 6);
		 *
		 * System.out.println(p1.distanceFromOrigin());
		 *
		 * System.out.println(p1); System.out.println(p2);
		 */

		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 2);
		Point p3 = new Point(2, 2);

		// testing Point.distance()
		System.out.println(p1.distance(p2)); // should be 1
		System.out.println(p2.distance(p3)); // should be 1
		System.out.println(p1.distance(p3)); // should be sqrt(2)
		System.out.println();

		// testing Point.manhattanDistance()
		System.out.println(p1.manhattanDistance(p2)); // should be 1
		System.out.println(p2.manhattanDistance(p3)); // should be 1
		System.out.println(p1.manhattanDistance(p3)); // should be 2
		System.out.println();

		// testing Point.isVertical()
		System.out.println(p1.isVertical(p2)); // should be true
		System.out.println(p2.isVertical(p3)); // should be false
		System.out.println(p1.isVertical(p3)); // should be false
		System.out.println();

		// testing Point.slope()
		System.out.println(p1.slope(p3)); // should be 1
		System.out.println(p2.slope(p3)); // should be 0

	}

}
