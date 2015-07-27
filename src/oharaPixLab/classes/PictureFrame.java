package oharaPixLab.classes;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class that holds a digital picture and displays it using a JFrame
 *
 * @author Barb Ericson
 */
public class PictureFrame {

	// //////////////// fields ////////////////////////////

	/** Main window used as the frame */
	JFrame frame = new JFrame();

	/** ImageIcon used to display the picture in the label */
	ImageIcon imageIcon = new ImageIcon();

	/** Label used to display the picture */
	private JLabel label = new JLabel(this.imageIcon);

	/** Digital Picture to display */
	private DigitalPicture picture;

	// /////////////// constructors ////////////////////////

	/**
	 * A constructor that takes no arguments. This is needed for subclasses of this class
	 */
	public PictureFrame() {
		// set up the frame
		this.initFrame();
	}

	/**
	 * A constructor that takes a picture to display
	 *
	 * @param picture the digital picture to display in the picture frame
	 */
	public PictureFrame(DigitalPicture picture) {
		// set the current object's picture to the passed in picture
		this.picture = picture;

		// set up the frame
		this.initFrame();
	}

	// /////////////////////// methods ///////////////////////////////

	/**
	 * Method to set the picture to show in this picture frame
	 *
	 * @param picture the new picture to use
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
		this.imageIcon.setImage(picture.getImage());
		this.frame.pack();
		this.frame.repaint();
	}

	/**
	 * A method to update the picture frame image with the image in the picture
	 */
	public void updateImage() {
		// only do this if there is a picture
		if (this.picture != null) {
			// set the image for the image icon from the picture
			this.imageIcon.setImage(this.picture.getImage());

			// set the title of the frame to the title of the picture
			this.frame.setTitle(this.picture.getTitle());

		}
	}

	/**
	 * A method to update the picture frame image with the image in the picture and show it
	 */
	public void updateImageAndShowIt() {
		// first update the image
		this.updateImage();

		// now make sure it is shown
		this.frame.setVisible(true);
	}

	/**
	 * A method to make sure the frame is displayed
	 */
	public void displayImage() {
		this.frame.setVisible(true);
	}

	/**
	 * A method to hide the frame
	 */
	public void hide() {
		this.frame.setVisible(false);
	}

	/**
	 * A method to set the visible flag on the frame
	 *
	 * @param flag the flag to use
	 */
	public void setVisible(boolean flag) {
		this.frame.setVisible(flag);
	}

	/**
	 * A method to close a picture frame
	 */
	public void close() {
		this.frame.setVisible(false);
		this.frame.dispose();
	}

	/**
	 * Method to set the title for the picture frame
	 *
	 * @param title the title to use
	 */
	public void setTitle(String title) {
		this.frame.setTitle(title);
	}

	/**
	 * Method to force the picture frame to repaint (redraw)
	 */
	public void repaint() {

		// make it visible
		this.frame.setVisible(true);

		// update the image from the picture
		this.updateImage();

		// tell the JFrame to handle the repaint
		this.frame.repaint();
	}

	/**
	 * A method to initialize the picture frame
	 */
	private void initFrame() {

		// set the image for the picture frame
		this.updateImage();

		// add the label to the frame
		this.frame.getContentPane().add(this.label);

		// pack the frame (set the size to as big as it needs to be)
		this.frame.pack();

		// make the frame visible
		this.frame.setVisible(true);
	}

}
