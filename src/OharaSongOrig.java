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

public class OharaSongOrig {

	static String[] objects = {"maiden all forlorn,", "cow with the crumpled horn,", "dog,", "cat,", "rat,",
		"malt,", "house that Jack built."};

	static String[] verbs = {"", "milked", "tossed", "worried", "killed", "ate", "lay in"};

	static int totalVerses = 7;

	static void verse(int verseNum) {
		System.out.println("This is the " + OharaSongOrig.objects[OharaSongOrig.totalVerses - verseNum]);
		for (int item = verseNum - 1; item > 0; item--) {
			System.out.println("That " + OharaSongOrig.verbs[OharaSongOrig.totalVerses - item] + " the "
					+ OharaSongOrig.objects[OharaSongOrig.totalVerses - item]);
		}
	}

	public static void main(String[] args) {
		for (int verse = 1; verse <= OharaSongOrig.totalVerses; verse++) {
			OharaSongOrig.verse(verse);
			System.out.println();
		}

	}

}
