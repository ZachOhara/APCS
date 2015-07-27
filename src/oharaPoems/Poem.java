package oharaPoems;

public abstract class Poem {

	// returns the number of lines in a poem
	public abstract int numLines();

	// returns the number of syllables in the nth line
	public abstract int getSyllables(int n);

	// shows the rythm of the poem
	public void printRhythm() {
		if (this instanceof Haiku) {
			System.out.println("A haiku is 3 lines of 5, 7, 5 syllables");
		} else if (this instanceof Limerick) {
			System.out.println("A limerick is 5 lines of 9, 9, 6, 6, 9 syllables");
		}
	}

}
