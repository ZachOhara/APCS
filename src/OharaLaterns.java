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

/* Output from problem 1:
 * I am method 1.
 * I am method 2.
 * I am method 3.
 * I am method 1
 * I am method 1.
 * I am method 1.
 * I am method 2.
 * I am method 1.
 * I am method 2.
 * I am method 3.
 * I am method 1.
 * I am method 1.
 * I am method 2.
 * I am method 1.
 */

// The purpose of this program is to exactly recreate a specific
// pattern of asterisks, spaces, and vertical lines
public class OharaLaterns {

	// prints a short row of 5 asterisks
	static void shortRow() {
		System.out.println("    *****    ");
	}

	// prints a long row of 13 asterisks
	static void longRow() {
		System.out.println("*************");
	}

	// prints three rows, with the top row having 5 asterisks, and each
	// successive row getting longer by 2 asterisks on each side
	static void threeRows() {
		OharaLaterns.shortRow();
		System.out.println("  *********  ");
		OharaLaterns.longRow();
	}

	// prints 5 vertical lines, each seperated by a space, and all lines
	// bordered by one asterisk on each side
	static void verticalLines() {
		System.out.println("* | | | | | *");
	}

	public static void main(String[] args) {
		OharaLaterns.threeRows(); // figure 1
		System.out.println();
		OharaLaterns.figure2();
		System.out.println();
		OharaLaterns.figure3();
	}

	public static void figure2() {
		OharaLaterns.threeRows();
		OharaLaterns.verticalLines();
		OharaLaterns.longRow();
		OharaLaterns.threeRows();
	}

	public static void figure3() {
		OharaLaterns.threeRows();
		OharaLaterns.shortRow();
		OharaLaterns.longRow();
		OharaLaterns.verticalLines();
		OharaLaterns.verticalLines();
		OharaLaterns.longRow();
		OharaLaterns.shortRow();
		OharaLaterns.shortRow();
	}
}
