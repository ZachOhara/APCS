package oharaPixLab.classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 * Class to display an image and the current location with a + sign
 *
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class ImageDisplay extends JPanel implements Scrollable {

	private static final long serialVersionUID = 1L;
	// ///////////////////////// fields (attributes ///////////////////////////
	/** the image to draw */
	private Image image;
	/** the preferred size of the display */
	private Dimension prefSize;
	/** the current x index */
	private int currentX = 0;
	/** the current y index */
	private int currentY = 0;

	// ////////////////////////// constructors /////////////////////////////////

	/**
	 * Constructor that takes the image to display
	 *
	 * @param theImage the image to display
	 */
	public ImageDisplay(Image theImage) {
		this.image = theImage;
		this.prefSize = new Dimension(this.image.getWidth(this), this.image.getHeight(this));
		this.setPreferredSize(this.prefSize);
		this.revalidate();
	}

	/**
	 * Constructor that takes the image and current x and y
	 *
	 * @param theImage the image to display
	 * @param x the current x value to use
	 * @param y the current y value to use
	 */
	public ImageDisplay(Image theImage, int x, int y) {
		this(theImage);
		this.currentX = x;
		this.currentY = y;
	}

	// //////////////////// methods
	// /////////////////////////////////////////////

	/**
	 * Method to get the image
	 *
	 * @return the image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Method to get the current x
	 *
	 * @return the current x value
	 */
	public int getCurrentX() {
		return this.currentX;
	}

	/**
	 * Method to get the current y
	 *
	 * @return the current y value
	 */
	public int getCurrentY() {
		return this.currentY;
	}

	/**
	 * Method to set the current x
	 *
	 * @param x the x value to use
	 */
	public void setCurrentX(int x) {
		this.currentX = x;
		this.repaint();
	}

	/**
	 * Method to set the current y
	 *
	 * @param y the y value to use
	 */
	public void setCurrentY(int y) {
		this.currentY = y;
		this.repaint();
	}

	/**
	 * Method to set the image
	 *
	 * @param theImage the new image to use
	 */
	public void setImage(Image theImage) {
		this.image = theImage;
		this.setPreferredSize(new Dimension(this.image.getWidth(this), this.image.getHeight(this)));
		this.repaint();
	}

	/**
	 * Method to return the preferred size
	 *
	 * @return the preferred size of this component
	 */
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return this.prefSize;
	}

	/**
	 * Method to return the unit increment for scrolling
	 *
	 * @param visibleRect the visible rectangle
	 * @param orientation vertical or horizontal
	 * @param direction neg is up or left and pos is right or down
	 * @return the unit increment for arrow clicks
	 */
	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 1;
	}

	/**
	 * Method to return the block increment for scrolling
	 *
	 * @param visibleRect the visible rectangle
	 * @param orientation vertical or horizontal
	 * @param direction neg is up or left and pos is right or down
	 * @return the block increment for clicking in scroll area
	 */
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 10;
	}

	/**
	 * Method to check if the viewport width is the source width
	 *
	 * @return true if viewport and source have same width
	 */
	@Override
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	/**
	 * Method to check if the viewport height is the source height
	 *
	 * @return true if viewport and source have same height
	 */
	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	/**
	 * Method to handle displaying this object
	 *
	 * @param g the graphics object for drawing with
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int num = 3;
		int xStart = this.currentX - num;
		int xEnd = this.currentX + num;
		int yStart = this.currentY - num;
		int yEnd = this.currentY + num;
		int width = this.image.getWidth(this);
		int maxX = width - 1;
		int height = this.image.getHeight(this);
		int maxY = height - 1;

		// draw the image
		g.drawImage(this.image, 0, 0, this);

		// check if the current index is in the image
		if (this.currentX >= 0 && this.currentX < width && this.currentY >= 0 && this.currentY < height) {

			// check that the start and end values are visible
			if (xStart < 0) {
				xStart = 0;
			}
			if (xEnd > maxX) {
				xEnd = maxX;
			}
			if (yStart < 0) {
				yStart = 0;
			}
			if (yEnd > maxY) {
				yEnd = maxY;
			}

			// draw a small cross at the current x and y in yellow
			g.setColor(Color.yellow);
			g.drawLine(xStart, this.currentY, xEnd, this.currentY);
			g.drawLine(this.currentX, yStart, this.currentX, yEnd);
			g.setColor(Color.black);

			// outline the cross in black so that it shows up better
			int leftX = this.currentX - 1;
			int rightX = this.currentX + 1;
			int upY = this.currentY - 1;
			int downY = this.currentY + 1;
			if (xStart <= leftX && upY >= 0) {
				g.drawLine(xStart, upY, leftX, upY);
			}
			if (yStart <= upY && leftX >= 0) {
				g.drawLine(leftX, yStart, leftX, upY);
			}
			if (yStart <= upY && rightX <= maxX) {
				g.drawLine(rightX, yStart, rightX, upY);
			}
			if (upY >= 0 && rightX <= xEnd) {
				g.drawLine(rightX, upY, xEnd, upY);
			}
			if (downY < height && rightX <= xEnd) {
				g.drawLine(rightX, downY, xEnd, downY);
			}
			if (downY <= yEnd && rightX < width) {
				g.drawLine(rightX, downY, rightX, yEnd);
			}
			if (xStart <= leftX && downY < height) {
				g.drawLine(xStart, downY, leftX, downY);
			}
			if (leftX >= 0 && downY <= yEnd) {
				g.drawLine(leftX, downY, leftX, yEnd);
			}

		}
	}

}
