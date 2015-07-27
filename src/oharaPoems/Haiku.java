package oharaPoems;

public class Haiku extends Poem {
	
	private static final int[] syllables = {
			5, 7, 5
	};
	
	public int numLines() {
		return 3;
	}
	
	public int getSyllables(int n) {
		return syllables[n];
	}
	
}
