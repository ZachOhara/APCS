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

public class OharaScanner {

	public static void main(String[] args) {
		OharaScanner.inputBirthday();
		OharaScanner.processName(new Scanner(System.in));
	}

	public static void inputBirthday() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("On what day of the month were you born? ");
		String day = input.next();
		System.out.print("What is the name of the month in which you were born? ");
		String month = input.next();
		System.out.print("During what year were you born? ");
		String year = input.next();
		System.out.println("You were born on " + month + " " + day + ", " + year + ". You're mighty old!");
		// input.close(); //uncommenting this line causes an exception; not sure
		// why
	}

	public static void processName(Scanner s) {
		System.out.print("Please enter your full name: ");
		String firstName = s.next();
		String revName = s.next() + ", " + firstName;

		System.out.println("Your name in reverse order is " + revName);
	}

}
