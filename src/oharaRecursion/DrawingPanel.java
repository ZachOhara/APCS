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

 package oharaRecursion;

/*
 Stuart Reges and Marty Stepp
 07/01/2005

 The DrawingPanel class provides a simple interface for drawing persistent
 images using a Graphics object.  An internal BufferedImage object is used
 to keep track of what has been drawn.  A client of the class simply
 constructs a DrawingPanel of a particular size and then draws on it with
 the Graphics object, setting the background color if they so choose.

 To ensure that the image is always displayed, a timer calls repaint at
 regular intervals.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

public class DrawingPanel implements ActionListener {

	private static final boolean PRETTY = true; // true to anti-alias

	private int width, height; // dimensions of window frame
	private JFrame frame; // overall window frame
	private JPanel panel; // overall drawing surface
	private BufferedImage image; // remembers drawing commands
	private Graphics2D g2; // graphics context for painting
	private JLabel statusBar; // status bar showing mouse position

	public static final int DELAY = 250; // delay between repaints in millis

	// construct a drawing panel of given width and height enclosed in a window
	public DrawingPanel(int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		this.statusBar = new JLabel(" ");
		this.statusBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.panel.setBackground(Color.WHITE);
		this.panel.setPreferredSize(new Dimension(width, height));
		this.panel.add(new JLabel(new ImageIcon(this.image)));

		// listen to mouse movement
		MouseInputAdapter listener = new MouseInputAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				DrawingPanel.this.statusBar.setText("(" + e.getX() + ", " + e.getY() + ")");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				DrawingPanel.this.statusBar.setText(" ");
			}
		};
		this.panel.addMouseListener(listener);
		this.panel.addMouseMotionListener(listener);

		this.g2 = (Graphics2D) this.image.getGraphics();
		this.g2.setColor(Color.BLACK);
		if (DrawingPanel.PRETTY) {
			this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			this.g2.setStroke(new BasicStroke(1.1f));
		}

		this.frame = new JFrame("Drawing Panel");
		this.frame.setResizable(false);
		this.frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				DrawingPanel.this.save("output.png");
				System.exit(0);
			}
		});
		this.frame.getContentPane().add(this.panel);
		this.frame.getContentPane().add(this.statusBar, "South");
		this.frame.pack();
		this.frame.setVisible(true);

		new Timer(DrawingPanel.DELAY, this).start();
	}

	// used for an internal timer that keeps repainting
	@Override
	public void actionPerformed(ActionEvent e) {
		this.panel.repaint();
	}

	// obtain the Graphics object to draw on the panel
	public Graphics2D getGraphics() {
		return this.g2;
	}

	// set the background color of the drawing panel
	public void setBackground(Color c) {
		this.panel.setBackground(c);
	}

	// show or hide the drawing panel on the screen
	public void setVisible(boolean visible) {
		this.frame.setVisible(visible);
	}

	// makes the program pause for the given amount of time,
	// allowing for animation
	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	// makes drawing panel become the frontmost window on the screen
	public void toFront() {
		this.frame.toFront();
	}

	// take the current contents of the panel and write them to a file
	public void save(String filename) {
		String extension = filename.substring(filename.lastIndexOf(".") + 1);

		// create second image so we get the background color
		BufferedImage image2 = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image2.getGraphics();
		g.setColor(this.panel.getBackground());
		g.fillRect(0, 0, this.width, this.height);
		g.drawImage(this.image, 0, 0, this.panel);

		// write file
		try {
			javax.imageio.ImageIO.write(image2, extension, new java.io.File(filename));
		} catch (java.io.IOException e) {
			System.err.println("Unable to save image:\n" + e);
		}
	}
}
