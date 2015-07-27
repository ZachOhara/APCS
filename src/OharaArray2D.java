import java.util.Arrays;

public class OharaArray2D {

	public static void main(String[] args) {
		OharaArray2D.problem1();
		OharaArray2D.problem2();
		OharaArray2D.problem3();
		OharaArray2D.problem4();
		OharaArray2D.problem5();
	}

	// Problem 1
	public static void problem1() {
		System.out.println("Problem 1");
		int[][] matrix = new int[6][8];
		// initialize the array
		for (int i = 0; i < matrix.length; i++) {
			int[] standardRow = {1, 2, 3, 4, 5, 6, 7, 8};
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = standardRow[j];
			}
		}
		OharaArray2D.printArray2D(matrix);
		OharaArray2D.switchRows(matrix);
		System.out.println();
		OharaArray2D.printArray2D(matrix);
	}

	public static void switchRows(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			a[i][4] = a[i][1];
		}
	}

	// Problem 2
	public static void problem2() {
		System.out.println("Problem 2");
		int[][] grid = new int[4][6];
		for (int i = 0; i < grid[1].length; i++) {
			grid[1][i] = 2 + (3 * i);
		}
		for (int[] a : grid) {
			System.out.print("[");
			for (int i : a) {
				System.out.print(i + "\t");
			}
			System.out.println("]");
		}
	}

	// Problem 3
	public static void problem3() {
		System.out.println("Problem 3");
		int[][] a = { {1, 2}, {3, 4}};
		int[][] b = { {5, 6}, {7, 8}};
		OharaArray2D.printArray2DFlat(a);
		OharaArray2D.printArray2DFlat(b);
		System.out.println();
		int[][] c = OharaArray2D.matrixAdd(a, b);
		OharaArray2D.printArray2DFlat(c);
	}

	public static int[][] matrixAdd(int[][] a, int[][] b) {
		int[][] c = new int[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				c[i][j] = a[i][j] + b[i][j];
			}
		}
		return c;
	}

	// Problem 4
	public static void problem4() {
		System.out.println("Problem 4");
		double[][] exp = new double[10][4];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				exp[i][j] = Math.pow(i + 1, j + 1);
			}
		}
		for (double[] a : exp) {
			System.out.print("[");
			for (double d : a) {
				System.out.print(d + "\t");
			}
			System.out.println("]");
		}
	}

	// Problem 5
	public static void problem5() {
		System.out.println("Problem 5");
		String[][] board = { {"X", "O", "O"}, {" ", "X", " "}, {" ", " ", "O"}};
		for (int i = 0; i < 3; i++) {
			System.out.println("-------------");
			for (int j = 0; j < 3; j++) {
				System.out.print("| " + board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("-------------");
	}

	// All problems
	public static void printArray2D(int[][] foo) {
		for (int i = 0; i < foo.length; i++) {
			System.out.println(Arrays.toString(foo[i]));
		}
	}

	public static void printArray2DFlat(int[][] foo) {
		System.out.print("[");
		for (int[] a : foo) {
			System.out.print(Arrays.toString(a));
			if (a != foo[foo.length - 1]) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

}
