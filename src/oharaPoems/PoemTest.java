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

public class PoemTest {

	public static void main(String[] args) {
		Haiku h = new Haiku();
		System.out.println(h.numLines());
		h.printRhythm();
		System.out.println();

		Limerick l = new Limerick();
		System.out.println(l.numLines());
		l.printRhythm();
		System.out.println();

		Poem[] poems = {new Haiku(), new Limerick()};
		System.out.println("Poems: Haiku then Limerick");
		for (int i = 0; i < poems.length; i++) {
			poems[i].printRhythm();
			System.out.println();
		}
	}
}
