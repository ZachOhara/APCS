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

 import java.util.Arrays;

public class OharaArrayMethods extends Object {

	public static void main(String[] args) {
		int[][] vals = new int[4][4];
		OharaArrayMethods.printArray2D(vals);
		System.out.println();
		OharaArrayMethods.setValues(vals);
		OharaArrayMethods.printArray2D(vals);
		System.out.println();

		int[][] vals2 = { {1, 4, 6}, {7, 2, 5}, {9, 8, 3}};
		OharaArrayMethods.printArray2D(vals2);
		System.out.println();
		OharaArrayMethods.reverseValues(vals2);
		OharaArrayMethods.printArray2D(vals2);
	}

	public static void setValues(int[][] matrix) {
		for (int i = 0; i < 4; i++) {
			matrix[2][i] = 3;
		}
		for (int[] arr : matrix) {
			arr[3] = 4;
		}
	}

	public static void reverseValues(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length / 2 + 1; j++) {
				int swap = matrix[i][j];
				matrix[i][j] = matrix[i][matrix[i].length - j - 1];
				matrix[i][matrix[i].length - j - 1] = swap;
			}
		}
	}

	public static void printArray2D(int[][] foo) {
		for (int i = 0; i < foo.length; i++) {
			System.out.println(Arrays.toString(foo[i]));
		}
	}

}
