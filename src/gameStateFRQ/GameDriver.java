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

public class GameDriver {

	private GameState state; // the current state of the game

	public GameDriver(GameState initial) {
		this.state = initial;
	}

	public void play() {
		System.out.println(this.state);
		Player currentPlayer;
		String move;
		while ( !this.state.isGameOver()) {
			currentPlayer = this.state.getCurrentPlayer();
			move = currentPlayer.getNextMove(this.state);
			System.out.println("Player: " + currentPlayer.getName());
			System.out.println("Move: " + move);
			this.state.makeMove(move);
		}
		Player winner = this.state.getWinner();
		if (winner != null) {
			System.out.println(winner.getName() + " wins");
		} else {
			System.out.println("Game ends in a draw");
		}
	}

}
