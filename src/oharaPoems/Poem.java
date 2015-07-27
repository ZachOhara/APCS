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

 package oharaPoems;

public abstract class Poem {

	// returns the number of lines in a poem
	public abstract int numLines();

	// returns the number of syllables in the nth line
	public abstract int getSyllables(int n);

	// shows the rythm of the poem
	public void printRhythm() {
		if (this instanceof Haiku) {
			System.out.println("A haiku is 3 lines of 5, 7, 5 syllables");
		} else if (this instanceof Limerick) {
			System.out.println("A limerick is 5 lines of 9, 9, 6, 6, 9 syllables");
		}
	}

}
