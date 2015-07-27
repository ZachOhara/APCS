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

 package oharaClassesProject;

import java.awt.Color;
import java.awt.Graphics;

public class RandomWalkerTest {

	public static void main(String[] args) {
		DrawingPanel p = new DrawingPanel(1000, 800);
		p.setBackground(Color.BLACK);
		Graphics g = p.getGraphics();
		g.setColor(Color.RED);
		g.drawString("Random Walker", 500, 50);
		RandomWalker henry = new RandomWalker(500, 400);
		for (int i = 0; i < 150000; i++) {
			henry.move();
			int x = henry.getX();
			int y = henry.getY();
			g.fillRect(x, y, 1, 1);
			p.sleep(1);
		}
		System.out.println("Total Steps = " + henry.getSteps() + ".");
		System.out.println(henry);
	}
}
