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

import java.util.List;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack",
		"queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	public ElevensBoard() {
		super(ElevensBoard.BOARD_SIZE, ElevensBoard.RANKS, ElevensBoard.SUITS, ElevensBoard.POINT_VALUES);
	}

	/**
	 * Determines if the selected cards form a valid group for removal. In Elevens, the
	 * legal groups are (1) a pair of non-face cards whose values add to 11, and (2) a
	 * group of three cards consisting of a jack, a queen, and a king in some order.
	 *
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal; false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		int len = selectedCards.size();
		return (len == 2 && this.containsPairSum11(selectedCards))
				|| (len == 3 && this.containsJQK(selectedCards));
	}

	/**
	 * Determine if there are any legal plays left on the board. In Elevens, there is a
	 * legal play if the board contains (1) a pair of non-face cards whose values add to
	 * 11, or (2) a group of three cards consisting of a jack, a queen, and a king in some
	 * order.
	 *
	 * @return true if there is a legal play left on the board; false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
		List<Integer> cardlist = this.cardIndexes();
		return this.containsPairSum11(cardlist) || this.containsJQK(cardlist);
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 *
	 * @param selectedCards selects a subset of this board. It is list of indexes into this
	 * board that are searched to find an 11-pair.
	 * @return true if the board entries in selectedCards contain an 11-pair; false
	 * otherwise.
	 */
	private boolean containsPairSum11(List<Integer> selectedCards) {
		for (int i : selectedCards) {
			for (int j : selectedCards) {
				if (this.cardAt(i).pointValue() + this.cardAt(j).pointValue() == 11) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check for a JQK in the selected cards.
	 *
	 * @param selectedCards selects a subset of this board. It is list of indexes into this
	 * board that are searched to find a JQK group.
	 * @return true if the board entries in selectedCards include a jack, a queen, and a
	 * king; false otherwise.
	 */
	private boolean containsJQK(List<Integer> selectedCards) {
		boolean j = false;
		boolean q = false;
		boolean k = false;
		for (int i : selectedCards) {
			if (this.cardAt(i).rank().equals("jack")) {
				j = true;
			}
			if (this.cardAt(i).rank().equals("queen")) {
				q = true;
			}
			if (this.cardAt(i).rank().equals("king")) {
				k = true;
			}
		}
		return j && q && k;
	}
}
