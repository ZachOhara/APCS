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
 * This is a class that tests the Card class.
 */
public class CardTester {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		Card[] cards =
			{new Card("Queen", "Spades", 13), new Card("Two", "Clubs", 0),
				new Card("Queen", "Spades", 13)};
		for (int i = 0; i < cards.length; i++) {
			System.out.println(cards[i]);
			System.out.println("\tRank: " + cards[i].rank());
			System.out.println("\tSuit: " + cards[i].suit());
			System.out.println("\tPoints: " + cards[i].pointValue());
			for (int j = i + 1; j < cards.length; j++) {
				System.out.println("\t" + cards[i] + " == " + cards[j] + ": " + cards[i].matches(cards[j]));
			}
			System.out.println();
		}
	}

}
