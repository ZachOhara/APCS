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
