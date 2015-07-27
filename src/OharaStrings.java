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

 public class OharaStrings {

	public static void main(String[] args) {
		// printStrings and vertical methods
		LastnameStrings.main(null);
		// padString
		System.out.println(OharaStrings.padString("string", 10));
		System.out.println();
		// printReverse
		OharaStrings.printReverse("hello there!");
	}

	public static String padString(String str, int length) {
		// In this method, I was unclear about where exactly the padding should
		// go
		// I put it on the end, but I may have been wrong
		while (str.length() < length) {
			str += "_";
		}
		return str;
	}

	public static void printReverse(String str) {
		for (int i = str.length() - 1; i >= 0; i--) {
			System.out.print(str.substring(i, i + 1));
		}
		System.out.println();
	}
}

// All code below this point was copied directly from the in-class code
// The only change that has been made is that the class is not public
class LastnameStrings {

	public static void main(String[] args) {
		// printStrings
		LastnameStrings.printStrings("abc", 5);
		System.out.println();
		// Exercise 3.12: printVertical
		String s = "hey now";
		LastnameStrings.vertical(s);
		System.out.println();
	}

	// printStrings
	public static void printStrings(String s, int repeat) {
		for (int i = 1; i <= repeat; i++) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

	// Exercise 3.12: vertical
	public static void vertical(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.substring(i, i + 1));
		}
	}

}
