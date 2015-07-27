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

 package oharaFloorPlan.frq;

// Exam 3 Question 4

import java.util.ArrayList;

public class FloorPlan {

	/** The number of rows in this floor plan */
	private final int ROWS;

	/** The number of columns in this floor plan */
	private final int COLS;

	/**
	 * room[r][c] == 0 means the cell at location (r, c) is empty and available for placing
	 * furniture; room[r][c] == 1 means the location is not available
	 */
	private int[][] room;

	/********************** Extra **********************/

	/**
	 * Constructs a two-dimensional array with given dimensions, filled with zeroes
	 */
	public FloorPlan(int rows, int cols) {
		this.ROWS = rows;
		this.COLS = cols;
		this.room = new int[rows][cols];
	}

	/********************** Part (a) **********************/

	/**
	 * Returns true if the rectangular region between top and bottom and between left and
	 * right, including the borders, contains only zeroes; otherwise returns false.
	 * Precondition: top <= bottom, left <= right; the entire region is within room limits
	 */
	private boolean isEmptyRegion(int top, int bottom, int left, int right) {
		try {
			for (int row = top; row < bottom; row++) {
				for (int col = left; col < right; col++) {
					if (this.room[row][col] != 0) {
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/********************** Part (b) **********************/

	/**
	 * Returns true if a piece of furniture with given dimensions fits into this floor plan
	 * at a specified location. To fit, all the cells under the piece must be within the
	 * room and all the cells within a slightly larger region must be empty. The larger
	 * region consists of all the cells under the piece plus all the adjacent cells that
	 * are within the room limits.
	 *
	 * @param width the horizontal dimension of the piece
	 * @param height the vertical dimension of the piece
	 * @param ulCorner location of the upper left corner of the piece
	 */
	public boolean fits(int width, int height, Location ulCorner) {
		int ulCol = ulCorner.getCol();
		int ulRow = ulCorner.getRow();
		if (ulCol != 0) {
			ulCol--;
			width++;
		}
		if (ulRow != 0) {
			ulRow--;
			height++;
		}
		width++;
		height++;
		if (ulCol + width > this.COLS) {
			width = this.COLS - ulCol;
		}
		if (ulRow + height > this.ROWS) {
			height = this.ROWS - ulRow;
		}
		return this.isEmptyRegion(ulRow, ulRow + height, ulCol, ulCol + width);
	}

	/********************** Part (c) **********************/

	/**
	 * Returns a list of all locations where a piece of furniture with given dimensions can
	 * fit.
	 *
	 * @param width the horizontal dimension of the piece
	 * @param height the vertical dimension of the piece
	 */
	public ArrayList<Location> whereFits(int width, int height) {
		ArrayList<Location> locList = new ArrayList<Location>();
		for (int r = 0; r < this.ROWS; r++) {
			for (int c = 0; c < this.COLS; c++) {
				Location l = new Location(r, c);
				if (this.fits(width, height, l)) {
					locList.add(l);
				}
			}
		}
		return locList;
	}

	/********************** Test **********************/

	public static void main(String[] args) {
		/*
		 * 0xx000000 000000000 00000000x 00000000x 00000000x 00000000x 000000000 00000xxx0
		 */

		FloorPlan plan = new FloorPlan(8, 9);
		plan.room[0][1] = 1;
		plan.room[0][2] = 1;
		plan.room[0][6] = 1;
		plan.room[2][8] = 1;
		plan.room[3][8] = 1;
		plan.room[4][8] = 1;
		plan.room[5][8] = 1;
		plan.room[7][5] = 1;
		plan.room[7][6] = 1;
		plan.room[7][7] = 1;

		System.out.println(plan.whereFits(4, 2));
	}
}
