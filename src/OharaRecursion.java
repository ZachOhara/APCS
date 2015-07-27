public class OharaRecursion {

	public static int multiplyEvens(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Cannot accept: " + n);
		}
		if (n == 1) {
			return 2;
		} else {
			return 2 * n * OharaRecursion.multiplyEvens(n - 1);
		}
	}

	public static void writeSquares(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("Cannot accept: " + n);
		}
		if (n == 1) {
			System.out.print("1");
		} else {
			if (n % 2 == 1) {
				System.out.print((n * n) + ", ");
			}
			OharaRecursion.writeSquares(n - 1);
			if (n % 2 == 0) {
				System.out.print(", " + (n * n));
			}
		}
	}

	public static void main(String[] args) {
		int[] testCases1 = {1, 2, 3, 4};
		for (int i : testCases1) {
			System.out.println(OharaRecursion.multiplyEvens(i));
		}

		int[] testCases2 = {5, 1, 8};
		for (int i : testCases2) {
			OharaRecursion.writeSquares(i);
			System.out.println();
		}
	}

}
