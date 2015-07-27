import java.util.Arrays;

/* Problem answers:
 * 
 * 1. On line 12 (the 'x++' in mystery) there is no semicolon.
 *    2 [0, 1]
 *    1 [0, 1]
 *    1 [1, 2]
 *    0 [1, 2]
 * 
 */
public class OharaMoreArrays {
	
	public static void main(String[] args) {
		String[] s1 = {
				"belt", "hat", "jelly", "bubble gum"
		};
		System.out.println("Avg len of " + Arrays.toString(s1) + " is "
				+ problem1(s1));
		System.out.println();
		
		String[] s2 = {
				"alpha", "beta", "gamma", "delta", "gamma", "beta", "alpha"
		};
		System.out.println("The array " + Arrays.toString(s2)
				+ " is a palindrome? " + problem2(s2));
		System.out.println();
		
		double[] list1 = {
				16.1, 12.3, 22.2, 14.4
		};
		double[] list2 = {
				1.5, 4.3, 7.0, 19.5, 25.1, 46.2
		};
		System.out.println("The array " + Arrays.toString(list1)
				+ " is sorted? " + isSorted(list1));
		System.out.println("The array " + Arrays.toString(list2)
				+ " is sorted? " + isSorted(list2));
		System.out.println();
		
		int[] bar = {
				3, 8, 12, 2, 9, 17, 43, -8, 46
		};
		int[] baz = {
				4, 7, 3, 9, 12, -47, 3, 74
		};
		System.out.println("The array " + Arrays.toString(bar) + " is unique? "
				+ isUnique(bar));
		System.out.println("The array " + Arrays.toString(baz) + " is unique? "
				+ isUnique(baz));
	}
	
	public static double problem1(String[] list) {
		double lengthSum = 0;
		for (String s : list)
			lengthSum += s.length();
		return lengthSum / list.length;
	}
	
	public static boolean problem2(String[] list) {
		boolean isPalindrome = true;
		for (int i = 0; i < list.length / 2; i++)
			if ( !list[i].equals(list[list.length - 1 - i]))
				isPalindrome = false;
		return isPalindrome;
	}
	
	// problem 3
	public static boolean isSorted(double[] foo) {
		boolean isSorted = true;
		for (int i = 1; i < foo.length; i++)
			if (foo[i] < foo[i - 1])
				isSorted = false;
		return isSorted;
	}
	
	// problem 4
	public static boolean isUnique(int[] foo) {
		boolean isUnique = true;
		for (int i = 0; i < foo.length; i++)
			for (int j = i + 1; j < foo.length; j++)
				if (foo[i] == foo[j])
					isUnique = false;
		return isUnique;
	}
	
	// problem 5
	// this method returns the amount of odd numbers in an array plus the number
	// of evens
	// this is different from the length of the array only in that this is much
	// slower and more pointless
	public static int mystery(int[] a) { // sound familiar?
		int odds = 0;
		int evens = 0;
		for (int i : a) {
			if (i % 2 == 0)
				evens++;
			else
				odds++;
		}
		return evens + odds;
	}
	
}
