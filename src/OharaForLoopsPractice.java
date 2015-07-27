/* Copyright (C) 2014-2015 Zach Ohara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/* Problem 1:
 * 1 3 5 7 9
 *
 * 1 * 3 * 11 *
 *
 * 1 3 1 2 1 1
 * 2 3 2 2 2 1
 * 3 3 3 2 3 1
 *
 * 4 0 4 3
 * 5 0 5 3
 *
 * 1 3 0 1 3 1
 * 1 2 0 1 2 1
 * 1 1 0 1 1 1
 *
 * 2 3 0 2 3 1
 * 2 2 0 2 2 1
 * 2 1 0 2 1 1
 */

/* Problem 2:
 *  i | j | output
 * ---+---+-------
 *  1 | 1 | " "
 *    | 2 | " "
 *    | 3 | " "
 *    | 1 | "*"
 *    |   | "\n"
 *  2 | 1 | " "
 *    | 2 | " "
 *    | 1 | "*"
 *    | 2 | "*"
 *    | 3 | "*"
 *    |   | "\n"
 *  3 | 1 | " "
 *    | 1 | "*"
 *    | 2 | "*"
 *    | 3 | "*"
 *    | 4 | "*"
 *    | 5 | "*"
 *    |   | "\n"
 *
 * Condensed output:
 * |   *
 * |  ***
 * | *****
 */

/* Problem 3:
 * (Expressions are given in the order they appear in on the document)
 * 1. 15 * i - 11
 * 2. -10 * i + 40
 * 3. 4 * i - 11
 * 4. -3 * i + 100
 */

public class OharaForLoopsPractice {

	public static void main(String[] args) {
		System.out.println("Problems 1-3 shown in comments\n");
		System.out.println("Problem 4:\n");
		OharaForLoopsPractice.problem4();
		System.out.println("\nProblem 5:\n");
		OharaForLoopsPractice.problem5();
		System.out.println("\nProblem 6:\n");
		OharaForLoopsPractice.problem6();
		System.out.println("\nProblem 7:\n");
		OharaForLoopsPractice.problem7();
		System.out.println("\nProblem 8:\n");
		OharaForLoopsPractice.problem8();
	}

	public static void problem4() {
		int sum = 0;
		for (int i = 1; i <= 6; i++) {
			sum += i;
		}
		System.out.println(sum);
	}

	public static void problem5() {
		// How do you completely skim over 90% of new content in a book?
		// Sara has much bigger problems than not being certified
		System.out.println("Months | Percent Learned");
		System.out.println("-------+----------------");
		double toLearn = 100;
		String spacing = "      | ";
		for (double i = 0; i <= 20; i++) {
			if (i == 10) {
				spacing = spacing.substring(1);
			}
			System.out.println((int) i + spacing + (int) (100 - toLearn));
			toLearn *= 0.9;
		}
		// She can read the same book for 20 months, and still not get a 90 on
		// the test?
	}

	public static void problem6() {
		System.out.print("1, ");
		System.out.print("1, ");
		int n1 = 1;
		int n2 = 1;
		int n3;
		for (int i = 1; i <= 12; i++) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
			System.out.print(n3 + ", ");
		}
		System.out.println();
	}

	public static void problem7() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	public static void problem8() {
		// Yes, I know it said A *OR* B, but I wanted to do both anyway
		System.out.println("Output A:");
		for (int i = 1; i <= 7; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(i);
			}
			System.out.println();
		}

		System.out.println("\nOutput B:");
		for (int i = 1; i <= 5; i++) {
			String dashes = "";
			for (int j = 0; j < 6 - i; j++) {
				dashes += "-";
			}
			System.out.print(dashes);
			for (int j = 0; j < i * 2 - 1; j++) {
				System.out.print(i * 2 - 1);
			}
			System.out.println(dashes);
		}
	}

	// Used in problem 5
	// Calculates how much of the book Sara has learned after n months
	// Deprecated to reduce complexity
	@Deprecated
	public static int percentLearned(double timesRead) {
		return (int) (100 - (100 * Math.pow(0.9, timesRead)));
	}

	// Used in problem 7
	// Calculates the nth fibonacci number recursively
	// Deprecated to better fit project guidelines
	@Deprecated
	public static int fibonacci(int k) {
		if (k <= 2) {
			return 1;
		} else {
			return OharaForLoopsPractice.fibonacci(k - 1) + OharaForLoopsPractice.fibonacci(k - 2);
		}
	}

}
