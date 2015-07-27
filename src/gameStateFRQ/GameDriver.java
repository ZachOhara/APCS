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
