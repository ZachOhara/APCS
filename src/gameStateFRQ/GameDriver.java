package gameStateFRQ;

public class GameDriver {
	
	private GameState state; // the current state of the game
	
	public GameDriver(GameState initial) {
		state = initial;
	}
	
	public void play() {
		System.out.println(state);
		Player currentPlayer;
		String move;
		while ( !state.isGameOver()) {
			currentPlayer = state.getCurrentPlayer();
			move = currentPlayer.getNextMove(state);
			System.out.println("Player: " + currentPlayer.getName());
			System.out.println("Move: " + move);
			state.makeMove(move);
		}
		Player winner = state.getWinner();
		if (winner != null)
			System.out.println(winner.getName() + " wins");
		else
			System.out.println("Game ends in a draw");
	}
	
}
