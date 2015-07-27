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

public class OharaTallying {

	public static void main(String[] args) {
		int[] arr1 = {27, 15, 15, 11, 27};
		System.out.println(OharaTallying.mode(arr1));

		String arr2 = "i	think, therefore i am";
		System.out.println(Arrays.toString(OharaTallying.vowelCount(arr2)));

		int[] arr3 = {74, 85, 102, 99, 101, 85, 56};
		System.out.println(OharaTallying.lastIndexOf(arr3, 85));

		int[] arr4 = {36, 12, 25, 19, 46, 31, 22};
		System.out.println(OharaTallying.range(arr4));

		int[] arr5 = {1, -2, 4, -4, 9, -6, 16, -8, 25, -10};
		System.out.println(OharaTallying.stdev(arr5));

		int[] arr6 = {200, 300, 250, 1, 950, 40};
		System.out.println(OharaTallying.priceIsRight(arr6, 280));
	}

	// problem 1
	public static int mode(int[] arr) {
		int[] occurances = new int[OharaTallying.max(arr) + 1];
		for (int i : arr) {
			occurances[i]++;
		}
		return OharaTallying.indexOf(occurances, OharaTallying.max(occurances));
	}

	// problem 2
	public static int[] vowelCount(String str) {
		int[] vowels = new int[5];
		String[] vowelStrings = {"a", "e", "i", "o", "u"};
		str = str.toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			if (OharaTallying.contains(vowelStrings, str.substring(i, i + 1))) {
				vowels[OharaTallying.indexOf(vowelStrings, str.substring(i, i + 1))]++;
			}
		}
		return vowels;
	}

	// problem 3
	public static int lastIndexOf(int[] arr, int n) {
		int i = arr.length - 1;
		while (arr[i] != n && i >= 0) {
			i--;
		}
		return i;
	}

	// problem 4
	public static int range(int[] arr) {
		return OharaTallying.max(arr) - OharaTallying.min(arr) + 1;
		// too easy
	}

	// problem 5
	public static double stdev(int[] arr) {
		double mean = OharaTallying.mean(arr);
		double sumSquares = 0;
		for (int i : arr) {
			sumSquares += Math.pow(i - mean, 2);
		}
		return Math.sqrt(sumSquares / (arr.length - 1));
	}

	// problem 6
	public static int priceIsRight(int[] bids, int price) {
		int closest = 0;
		for (int i : bids) {
			if (i <= price && price - closest > price - i) {
				closest = i;
			}
		}
		return closest;
	}

	public static int max(int[] arr) {
		int max = arr[0];
		for (int i : arr) {
			max = max > i ? max : i;
		}
		return max;
	}

	public static int min(int[] arr) {
		int min = arr[0];
		for (int i : arr) {
			min = min < i ? min : i;
		}
		return min;
	}

	public static int sum(int[] arr) {
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return sum;
	}

	public static double mean(int[] arr) {
		return (double) OharaTallying.sum(arr) / arr.length;
	}

	public static int indexOf(int[] arr, int n) {
		int i = 0;
		while (arr[i] != n && i < arr.length) {
			i++;
		}
		if (i == arr.length) {
			i = -1;
		}
		return i;
	}

	public static int indexOf(String[] arr, String s) {
		int i = 0;
		while ( !arr[i].equals(s) && i < arr.length) {
			i++;
		}
		if (i == arr.length) {
			i = -1;
		}
		return i;
	}

	public static boolean contains(String[] arr, String s) {
		boolean contains = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(s)) {
				contains = true;
			}
		}
		return contains;
	}

}
