package oharaPoems;

public class Limerick extends Poem {
	
	private static final int[] syllables = {
			9, 9, 6, 6, 9
	};
	
	public int numLines() {
		return 5;
	}
	
	public int getSyllables(int n) {
		return syllables[n];
	}
	
}
