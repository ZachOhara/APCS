package given;

public class ArrayDisputes {

	public static void mystery(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++)
			a[i] += b[b.length - 1 - i];
	}
	
	public static void main(String[] args) {
		int[] a1 = {1, 3, 5, 7, 9};
		int[] a2 = {1, 4, 9, 16, 25};
		printArray(a1);
		printArray(a2);
		mystery(a1, a2);
		printArray(a1);
		printArray(a2);
	}

	public static void printArray(int[] a) {
		for (int i: a)
			System.out.print(i + " ");
		System.out.println();
	}
	
}
