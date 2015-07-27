public class OharaRhymeAlliterate {
	
	public static void main(String[] args) {
		String word1 = "fare";
		String word2 = "flare";
		if (rhyme(word1, word2))
			System.out.println("They rhyme!");
		if (alliterate(word1, word2))
			System.out.println("They alliterate!");
	}
	
	// Returns true if s1 and s2 end with the same two letters.
	public static boolean rhyme(String s1, String s2) {
		return s1.length() >= 2 && s2.length() >= 2
				&& s1.endsWith(s2.substring(s2.length() - 2));
	}
	
	// Returns true if s1 and s2 start with the same letter.
	public static boolean alliterate(String s1, String s2) {
		return s1.length() > 0 && s2.length() > 0
				&& s1.substring(0, 1).equals(s2.substring(0, 1));
	}
}
