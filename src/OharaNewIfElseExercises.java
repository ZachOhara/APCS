import java.util.Scanner;

public class OharaNewIfElseExercises {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		OharaNewIfElseExercises.printRange(20, 10);
		OharaNewIfElseExercises.smallestLargest(new Scanner(System.in));
		OharaNewIfElseExercises.evenSumMax(new Scanner(System.in));
	}

	public static void printRange(int a, int b) {
		int increment = a < b ? 1 : -1;
		/*
		 * Would be done with if-statements: int increment = 1; if (b > a) increment = -1;
		 */
		System.out.print("[");
		for (int i = a; i != b; i += increment) {
			System.out.print(i + ", ");
		}
		System.out.println(b + "]"); // discluded from for loop because print
		// formatting. Easier this way.
	}

	public static void smallestLargest(Scanner input) {
		System.out.print("How many numbers do you want to enter? ");
		int n = input.nextInt();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int in;
		for (int i = 1; i <= n; i++) {
			System.out.print("Number " + i + ": ");
			in = input.nextInt();
			min = Math.min(min, in);
			max = Math.max(max, in);
		}
		System.out.println("Smallest: " + min);
		System.out.println("Largest: " + max);
	}

	public static void evenSumMax(Scanner input) {
		System.out.print("How many integers? ");
		int n = input.nextInt();
		int in;
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			System.out.print("Next integer? ");
			in = input.nextInt();
			if (in % 2 == 0) {
				sum += in;
				max = Math.max(max, in);
			}
		}
		System.out.println("Even sum = " + sum + ", even max = " + max);
	}

}
