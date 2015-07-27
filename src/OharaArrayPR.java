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

import java.util.Arrays;

/* Problem 1:
 * Array              | Value returned
 * 5                  | 0
 * 3, 12              | 9
 * 4, 2, 10, 8        | 6
 * 1, 9, 3, 5, 7      | 8
 * 8, 2, 10, 4, 10, 9 | 2
 *
 * Problem 2:
 * Array              | Value returned
 * {8}                | 0
 * {14, 7}            | 1
 * {7, 1, 3, 2, 0, 4} | 3
 * {10, 8, 9, 5, 6}   | 2
 * {8, 10, 8, 6, 4, 2}| 4
 */

public class OharaArrayPR {

	public static void main(String[] args) {
		// 1. Write a method swap that accepts an array of integers
		// and two indexes and swaps the elements at those indexes.
		int[] a1 = {12, 34, 56};
		OharaArrayPR.swap(a1, 1, 2);
		System.out.println(Arrays.toString(a1)); // [12, 56, 34]

		// 2. Write a method swapAll that accepts two arrays of integers
		// as parameters and swaps their entire contents.
		// Assume that the two arrays are the same length.
		int[] a1b = {12, 34, 56};
		int[] a2b = {20, 50, 80};
		OharaArrayPR.swapAll(a1b, a2b);
		System.out.println(Arrays.toString(a1b)); // [20, 50, 80]
		System.out.println(Arrays.toString(a2b)); // [12, 34, 56]

		// 3. Write a method merge that accepts two arrays of integers
		// and returns a new array containing all elements of the first array
		// followed by all elements of the second.
		int[] a1c = {12, 34, 56};
		int[] a2c = {7, 8, 9, 10};
		int[] a3c = OharaArrayPR.merge(a1c, a2c);
		System.out.println(Arrays.toString(a3c)); // [12, 34, 56, 7, 8, 9, 10]

		// 4. Write a method merge3 that merges 3 arrays similarly to problem 3.
		int[] a1d = {12, 34, 56};
		int[] a2d = {7, 8, 9, 10};
		int[] a3d = {444, 222, -1};
		int[] a4d = OharaArrayPR.merge3(a1d, a2d, a3d);
		System.out.println(Arrays.toString(a4d));
		// [12, 34, 56, 7, 8, 9, 10, 444, 222, -1]

		// 5. Determines the percent of even numbers in an array of integers.
		int[] numbers = {6, 2, 9, 11, 3};
		double percent = OharaArrayPR.percentEven(numbers);
		System.out.println("The percent of even numbers in " + Arrays.toString(numbers) + " is " + percent
				+ ".");
	}

	public static void swap(int[] a, int in1, int in2) {
		int swap = a[in1];
		a[in1] = a[in2];
		a[in2] = swap;
	}

	public static void swapAll(int[] a, int[] b) {
		if (a.length != b.length) {
			throw new IllegalArgumentException();
		}
		int[] swap = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			swap[i] = a[i];
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = b[i];
		}
		for (int i = 0; i < a.length; i++) {
			b[i] = swap[i];
		}
	}

	public static int[] merge(int[] a, int[] b) {
		int[] result = new int[a.length + b.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i];
		}
		for (int i = a.length; i < a.length + b.length; i++) {
			result[i] = b[i - a.length];
		}
		return result;
	}

	public static int[] merge3(int[] a, int[] b, int[] c) {
		return OharaArrayPR.merge(OharaArrayPR.merge(a, b), c);
		// You never said I couldn't do this
	}

	public static double percentEven(int[] a) {
		int evens = 0;
		for (int i : a) {
			if (i % 2 == 0) {
				evens++;
			}
		}
		return (double) evens / a.length;
	}

}
