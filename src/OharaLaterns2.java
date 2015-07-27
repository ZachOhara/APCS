/* Output from problem 1:
 * I am method 1.
 * I am method 2.
 * I am method 3.
 * I am method 1
 * I am method 1.
 * I am method 1.
 * I am method 2.
 * I am method 1.
 * I am method 2.
 * I am method 3.
 * I am method 1.
 * I am method 1.
 * I am method 2.
 * I am method 1.
 */

// The purpose of this program is to exactly recreate a specific
// pattern of askterisks, spaces, and vertical lines
public class OharaLaterns2 {
	
	// prints a short row of 5 asterisks
	static void shortRow() {
		System.out.println("    *****    ");
	}
	
	// prints a long row of 13 asterisks
	static void longRow() {
		System.out.println("*************");
	}
	
	// prints three rows, with the top row having 5 asterisks, and each
	// successive row getting longer by 2 asterisks on each side
	static void threeRows() {
		shortRow();
		System.out.println("  *********  ");
		longRow();
	}
	
	// prints 5 vertical lines, each seperated by a space, and all lines
	// bordered by one asterisk on each side
	static void verticalLines() {
		System.out.println("* | | | | | *");
	}
	
	public static void main(String[] args) {
		threeRows(); // figure 1
		System.out.println();
		figure2();
		System.out.println();
		figure3();
	}
	
	public static void figure2() {
		threeRows();
		verticalLines();
		longRow();
		threeRows();
	}
	
	public static void figure3() {
		threeRows();
		shortRow();
		longRow();
		verticalLines();
		verticalLines();
		longRow();
		shortRow();
		shortRow();
	}
}
