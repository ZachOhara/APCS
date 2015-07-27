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

package gameStateFRQ;

import java.util.ArrayList;

public interface GameState {

	/**
	 * @return true if the game is in an ending state; false otherwise
	 */
	boolean isGameOver();

	/**
	 * Precondition: isGameOver() returns true
	 *
	 * @return the player that won the game or null if there was no winner
	 */
	Player getWinner();

	/**
	 * Precondition: isGameOver() returns false
	 *
	 * @return the player who is to make the next move
	 */
	Player getCurrentPlayer();

	/**
	 * @return a list of valid moves for the current player; the size of the returned list
	 * is 0 if there are no valid moves.
	 */
	ArrayList<String> getCurrentMoves();

	/**
	 * Updates game state to reflect the effect of the specified move.
	 *
	 * @param move a description of the move to be made
	 */
	void makeMove(String move);

	/**
	 * @return a string representing the current GameState
	 */
	@Override
	String toString();
}
