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

package oharaPixLab.classes;

public class IntArrayWorker {

	/** two dimensional matrix */
	private int[][] matrix = null;

	/**
	 * set the matrix to the passed one
	 *
	 * @param theMatrix the one to use
	 */
	public void setMatrix(int[][] theMatrix) {
		this.matrix = theMatrix;
	}

	/**
	 * Method to return the total
	 *
	 * @return the total of the values in the array
	 */
	public int getTotal() {
		int total = 0;
		for (int row = 0; row < this.matrix.length; row++) {
			for (int col = 0; col < this.matrix[0].length; col++) {
				total = total + this.matrix[row][col];
			}
		}
		return total;
	}

	/**
	 * Method to return the total using a nested for-each loop
	 *
	 * @return the total of the values in the array
	 */
	public int getTotalNested() {
		int total = 0;
		for (int[] rowArray : this.matrix) {
			for (int item : rowArray) {
				total = total + item;
			}
		}
		return total;
	}

	/**
	 * Method to fill with an increasing count
	 */
	public void fillCount() {
		int numCols = this.matrix[0].length;
		int count = 1;
		for (int row = 0; row < this.matrix.length; row++) {
			for (int col = 0; col < numCols; col++) {
				this.matrix[row][col] = count;
				count++;
			}
		}
	}

	/**
	 * print the values in the array in rows and columns
	 */
	public void print() {
		for (int row = 0; row < this.matrix.length; row++) {
			for (int col = 0; col < this.matrix[0].length; col++) {
				System.out.print(this.matrix[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * fill the array with a pattern
	 */
	public void fillPattern1() {
		for (int row = 0; row < this.matrix.length; row++) {
			for (int col = 0; col < this.matrix[0].length; col++) {
				if (row < col) {
					this.matrix[row][col] = 1;
				} else if (row == col) {
					this.matrix[row][col] = 2;
				} else {
					this.matrix[row][col] = 3;
				}
			}
		}
	}

	/**
	 * Method to count the instances of a given integer in the matrix
	 *
	 * @param find the integer to look for
	 * @return the instances of find in the matrix
	 */
	public int getCount(int find) {
		int count = 0;
		for (int[] row : this.matrix) {
			for (int i : row) {
				if (i == find) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Method to get the largest value in the matrix
	 *
	 * @return the max value in the matrix
	 */
	public int getLargest() {
		int max = Integer.MIN_VALUE;
		for (int[] row : this.matrix) {
			for (int i : row) {
				max = Math.max(max, i);
			}
		}
		return max;
	}

	/**
	 * Method to get the toal of a specified column
	 *
	 * @param col the index of the column to calculate
	 * @return the total of values in the column 'col'
	 */
	public int getColTotal(int col) {
		int total = 0;
		for (int[] row : this.matrix) {
			total += row[col];
		}
		return total;
	}

}
