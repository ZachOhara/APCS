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

 public class OharaRecursion {

	public static int multiplyEvens(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Cannot accept: " + n);
		}
		if (n == 1) {
			return 2;
		} else {
			return 2 * n * OharaRecursion.multiplyEvens(n - 1);
		}
	}

	public static void writeSquares(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("Cannot accept: " + n);
		}
		if (n == 1) {
			System.out.print("1");
		} else {
			if (n % 2 == 1) {
				System.out.print((n * n) + ", ");
			}
			OharaRecursion.writeSquares(n - 1);
			if (n % 2 == 0) {
				System.out.print(", " + (n * n));
			}
		}
	}

	public static void main(String[] args) {
		int[] testCases1 = {1, 2, 3, 4};
		for (int i : testCases1) {
			System.out.println(OharaRecursion.multiplyEvens(i));
		}

		int[] testCases2 = {5, 1, 8};
		for (int i : testCases2) {
			OharaRecursion.writeSquares(i);
			System.out.println();
		}
	}

}
