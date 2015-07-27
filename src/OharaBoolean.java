import java.util.Arrays;

/* Problem 1:
 *          next == 0  prev == 0  next == prev
 * Point A	SOMETIMES	ALWAYS	SOMETIMES
 * Point B	NEVER	SOMETIMES	SOMETIMES
 * Point C	NEVER	SOMETIMES	ALWAYS
 * Point D	SOMETIMES	NEVER	SOMETIMES
 * Point E	ALWAYS	NEVER	NEVER
 * 
 * Problem 2:
 *          y > 0      y%2 == 0
 * Point A	SOMETIMES	SOMETIMES
 * Point B	ALWAYS	SOMETIMES
 * Point C	ALWAYS	ALWAYS
 * Point D	ALWAYS	SOMETIMES
 * Point E	ALWAYS	SOMETIMES
 * Point F	SOMETIMES	SOMETIMES
 * Point G	NEVER	SOMETIMES
 */

public class OharaBoolean {
	
	public static void main(String[] args) {
		System.out.println(consecutive(1, 2, 3));
		System.out.println(consecutive(3, 4, 5));
		System.out.println(consecutive( -10, -8, -9));
		System.out.println(consecutive(3, 5, 7));
		System.out.println(consecutive(1, 2, 2));
		System.out.println(consecutive(7, 7, 9));
		
		System.out.println();
		System.out.println(monthApart(4, 15, 5, 22));
		System.out.println(monthApart(9, 19, 10, 17));
	}
	
	public static boolean consecutive(int a, int b, int c) {
		int[] i = {
				a, b, c
		};
		Arrays.sort(i);
		return (i[2] - i[1] == 1 && i[1] - i[0] == 1);
		// see consecutiveHardWay() if this is not valid code
	}
	
	public static boolean monthApart(int m1, int d1, int m2, int d2) {
		if (m2 - m1 == 1)
			return (d2 - d1 > 0);
		else if (m1 - m2 == 1)
			return (d1 - d2 > 0);
		else if (m1 == m2)
			return false;
		else
			return true;
	}
	
	@Deprecated
	// Just exists because I realized partway through that I could do this
	// better
	public static boolean consecutiveHardWay(int a, int b, int c) {
		if (a == b || b == c || a == c)
			return false;
		int max = Math.max(a, Math.max(b, c)); // max of {a,b,c}
		int min = Math.min(a, Math.min(b, c)); // min of {a,b,c}
		int mid; // middle of {a,b,c}
		if (min == a) {
			if (max == b)
				mid = c;
			else
				mid = b;
		} else if (min == b) {
			if (max == a)
				mid = c;
			else
				mid = a;
		} else {
			if (max == a)
				mid = b;
			else
				mid = a;
		}
		return (max - mid == 1 && mid - min == 1);
	}
}
