package oharaRecursion;

// This program draws the Koch snowflake fractal.

import java.awt.*;
import java.util.*;

public class KSnowflake {
	
	public static final int SIZE = 256;
	public static final double m = Math.sqrt(3.0) / 6.0;

	public static void main(String[] args) {
		// prompt for level
		Scanner console = new Scanner(System.in);
		System.out.print("What level do you want? ");
		int level = console.nextInt();
		console.close();

		// initialize drawing panel
		int deltaY = (int) Math.round(SIZE * Math.sqrt(3.0) / 2.0);
		int height = (int) (deltaY + m * SIZE);
		DrawingPanel p = new DrawingPanel(SIZE, height);
		p.setBackground(Color.CYAN);
		Graphics g = p.getGraphics();

		Point p1 = new Point(0, deltaY);
		Point p2 = new Point(SIZE / 2, 0);
		Point p3 = new Point(SIZE, deltaY);
		drawEdge(level, g, p1, p2);
		drawEdge(level, g, p2, p3);
		drawEdge(level, g, p3, p1);
	}

	// drawEdge recursive method
	public static void drawEdge(int level, Graphics g, Point p1, Point p2) {
		if (level == 1) {
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		} else {
			int dx = p2.x - p1.x;
			int dy = p2.y - p1.y;
			int sx = dx / 3; // the distances to get to each 'stop' on the edge
			int sy = dy / 3;
			int xMid = p1.x + (dx / 2); //coordinates of the midpoint between p1 and p2
			int yMid = p1.y + (dy / 2);
			int xPeak = (int) (xMid + (m * dy)); //coordinates of the 'peak' of the edge
			int yPeak = (int) (yMid - (m * dx));
			
			// each 'stop' on the line (separated by thirds)
			Point stop1 = new Point(p1.x + sx, p1.y + sy);
			Point stop2 = new Point(p2.x - sx, p2.y - sy);
			Point peak = new Point(xPeak, yPeak);
			
			level--;
			drawEdge(level, g, p1, stop1);
			drawEdge(level, g, stop1, peak);
			drawEdge(level, g, peak, stop2);
			drawEdge(level, g, stop2, p2);
		}
	}
	
	/* Select at least 4 of ch12 problems 3, 4, 7, 9, 11, 12, 13, 14 */
	
}