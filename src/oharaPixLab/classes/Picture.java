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

import java.awt.Color;
import java.awt.image.BufferedImage;

// resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and allows the
 * student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {

	// /////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super() child
		 * constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 *
	 * @param fileName the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 *
	 * @param height the height of the desired picture
	 * @param width the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 *
	 * @param copyPicture the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 *
	 * @param image the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	// //////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 *
	 * @return a string with information about the picture such as fileName, height and
	 * width.
	 */
	@Override
	public String toString() {
		String output =
				"Picture, filename " + this.getFileName() + " height " + this.getHeight() + " width "
						+ this.getWidth();
		return output;
	}

	/** Method to set the red to 0 */
	public void zeroRed() {
		this.setPixels(0, -1, -1);
	}

	/** Method to set the green to 0 */
	public void zeroGreen() {
		this.setPixels( -1, 0, -1);
	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		this.setPixels( -1, -1, 0);
	}

	/** Method to set the green and blue to 0 */
	public void keepOnlyRed() {
		this.setPixels( -1, 0, 0);
	}

	/** Method to set the red and blue to 0 */
	public void keepOnlyGreen() {
		this.setPixels(0, -1, 0);
	}

	/** Method to set the red and green to 0 */
	public void keepOnlyBlue() {
		this.setPixels(0, 0, -1);
	}

	/** Private method to set all pixels to the given values. -1 for unchanged */
	private void setPixels(int r, int g, int b) {
		for (Pixel[] rowArray : this.getPixels2D()) {
			for (Pixel pixel : rowArray) {
				if (r >= 0) {
					pixel.setRed(r);
				}
				if (g >= 0) {
					pixel.setGreen(g);
				}
				if (b >= 0) {
					pixel.setBlue(b);
				}
			}
		}
	}

	/** Method to set the pixels to their opposite colors */
	public void negate() {
		for (Pixel[] rowArray : this.getPixels2D()) {
			for (Pixel pixel : rowArray) {
				pixel.setRed(255 - pixel.getRed());
				pixel.setGreen(255 - pixel.getGreen());
				pixel.setBlue(255 - pixel.getBlue());
			}
		}
	}

	/** Method to set the pixels to a corresponding shade of gray */
	public void grayscale() {
		for (Pixel[] rowArray : this.getPixels2D()) {
			for (Pixel pixel : rowArray) {
				int average = pixel.getRed() + pixel.getGreen() + pixel.getBlue();
				average /= 3;
				pixel.setRed(average);
				pixel.setGreen(average);
				pixel.setBlue(average);
			}
		}
	}

	/*
	 * Commented because currently unused, but no want delete private static int
	 * getBrightness(Pixel p) { int average = p.getRed() + p.getGreen() + p.getBlue();
	 * average /= 3; return average; }
	 */

	/** Method to fix an 'underwater' image */
	public void fixUnderwater() {
		int minRed = 255;
		int minGreen = 255;
		int minBlue = 255;
		for (Pixel[] rowArray : this.getPixels2D()) {
			for (Pixel p : rowArray) {
				minRed = Math.min(minRed, p.getRed());
				minGreen = Math.min(minGreen, p.getGreen());
				minBlue = Math.min(minBlue, p.getBlue());
			}
		}
		this.addPixels( -minRed, -minGreen, -minBlue);
	}

	/** Method to fully saturate an image */
	public void saturate() {
		int maxRed = 0;
		int maxGreen = 0;
		int maxBlue = 0;
		for (Pixel[] rowArray : this.getPixels2D()) {
			for (Pixel p : rowArray) {
				maxRed = Math.max(maxRed, p.getRed());
				maxGreen = Math.max(maxGreen, p.getGreen());
				maxBlue = Math.max(maxBlue, p.getBlue());
			}
		}
		int max = Math.max(Math.max(maxRed, maxGreen), maxBlue);
		this.saturate((double) 255 / max);
	}

	/** Method to saturate an image by a given factor */
	public void saturate(double f) {
		this.multiplyPixels(f, f, f);
	}

	/** Private method to multiply pixel values by given factors */
	private void multiplyPixels(double r, double g, double b) {
		for (Pixel[] rowArray : this.getPixels2D()) {
			for (Pixel pixel : rowArray) {
				pixel.setRed((int) (r * pixel.getRed()));
				pixel.setGreen((int) (g * pixel.getGreen()));
				pixel.setBlue((int) (b * pixel.getBlue()));
			}
		}
	}

	/** Private method to add a given value to each pixel color */
	private void addPixels(int r, int g, int b) {
		for (Pixel[] rowArray : this.getPixels2D()) {
			for (Pixel pixel : rowArray) {
				pixel.setRed(r + pixel.getRed());
				pixel.setGreen(g + pixel.getGreen());
				pixel.setBlue(b + pixel.getBlue());
			}
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of the
	 * picture from left to right
	 */
	public void mirrorVertical() {
		this.mirrorVertical(true);
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of the
	 * picture from right to left
	 */
	public void mirrorVerticalRightToLeft() {
		this.mirrorVertical(false);
	}

	private void mirrorVertical(boolean isLeftToRight) {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				if (isLeftToRight) {
					rightPixel.setColor(leftPixel.getColor());
				} else {
					leftPixel.setColor(rightPixel.getColor());
				}
			}
		}
	}

	/**
	 * Method that mirrors the picture around a horizontal mirror in the center of the
	 * picture from top to bottom
	 */
	public void mirrorHorizontal() {
		this.mirrorHorizontal(true);
	}

	/**
	 * Method that mirrors the picture around a horizontal mirror in the center of the
	 * picture from bottom to top
	 */
	public void mirrorHorizontalBotToTop() {
		this.mirrorHorizontal(false);
	}

	private void mirrorHorizontal(boolean isTopToBottom) {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int col = 0; col < pixels[0].length; col++) {
			for (int row = 0; row < height / 2; row++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[height - 1 - row][col];
				if (isTopToBottom) {
					bottomPixel.setColor(topPixel.getColor());
				} else {
					topPixel.setColor(bottomPixel.getColor());
				}
			}
		}
	}

	/**
	 * Method that mirrors the picture around a diagonal mirror (drawn from top-left to
	 * bottom-right) in the center of the picture from top to bottom
	 */
	public void mirrorDiagonal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int col = 0; col < height; col++) {
			for (int row = 0; row < height; row++) {
				topPixel = pixels[col][height - 1 - row];
				bottomPixel = pixels[height - 1 - row][col];
				topPixel.setColor(bottomPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		// row 27 - 97
		// col 13 - mirrorPoint
		// extension: -263
		this.mirrorVertical(276, -263, 27, 97);
	}

	/** Method to mirror the arms of the snowman */
	public void mirrorArms() {
		this.mirrorVertical(206, -105, 155, 300);
		this.mirrorHorizontal(194, -40, 95, 315);
	}

	/** Method to mirror the seagull into a double-gull */
	public void mirrorGull() {
		this.mirrorVertical();
		// I never guessed it would be this easy...
	}

	private void mirrorVertical(int mirrorPoint, int extendFrom, int topRow, int bottomRow) {
		int count = 0;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		for (int row = topRow; row < bottomRow; row++) {
			for (int col = mirrorPoint + extendFrom; col != mirrorPoint; col -= Integer.signum(extendFrom)) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
				count++;
			}
		}
		System.out.println(count);
	}

	private void mirrorHorizontal(int mirrorPoint, int extendFrom, int lCol, int rCol) {
		int count = 0;
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		for (int col = lCol; col < rCol; col++) {
			for (int row = mirrorPoint + extendFrom; row != mirrorPoint; row -= Integer.signum(extendFrom)) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
				bottomPixel.setColor(topPixel.getColor());
				count++;
			}
		}
		System.out.println(count);
	}

	/**
	 * Copy from the passed fromPic to the specified startRow and startCol in the current
	 * picture
	 *
	 * @param fromPic the picture to copy from
	 * @param startRow the start row to copy to
	 * @param startCol the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		this.copy(fromPic, startRow, startRow + fromPic.getHeight(), startCol, startCol + fromPic.getWidth());
	}

	/**
	 * Copy from the passed fromPic to the specified startRow and startCol in the current
	 * picture
	 *
	 * @param fromPic the picture to copy from
	 * @param startRow the start row to copy to
	 * @param endRow the end row to copy to
	 * @param startCol the start column to copy to
	 * @param endCol the end column to copy to
	 */
	public void copy(Picture fromPic, int startRow, int endRow, int startCol, int endCol) {
		Pixel[][] toPix = this.getPixels2D();
		Pixel[][] fromPix = fromPic.getPixels2D();
		endRow = Picture.minimize(endRow, startRow + fromPic.getHeight(), this.getHeight());
		endCol = Picture.minimize(endCol, startCol + fromPic.getWidth(), this.getWidth());
		for (int row = startRow; row < endRow; row++) {
			for (int col = startCol; col < endCol; col++) {
				toPix[row][col].setColor(fromPix[row - startRow][col - startCol].getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	/** Method to create a second collage of several pictures */
	public void myCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 50, 0, 50);
		this.copy(flower2, 100, 150, 0, 50);
		this.copy(flower1, 200, 250, 0, 50);
		this.copy(flower2, 300, 350, 0, 50);
		this.copy(flower1, 400, 450, 0, 50);
		this.copy(flower2, 500, 550, 0, 50);
		this.mirrorVertical();
		this.write("myCollage.jpg");
	}

	/**
	 * Method to show large changes in color
	 *
	 * @param edgeDist the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel centerPixel = null;
		Pixel leftPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		Color dc = null;
		for (int row = 0; row < pixels.length - 1; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				centerPixel = pixels[row][col];
				leftPixel = pixels[row][col + 1];
				bottomPixel = pixels[row + 1][col];
				rightColor = leftPixel.getColor();
				dc = bottomPixel.getColor();
				if (centerPixel.colorDistance(rightColor) > edgeDist
						|| centerPixel.colorDistance(dc) > edgeDist) {
					centerPixel.setColor(Color.BLACK);
				} else {
					centerPixel.setColor(Color.WHITE);
				}
			}
		}
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

	/**
	 * @return the minimum of the three numbers
	 */
	private static int minimize(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}

}
