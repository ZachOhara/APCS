public class OharaSongOrig {
	
	static String[] objects = {
			"maiden all forlorn,", "cow with the crumpled horn,", "dog,",
			"cat,", "rat,", "malt,", "house that Jack built."
	};
	
	static String[] verbs = {
			"", "milked", "tossed", "worried", "killed", "ate", "lay in"
	};
	
	static int totalVerses = 7;
	
	static void verse(int verseNum) {
		System.out.println("This is the " + objects[totalVerses - verseNum]);
		for (int item = verseNum - 1; item > 0; item--) {
			System.out.println("That " + verbs[totalVerses - item] + " the "
					+ objects[totalVerses - item]);
		}
	}
	
	public static void main(String[] args) {
		for (int verse = 1; verse <= totalVerses; verse++) {
			verse(verse);
			System.out.println();
		}
		
	}
	
}
