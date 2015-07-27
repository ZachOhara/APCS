package oharaPoems;

public class Haiku extends Poem {

	private static final int[] syllables = {5, 7, 5};

	@Override
	public int numLines() {
		return 3;
	}

	@Override
	public int getSyllables(int n) {
		return Haiku.syllables[n];
	}

}
