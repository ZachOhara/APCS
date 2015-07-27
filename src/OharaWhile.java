import java.util.Scanner;

public class OharaWhile {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		OharaWhile.printAverage(console);
		OharaWhile.printFactors(72);
		OharaWhile.randomX();
		OharaWhile.randomWalk();
	}

	/* Place your methods' body below. */
	public static void printAverage(Scanner console) {
		int sum = 0;
		int terms = 0;
		int last;
		do {
			System.out.print("Type a number: ");
			last = console.nextInt();
			sum += last;
			terms++;
		} while (last >= 0);

		terms--; // undo effects of negative number
		sum -= last;

		if (terms > 0) {
			System.out.println("Average was " + ((double) sum / terms));
		}
	}

	public static void printFactors(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				if (i != n) {
					System.out.print(i + " and ");
				} else {
					// is last loop
					System.out.println(i);
				}
			}
		}
	}

	public static void randomX() {
		int len;
		do {
			len = 5 + (int) (15 * Math.random());
			for (int i = 0; i < len; i++) {
				System.out.print("x");
			}
			System.out.println();
		} while (len < 16);
	}

	public static void randomWalk() {
		int pos = 0;
		double rand;
		int dPos; // delta pos
		System.out.println("position = 0");
		while (Math.abs(pos) < 3) {
			rand = Math.random();
			if (rand < 0.5) {
				dPos = 1;
			} else {
				dPos = -1;
			}
			System.out.println("position = " + (pos += dPos));
		}
	}
}
