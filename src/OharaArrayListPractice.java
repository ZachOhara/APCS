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

import java.util.ArrayList;
import java.util.Arrays;

public class OharaArrayListPractice {

	public static void main(String[] args) {
		// Problem 1
		String[] wordsArr = {"be", "be", "is", "not", "or", "question", "that", "the", "to", "to"};
		ArrayList<String> words = new ArrayList<String>();
		words.addAll(Arrays.asList(wordsArr));
		System.out.println(words);
		words = Problem1.removeDuplicates(words);
		System.out.println(words);
		System.out.println();

		// Problem 2
		Integer[] numsArr = {0, 7, 2, 0, 0, 4, 0};
		ArrayList<Integer> numsList = new ArrayList<Integer>();
		numsList.addAll(Arrays.asList(numsArr));
		System.out.println(numsList);
		numsList = Problem2.removeZeroes(numsList);
		System.out.println(numsList);
		System.out.println();

		// Problem 3
		Integer[] numsArr2 = {3, 8, 19, 42, 7, 26, 19, -8};
		ArrayList<Integer> numsList2 = new ArrayList<Integer>();
		numsList2.addAll(Arrays.asList(numsArr2));
		System.out.println(numsList2);
		numsList2 = Problem3.reverse3(numsList2);
		System.out.println(numsList2);

	}

	/*
	 * Each problem instructs to write a class, so each problem gets its own class.
	 *
	 * Also, I made all the solutions generic. The code works exactly the same, but the
	 * functions will work with lists of any type, as long as that type has a meaningful
	 * equals() method.
	 */

	public static class Problem1 {

		public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
			ArrayList<T> newlist = new ArrayList<T>();
			for (T element : list) {
				if ( !newlist.contains(element)) {
					newlist.add(element);
				}
			}
			return newlist;
		}

	}

	public static class Problem2 {

		public static <T> ArrayList<T> removeZeroes(ArrayList<T> list) {
			while (list.contains(0)) {
				list.remove(list.indexOf(0));
			}
			return list;
		}

	}

	public static class Problem3 {

		public static <T> ArrayList<T> reverse3(ArrayList<T> list) {
			for (int i = 0; i < list.size() / 3; i++) {
				T placeholder = list.get(3 * i);
				list.set(3 * i, list.get(3 * i + 2));
				list.set(3 * i + 2, placeholder);
			}
			return list;
		}

	}

}
