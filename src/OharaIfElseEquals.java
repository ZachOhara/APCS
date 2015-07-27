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

 import java.util.Scanner;

public class OharaIfElseEquals {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		OharaIfElseEquals.printPalindrome(console);
		System.out.println(OharaIfElseEquals.wordCount("hello"));
		System.out.println(OharaIfElseEquals.wordCount("how are you?"));
		System.out.println(OharaIfElseEquals.wordCount(" this string has wide spaces "));
		System.out.println(OharaIfElseEquals.wordCount(" "));
	}

	public static void printPalindrome(Scanner console) {
		String s = console.nextLine().toLowerCase();
		String str = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			str += s.substring(i, i + 1).toLowerCase();
		}
		System.out.println(s.equals(str) ? "It's a palindrome!" : "It's not a palindrome...");
	}

	public static int wordCount(String str) {
		if (str.length() == 0 || (str.length() == 1 && str.substring(0, 1).equals(" "))) {
			return 0;
		}
		while (str.substring(0, 1).equals(" ")) {
			str = str.substring(1);
		}
		while (str.substring(str.length() - 1).equals(" ")) {
			str = str.substring(0, str.length() - 1);
		}
		int spaces = 0;
		int i = 0;
		while (i < str.length()) {
			while (str.substring(i, i + 1).equals(" ")) {
				spaces++;
				i++;
				if (i == str.length()) {
					break;
				}
			}
			i++;
		}
		return spaces + 1;
	}

}
