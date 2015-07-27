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

import java.util.ArrayList;
import java.util.List;

/**
 * The Deck class represents a shuffled deck of cards. It provides several operations
 * including initialize, shuffle, deal, and check if empty.
 */
public class Deck {

	/**
	 * cards contains all the cards in the deck.
	 */
	private List<Card> cards;

	/**
	 * size is the number of not-yet-dealt cards. Cards are dealt from the top (highest
	 * index) down. The next card to be dealt is at size - 1.
	 */
	private int size;

	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits, and produces one of the
	 * corresponding card.
	 *
	 * @param ranks is an array containing all of the card ranks.
	 * @param suits is an array containing all of the card suits.
	 * @param values is an array containing all of the card point values.
	 */
	public Deck(String[] ranks, String[] suits, int[] values) {
		this.cards = new ArrayList<Card>();
		for (int i = 0; i < ranks.length; i++) {
			for (String suit : suits) {
				this.cards.add(new Card(ranks[i], suit, values[i]));
			}
		}
		this.size = this.cards.size();
		this.shuffle();
	}

	/**
	 * Determines if this deck is empty (no undealt cards).
	 *
	 * @return true if this deck is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Accesses the number of undealt cards in this deck.
	 *
	 * @return the number of undealt cards in this deck.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Randomly permute the given collection of cards and reset the size to represent the
	 * entire deck.
	 */
	public void shuffle() {
		for (int i = 0; i < this.size; i++) {
			int rand = (int) (Math.random() * this.size);
			Card temp = this.cards.get(i);
			this.cards.set(i, this.cards.get(rand));
			this.cards.set(rand, temp);
		}
	}

	/**
	 * Deals a card from this deck.
	 *
	 * @return the card just dealt, or null if all the cards have been previously dealt.
	 */
	public Card deal() {
		if (this.isEmpty()) {
			return null;
		} else {
			this.size--;
			return this.cards.get(this.size);
		}
	}

	/**
	 * Generates and returns a string representation of this deck.
	 *
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String rtn = "size = " + this.size + "\nUndealt cards: \n";

		for (int k = this.size - 1; k >= 0; k--) {
			rtn = rtn + this.cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((this.size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = this.cards.size() - 1; k >= this.size; k--) {
			rtn = rtn + this.cards.get(k);
			if (k != this.size) {
				rtn = rtn + ", ";
			}
			if ((k - this.cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}
}
