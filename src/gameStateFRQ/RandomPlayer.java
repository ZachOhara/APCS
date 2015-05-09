package gameStateFRQ;

import java.util.List;

public class RandomPlayer extends Player {

	public RandomPlayer(String aName) {
		super(aName);
	}

	@Override
	public String getNextMove(GameState state) {
		List<String> movesList = state.getCurrentMoves();
		if (movesList.size() == 0)
			return "no move";
		int moveIndex = (int)(Math.random() * movesList.size());
		return movesList.get(moveIndex);
	}

}
