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

public class OharaRhymeAlliterate {

	public static void main(String[] args) {
		String word1 = "fare";
		String word2 = "flare";
		if (OharaRhymeAlliterate.rhyme(word1, word2)) {
			System.out.println("They rhyme!");
		}
		if (OharaRhymeAlliterate.alliterate(word1, word2)) {
			System.out.println("They alliterate!");
		}
	}

	// Returns true if s1 and s2 end with the same two letters.
	public static boolean rhyme(String s1, String s2) {
		return s1.length() >= 2 && s2.length() >= 2 && s1.endsWith(s2.substring(s2.length() - 2));
	}

	// Returns true if s1 and s2 start with the same letter.
	public static boolean alliterate(String s1, String s2) {
		return s1.length() > 0 && s2.length() > 0 && s1.substring(0, 1).equals(s2.substring(0, 1));
	}
}
