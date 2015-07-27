public class OharaSongOrig {

	static String[] objects = {"maiden all forlorn,", "cow with the crumpled horn,", "dog,", "cat,", "rat,",
		"malt,", "house that Jack built."};

	static String[] verbs = {"", "milked", "tossed", "worried", "killed", "ate", "lay in"};

	static int totalVerses = 7;

	static void verse(int verseNum) {
		System.out.println("This is the " + OharaSongOrig.objects[OharaSongOrig.totalVerses - verseNum]);
		for (int item = verseNum - 1; item > 0; item--) {
			System.out.println("That " + OharaSongOrig.verbs[OharaSongOrig.totalVerses - item] + " the "
					+ OharaSongOrig.objects[OharaSongOrig.totalVerses - item]);
		}
	}

	public static void main(String[] args) {
		for (int verse = 1; verse <= OharaSongOrig.totalVerses; verse++) {
			OharaSongOrig.verse(verse);
			System.out.println();
		}

	}

}
