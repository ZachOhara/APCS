import java.util.*;

public class OharaIfElseEquals {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		printPalindrome(console);
		System.out.println(wordCount("hello"));
		System.out.println(wordCount("how are you?"));
		System.out.println(wordCount(" this string has wide spaces "));
		System.out.println(wordCount(" "));
	}

	public static void printPalindrome(Scanner console) {
		String s = console.nextLine().toLowerCase();
		String str = "";
		for (int i = s.length()-1; i >= 0; i--) {
			str += s.substring(i,i+1).toLowerCase();
		}
		System.out.println(s.equals(str) ? "It's a palindrome!" : "It's not a palindrome...");
	}

	public static int wordCount(String str) {
		if (str.length() == 0 || (str.length() == 1 && str.substring(0,1).equals(" ")))
			return 0;
		while (str.substring(0,1).equals(" "))
			str = str.substring(1);
		while (str.substring(str.length() - 1).equals(" "))
			str = str.substring(0, str.length()-1);
		int spaces = 0;
		int i = 0;
		while (i < str.length()) {
			while (str.substring(i, i+1).equals(" ")) {
				spaces++;
				i++;
				if (i == str.length())
					break;
			}
			i++;
		}
		return spaces + 1;
	}

}