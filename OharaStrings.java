public class OharaStrings {

	public static void main(String[] args) {
		// printStrings and vertical methods
		LastnameStrings.main(null);
		// padString
		System.out.println(padString("string", 10));
		System.out.println();
		// printReverse
		printReverse("hello there!");
	}

	public static String padString(String str, int length) {
		// In this method, I was unclear about where exactly the padding should go
		// I put it on the end, but I may have been wrong
		while (str.length() < length) {
			str += "_";
		}
		return str;
	}

	public static void printReverse(String str) {
		for (int i = str.length()-1; i >=0; i--)
			System.out.print(str.substring(i, i+1));
		System.out.println();
	}
}

//All code below this point was copied directly from the in-class code
//The only change that has been made is that the class is not public
class LastnameStrings {
	public static void main(String[] args) {
		// printStrings
		printStrings("abc", 5);
		System.out.println();
		// Exercise 3.12: printVertical
		String s = "hey now";
		vertical(s);
		System.out.println();
	}

	// printStrings
	public static void printStrings(String s, int repeat) {
		for (int i = 1; i<=repeat; i++) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

	// Exercise 3.12: vertical
	public static void vertical(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.substring(i,i+1));
		}
	}

}
