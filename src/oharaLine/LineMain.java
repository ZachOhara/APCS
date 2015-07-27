package oharaLine;

import oharaPoint.Point;

public class LineMain {
	
	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(10, 10);
		Point p3 = new Point(0, 5);
		
		Line l1 = new Line(p1, p2);
		Line l2 = new Line(p2, p3);
		
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(l1.getP1() + " " + l2.getP2());
		System.out.println();
		
		System.out.println(l1.getLength());
		System.out.println(l2.getLength());
		System.out.println();
		
		System.out.println(l1.getSlope());
		System.out.println(l2.getSlope());
	}
	
}
