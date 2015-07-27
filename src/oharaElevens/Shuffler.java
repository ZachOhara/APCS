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

 package oharaElevens;

/**
 * This class provides a convenient way to test shuffling methods.
 */
public class Shuffler {

	/**
	 * The number of consecutive shuffle steps to be performed in each call to each sorting
	 * procedure.
	 */
	private static final int SHUFFLE_COUNT = 1;

	/**
	 * Tests shuffling methods.
	 *
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		System.out.println("Results of " + Shuffler.SHUFFLE_COUNT + " consecutive perfect shuffles:");
		int[] values1 = {0, 1, 2, 3};
		for (int j = 1; j <= Shuffler.SHUFFLE_COUNT; j++) {
			Shuffler.perfectShuffle(values1);
			System.out.print("  " + j + ":");
			for (int k = 0; k < values1.length; k++) {
				System.out.print(" " + values1[k]);
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("Results of " + Shuffler.SHUFFLE_COUNT
				+ " consecutive efficient selection shuffles:");
		int[] values2 = {0, 1, 2, 3};
		for (int j = 1; j <= Shuffler.SHUFFLE_COUNT; j++) {
			Shuffler.selectionShuffle(values2);
			System.out.print("  " + j + ":");
			for (int k = 0; k < values2.length; k++) {
				System.out.print(" " + values2[k]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Apply a "perfect shuffle" to the argument. The perfect shuffle algorithm splits the
	 * deck in half, then interleaves the cards in one half with the cards in the other.
	 *
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static void perfectShuffle(int[] values) {
		int midIndex = values.length / 2;
		int[] shuffled = new int[values.length];
		for (int i = 0; i < midIndex; i++) {
			shuffled[2 * i] = values[i];
			shuffled[2 * i + 1] = values[midIndex + i];
		}
		for (int i = 0; i < values.length; i++) {
			values[i] = shuffled[i];
		}
	}

	/**
	 * Apply an "efficient selection shuffle" to the argument. The selection shuffle
	 * algorithm conceptually maintains two sequences of cards: the selected cards
	 * (initially empty) and the not-yet-selected cards (initially the entire deck). It
	 * repeatedly does the following until all cards have been selected: randomly remove a
	 * card from those not yet selected and add it to the selected cards. An efficient
	 * version of this algorithm makes use of arrays to avoid searching for an
	 * as-yet-unselected card.
	 *
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static void selectionShuffle(int[] values) {
		for (int i = 0; i < values.length; i++) {
			int rand = (int) (Math.random() * values.length);
			int temp = values[i];
			values[i] = values[rand];
			values[rand] = temp;
		}
	}
}
