package oharaPoems;

public class Limerick extends Poem {

	private static final int[] syllables = {9, 9, 6, 6, 9};

	@Override
	public int numLines() {
		return 5;
	}

	@Override
	public int getSyllables(int n) {
		return Limerick.syllables[n];
	}

}
