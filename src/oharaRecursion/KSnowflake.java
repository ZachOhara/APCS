/* Copyright (C) 2014-2015 Zach Ohara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package oharaRecursion;

// This program draws the Koch snowflake fractal.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

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
		int deltaY = (int) Math.round(KSnowflake.SIZE * Math.sqrt(3.0) / 2.0);
		int height = (int) (deltaY + KSnowflake.m * KSnowflake.SIZE);
		DrawingPanel p = new DrawingPanel(KSnowflake.SIZE, height);
		p.setBackground(Color.CYAN);
		Graphics g = p.getGraphics();

		Point p1 = new Point(0, deltaY);
		Point p2 = new Point(KSnowflake.SIZE / 2, 0);
		Point p3 = new Point(KSnowflake.SIZE, deltaY);
		KSnowflake.drawEdge(level, g, p1, p2);
		KSnowflake.drawEdge(level, g, p2, p3);
		KSnowflake.drawEdge(level, g, p3, p1);
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
			int xMid = p1.x + (dx / 2); // coordinates of the midpoint between
			// p1 and p2
			int yMid = p1.y + (dy / 2);
			int xPeak = (int) (xMid + (KSnowflake.m * dy)); // coordinates of the 'peak' of
			// the edge
			int yPeak = (int) (yMid - (KSnowflake.m * dx));

			// each 'stop' on the line (separated by thirds)
			Point stop1 = new Point(p1.x + sx, p1.y + sy);
			Point stop2 = new Point(p2.x - sx, p2.y - sy);
			Point peak = new Point(xPeak, yPeak);

			level--;
			KSnowflake.drawEdge(level, g, p1, stop1);
			KSnowflake.drawEdge(level, g, stop1, peak);
			KSnowflake.drawEdge(level, g, peak, stop2);
			KSnowflake.drawEdge(level, g, stop2, p2);
		}
	}

	/* Select at least 4 of ch12 problems 3, 4, 7, 9, 11, 12, 13, 14 */

}
