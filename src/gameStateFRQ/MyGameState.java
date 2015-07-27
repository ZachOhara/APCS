package gameStateFRQ;

import java.util.ArrayList;

public class MyGameState implements GameState {

	private RandomPlayer randy;
	private ArrayList<String> moves;

	public MyGameState() {
		this.randy = new RandomPlayer("Randy");
		this.moves = new ArrayList<String>();
		this.moves.add("Move1");
		this.moves.add("Move2");
		this.moves.add("Move3");
	}

	/**
	 * @return true if the game is in an ending state; false otherwise
	 */
	@Override
	public boolean isGameOver() {
		return this.moves.isEmpty();
	}

	/**
	 * Precondition: isGameOver() returns true
	 *
	 * @return the player that won the game or null if there was no winner
	 */
	@Override
	public Player getWinner() {
		return this.randy;
	}

	/**
	 * Precondition: isGameOver() returns false
	 *
	 * @return the player who is to make the next move
	 */
	@Override
	public Player getCurrentPlayer() {
		return this.randy;
	}

	/**
	 * @return a list of valid moves for the current player; the size of the returned list
	 * is 0 if there are no valid moves.
	 */
	@Override
	public ArrayList<String> getCurrentMoves() {
		return this.moves;
	}

	/**
	 * Updates game state to reflect the effect of the specified move.
	 *
	 * @param move a description of the move to be made
	 */
	@Override
	public void makeMove(String move) {
		this.moves.remove(move);
	}

	/**
	 * @return a string representing the current GameState
	 */
	@Override
	public String toString() {
		return this.moves.toString();
	}
}
