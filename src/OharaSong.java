/*
 * Project #1: Song
 * This program will print out the lines of a song, in the correct
 * order, only using static methods, println statements, and string
 * literals. Please have mercy next time and allow loops, or at least
 * expressions and if-statements.
 *
 * By Zach Ohara
 * AP CS, 2014-1015
 * September 10, 2014
 */

public class OharaSong {

	public static void main(String[] args) {
		OharaSong.verse1();
		OharaSong.verse2();
		OharaSong.verse3();
		OharaSong.verse4();
		OharaSong.verse5();
		OharaSong.verse6();
		OharaSong.verse7();
	}

	// All methods named "verse_" are only for the first line of the given verse

	static void verse1() {
		System.out.println("This is the house that Jack built.");
		OharaSong.refrain0();
	}

	static void verse2() {
		System.out.println("This is the malt,");
		OharaSong.refrain1();
	}

	static void verse3() {
		System.out.println("This is the rat,");
		OharaSong.refrain2();
	}

	static void verse4() {
		System.out.println("This is the cat,");
		OharaSong.refrain3();
	}

	static void verse5() {
		System.out.println("This is the dog,");
		OharaSong.refrain4();
	}

	static void verse6() {
		System.out.println("This is the cow with the crumpled horn,");
		OharaSong.refrain5();
	}

	static void verse7() {
		System.out.println("This is the maiden all forlorn");
		System.out.println("That milked the cow with the crumpled horn, That tossed the dog,");
		// The line above should be split into two lines, but the assignment
		// said to "EXACTLY reproduce" the format of the given output.
		OharaSong.refrain4();
	}

	// All methods named "refrain_" work recursively to print all the repeated
	// lines between verses

	static void refrain0() {
		System.out.println();
	}

	static void refrain1() {
		System.out.println("That lay in the house that Jack built.");
		OharaSong.refrain0();
	}

	static void refrain2() {
		System.out.println("That ate the malt");
		OharaSong.refrain1();
	}

	static void refrain3() {
		System.out.println("That killed the rat,");
		OharaSong.refrain2();
	}

	static void refrain4() {
		System.out.println("That worried the cat,");
		OharaSong.refrain3();
	}

	static void refrain5() {
		System.out.println("That tossed the dog,");
		OharaSong.refrain4();
	}

}
