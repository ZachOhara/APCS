import java.util.Arrays;


public class OharaArrayMethods extends Object {

	public static void main(String[] args) {
		int[][] vals = new int[4][4];
		printArray2D(vals);
		System.out.println();
		setValues(vals);
		printArray2D(vals);
		System.out.println();
		
		int[][] vals2 = {{1, 4, 6},
					   {7, 2, 5},
					   {9, 8, 3}};
		printArray2D(vals2);
		System.out.println();
		reverseValues(vals2);
		printArray2D(vals2);
	}
	
	public static void setValues(int[][] matrix) {
		for (int i = 0; i < 4; i++)
			matrix[2][i] = 3;
		for (int[] arr : matrix)
			arr[3] = 4;
	}
	
	public static void reverseValues(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length/2 + 1; j++) {
				int swap = matrix[i][j];
				matrix[i][j] = matrix[i][matrix[i].length - j - 1];
				matrix[i][matrix[i].length - j - 1] = swap;
			}
		}
	}

	public static void printArray2D(int[][] foo) {
		for (int i = 0; i < foo.length; i++)
			System.out.println(Arrays.toString(foo[i]));
	}

}
