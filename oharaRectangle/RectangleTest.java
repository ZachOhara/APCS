package oharaRectangle;

import oharaPoint.Point;

public class RectangleTest {

	public static void main(String[] args) {
		Rectangle r = new Rectangle(0, 10, 10, 10);
		System.out.println(r);
		System.out.println(r.coordinates());
		System.out.println(r.getX2()); // should be 10
		System.out.println(r.getY2()); // should be 0
		System.out.println(r.contains(5,5)); // should be true
		System.out.println(r.contains(new Point(1, 9))); // should be true
		Rectangle r2 = new Rectangle(5, 15, 10, 10);
		System.out.println(r2.getX2()); // should be 15
		System.out.println(r2.getY2()); // should be 5
		System.out.println(r2);
		System.out.println(r2.coordinates()); // should be [5,15,15,5]
		System.out.println(r.union(r2).coordinates()); // should be [0, 15, 15, 0]
		System.out.println(r.intersection(r2).coordinates()); // should be [5,10, 5, 10]
	}

}
