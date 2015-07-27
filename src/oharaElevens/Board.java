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
 * This class represents a Board that can be used in a collection of solitaire games
 * similar to Elevens. The variants differ in card removal and the board size.
 */
public abstract class Board {

	/**
	 * The cards on this board.
	 */
	private Card[] cards;

	/**
	 * The deck of cards being used to play the current game.
	 */
	private Deck deck;

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;

	/**
	 * Creates a new <code>Board</code> instance.
	 *
	 * @param size the number of cards in the board
	 * @param ranks the names of the card ranks needed to create the deck
	 * @param suits the names of the card suits needed to create the deck
	 * @param pointValues the integer values of the cards needed to create the deck
	 */
	public Board(int size, String[] ranks, String[] suits, int[] pointValues) {
		this.cards = new Card[size];
		this.deck = new Deck(ranks, suits, pointValues);
		if (Board.I_AM_DEBUGGING) {
			System.out.println(this.deck);
			System.out.println("----------");
		}
		this.dealMyCards();
	}

	/**
	 * Start a new game by shuffling the deck and dealing some cards to this board.
	 */
	public void newGame() {
		this.deck.shuffle();
		this.dealMyCards();
	}

	/**
	 * Accesses the size of the board. Note that this is not the number of cards it
	 * contains, which will be smaller near the end of a winning game.
	 *
	 * @return the size of the board
	 */
	public int size() {
		return this.cards.length;
	}

	/**
	 * Determines if the board is empty (has no cards).
	 *
	 * @return true if this board is empty; false otherwise.
	 */
	public boolean isEmpty() {
		for (int k = 0; k < this.cards.length; k++) {
			if (this.cards[k] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Deal a card to the kth position in this board. If the deck is empty, the kth card is
	 * set to null.
	 *
	 * @param k the index of the card to be dealt.
	 */
	public void deal(int k) {
		this.cards[k] = this.deck.deal();
	}

	/**
	 * Accesses the deck's size.
	 *
	 * @return the number of undealt cards left in the deck.
	 */
	public int deckSize() {
		return this.deck.size();
	}

	/**
	 * Accesses a card on the board.
	 *
	 * @return the card at position k on the board.
	 * @param k is the board position of the card to return.
	 */
	public Card cardAt(int k) {
		return this.cards[k];
	}

	/**
	 * Replaces selected cards on the board by dealing new cards.
	 *
	 * @param selectedCards is a list of the indices of the cards to be replaced.
	 */
	public void replaceSelectedCards(List<Integer> selectedCards) {
		for (Integer k : selectedCards) {
			this.deal(k.intValue());
		}
	}

	/**
	 * Gets the indexes of the actual (non-null) cards on the board.
	 *
	 * @return a List that contains the locations (indexes) of the non-null entries on the
	 * board.
	 */
	public List<Integer> cardIndexes() {
		List<Integer> selected = new ArrayList<Integer>();
		for (int k = 0; k < this.cards.length; k++) {
			if (this.cards[k] != null) {
				selected.add(new Integer(k));
			}
		}
		return selected;
	}

	/**
	 * Generates and returns a string representation of this board.
	 *
	 * @return the string version of this board.
	 */
	@Override
	public String toString() {
		String s = "";
		for (int k = 0; k < this.cards.length; k++) {
			s = s + k + ": " + this.cards[k] + "\n";
		}
		return s;
	}

	/**
	 * Determine whether or not the game has been won, i.e. neither the board nor the deck
	 * has any more cards.
	 *
	 * @return true when the current game has been won; false otherwise.
	 */
	public boolean gameIsWon() {
		if (this.deck.isEmpty()) {
			for (Card c : this.cards) {
				if (c != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Method to be completed by the concrete class that determines if the selected cards
	 * form a valid group for removal.
	 *
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal; false otherwise.
	 */
	public abstract boolean isLegal(List<Integer> selectedCards);

	/**
	 * Method to be completed by the concrete class that determines if there are any legal
	 * plays left on the board.
	 *
	 * @return true if there is a legal play left on the board; false otherwise.
	 */
	public abstract boolean anotherPlayIsPossible();

	/**
	 * Deal cards to this board to start the game.
	 */
	private void dealMyCards() {
		for (int k = 0; k < this.cards.length; k++) {
			this.cards[k] = this.deck.deal();
		}
	}
}
