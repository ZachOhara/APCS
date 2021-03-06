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

package oharaClassesProject;

//This provided class helps make it easier to draw the assignment's graphics.
//A DrawingPanel object is a drawing surface for painting 2D graphics.

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileFilter;

/**
 * The DrawingPanel class provides a simple interface for drawing persistent images using a
 * Graphics object. An internal BufferedImage object is used to keep track of what has been
 * drawn. A client of the class simply constructs a DrawingPanel of a particular size and
 * then draws on it with the Graphics object, setting the background color if they so
 * choose.
 * <p>
 * To ensure that the image is always displayed, a timer calls repaint at regular
 * intervals.
 * <p>
 * This version of DrawingPanel also saves animated GIFs, though this is kind of
 * hit-and-miss because animated GIFs are pretty sucky (256 color limit, large file size,
 * etc).
 *
 * @author Marty Stepp
 * @version January 5, 2010
 */
public final class DrawingPanel extends FileFilter implements ActionListener, MouseMotionListener, Runnable,
WindowListener {

	// inner class to represent one frame of an animated GIF
	private static class ImageFrame {

		public Image image;
		public int delay;

		public ImageFrame(Image image, int delay) {
			this.image = image;
			this.delay = delay / 10; // strangely, gif stores delay as sec/100
		}
	}

	// class constants
	public static final String ANIMATED_PROPERTY = "drawingpanel.animated";
	public static final String AUTO_ENABLE_ANIMATION_ON_SLEEP_PROPERTY = "drawingpanel.animateonsleep";
	public static final String DIFF_PROPERTY = "drawingpanel.diff";
	public static final String HEADLESS_PROPERTY = "drawingpanel.headless";
	public static final String MULTIPLE_PROPERTY = "drawingpanel.multiple";
	public static final String SAVE_PROPERTY = "drawingpanel.save";
	public static final String ANIMATION_FILE_NAME = "_drawingpanel_animation_save.txt";
	private static final String TITLE = "Drawing Panel";
	private static final String COURSE_WEB_SITE =
			"http://www.cs.washington.edu/education/courses/cse143/CurrentQtr/drawingpanel.txt";
	private static final int DELAY = 100; // delay between repaints in millis
	private static final int MAX_FRAMES = 100; // max animation frames
	private static final int MAX_SIZE = 10000; // max width/height
	private static final boolean DEBUG = false;
	private static int instances = 0;
	private static Thread shutdownThread = null;

	private static void checkAnimationSettings() {
		try {
			File settingsFile = new File(DrawingPanel.ANIMATION_FILE_NAME);
			if (settingsFile.exists()) {
				Scanner input = new Scanner(settingsFile);
				String animationSaveFileName = input.nextLine();
				input.close();
				// *** TODO: delete the file
				System.out.println("***");
				System.out.println("*** DrawingPanel saving animated GIF: "
						+ new File(animationSaveFileName).getName());
				System.out.println("***");
				settingsFile.delete();

				System.setProperty(DrawingPanel.ANIMATED_PROPERTY, "1");
				System.setProperty(DrawingPanel.SAVE_PROPERTY, animationSaveFileName);
			}
		} catch (Exception e) {
			if (DrawingPanel.DEBUG) {
				System.out.println("error checking animation settings: " + e);
			}
		}
	}

	private static boolean hasProperty(String name) {
		try {
			return System.getProperty(name) != null;
		} catch (SecurityException e) {
			if (DrawingPanel.DEBUG) {
				System.out.println("Security exception when trying to read " + name);
			}
			return false;
		}
	}

	private static boolean propertyIsTrue(String name) {
		try {
			String prop = System.getProperty(name);
			return prop != null
					&& (prop.equalsIgnoreCase("true") || prop.equalsIgnoreCase("yes") || prop
							.equalsIgnoreCase("1"));
		} catch (SecurityException e) {
			if (DrawingPanel.DEBUG) {
				System.out.println("Security exception when trying to read " + name);
			}
			return false;
		}
	}

	/*
	 * private static boolean propertyIsFalse(String name) { try { String prop =
	 * System.getProperty(name); return prop != null && (prop.equalsIgnoreCase("false") ||
	 * prop.equalsIgnoreCase("no") || prop.equalsIgnoreCase("0")); } catch
	 * (SecurityException e) { if (DEBUG)
	 * System.out.println("Security exception when trying to read " + name); return false;
	 * } }
	 */

	// Returns whether the 'main' thread is still running.
	private static boolean mainIsActive() {
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		int activeCount = group.activeCount();

		// look for the main thread in the current thread group
		Thread[] threads = new Thread[activeCount];
		group.enumerate(threads);
		for (int i = 0; i < threads.length; i++) {
			Thread thread = threads[i];
			String name = ("" + thread.getName()).toLowerCase();
			if (name.indexOf("main") >= 0 || name.indexOf("testrunner-assignmentrunner") >= 0) {
				// found main thread!
				// (TestRunnerApplet's main runner also counts as "main" thread)
				return thread.isAlive();
			}
		}

		// didn't find a running main thread; guess that main is done running
		return false;
	}

	private static boolean usingDrJava() {
		try {
			return System.getProperty("drjava.debug.port") != null
					|| System.getProperty("java.class.path").toLowerCase().indexOf("drjava") >= 0;
		} catch (SecurityException e) {
			// running as an applet, or something
			return false;
		}
	}

	private class ImagePanel extends JPanel {

		private static final long serialVersionUID = 0;
		private Image image;

		public ImagePanel(Image image) {
			this.setImage(image);
			this.setBackground(Color.WHITE);
			this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			if (DrawingPanel.this.currentZoom != 1) {
				g2.scale(DrawingPanel.this.currentZoom, DrawingPanel.this.currentZoom);
			}
			g2.drawImage(this.image, 0, 0, this);
		}

		public void setImage(Image image) {
			this.image = image;
			this.repaint();
		}
	}

	// fields
	private int width, height; // dimensions of window frame
	private JFrame frame; // overall window frame
	private JPanel panel; // overall drawing surface
	private ImagePanel imagePanel; // real drawing surface
	private BufferedImage image; // remembers drawing commands
	private Graphics2D g2; // graphics context for painting
	private JLabel statusBar; // status bar showing mouse position
	private JFileChooser chooser; // file chooser to save files
	private long createTime; // time at which DrawingPanel was constructed
	private Timer timer; // animation timer
	private ArrayList<ImageFrame> frames; // stores frames of animation to save
	private Gif89Encoder encoder;
	// private FileOutputStream stream;
	private Color backgroundColor = Color.WHITE;
	private String callingClassName; // name of class that constructed this
	// panel
	private boolean animated = false; // changes to true if sleep() is called
	private boolean PRETTY = true; // true to anti-alias
	private int instanceNumber;
	private int currentZoom = 1;
	private int initialPixel; // initial value in each pixel, for clear()

	// construct a drawing panel of given width and height enclosed in a window
	public DrawingPanel(int width, int height) {
		if (width < 0 || width > DrawingPanel.MAX_SIZE || height < 0 || height > DrawingPanel.MAX_SIZE) {
			throw new IllegalArgumentException("Illegal width/height: " + width + " x " + height);
		}

		DrawingPanel.checkAnimationSettings();

		synchronized (this.getClass()) {
			DrawingPanel.instances++;
			this.instanceNumber = DrawingPanel.instances; // each DrawingPanel stores its
			// own int
			// number

			if (DrawingPanel.shutdownThread == null && !DrawingPanel.usingDrJava()) {
				DrawingPanel.shutdownThread = new Thread(new Runnable() {

					// Runnable implementation; used for shutdown thread.
					@Override
					public void run() {
						try {
							while (true) {
								// maybe shut down the program, if no more
								// DrawingPanels are onscreen
								// and main has finished executing
								if ((DrawingPanel.instances == 0 || DrawingPanel.this.shouldSave())
										&& !DrawingPanel.mainIsActive()) {
									try {
										System.exit(0);
									} catch (SecurityException sex) {
									}
								}

								Thread.sleep(250);
							}
						} catch (Exception e) {
						}
					}
				});
				DrawingPanel.shutdownThread.setPriority(Thread.MIN_PRIORITY);
				DrawingPanel.shutdownThread.start();
			}
		}
		this.width = width;
		this.height = height;

		if (DrawingPanel.DEBUG) {
			System.out.println("w=" + width + ",h=" + height + ",anim=" + this.isAnimated() + ",graph="
					+ this.isGraphical() + ",save=" + this.shouldSave());
		}

		if (this.isAnimated() && this.shouldSave()) {
			// image must be no more than 256 colors
			this.image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);
			// image = new BufferedImage(width, height,
			// BufferedImage.TYPE_INT_ARGB);
			this.PRETTY = false; // turn off anti-aliasing to save palette colors

			// initially fill the entire frame with the background color,
			// because it won't show through via transparency like with a full
			// ARGB image
			Graphics g = this.image.getGraphics();
			g.setColor(this.backgroundColor);
			g.fillRect(0, 0, width + 1, height + 1);
		} else {
			this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		}
		this.initialPixel = this.image.getRGB(0, 0);

		this.g2 = (Graphics2D) this.image.getGraphics();
		this.g2.setColor(Color.BLACK);
		if (this.PRETTY) {
			this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}

		if (this.isAnimated()) {
			this.initializeAnimation();
		}

		if (this.isGraphical()) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			}

			this.statusBar = new JLabel(" ");
			this.statusBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			this.panel.setBackground(this.backgroundColor);
			this.panel.setPreferredSize(new Dimension(width, height));
			this.imagePanel = new ImagePanel(this.image);
			this.imagePanel.setBackground(this.backgroundColor);
			this.panel.add(this.imagePanel);

			// listen to mouse movement
			this.panel.addMouseMotionListener(this);

			// main window frame
			this.frame = new JFrame(DrawingPanel.TITLE);
			// frame.setResizable(false);
			this.frame.addWindowListener(this);
			// JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,
			// 0));
			JScrollPane center = new JScrollPane(this.panel);
			// center.add(panel);
			this.frame.getContentPane().add(center);
			this.frame.getContentPane().add(this.statusBar, "South");
			this.frame.setBackground(Color.DARK_GRAY);

			// menu bar
			this.setupMenuBar();

			this.frame.pack();
			this.center(this.frame);
			this.frame.setVisible(true);
			if ( !this.shouldSave()) {
				this.frame.toFront();
			}

			// repaint timer so that the screen will update
			this.createTime = System.currentTimeMillis();
			this.timer = new Timer(DrawingPanel.DELAY, this);
			this.timer.start();
		} else if (this.shouldSave()) {
			// headless mode; just set a hook on shutdown to save the image
			this.callingClassName = this.getCallingClassName();
			try {
				Runtime.getRuntime().addShutdownHook(new Thread(this));
			} catch (Exception e) {
				if (DrawingPanel.DEBUG) {
					System.out.println("unable to add shutdown hook: " + e);
				}
			}
		}
	}

	// method of FileFilter interface
	@Override
	public boolean accept(File file) {
		return file.isDirectory()
				|| (file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase()
						.endsWith(".gif"));
	}

	// used for an internal timer that keeps repainting
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof Timer) {
			// redraw the screen at regular intervals to catch all paint
			// operations
			this.panel.repaint();
			if (this.shouldDiff() && System.currentTimeMillis() > this.createTime + 4 * DrawingPanel.DELAY) {
				String expected = System.getProperty(DrawingPanel.DIFF_PROPERTY);
				try {
					String actual = this.saveToTempFile();
					DiffImage diff = new DiffImage(expected, actual);
					diff.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (IOException ioe) {
					System.err.println("Error diffing image: " + ioe);
				}
				this.timer.stop();
			} else if (this.shouldSave() && this.readyToClose()) {
				// auto-save-and-close if desired
				try {
					if (this.isAnimated()) {
						this.saveAnimated(System.getProperty(DrawingPanel.SAVE_PROPERTY));
					} else {
						this.save(System.getProperty(DrawingPanel.SAVE_PROPERTY));
					}
				} catch (IOException ioe) {
					System.err.println("Error saving image: " + ioe);
				}
				this.exit();
			}
		} else if (e.getActionCommand().equals("Exit")) {
			this.exit();
		} else if (e.getActionCommand().equals("Compare to File...")) {
			this.compareToFile();
		} else if (e.getActionCommand().equals("Compare to Web File...")) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					DrawingPanel.this.compareToURL();
				}
			}).start();
		} else if (e.getActionCommand().equals("Save As...")) {
			this.saveAs();
		} else if (e.getActionCommand().equals("Save Animated GIF...")) {
			this.saveAsAnimated();
		} else if (e.getActionCommand().equals("Zoom In")) {
			this.zoom(this.currentZoom + 1);
		} else if (e.getActionCommand().equals("Zoom Out")) {
			this.zoom(this.currentZoom - 1);
		} else if (e.getActionCommand().equals("Zoom Normal (100%)")) {
			this.zoom(1);
		} else if (e.getActionCommand().equals("About...")) {
			JOptionPane.showMessageDialog(this.frame, "DrawingPanel\n"
					+ "Graphical library class to support Building Java Programs textbook\n"
					+ "written by Marty Stepp and Stuart Reges\n" + "University of Washington\n\n"
					+ "please visit our web site at:\n" + "http://www.buildingjavaprograms.com/",

					"About DrawingPanel", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void addMouseListener(MouseInputListener listener) {
		this.panel.addMouseListener(listener);
		if (listener instanceof MouseMotionListener) {
			this.panel.addMouseMotionListener(listener);
		}
	}

	// erases all drawn shapes/lines/colors from the panel
	public void clear() {
		int[] pixels = new int[this.width * this.height];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = this.initialPixel;
		}
		this.image.setRGB(0, 0, this.width, this.height, pixels, 0, 1);
	}

	// method of FileFilter interface
	@Override
	public String getDescription() {
		return "Image files (*.png; *.gif)";
	}

	// obtain the Graphics object to draw on the panel
	public Graphics2D getGraphics() {
		return this.g2;
	}

	// returns the drawing panel's width in pixels
	public int getHeight() {
		return this.height;
	}

	// returns the drawing panel's pixel size (width, height) as a Dimension
	// object
	public Dimension getSize() {
		return new Dimension(this.width, this.height);
	}

	// returns the drawing panel's width in pixels
	public int getWidth() {
		return this.width;
	}

	// returns panel's current zoom factor
	public int getZoom() {
		return this.currentZoom;
	}

	// listens to mouse dragging
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	// listens to mouse movement
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX() / this.currentZoom;
		int y = e.getY() / this.currentZoom;
		this.setStatusBarText("(" + x + ", " + y + ")");
	}

	// run on shutdown to save the image
	@Override
	public void run() {
		if (DrawingPanel.DEBUG) {
			System.out.println("Running shutdown hook");
		}
		try {
			String filename = System.getProperty(DrawingPanel.SAVE_PROPERTY);
			if (filename == null) {
				filename = this.callingClassName + ".png";
			}

			if (this.isAnimated()) {
				this.saveAnimated(filename);
			} else {
				this.save(filename);
			}
		} catch (SecurityException e) {
		} catch (IOException e) {
			System.err.println("Error saving image: " + e);
		}
	}

	// take the current contents of the panel and write them to a file
	public void save(String filename) throws IOException {
		BufferedImage image2 = this.getImage();

		// if saving multiple panels, append number
		// (e.g. output_*.png becomes output_1.png, output_2.png, etc.)
		if (this.isMultiple()) {
			filename = filename.replaceAll("\\*", String.valueOf(this.instanceNumber));
		}

		int lastDot = filename.lastIndexOf(".");
		String extension = filename.substring(lastDot + 1);

		// write file
		// TODO: doesn't save background color I don't think
		ImageIO.write(image2, extension, new File(filename));
	}

	// take the current contents of the panel and write them to a file
	public void saveAnimated(String filename) throws IOException {
		// add one more final frame
		if (DrawingPanel.DEBUG) {
			System.out.println("saveAnimated(" + filename + ")");
		}
		this.frames.add(new ImageFrame(this.getImage(), 5000));
		// encoder.continueEncoding(stream, getImage(), 5000);

		// Gif89Encoder gifenc = new Gif89Encoder();

		// add each frame of animation to the encoder
		try {
			for (int i = 0; i < this.frames.size(); i++) {
				ImageFrame imageFrame = this.frames.get(i);
				this.encoder.addFrame(imageFrame.image);
				this.encoder.getFrameAt(i).setDelay(imageFrame.delay);
				imageFrame.image.flush();
				this.frames.set(i, null);
			}
		} catch (OutOfMemoryError e) {
			System.out.println("Out of memory when saving");
		}

		// gifenc.setComments(annotation);
		// gifenc.setUniformDelay((int) Math.round(100 / frames_per_second));
		// gifenc.setUniformDelay(DELAY);
		// encoder.setBackground(backgroundColor);
		this.encoder.setLoopCount(0);
		this.encoder.encode(new FileOutputStream(filename));
	}

	// set the background color of the drawing panel
	public void setBackground(Color c) {
		Color oldBackgroundColor = this.backgroundColor;
		this.backgroundColor = c;
		if (this.isGraphical()) {
			this.panel.setBackground(c);
			this.imagePanel.setBackground(c);
		}

		// with animated images, need to palette-swap the old bg color for the
		// new
		// because there's no notion of transparency in a palettized 8-bit image
		if (this.isAnimated()) {
			this.replaceColor(this.image, oldBackgroundColor, c);
		}
	}

	// sets the drawing panel's height in pixels to the given value
	// After calling this method, the client must call getGraphics() again
	// to get the new graphics context of the newly enlarged image buffer.
	public void setHeight(int height) {
		this.setSize(this.getWidth(), height);
	}

	// sets the drawing panel's pixel size (width, height) to the given values
	// After calling this method, the client must call getGraphics() again
	// to get the new graphics context of the newly enlarged image buffer.
	public void setSize(int width, int height) {
		// replace the image buffer for drawing
		BufferedImage newImage = new BufferedImage(width, height, this.image.getType());
		this.imagePanel.setImage(newImage);
		newImage.getGraphics().drawImage(this.image, 0, 0, this.imagePanel);

		this.width = width;
		this.height = height;
		this.image = newImage;
		this.g2 = (Graphics2D) newImage.getGraphics();
		this.g2.setColor(Color.BLACK);
		if (this.PRETTY) {
			this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		this.zoom(this.currentZoom);
		if (this.isGraphical()) {
			this.frame.pack();
		}
	}

	// show or hide the drawing panel on the screen
	public void setVisible(boolean visible) {
		if (this.isGraphical()) {
			this.frame.setVisible(visible);
		}
	}

	// sets the drawing panel's width in pixels to the given value
	// After calling this method, the client must call getGraphics() again
	// to get the new graphics context of the newly enlarged image buffer.
	public void setWidth(int width) {
		this.setSize(width, this.getHeight());
	}

	// makes the program pause for the given amount of time,
	// allowing for animation
	public void sleep(int millis) {
		if (this.isGraphical() && this.frame.isVisible()) {
			// if not even displaying, we don't actually need to sleep
			if (millis > 0) {
				try {
					Thread.sleep(millis);
					this.panel.repaint();
					this.toFront();
				} catch (Exception e) {
				}
			}
		}

		// manually enable animation if necessary
		if ( !this.isAnimated() && !this.isMultiple() && this.autoEnableAnimationOnSleep()) {
			this.animated = true;
			this.initializeAnimation();
		}

		// capture a frame of animation
		if (this.isAnimated() && this.shouldSave() && !this.isMultiple()) {
			try {
				if (this.frames.size() < DrawingPanel.MAX_FRAMES) {
					this.frames.add(new ImageFrame(this.getImage(), millis));
				}

				// reset creation timer so that we won't save/close just yet
				this.createTime = System.currentTimeMillis();
			} catch (OutOfMemoryError e) {
				System.out.println("Out of memory after capturing " + this.frames.size() + " frames");
			}
		}
	}

	// moves window on top of other windows
	public void toFront() {
		this.frame.toFront();
	}

	// called when DrawingPanel closes, to potentially exit the program
	@Override
	public void windowClosing(WindowEvent event) {
		this.frame.setVisible(false);
		synchronized (this.getClass()) {
			DrawingPanel.instances--;
		}
		this.frame.dispose();
	}

	// methods required by WindowListener interface
	@Override
	public void windowActivated(WindowEvent event) {
	}

	@Override
	public void windowClosed(WindowEvent event) {
	}

	@Override
	public void windowDeactivated(WindowEvent event) {
	}

	@Override
	public void windowDeiconified(WindowEvent event) {
	}

	@Override
	public void windowIconified(WindowEvent event) {
	}

	@Override
	public void windowOpened(WindowEvent event) {
	}

	// zooms the drawing panel in/out to the given factor
	// factor should be >= 1
	public void zoom(int zoomFactor) {
		this.currentZoom = Math.max(1, zoomFactor);
		if (this.isGraphical()) {
			Dimension size = new Dimension(this.width * this.currentZoom, this.height * this.currentZoom);
			this.imagePanel.setPreferredSize(size);
			this.panel.setPreferredSize(size);
			this.panel.revalidate();
			this.imagePanel.setSize(size);
			this.frame.getContentPane().validate();
			this.imagePanel.repaint();
			this.setStatusBarText(" ");

			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			if (size.width <= screen.width && size.height <= screen.height) {
				this.frame.pack();
				this.center(this.frame);
			}
		}
	}

	// moves given jframe to center of screen
	private void center(Window frame) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();

		int x = Math.max(0, (screen.width - frame.getWidth()) / 2);
		int y = Math.max(0, (screen.height - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	// constructs and initializes JFileChooser object if necessary
	private void checkChooser() {
		if (this.chooser == null) {
			// TODO: fix security on applet mode
			this.chooser = new JFileChooser(System.getProperty("user.dir"));
			this.chooser.setMultiSelectionEnabled(false);
			this.chooser.setFileFilter(this);
		}
	}

	// compares current DrawingPanel image to an image file on disk
	private void compareToFile() {
		// save current image to a temp file
		try {
			String tempFile = this.saveToTempFile();

			// use file chooser dialog to find image to compare against
			this.checkChooser();
			if (this.chooser.showOpenDialog(this.frame) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			// user chose a file; let's diff it
			new DiffImage(this.chooser.getSelectedFile().toString(), tempFile);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this.frame, "Unable to compare images: \n" + ioe);
		}
	}

	// compares current DrawingPanel image to an image file on the web
	private void compareToURL() {
		// save current image to a temp file
		try {
			String tempFile = this.saveToTempFile();

			// get list of images to compare against from web site
			URL url = new URL(DrawingPanel.COURSE_WEB_SITE);
			Scanner input = new Scanner(url.openStream());
			List<String> lines = new ArrayList<String>();
			List<String> filenames = new ArrayList<String>();
			while (input.hasNextLine()) {
				String line = input.nextLine().trim();
				if (line.length() > 0 && !line.startsWith("#")) {
					lines.add(line);

					// get filename
					int lastSlash = line.lastIndexOf('/');
					if (lastSlash >= 0) {
						line = line.substring(lastSlash + 1);
					}

					// remove extension
					int dot = line.lastIndexOf('.');
					if (dot >= 0) {
						line = line.substring(0, dot);
					}

					filenames.add(line);
				}
			}

			input.close();

			if (filenames.isEmpty()) {
				JOptionPane.showMessageDialog(this.frame, "No valid web files found to compare against.",
						"Error: no web files found", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				String fileURL = null;
				if (filenames.size() == 1) {
					// only one choice; take it
					fileURL = lines.get(0);
				} else {
					// user chooses file to compare against
					int choice =
							this.showOptionDialog(this.frame, "File to compare against?", "Choose File",
									filenames.toArray(new String[0]));
					if (choice < 0) {
						return;
					}

					// user chose a file; let's diff it
					fileURL = lines.get(choice);
				}
				if (DrawingPanel.DEBUG) {
					System.out.println(fileURL);
				}
				new DiffImage(fileURL, tempFile);
			}
		} catch (NoRouteToHostException nrthe) {
			JOptionPane
			.showMessageDialog(
					this.frame,
					"You do not appear to have a working internet connection.\nPlease check your internet settings and try again.\n\n"
							+ nrthe);
		} catch (UnknownHostException uhe) {
			JOptionPane.showMessageDialog(this.frame, "Internet connection error: \n" + uhe);
		} catch (SocketException se) {
			JOptionPane.showMessageDialog(this.frame, "Internet connection error: \n" + se);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this.frame, "Unable to compare images: \n" + ioe);
		}
	}

	// closes the frame and exits the program
	private void exit() {
		if (this.isGraphical()) {
			this.frame.setVisible(false);
			this.frame.dispose();
		}
		try {
			System.exit(0);
		} catch (SecurityException e) {
			// if we're running in an applet or something, can't do System.exit
		}
	}

	// returns a best guess about the name of the class that constructed this
	// panel
	private String getCallingClassName() {
		StackTraceElement[] stack = new RuntimeException().getStackTrace();
		String className = this.getClass().getName();
		for (StackTraceElement element : stack) {
			String cl = element.getClassName();
			if ( !className.equals(cl)) {
				className = cl;
				break;
			}
		}

		return className;
	}

	private BufferedImage getImage() {
		// create second image so we get the background color
		BufferedImage image2;
		if (this.isAnimated()) {
			image2 = new BufferedImage(this.width, this.height, BufferedImage.TYPE_BYTE_INDEXED);
		} else {
			image2 = new BufferedImage(this.width, this.height, this.image.getType());
		}
		Graphics g = image2.getGraphics();
		if (DrawingPanel.DEBUG) {
			System.out.println("getImage setting background to " + this.backgroundColor);
		}
		g.setColor(this.backgroundColor);
		g.fillRect(0, 0, this.width, this.height);
		g.drawImage(this.image, 0, 0, this.panel);
		return image2;
	}

	private void initializeAnimation() {
		this.frames = new ArrayList<ImageFrame>();
		this.encoder = new Gif89Encoder();
		/*
		 * try { if (hasProperty(SAVE_PROPERTY)) { stream = new
		 * FileOutputStream(System.getProperty(SAVE_PROPERTY)); } //
		 * encoder.startEncoding(stream); } catch (IOException e) { System.out.println(e);
		 * }
		 */
	}

	private boolean autoEnableAnimationOnSleep() {
		return DrawingPanel.propertyIsTrue(DrawingPanel.AUTO_ENABLE_ANIMATION_ON_SLEEP_PROPERTY);
	}

	private boolean isAnimated() {
		return this.animated || DrawingPanel.propertyIsTrue(DrawingPanel.ANIMATED_PROPERTY);
	}

	private boolean isGraphical() {
		return !DrawingPanel.hasProperty(DrawingPanel.SAVE_PROPERTY)
				&& !DrawingPanel.hasProperty(DrawingPanel.HEADLESS_PROPERTY);
	}

	private boolean isMultiple() {
		return DrawingPanel.propertyIsTrue(DrawingPanel.MULTIPLE_PROPERTY);
	}

	private boolean readyToClose() {
		/*
		 * if (isAnimated()) { // wait a little longer, in case animation is sleeping
		 * return System.currentTimeMillis() > createTime + 5 * DELAY; } else { return
		 * System.currentTimeMillis() > createTime + 4 * DELAY; }
		 */
		return (DrawingPanel.instances == 0 || this.shouldSave()) && !DrawingPanel.mainIsActive();
	}

	private void replaceColor(BufferedImage image, Color oldColor, Color newColor) {
		int oldRGB = oldColor.getRGB();
		int newRGB = newColor.getRGB();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				if (image.getRGB(x, y) == oldRGB) {
					image.setRGB(x, y, newRGB);
				}
			}
		}
	}

	// called when user presses "Save As" menu item
	private void saveAs() {
		String filename = this.saveAsHelper("png");
		if (filename != null) {
			try {
				this.save(filename); // save the file
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this.frame, "Unable to save image:\n" + ex);
			}
		}
	}

	private void saveAsAnimated() {
		String filename = this.saveAsHelper("gif");
		if (filename != null) {
			try {
				// record that the file should be saved next time
				PrintStream out = new PrintStream(new File(DrawingPanel.ANIMATION_FILE_NAME));
				out.println(filename);
				out.close();

				JOptionPane.showMessageDialog(
						this.frame,
						"Due to constraints about how DrawingPanel works, you'll need to\n"
								+ "re-run your program.  When you run it the next time, DrawingPanel will \n"
								+ "automatically save your animated image as: "
								+ new File(filename).getName());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this.frame, "Unable to store animation settings:\n" + ex);
			}
		}
	}

	private String saveAsHelper(String extension) {
		// use file chooser dialog to get filename to save into
		this.checkChooser();
		if (this.chooser.showSaveDialog(this.frame) != JFileChooser.APPROVE_OPTION) {
			return null;
		}

		File selectedFile = this.chooser.getSelectedFile();
		String filename = selectedFile.toString();
		if ( !filename.toLowerCase().endsWith(extension)) {
			// Windows is dumb about extensions with file choosers
			filename += "." + extension;
		}

		// confirm overwrite of file
		if (new File(filename).exists()
				&& JOptionPane.showConfirmDialog(this.frame, "File exists.  Overwrite?", "Overwrite?",
						JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
			return null;
		}

		return filename;
	}

	// saves DrawingPanel image to a temporary file and returns file's name
	private String saveToTempFile() throws IOException {
		File currentImageFile = File.createTempFile("current_image", ".png");
		this.save(currentImageFile.toString());
		return currentImageFile.toString();
	}

	// sets the text that will appear in the bottom status bar
	private void setStatusBarText(String text) {
		if (this.currentZoom != 1) {
			text += " (current zoom: " + this.currentZoom + "x" + ")";
		}
		this.statusBar.setText(text);
	}

	// initializes DrawingPanel's menu bar items
	private void setupMenuBar() {
		// abort compare if we're running as an applet or in a secure
		// environment
		boolean secure = (System.getSecurityManager() != null);

		JMenuItem saveAs = new JMenuItem("Save As...", 'A');
		saveAs.addActionListener(this);
		saveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		saveAs.setEnabled( !secure);

		JMenuItem saveAnimated = new JMenuItem("Save Animated GIF...", 'G');
		saveAnimated.addActionListener(this);
		saveAnimated.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		saveAnimated.setEnabled( !secure);

		JMenuItem compare = new JMenuItem("Compare to File...", 'C');
		compare.addActionListener(this);
		compare.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		compare.setEnabled( !secure);

		JMenuItem compareURL = new JMenuItem("Compare to Web File...", 'U');
		compareURL.addActionListener(this);
		compareURL.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		compareURL.setEnabled( !secure);

		JMenuItem zoomIn = new JMenuItem("Zoom In", 'I');
		zoomIn.addActionListener(this);
		zoomIn.setAccelerator(KeyStroke.getKeyStroke("ctrl EQUALS"));

		JMenuItem zoomOut = new JMenuItem("Zoom Out", 'O');
		zoomOut.addActionListener(this);
		zoomOut.setAccelerator(KeyStroke.getKeyStroke("ctrl MINUS"));

		JMenuItem zoomNormal = new JMenuItem("Zoom Normal (100%)", 'N');
		zoomNormal.addActionListener(this);
		zoomNormal.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));

		JMenuItem exit = new JMenuItem("Exit", 'x');
		exit.addActionListener(this);

		JMenuItem about = new JMenuItem("About...", 'A');
		about.addActionListener(this);

		JMenu file = new JMenu("File");
		file.setMnemonic('F');
		file.add(compareURL);
		file.add(compare);
		file.addSeparator();
		file.add(saveAs);
		file.add(saveAnimated);
		file.addSeparator();
		file.add(exit);

		JMenu view = new JMenu("View");
		view.setMnemonic('V');
		view.add(zoomIn);
		view.add(zoomOut);
		view.add(zoomNormal);

		JMenu help = new JMenu("Help");
		help.setMnemonic('H');
		help.add(about);

		JMenuBar bar = new JMenuBar();
		bar.add(file);
		bar.add(view);
		bar.add(help);
		this.frame.setJMenuBar(bar);
	}

	private boolean shouldDiff() {
		return DrawingPanel.hasProperty(DrawingPanel.DIFF_PROPERTY);
	}

	private boolean shouldSave() {
		return DrawingPanel.hasProperty(DrawingPanel.SAVE_PROPERTY);
	}

	// show dialog box with given choices; return index chosen (-1 == cancel)
	private int showOptionDialog(Frame parent, String title, String message, final String[] names) {
		final JDialog dialog = new JDialog(parent, title, true);
		JPanel center = new JPanel(new GridLayout(0, 1));

		// just a hack to make the return value a mutable reference to an int
		final int[] hack = {-1};

		for (int i = 0; i < names.length; i++) {
			final JButton button = new JButton(names[i]);
			button.setActionCommand(String.valueOf(i));
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					hack[0] = Integer.parseInt(button.getActionCommand());
					dialog.setVisible(false);
				}
			});
			center.add(button);
		}

		JPanel south = new JPanel();
		JButton cancel = new JButton("Cancel");
		cancel.setMnemonic('C');
		cancel.requestFocus();
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		south.add(cancel);

		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.getContentPane().setLayout(new BorderLayout(10, 5));
		// ((JComponent)
		// dialog.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,
		// 10, 10, 10));

		if (message != null) {
			JLabel messageLabel = new JLabel(message);
			dialog.add(messageLabel, BorderLayout.NORTH);
		}
		dialog.add(center);
		dialog.add(south, BorderLayout.SOUTH);
		dialog.pack();
		dialog.setResizable(false);
		this.center(dialog);
		cancel.requestFocus();
		dialog.setVisible(true);
		cancel.requestFocus();

		return hack[0];
	}

	// Reports the differences between two images.
	private class DiffImage extends JPanel implements ActionListener, ChangeListener {

		private static final long serialVersionUID = 0;

		private BufferedImage image1;
		private BufferedImage image2;
		private String image1name;
		private int numDiffPixels;
		private int opacity = 50;
		private String label1Text = "Expected";
		private String label2Text = "Actual";
		private boolean highlightDiffs = false;

		private Color highlightColor = new Color(224, 0, 224);
		private JLabel image1Label;
		private JLabel image2Label;
		private JLabel diffPixelsLabel;
		private JSlider slider;
		private JCheckBox box;
		private JMenuItem saveAsItem;
		private JMenuItem setImage1Item;
		private JMenuItem setImage2Item;
		private JFrame frame;
		private JButton colorButton;

		public DiffImage(String file1, String file2) throws IOException {
			this.setImage1(file1);
			this.setImage2(file2);
			this.display();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == this.box) {
				this.highlightDiffs = this.box.isSelected();
				this.repaint();
			} else if (source == this.colorButton) {
				Color color =
						JColorChooser.showDialog(this.frame, "Choose highlight color", this.highlightColor);
				if (color != null) {
					this.highlightColor = color;
					this.colorButton.setBackground(color);
					this.colorButton.setForeground(color);
					this.repaint();
				}
			} else if (source == this.saveAsItem) {
				this.saveAs();
			} else if (source == this.setImage1Item) {
				this.setImage1();
			} else if (source == this.setImage2Item) {
				this.setImage2();
			}
		}

		// Counts number of pixels that differ between the two images.
		public void countDiffPixels() {
			if (this.image1 == null || this.image2 == null) {
				return;
			}

			int w1 = this.image1.getWidth();
			int h1 = this.image1.getHeight();
			int w2 = this.image2.getWidth();
			int h2 = this.image2.getHeight();
			int wmax = Math.max(w1, w2);
			int hmax = Math.max(h1, h2);

			// check each pair of pixels
			this.numDiffPixels = 0;
			for (int y = 0; y < hmax; y++) {
				for (int x = 0; x < wmax; x++) {
					int pixel1 = (x < w1 && y < h1) ? this.image1.getRGB(x, y) : 0;
					int pixel2 = (x < w2 && y < h2) ? this.image2.getRGB(x, y) : 0;
					if (pixel1 != pixel2) {
						this.numDiffPixels++;
					}
				}
			}
		}

		// initializes diffimage panel
		public void display() {
			this.countDiffPixels();

			this.setupComponents();
			this.setupEvents();
			this.setupLayout();

			this.frame.pack();
			DrawingPanel.this.center(this.frame);

			this.frame.setVisible(true);
			this.frame.toFront();
		}

		// draws the given image onto the given graphics context
		public void drawImageFull(Graphics2D g2, BufferedImage image) {
			int iw = image.getWidth();
			int ih = image.getHeight();
			int w = this.getWidth();
			int h = this.getHeight();
			int dw = w - iw;
			int dh = h - ih;

			if (dw > 0) {
				g2.fillRect(iw, 0, dw, ih);
			}
			if (dh > 0) {
				g2.fillRect(0, ih, iw, dh);
			}
			if (dw > 0 && dh > 0) {
				g2.fillRect(iw, ih, dw, dh);
			}
			g2.drawImage(image, 0, 0, this);
		}

		// paints the DiffImage panel
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			// draw the expected output (image 1)
			if (this.image1 != null) {
				this.drawImageFull(g2, this.image1);
			}

			// draw the actual output (image 2)
			if (this.image2 != null) {
				Composite oldComposite = g2.getComposite();
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
						((float) this.opacity) / 100));
				this.drawImageFull(g2, this.image2);
				g2.setComposite(oldComposite);
			}
			g2.setColor(Color.BLACK);

			// draw the highlighted diffs (if so desired)
			if (this.highlightDiffs && this.image1 != null && this.image2 != null) {
				int w1 = this.image1.getWidth();
				int h1 = this.image1.getHeight();
				int w2 = this.image2.getWidth();
				int h2 = this.image2.getHeight();

				int wmax = Math.max(w1, w2);
				int hmax = Math.max(h1, h2);

				// check each pair of pixels
				g2.setColor(this.highlightColor);
				for (int y = 0; y < hmax; y++) {
					for (int x = 0; x < wmax; x++) {
						int pixel1 = (x < w1 && y < h1) ? this.image1.getRGB(x, y) : 0;
						int pixel2 = (x < w2 && y < h2) ? this.image2.getRGB(x, y) : 0;
						if (pixel1 != pixel2) {
							g2.fillRect(x, y, 1, 1);
						}
					}
				}
			}
		}

		public void save(File file) throws IOException {
			// String extension = filename.substring(filename.lastIndexOf(".") +
			// 1);
			// ImageIO.write(diffImage, extension, new File(filename));
			String filename = file.getName();
			String extension = filename.substring(filename.lastIndexOf(".") + 1);
			BufferedImage img =
					new BufferedImage(this.getPreferredSize().width, this.getPreferredSize().height,
							BufferedImage.TYPE_INT_ARGB);
			img.getGraphics().setColor(this.getBackground());
			img.getGraphics().fillRect(0, 0, img.getWidth(), img.getHeight());
			this.paintComponent(img.getGraphics());
			ImageIO.write(img, extension, file);
		}

		public void save(String filename) throws IOException {
			this.save(new File(filename));
		}

		// Called when "Save As" menu item is clicked
		public void saveAs() {
			DrawingPanel.this.checkChooser();
			if (DrawingPanel.this.chooser.showSaveDialog(this.frame) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			File selectedFile = DrawingPanel.this.chooser.getSelectedFile();
			try {
				this.save(selectedFile.toString());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this.frame, "Unable to save image:\n" + ex);
			}
		}

		// called when "Set Image 1" menu item is clicked
		public void setImage1() {
			DrawingPanel.this.checkChooser();
			if (DrawingPanel.this.chooser.showSaveDialog(this.frame) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			File selectedFile = DrawingPanel.this.chooser.getSelectedFile();
			try {
				this.setImage1(selectedFile.toString());
				this.countDiffPixels();
				this.diffPixelsLabel.setText("(" + this.numDiffPixels + " pixels differ)");
				this.image1Label.setText(selectedFile.getName());
				this.frame.pack();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this.frame, "Unable to set image 1:\n" + ex);
			}
		}

		// sets image 1 to be the given image
		public void setImage1(BufferedImage image) {
			if (image == null) {
				throw new NullPointerException();
			}

			this.image1 = image;
			this.setPreferredSize(new Dimension(Math.max(this.getPreferredSize().width, image.getWidth()),
					Math.max(this.getPreferredSize().height, image.getHeight())));
			if (this.frame != null) {
				this.frame.pack();
			}
			this.repaint();
		}

		// loads image 1 from the given filename or URL
		public void setImage1(String filename) throws IOException {
			this.image1name = new File(filename).getName();
			if (filename.startsWith("http")) {
				this.setImage1(ImageIO.read(new URL(filename)));
			} else {
				this.setImage1(ImageIO.read(new File(filename)));
			}
		}

		// called when "Set Image 2" menu item is clicked
		public void setImage2() {
			DrawingPanel.this.checkChooser();
			if (DrawingPanel.this.chooser.showSaveDialog(this.frame) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			File selectedFile = DrawingPanel.this.chooser.getSelectedFile();
			try {
				this.setImage2(selectedFile.toString());
				this.countDiffPixels();
				this.diffPixelsLabel.setText("(" + this.numDiffPixels + " pixels differ)");
				this.image2Label.setText(selectedFile.getName());
				this.frame.pack();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this.frame, "Unable to set image 2:\n" + ex);
			}
		}

		// sets image 2 to be the given image
		public void setImage2(BufferedImage image) {
			if (image == null) {
				throw new NullPointerException();
			}

			this.image2 = image;
			this.setPreferredSize(new Dimension(Math.max(this.getPreferredSize().width, image.getWidth()),
					Math.max(this.getPreferredSize().height, image.getHeight())));
			if (this.frame != null) {
				this.frame.pack();
			}
			this.repaint();
		}

		// loads image 2 from the given filename
		public void setImage2(String filename) throws IOException {
			if (filename.startsWith("http")) {
				this.setImage2(ImageIO.read(new URL(filename)));
			} else {
				this.setImage2(ImageIO.read(new File(filename)));
			}

		}

		private void setupComponents() {
			String title = "DiffImage";
			if (this.image1name != null) {
				title = "Compare to " + this.image1name;
			}
			this.frame = new JFrame(title);
			this.frame.setResizable(false);
			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			this.slider = new JSlider();
			this.slider.setPaintLabels(false);
			this.slider.setPaintTicks(true);
			this.slider.setSnapToTicks(true);
			this.slider.setMajorTickSpacing(25);
			this.slider.setMinorTickSpacing(5);

			this.box = new JCheckBox("Highlight diffs in color: ", this.highlightDiffs);

			this.colorButton = new JButton();
			this.colorButton.setBackground(this.highlightColor);
			this.colorButton.setForeground(this.highlightColor);
			this.colorButton.setPreferredSize(new Dimension(24, 24));

			this.diffPixelsLabel = new JLabel("(" + this.numDiffPixels + " pixels differ)");
			this.diffPixelsLabel.setFont(this.diffPixelsLabel.getFont().deriveFont(Font.BOLD));
			this.image1Label = new JLabel(this.label1Text);
			this.image2Label = new JLabel(this.label2Text);

			this.setupMenuBar();
		}

		// initializes layout of components
		private void setupLayout() {
			JPanel southPanel1 = new JPanel();
			southPanel1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			southPanel1.add(this.image1Label);
			southPanel1.add(this.slider);
			southPanel1.add(this.image2Label);
			southPanel1.add(Box.createHorizontalStrut(20));

			JPanel southPanel2 = new JPanel();
			southPanel2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			southPanel2.add(this.diffPixelsLabel);
			southPanel2.add(Box.createHorizontalStrut(20));
			southPanel2.add(this.box);
			southPanel2.add(this.colorButton);

			Container southPanel = javax.swing.Box.createVerticalBox();
			southPanel.add(southPanel1);
			southPanel.add(southPanel2);

			this.frame.add(this, BorderLayout.CENTER);
			this.frame.add(southPanel, BorderLayout.SOUTH);
		}

		// initializes main menu bar
		private void setupMenuBar() {
			this.saveAsItem = new JMenuItem("Save As...", 'A');
			this.saveAsItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
			this.setImage1Item = new JMenuItem("Set Image 1...", '1');
			this.setImage1Item.setAccelerator(KeyStroke.getKeyStroke("ctrl 1"));
			this.setImage2Item = new JMenuItem("Set Image 2...", '2');
			this.setImage2Item.setAccelerator(KeyStroke.getKeyStroke("ctrl 2"));

			JMenu file = new JMenu("File");
			file.setMnemonic('F');
			file.add(this.setImage1Item);
			file.add(this.setImage2Item);
			file.addSeparator();
			file.add(this.saveAsItem);

			JMenuBar bar = new JMenuBar();
			bar.add(file);

			// disabling menu bar to simplify code
			// frame.setJMenuBar(bar);
		}

		// method of ChangeListener interface
		@Override
		public void stateChanged(ChangeEvent e) {
			this.opacity = this.slider.getValue();
			this.repaint();
		}

		// adds event listeners to various components
		private void setupEvents() {
			this.slider.addChangeListener(this);
			this.box.addActionListener(this);
			this.colorButton.addActionListener(this);
			this.saveAsItem.addActionListener(this);
			this.setImage1Item.addActionListener(this);
			this.setImage2Item.addActionListener(this);
		}
	}

	// ******************************************************************************
	// DirectGif89Frame.java
	// ******************************************************************************

	// ==============================================================================
	/**
	 * Instances of this Gif89Frame subclass are constructed from RGB image info, either in
	 * the form of an Image object or a pixel array.
	 * <p>
	 * There is an important restriction to note. It is only permissible to add
	 * DirectGif89Frame objects to a Gif89Encoder constructed without an explicit color
	 * map. The GIF color table will be automatically generated from pixel information.
	 *
	 * @version 0.90 beta (15-Jul-2000)
	 * @author J. M. G. Elliott (tep@jmge.net)
	 * @see Gif89Encoder
	 * @see Gif89Frame
	 * @see IndexGif89Frame
	 */
	class DirectGif89Frame extends Gif89Frame {

		private int[] argbPixels;

		// ----------------------------------------------------------------------------
		/**
		 * Construct an DirectGif89Frame from a Java image.
		 *
		 * @param img A java.awt.Image object that supports pixel-grabbing.
		 * @exception IOException If the image is unencodable due to failure of
		 * pixel-grabbing.
		 */
		public DirectGif89Frame(Image img) throws IOException {
			PixelGrabber pg = new PixelGrabber(img, 0, 0, -1, -1, true);

			String errmsg = null;
			try {
				if ( !pg.grabPixels()) {
					errmsg = "can't grab pixels from image";
				}
			} catch (InterruptedException e) {
				errmsg = "interrupted grabbing pixels from image";
			}

			if (errmsg != null) {
				throw new IOException(errmsg + " (" + this.getClass().getName() + ")");
			}

			this.theWidth = pg.getWidth();
			this.theHeight = pg.getHeight();
			this.argbPixels = (int[]) pg.getPixels();
			this.ciPixels = new byte[this.argbPixels.length];

			// flush to conserve resources
			img.flush();
		}

		// ----------------------------------------------------------------------------
		/**
		 * Construct an DirectGif89Frame from ARGB pixel data.
		 *
		 * @param width Width of the bitmap.
		 * @param height Height of the bitmap.
		 * @param argb_pixels Array containing at least width*height pixels in the format
		 * returned by java.awt.Color.getRGB().
		 */
		public DirectGif89Frame(int width, int height, int argb_pixels[]) {
			this.theWidth = width;
			this.theHeight = height;
			this.argbPixels = new int[this.theWidth * this.theHeight];
			System.arraycopy(argb_pixels, 0, this.argbPixels, 0, this.argbPixels.length);
			this.ciPixels = new byte[this.argbPixels.length];
		}

		// ----------------------------------------------------------------------------
		@Override
		Object getPixelSource() {
			return this.argbPixels;
		}
	}

	// ******************************************************************************
	// Gif89Encoder.java
	// ******************************************************************************

	// ==============================================================================
	/**
	 * This is the central class of a JDK 1.1 compatible GIF encoder that, AFAIK, supports
	 * more features of the extended GIF spec than any other Java open source encoder. Some
	 * sections of the source are lifted or adapted from Jef Poskanzer's <cite>Acme
	 * GifEncoder</cite> (so please see the <a href="../readme.txt">readme</a> containing
	 * his notice), but much of it, including nearly all of the present class, is original
	 * code. My main motivation for writing a new encoder was to support animated GIFs, but
	 * the package also adds support for embedded textual comments.
	 * <p>
	 * There are still some limitations. For instance, animations are limited to a single
	 * global color table. But that is usually what you want anyway, so as to avoid
	 * irregularities on some displays. (So this is not really a limitation, but a
	 * "disciplinary feature" :) Another rather more serious restriction is that the total
	 * number of RGB colors in a given input-batch mustn't exceed 256. Obviously, there is
	 * an opening here for someone who would like to add a color-reducing preprocessor.
	 * <p>
	 * The encoder, though very usable in its present form, is at bottom only a partial
	 * implementation skewed toward my own particular needs. Hence a couple of caveats are
	 * in order. (1) During development it was in the back of my mind that an encoder
	 * object should be reusable - i.e., you should be able to make multiple calls to
	 * encode() on the same object, with or without intervening frame additions or changes
	 * to options. But I haven't reviewed the code with such usage in mind, much less
	 * tested it, so it's likely I overlooked something. (2) The encoder classes aren't
	 * thread safe, so use caution in a context where access is shared by multiple threads.
	 * (Better yet, finish the library and re-release it :)
	 * <p>
	 * There follow a couple of simple examples illustrating the most common way to use the
	 * encoder, i.e., to encode AWT Image objects created elsewhere in the program. Use of
	 * some of the most popular format options is also shown, though you will want to
	 * peruse the API for additional features.
	 * <p>
	 * <strong>Animated GIF Example</strong>
	 *
	 * <pre>
	 *  import net.jmge.gif.Gif89Encoder;
	 *  // ...
	 *  void writeAnimatedGIF(Image[] still_images,
	 *                      String annotation,
	 *                      boolean looped,
	 *                      double frames_per_second,
	 *                      OutputStream out) throws IOException
	 *  {
	 *  Gif89Encoder gifenc = new Gif89Encoder();
	 *  for (int i = 0; i < still_images.length; ++i)
	 *    gifenc.addFrame(still_images[i]);
	 *  gifenc.setComments(annotation);
	 *  gifenc.setLoopCount(looped ? 0 : 1);
	 *  gifenc.setUniformDelay((int) Math.round(100 / frames_per_second));
	 *  gifenc.encode(out);
	 *  }
	 * </pre>
	 *
	 * <strong>Static GIF Example</strong>
	 *
	 * <pre>
	 *  import net.jmge.gif.Gif89Encoder;
	 *  // ...
	 *  void writeNormalGIF(Image img,
	 *                    String annotation,
	 *                    int transparent_index,  // pass -1 for none
	 *                    boolean interlaced,
	 *                    OutputStream out) throws IOException
	 *  {
	 *  Gif89Encoder gifenc = new Gif89Encoder(img);
	 *  gifenc.setComments(annotation);
	 *  gifenc.setTransparentIndex(transparent_index);
	 *  gifenc.getFrameAt(0).setInterlaced(interlaced);
	 *  gifenc.encode(out);
	 *  }
	 * </pre>
	 *
	 * @version 0.90 beta (15-Jul-2000)
	 * @author J. M. G. Elliott (tep@jmge.net)
	 * @see Gif89Frame
	 * @see DirectGif89Frame
	 * @see IndexGif89Frame
	 */
	class Gif89Encoder {

		private static final boolean DEBUG = false;
		private Dimension dispDim = new Dimension(0, 0);
		private GifColorTable colorTable;
		private int bgIndex = 0;
		private int loopCount = 1;
		private String theComments;
		private Vector<Gif89Frame> vFrames = new Vector<Gif89Frame>();

		// ----------------------------------------------------------------------------
		/**
		 * Use this default constructor if you'll be adding multiple frames constructed
		 * from RGB data (i.e., AWT Image objects or ARGB-pixel arrays).
		 */
		public Gif89Encoder() {
			// empty color table puts us into "palette autodetect" mode
			this.colorTable = new GifColorTable();
		}

		// ----------------------------------------------------------------------------
		/**
		 * Like the default except that it also adds a single frame, for conveniently
		 * encoding a static GIF from an image.
		 *
		 * @param static_image Any Image object that supports pixel-grabbing.
		 * @exception IOException See the addFrame() methods.
		 */
		public Gif89Encoder(Image static_image) throws IOException {
			this();
			this.addFrame(static_image);
		}

		// ----------------------------------------------------------------------------
		/**
		 * This constructor installs a user color table, overriding the detection of of a
		 * palette from ARBG pixels. Use of this constructor imposes a couple of
		 * restrictions: (1) Frame objects can't be of type DirectGif89Frame (2)
		 * Transparency, if desired, must be set explicitly.
		 *
		 * @param colors Array of color values; no more than 256 colors will be read, since
		 * that's the limit for a GIF.
		 */
		public Gif89Encoder(Color[] colors) {
			this.colorTable = new GifColorTable(colors);
		}

		// ----------------------------------------------------------------------------
		/**
		 * Convenience constructor for encoding a static GIF from index-model data. Adds a
		 * single frame as specified.
		 *
		 * @param colors Array of color values; no more than 256 colors will be read, since
		 * that's the limit for a GIF.
		 * @param width Width of the GIF bitmap.
		 * @param height Height of same.
		 * @param ci_pixels Array of color-index pixels no less than width * height in
		 * length.
		 * @exception IOException See the addFrame() methods.
		 */
		public Gif89Encoder(Color[] colors, int width, int height, byte ci_pixels[]) throws IOException {
			this(colors);
			this.addFrame(width, height, ci_pixels);
		}

		// ----------------------------------------------------------------------------
		/**
		 * Get the number of frames that have been added so far.
		 *
		 * @return Number of frame items.
		 */
		public int getFrameCount() {
			return this.vFrames.size();
		}

		// ----------------------------------------------------------------------------
		/**
		 * Get a reference back to a Gif89Frame object by position.
		 *
		 * @param index Zero-based index of the frame in the sequence.
		 * @return Gif89Frame object at the specified position (or null if no such frame).
		 */
		public Gif89Frame getFrameAt(int index) {
			return this.isOk(index) ? this.vFrames.elementAt(index) : null;
		}

		// ----------------------------------------------------------------------------
		/**
		 * Add a Gif89Frame frame to the end of the internal sequence. Note that there are
		 * restrictions on the Gif89Frame type: if the encoder object was constructed with
		 * an explicit color table, an attempt to add a DirectGif89Frame will throw an
		 * exception.
		 *
		 * @param gf An externally constructed Gif89Frame.
		 * @exception IOException If Gif89Frame can't be accommodated. This could happen if
		 * either (1) the aggregate cross-frame RGB color count exceeds 256, or (2) the
		 * Gif89Frame subclass is incompatible with the present encoder object.
		 */
		public void addFrame(Gif89Frame gf) throws IOException {
			this.accommodateFrame(gf);
			this.vFrames.addElement(gf);
		}

		// ----------------------------------------------------------------------------
		/**
		 * Convenience version of addFrame() that takes a Java Image, internally
		 * constructing the requisite DirectGif89Frame.
		 *
		 * @param image Any Image object that supports pixel-grabbing.
		 * @exception IOException If either (1) pixel-grabbing fails, (2) the aggregate
		 * cross-frame RGB color count exceeds 256, or (3) this encoder object was
		 * constructed with an explicit color table.
		 */
		public void addFrame(Image image) throws IOException {
			DirectGif89Frame frame = new DirectGif89Frame(image);
			this.addFrame(frame);
		}

		// ----------------------------------------------------------------------------
		/**
		 * The index-model convenience version of addFrame().
		 *
		 * @param width Width of the GIF bitmap.
		 * @param height Height of same.
		 * @param ci_pixels Array of color-index pixels no less than width * height in
		 * length.
		 * @exception IOException Actually, in the present implementation, there aren't any
		 * unchecked exceptions that can be thrown when adding an IndexGif89Frame <i>per
		 * se</i>. But I might add some pedantic check later, to justify the generality :)
		 */
		public void addFrame(int width, int height, byte ci_pixels[]) throws IOException {
			this.addFrame(new IndexGif89Frame(width, height, ci_pixels));
		}

		// ----------------------------------------------------------------------------
		/**
		 * Like addFrame() except that the frame is inserted at a specific point in the
		 * sequence rather than appended.
		 *
		 * @param index Zero-based index at which to insert frame.
		 * @param gf An externally constructed Gif89Frame.
		 * @exception IOException If Gif89Frame can't be accommodated. This could happen if
		 * either (1) the aggregate cross-frame RGB color count exceeds 256, or (2) the
		 * Gif89Frame subclass is incompatible with the present encoder object.
		 */
		public void insertFrame(int index, Gif89Frame gf) throws IOException {
			this.accommodateFrame(gf);
			this.vFrames.insertElementAt(gf, index);
		}

		// ----------------------------------------------------------------------------
		/**
		 * Set the color table index for the transparent color, if any.
		 *
		 * @param index Index of the color that should be rendered as transparent, if any.
		 * A value of -1 turns off transparency. (Default: -1)
		 */
		public void setTransparentIndex(int index) {
			this.colorTable.setTransparent(index);
		}

		// ----------------------------------------------------------------------------
		/**
		 * Sets attributes of the multi-image display area, if applicable.
		 *
		 * @param dim Width/height of display. (Default: largest detected frame size)
		 * @param background Color table index of background color. (Default: 0)
		 * @see Gif89Frame#setPosition
		 */
		public void setLogicalDisplay(Dimension dim, int background) {
			this.dispDim = new Dimension(dim);
			this.bgIndex = background;
		}

		// ----------------------------------------------------------------------------
		/**
		 * Set animation looping parameter, if applicable.
		 *
		 * @param count Number of times to play sequence. Special value of 0 specifies
		 * indefinite looping. (Default: 1)
		 */
		public void setLoopCount(int count) {
			this.loopCount = count;
		}

		// ----------------------------------------------------------------------------
		/**
		 * Specify some textual comments to be embedded in GIF.
		 *
		 * @param comments String containing ASCII comments.
		 */
		public void setComments(String comments) {
			this.theComments = comments;
		}

		// ----------------------------------------------------------------------------
		/**
		 * A convenience method for setting the "animation speed". It simply sets the delay
		 * parameter for each frame in the sequence to the supplied value. Since this is
		 * actually frame-level rather than animation-level data, take care to add your
		 * frames before calling this method.
		 *
		 * @param interval Interframe interval in centiseconds.
		 */
		public void setUniformDelay(int interval) {
			for (int i = 0; i < this.vFrames.size(); ++i) {
				this.vFrames.elementAt(i).setDelay(interval);
			}
		}

		// ----------------------------------------------------------------------------
		/**
		 * After adding your frame(s) and setting your options, simply call this method to
		 * write the GIF to the passed stream. Multiple calls are permissible if for some
		 * reason that is useful to your application. (The method simply encodes the
		 * current state of the object with no thought to previous calls.)
		 *
		 * @param out The stream you want the GIF written to.
		 * @exception IOException If a write error is encountered.
		 */
		public void encode(OutputStream out) throws IOException {
			int nframes = this.getFrameCount();
			boolean is_sequence = nframes > 1;

			// N.B. must be called before writing screen descriptor
			this.colorTable.closePixelProcessing();

			// write GIF HEADER
			DrawingPanel.putAscii("GIF89a", out);

			// write global blocks
			this.writeLogicalScreenDescriptor(out);
			this.colorTable.encode(out);
			if (is_sequence && this.loopCount != 1) {
				this.writeNetscapeExtension(out);
			}
			if (this.theComments != null && this.theComments.length() > 0) {
				this.writeCommentExtension(out);
			}

			// write out the control and rendering data for each frame
			for (int i = 0; i < nframes; ++i) {
				DirectGif89Frame frame = (DirectGif89Frame) this.vFrames.elementAt(i);
				frame.encode(out, is_sequence, this.colorTable.getDepth(), this.colorTable.getTransparent());
				this.vFrames.set(i, null); // for GC's sake
				System.gc();
			}

			// write GIF TRAILER
			out.write(';');

			out.flush();
		}

		public boolean hasStarted = false;

		// ----------------------------------------------------------------------------
		/**
		 * After adding your frame(s) and setting your options, simply call this method to
		 * write the GIF to the passed stream. Multiple calls are permissible if for some
		 * reason that is useful to your application. (The method simply encodes the
		 * current state of the object with no thought to previous calls.)
		 *
		 * @param out The stream you want the GIF written to.
		 * @exception IOException If a write error is encountered.
		 */
		public void startEncoding(OutputStream out, Image image, int delay) throws IOException {
			this.hasStarted = true;
			boolean is_sequence = true;
			Gif89Frame gf = new DirectGif89Frame(image);
			this.accommodateFrame(gf);

			// N.B. must be called before writing screen descriptor
			this.colorTable.closePixelProcessing();

			// write GIF HEADER
			DrawingPanel.putAscii("GIF89a", out);

			// write global blocks
			this.writeLogicalScreenDescriptor(out);
			this.colorTable.encode(out);
			if (is_sequence && this.loopCount != 1) {
				this.writeNetscapeExtension(out);
			}
			if (this.theComments != null && this.theComments.length() > 0) {
				this.writeCommentExtension(out);
			}
		}

		public void continueEncoding(OutputStream out, Image image, int delay) throws IOException {
			// write out the control and rendering data for each frame
			Gif89Frame gf = new DirectGif89Frame(image);
			this.accommodateFrame(gf);
			gf.encode(out, true, this.colorTable.getDepth(), this.colorTable.getTransparent());
			out.flush();
			image.flush();
		}

		public void endEncoding(OutputStream out) throws IOException {
			// write GIF TRAILER
			out.write(';');

			out.flush();
		}

		public void setBackground(Color color) {
			this.bgIndex = this.colorTable.indexOf(color);
			if (this.bgIndex < 0) {
				try {
					BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_INDEXED);
					Graphics g = img.getGraphics();
					g.setColor(color);
					g.fillRect(0, 0, 2, 2);
					DirectGif89Frame frame = new DirectGif89Frame(img);
					this.accommodateFrame(frame);
					this.bgIndex = this.colorTable.indexOf(color);
				} catch (IOException e) {
					if (Gif89Encoder.DEBUG) {
						System.out.println("Error while setting background color: " + e);
					}
				}
			}
			if (Gif89Encoder.DEBUG) {
				System.out.println("Setting bg index to " + this.bgIndex);
			}
		}

		// ----------------------------------------------------------------------------
		private void accommodateFrame(Gif89Frame gf) throws IOException {
			this.dispDim.width = Math.max(this.dispDim.width, gf.getWidth());
			this.dispDim.height = Math.max(this.dispDim.height, gf.getHeight());
			this.colorTable.processPixels(gf);
		}

		// ----------------------------------------------------------------------------
		private void writeLogicalScreenDescriptor(OutputStream os) throws IOException {
			DrawingPanel.putShort(this.dispDim.width, os);
			DrawingPanel.putShort(this.dispDim.height, os);

			// write 4 fields, packed into a byte (bitfieldsize:value)
			// global color map present? (1:1)
			// bits per primary color less 1 (3:7)
			// sorted color table? (1:0)
			// bits per pixel less 1 (3:varies)
			os.write(0xf0 | this.colorTable.getDepth() - 1);

			// write background color index
			os.write(this.bgIndex);

			// Jef Poskanzer's notes on the next field, for our possible
			// edification:
			// Pixel aspect ratio - 1:1.
			// Putbyte( (byte) 49, outs );
			// Java's GIF reader currently has a bug, if the aspect ratio byte
			// is
			// not zero it throws an ImageFormatException. It doesn't know that
			// 49 means a 1:1 aspect ratio. Well, whatever, zero works with all
			// the other decoders I've tried so it probably doesn't hurt.

			// OK, if it's good enough for Jef, it's definitely good enough for
			// us:
			os.write(0);
		}

		// ----------------------------------------------------------------------------
		private void writeNetscapeExtension(OutputStream os) throws IOException {
			// n.b. most software seems to interpret the count as a repeat count
			// (i.e., interations beyond 1) rather than as an iteration count
			// (thus, to avoid repeating we have to omit the whole extension)

			os.write('!'); // GIF Extension Introducer
			os.write(0xff); // Application Extension Label

			os.write(11); // application ID block size
			DrawingPanel.putAscii("NETSCAPE2.0", os); // application ID data

			os.write(3); // data sub-block size
			os.write(1); // a looping flag? dunno

			// we finally write the relevent data
			DrawingPanel.putShort(this.loopCount > 1 ? this.loopCount - 1 : 0, os);

			os.write(0); // block terminator
		}

		// ----------------------------------------------------------------------------
		private void writeCommentExtension(OutputStream os) throws IOException {
			os.write('!'); // GIF Extension Introducer
			os.write(0xfe); // Comment Extension Label

			int remainder = this.theComments.length() % 255;
			int nsubblocks_full = this.theComments.length() / 255;
			int nsubblocks = nsubblocks_full + (remainder > 0 ? 1 : 0);
			int ibyte = 0;
			for (int isb = 0; isb < nsubblocks; ++isb) {
				int size = isb < nsubblocks_full ? 255 : remainder;

				os.write(size);
				DrawingPanel.putAscii(this.theComments.substring(ibyte, ibyte + size), os);
				ibyte += size;
			}

			os.write(0); // block terminator
		}

		// ----------------------------------------------------------------------------
		private boolean isOk(int frame_index) {
			return frame_index >= 0 && frame_index < this.vFrames.size();
		}
	}

	// ==============================================================================
	class GifColorTable {

		// the palette of ARGB colors, packed as returned by Color.getRGB()
		private int[] theColors = new int[256];

		// other basic attributes
		private int colorDepth;
		private int transparentIndex = -1;

		// these fields track color-index info across frames
		private int ciCount = 0; // count of distinct color indices
		private ReverseColorMap ciLookup; // cumulative rgb-to-ci lookup table

		// ----------------------------------------------------------------------------
		GifColorTable() {
			this.ciLookup = new ReverseColorMap(); // puts us into "auto-detect mode"
		}

		// ----------------------------------------------------------------------------
		GifColorTable(Color[] colors) {
			int n2copy = Math.min(this.theColors.length, colors.length);
			for (int i = 0; i < n2copy; ++i) {
				this.theColors[i] = colors[i].getRGB();
			}
		}

		int indexOf(Color color) {
			int rgb = color.getRGB();
			for (int i = 0; i < this.theColors.length; i++) {
				if (rgb == this.theColors[i]) {
					return i;
				}
			}
			return -1;
		}

		// ----------------------------------------------------------------------------
		int getDepth() {
			return this.colorDepth;
		}

		// ----------------------------------------------------------------------------
		int getTransparent() {
			return this.transparentIndex;
		}

		// ----------------------------------------------------------------------------
		// default: -1 (no transparency)
		void setTransparent(int color_index) {
			this.transparentIndex = color_index;
		}

		// ----------------------------------------------------------------------------
		void processPixels(Gif89Frame gf) throws IOException {
			if (gf instanceof DirectGif89Frame) {
				this.filterPixels((DirectGif89Frame) gf);
			} else {
				this.trackPixelUsage((IndexGif89Frame) gf);
			}
		}

		// ----------------------------------------------------------------------------
		void closePixelProcessing() // must be called before encode()
		{
			this.colorDepth = this.computeColorDepth(this.ciCount);
		}

		// ----------------------------------------------------------------------------
		void encode(OutputStream os) throws IOException {
			// size of palette written is the smallest power of 2 that can
			// accomdate
			// the number of RGB colors detected (or largest color index, in
			// case of
			// index pixels)
			int palette_size = 1 << this.colorDepth;
			for (int i = 0; i < palette_size; ++i) {
				os.write(this.theColors[i] >> 16 & 0xff);
				os.write(this.theColors[i] >> 8 & 0xff);
				os.write(this.theColors[i] & 0xff);
			}
		}

		// ----------------------------------------------------------------------------
		// This method accomplishes three things:
		// (1) converts the passed rgb pixels to indexes into our rgb lookup
		// table
		// (2) fills the rgb table as new colors are encountered
		// (3) looks for transparent pixels so as to set the transparent index
		// The information is cumulative across multiple calls.
		//
		// (Note: some of the logic is borrowed from Jef Poskanzer's code.)
		// ----------------------------------------------------------------------------
		private void filterPixels(DirectGif89Frame dgf) throws IOException {
			if (this.ciLookup == null) {
				throw new IOException("RGB frames require palette autodetection");
			}

			int[] argb_pixels = (int[]) dgf.getPixelSource();
			byte[] ci_pixels = dgf.getPixelSink();
			int npixels = argb_pixels.length;
			for (int i = 0; i < npixels; ++i) {
				int argb = argb_pixels[i];

				// handle transparency
				if ((argb >>> 24) < 0x80) {
					if (this.transparentIndex == -1) {
						// encountered?
						this.transparentIndex = this.ciCount; // record its index
					} else if (argb != this.theColors[this.transparentIndex]) // different
						// pixel
						// value?
					{
						// collapse all transparent pixels into one color index
						ci_pixels[i] = (byte) this.transparentIndex;
						continue; // CONTINUE - index already in table
					}
				}

				// try to look up the index in our "reverse" color table
				int color_index = this.ciLookup.getPaletteIndex(argb & 0xffffff);

				if (color_index == -1) // if it isn't in there yet
				{
					if (this.ciCount == 256) {
						throw new IOException("can't encode as GIF (> 256 colors)");
					}

					// store color in our accumulating palette
					this.theColors[this.ciCount] = argb;

					// store index in reverse color table
					this.ciLookup.put(argb & 0xffffff, this.ciCount);

					// send color index to our output array
					ci_pixels[i] = (byte) this.ciCount;

					// increment count of distinct color indices
					++this.ciCount;
				} else {
					// we've already snagged color into our palette
					ci_pixels[i] = (byte) color_index; // just send filtered
					// pixel
				}
			}
		}

		// ----------------------------------------------------------------------------
		private void trackPixelUsage(IndexGif89Frame igf) throws IOException {
			byte[] ci_pixels = (byte[]) igf.getPixelSource();
			int npixels = ci_pixels.length;
			for (int i = 0; i < npixels; ++i) {
				if (ci_pixels[i] >= this.ciCount) {
					this.ciCount = ci_pixels[i] + 1;
				}
			}
		}

		// ----------------------------------------------------------------------------
		private int computeColorDepth(int colorcount) {
			// color depth = log-base-2 of maximum number of simultaneous
			// colors, i.e.
			// bits per color-index pixel
			if (colorcount <= 2) {
				return 1;
			}
			if (colorcount <= 4) {
				return 2;
			}
			if (colorcount <= 16) {
				return 4;
			}
			return 8;
		}
	}

	// ==============================================================================
	// We're doing a very simple linear hashing thing here, which seems
	// sufficient
	// for our needs. I make no claims for this approach other than that it
	// seems
	// an improvement over doing a brute linear search for each pixel on the one
	// hand, and creating a Java object for each pixel (if we were to use a Java
	// Hashtable) on the other. Doubtless my little hash could be improved by
	// tuning the capacity (at the very least). Suggestions are welcome.
	// ==============================================================================
	class ReverseColorMap {

		private class ColorRecord {

			int rgb;
			int ipalette;

			ColorRecord(int rgb, int ipalette) {
				this.rgb = rgb;
				this.ipalette = ipalette;
			}
		}

		// I wouldn't really know what a good hashing capacity is, having missed
		// out
		// on data structures and algorithms class :) Alls I know is, we've got
		// a lot
		// more space than we have time. So let's try a sparse table with a
		// maximum
		// load of about 1/8 capacity.
		private static final int HCAPACITY = 2053; // a nice prime number

		// our hash table proper
		private ColorRecord[] hTable = new ColorRecord[ReverseColorMap.HCAPACITY];

		// ----------------------------------------------------------------------------
		// Assert: rgb is not negative (which is the same as saying, be sure the
		// alpha transparency byte - i.e., the high byte - has been masked out).
		// ----------------------------------------------------------------------------
		int getPaletteIndex(int rgb) {
			ColorRecord rec;

			for (int itable = rgb % this.hTable.length; (rec = this.hTable[itable]) != null && rec.rgb != rgb; itable =
					++itable % this.hTable.length) {
				;
			}

			if (rec != null) {
				return rec.ipalette;
			}

			return -1;
		}

		// ----------------------------------------------------------------------------
		// Assert: (1) same as above; (2) rgb key not already present
		// ----------------------------------------------------------------------------
		void put(int rgb, int ipalette) {
			int itable;

			for (itable = rgb % this.hTable.length; this.hTable[itable] != null; itable =
					++itable % this.hTable.length) {
				;
			}

			this.hTable[itable] = new ColorRecord(rgb, ipalette);
		}
	}

	// ******************************************************************************
	// Gif89Frame.java
	// ******************************************************************************

	// ==============================================================================
	/**
	 * First off, just to dispel any doubt, this class and its subclasses have nothing to
	 * do with GUI "frames" such as java.awt.Frame. We merely use the term in its very
	 * common sense of a still picture in an animation sequence. It's hoped that the
	 * restricted context will prevent any confusion.
	 * <p>
	 * An instance of this class is used in conjunction with a Gif89Encoder object to
	 * represent and encode a single static image and its associated "control" data. A
	 * Gif89Frame doesn't know or care whether it is encoding one of the many animation
	 * frames in a GIF movie, or the single bitmap in a "normal" GIF. (FYI, this design
	 * mirrors the encoded GIF structure.)
	 * <p>
	 * Since Gif89Frame is an abstract class we don't instantiate it directly, but instead
	 * create instances of its concrete subclasses, IndexGif89Frame and DirectGif89Frame.
	 * From the API standpoint, these subclasses differ only in the sort of data their
	 * instances are constructed from. Most folks will probably work with DirectGif89Frame,
	 * since it can be constructed from a java.awt.Image object, but the lower-level
	 * IndexGif89Frame class offers advantages in specialized circumstances. (Of course, in
	 * routine situations you might not explicitly instantiate any frames at all, instead
	 * letting Gif89Encoder's convenience methods do the honors.)
	 * <p>
	 * As far as the public API is concerned, objects in the Gif89Frame hierarchy interact
	 * with a Gif89Encoder only via the latter's methods for adding and querying frames.
	 * (As a side note, you should know that while Gif89Encoder objects are permanently
	 * modified by the addition of Gif89Frames, the reverse is NOT true. That is, even
	 * though the ultimate encoding of a Gif89Frame may be affected by the context its
	 * parent encoder object provides, it retains its original condition and can be reused
	 * in a different context.)
	 * <p>
	 * The core pixel-encoding code in this class was essentially lifted from Jef
	 * Poskanzer's well-known <cite>Acme GifEncoder</cite>, so please see the <a
	 * href="../readme.txt">readme</a> containing his notice.
	 *
	 * @version 0.90 beta (15-Jul-2000)
	 * @author J. M. G. Elliott (tep@jmge.net)
	 * @see Gif89Encoder
	 * @see DirectGif89Frame
	 * @see IndexGif89Frame
	 */
	abstract class Gif89Frame {

		// // Public "Disposal Mode" constants ////

		/**
		 * The animated GIF renderer shall decide how to dispose of this Gif89Frame's
		 * display area.
		 *
		 * @see Gif89Frame#setDisposalMode
		 */
		public static final int DM_UNDEFINED = 0;

		/**
		 * The animated GIF renderer shall take no display-disposal action.
		 *
		 * @see Gif89Frame#setDisposalMode
		 */
		public static final int DM_LEAVE = 1;

		/**
		 * The animated GIF renderer shall replace this Gif89Frame's area with the
		 * background color.
		 *
		 * @see Gif89Frame#setDisposalMode
		 */
		public static final int DM_BGCOLOR = 2;

		/**
		 * The animated GIF renderer shall replace this Gif89Frame's area with the previous
		 * frame's bitmap.
		 *
		 * @see Gif89Frame#setDisposalMode
		 */
		public static final int DM_REVERT = 3;

		// // Bitmap variables set in package subclass constructors ////
		int theWidth = -1;
		int theHeight = -1;
		byte[] ciPixels;

		// // GIF graphic frame control options ////
		private Point thePosition = new Point(0, 0);
		private boolean isInterlaced;
		private int csecsDelay;
		private int disposalCode = Gif89Frame.DM_LEAVE;

		// ----------------------------------------------------------------------------
		/**
		 * Set the position of this frame within a larger animation display space.
		 *
		 * @param p Coordinates of the frame's upper left corner in the display space.
		 * (Default: The logical display's origin [0, 0])
		 * @see Gif89Encoder#setLogicalDisplay
		 */
		public void setPosition(Point p) {
			this.thePosition = new Point(p);
		}

		// ----------------------------------------------------------------------------
		/**
		 * Set or clear the interlace flag.
		 *
		 * @param b true if you want interlacing. (Default: false)
		 */
		public void setInterlaced(boolean b) {
			this.isInterlaced = b;
		}

		// ----------------------------------------------------------------------------
		/**
		 * Set the between-frame interval.
		 *
		 * @param interval Centiseconds to wait before displaying the subsequent frame.
		 * (Default: 0)
		 */
		public void setDelay(int interval) {
			this.csecsDelay = interval;
		}

		// ----------------------------------------------------------------------------
		/**
		 * Setting this option determines (in a cooperative GIF-viewer) what will be done
		 * with this frame's display area before the subsequent frame is displayed. For
		 * instance, a setting of DM_BGCOLOR can be used for erasure when redrawing with
		 * displacement.
		 *
		 * @param code One of the four int constants of the Gif89Frame.DM_* series.
		 * (Default: DM_LEAVE)
		 */
		public void setDisposalMode(int code) {
			this.disposalCode = code;
		}

		// ----------------------------------------------------------------------------
		Gif89Frame() {
		} // package-visible default constructor

		// ----------------------------------------------------------------------------
		abstract Object getPixelSource();

		// ----------------------------------------------------------------------------
		int getWidth() {
			return this.theWidth;
		}

		// ----------------------------------------------------------------------------
		int getHeight() {
			return this.theHeight;
		}

		// ----------------------------------------------------------------------------
		byte[] getPixelSink() {
			return this.ciPixels;
		}

		// ----------------------------------------------------------------------------
		void encode(OutputStream os, boolean epluribus, int color_depth, int transparent_index)
				throws IOException {
			this.writeGraphicControlExtension(os, epluribus, transparent_index);
			this.writeImageDescriptor(os);
			new GifPixelsEncoder(this.theWidth, this.theHeight, this.ciPixels, this.isInterlaced, color_depth)
			.encode(os);
		}

		// ----------------------------------------------------------------------------
		private void writeGraphicControlExtension(OutputStream os, boolean epluribus, int itransparent)
				throws IOException {
			int transflag = itransparent == -1 ? 0 : 1;
			if (transflag == 1 || epluribus) // using transparency or animating
				// ?
			{
				os.write('!'); // GIF Extension Introducer
				os.write(0xf9); // Graphic Control Label
				os.write(4); // subsequent data block size
				os.write((this.disposalCode << 2) | transflag); // packed fields (1
				// byte)
				DrawingPanel.putShort(this.csecsDelay, os); // delay field (2 bytes)
				os.write(itransparent); // transparent index field
				os.write(0); // block terminator
			}
		}

		// ----------------------------------------------------------------------------
		private void writeImageDescriptor(OutputStream os) throws IOException {
			os.write(','); // Image Separator
			DrawingPanel.putShort(this.thePosition.x, os);
			DrawingPanel.putShort(this.thePosition.y, os);
			DrawingPanel.putShort(this.theWidth, os);
			DrawingPanel.putShort(this.theHeight, os);
			os.write(this.isInterlaced ? 0x40 : 0); // packed fields (1 byte)
		}
	}

	// ==============================================================================
	class GifPixelsEncoder {

		private static final int EOF = -1;

		private int imgW, imgH;
		private byte[] pixAry;
		private boolean wantInterlaced;
		private int initCodeSize;

		// raster data navigators
		private int countDown;
		private int xCur, yCur;
		private int curPass;

		// ----------------------------------------------------------------------------
		GifPixelsEncoder(int width, int height, byte[] pixels, boolean interlaced, int color_depth) {
			this.imgW = width;
			this.imgH = height;
			this.pixAry = pixels;
			this.wantInterlaced = interlaced;
			this.initCodeSize = Math.max(2, color_depth);
		}

		// ----------------------------------------------------------------------------
		void encode(OutputStream os) throws IOException {
			os.write(this.initCodeSize); // write "initial code size" byte

			this.countDown = this.imgW * this.imgH; // reset navigation variables
			this.xCur = this.yCur = this.curPass = 0;

			this.compress(this.initCodeSize + 1, os); // compress and write the pixel data

			os.write(0); // write block terminator
		}

		// ****************************************************************************
		// (J.E.) The logic of the next two methods is largely intact from
		// Jef Poskanzer. Some stylistic changes were made for consistency sake,
		// plus the second method accesses the pixel value from a prefiltered
		// linear
		// array. That's about it.
		// ****************************************************************************

		// ----------------------------------------------------------------------------
		// Bump the 'xCur' and 'yCur' to point to the next pixel.
		// ----------------------------------------------------------------------------
		private void bumpPosition() {
			// Bump the current X position
			++this.xCur;

			// If we are at the end of a scan line, set xCur back to the
			// beginning
			// If we are interlaced, bump the yCur to the appropriate spot,
			// otherwise, just increment it.
			if (this.xCur == this.imgW) {
				this.xCur = 0;

				if ( !this.wantInterlaced) {
					++this.yCur;
				} else {
					switch (this.curPass) {
						case 0:
							this.yCur += 8;
							if (this.yCur >= this.imgH) {
								++this.curPass;
								this.yCur = 4;
							}
							break;
						case 1:
							this.yCur += 8;
							if (this.yCur >= this.imgH) {
								++this.curPass;
								this.yCur = 2;
							}
							break;
						case 2:
							this.yCur += 4;
							if (this.yCur >= this.imgH) {
								++this.curPass;
								this.yCur = 1;
							}
							break;
						case 3:
							this.yCur += 2;
							break;
					}
				}
			}
		}

		// ----------------------------------------------------------------------------
		// Return the next pixel from the image
		// ----------------------------------------------------------------------------
		private int nextPixel() {
			if (this.countDown == 0) {
				return GifPixelsEncoder.EOF;
			}

			--this.countDown;

			byte pix = this.pixAry[this.yCur * this.imgW + this.xCur];

			this.bumpPosition();

			return pix & 0xff;
		}

		// ****************************************************************************
		// (J.E.) I didn't touch Jef Poskanzer's code from this point on. (Well,
		// OK,
		// I changed the name of the sole outside method it accesses.) I figure
		// if I have no idea how something works, I shouldn't play with it :)
		//
		// Despite its unencapsulated structure, this section is actually highly
		// self-contained. The calling code merely calls compress(), and the
		// present
		// code calls nextPixel() in the caller. That's the sum total of their
		// communication. I could have dumped it in a separate class with a
		// callback
		// via an interface, but it didn't seem worth messing with.
		// ****************************************************************************

		// GIFCOMPR.C - GIF Image compression routines
		//
		// Lempel-Ziv compression based on 'compress'. GIF modifications by
		// David Rowley (mgardi@watdcsu.waterloo.edu)

		// General DEFINEs

		static final int BITS = 12;

		static final int HSIZE = 5003; // 80% occupancy

		// GIF Image compression - modified 'compress'
		//
		// Based on: compress.c - File compression ala IEEE Computer, June 1984.
		//
		// By Authors: Spencer W. Thomas (decvax!harpo!utah-cs!utah-gr!thomas)
		// Jim McKie (decvax!mcvax!jim)
		// Steve Davies (decvax!vax135!petsd!peora!srd)
		// Ken Turkowski (decvax!decwrl!turtlevax!ken)
		// James A. Woods (decvax!ihnp4!ames!jaw)
		// Joe Orost (decvax!vax135!petsd!joe)

		int n_bits; // number of bits/code
		int maxbits = GifPixelsEncoder.BITS; // user settable max # bits/code
		int maxcode; // maximum code, given n_bits
		int maxmaxcode = 1 << GifPixelsEncoder.BITS; // should NEVER generate this code

		final int MAXCODE(int n_bits) {
			return (1 << n_bits) - 1;
		}

		int[] htab = new int[GifPixelsEncoder.HSIZE];
		int[] codetab = new int[GifPixelsEncoder.HSIZE];

		int hsize = GifPixelsEncoder.HSIZE; // for dynamic table sizing

		int free_ent = 0; // first unused entry

		// block compression parameters -- after all codes are used up,
		// and compression rate changes, start over.
		boolean clear_flg = false;

		// Algorithm: use open addressing double hashing (no chaining) on the
		// prefix code / next character combination. We do a variant of Knuth's
		// algorithm D (vol. 3, sec. 6.4) along with G. Knott's relatively-prime
		// secondary probe. Here, the modular division first probe is gives way
		// to a faster exclusive-or manipulation. Also do block compression with
		// an adaptive reset, whereby the code table is cleared when the
		// compression
		// ratio decreases, but after the table fills. The variable-length
		// output
		// codes are re-sized at this point, and a special CLEAR code is
		// generated
		// for the decompressor. Late addition: construct the table according to
		// file size for noticeable speed improvement on small files. Please
		// direct
		// questions about this implementation to ames!jaw.

		int g_init_bits;

		int ClearCode;
		int EOFCode;

		void compress(int init_bits, OutputStream outs) throws IOException {
			int fcode;
			int i /* = 0 */;
			int c;
			int ent;
			int disp;
			int hsize_reg;
			int hshift;

			// Set up the globals: g_init_bits - initial number of bits
			this.g_init_bits = init_bits;

			// Set up the necessary values
			this.clear_flg = false;
			this.n_bits = this.g_init_bits;
			this.maxcode = this.MAXCODE(this.n_bits);

			this.ClearCode = 1 << (init_bits - 1);
			this.EOFCode = this.ClearCode + 1;
			this.free_ent = this.ClearCode + 2;

			this.char_init();

			ent = this.nextPixel();

			hshift = 0;
			for (fcode = this.hsize; fcode < 65536; fcode *= 2) {
				++hshift;
			}
			hshift = 8 - hshift; // set hash code range bound

			hsize_reg = this.hsize;
			this.cl_hash(hsize_reg); // clear hash table

			this.output(this.ClearCode, outs);

			outer_loop:
				while ((c = this.nextPixel()) != GifPixelsEncoder.EOF) {
					fcode = (c << this.maxbits) + ent;
					i = (c << hshift) ^ ent; // xor hashing

					if (this.htab[i] == fcode) {
						ent = this.codetab[i];
						continue;
					} else if (this.htab[i] >= 0) // non-empty slot
					{
						disp = hsize_reg - i; // secondary hash (after G. Knott)
						if (i == 0) {
							disp = 1;
						}
						do {
							if ((i -= disp) < 0) {
								i += hsize_reg;
							}

							if (this.htab[i] == fcode) {
								ent = this.codetab[i];
								continue outer_loop;
							}
						} while (this.htab[i] >= 0);
					}
					this.output(ent, outs);
					ent = c;
					if (this.free_ent < this.maxmaxcode) {
						this.codetab[i] = this.free_ent++; // code -> hashtable
						this.htab[i] = fcode;
					} else {
						this.cl_block(outs);
					}
				}
			// Put out the final code.
			this.output(ent, outs);
			this.output(this.EOFCode, outs);
		}

		// output
		//
		// Output the given code.
		// Inputs:
		// code: A n_bits-bit integer. If == -1, then EOF. This assumes
		// that n_bits =< wordsize - 1.
		// Outputs:
		// Outputs code to the file.
		// Assumptions:
		// Chars are 8 bits long.
		// Algorithm:
		// Maintain a BITS character long buffer (so that 8 codes will
		// fit in it exactly). Use the VAX insv instruction to insert each
		// code in turn. When the buffer fills up empty it and start over.

		int cur_accum = 0;
		int cur_bits = 0;

		int masks[] = {0x0000, 0x0001, 0x0003, 0x0007, 0x000F, 0x001F, 0x003F, 0x007F, 0x00FF, 0x01FF,
				0x03FF, 0x07FF, 0x0FFF, 0x1FFF, 0x3FFF, 0x7FFF, 0xFFFF};

		void output(int code, OutputStream outs) throws IOException {
			this.cur_accum &= this.masks[this.cur_bits];

			if (this.cur_bits > 0) {
				this.cur_accum |= (code << this.cur_bits);
			} else {
				this.cur_accum = code;
			}

			this.cur_bits += this.n_bits;

			while (this.cur_bits >= 8) {
				this.char_out((byte) (this.cur_accum & 0xff), outs);
				this.cur_accum >>= 8;
				this.cur_bits -= 8;
			}

			// If the next entry is going to be too big for the code size,
			// then increase it, if possible.
			if (this.free_ent > this.maxcode || this.clear_flg) {
				if (this.clear_flg) {
					this.maxcode = this.MAXCODE(this.n_bits = this.g_init_bits);
					this.clear_flg = false;
				} else {
					++this.n_bits;
					if (this.n_bits == this.maxbits) {
						this.maxcode = this.maxmaxcode;
					} else {
						this.maxcode = this.MAXCODE(this.n_bits);
					}
				}
			}

			if (code == this.EOFCode) {
				// At EOF, write the rest of the buffer.
				while (this.cur_bits > 0) {
					this.char_out((byte) (this.cur_accum & 0xff), outs);
					this.cur_accum >>= 8;
			this.cur_bits -= 8;
				}

				this.flush_char(outs);
			}
		}

		// Clear out the hash table

		// table clear for block compress
		void cl_block(OutputStream outs) throws IOException {
			this.cl_hash(this.hsize);
			this.free_ent = this.ClearCode + 2;
			this.clear_flg = true;

			this.output(this.ClearCode, outs);
		}

		// reset code table
		void cl_hash(int hsize) {
			for (int i = 0; i < hsize; ++i) {
				this.htab[i] = -1;
			}
		}

		// GIF Specific routines

		// Number of characters so far in this 'packet'
		int a_count;

		// Set up the 'byte output' routine
		void char_init() {
			this.a_count = 0;
		}

		// Define the storage for the packet accumulator
		byte[] accum = new byte[256];

		// Add a character to the end of the current packet, and if it is 254
		// characters, flush the packet to disk.
		void char_out(byte c, OutputStream outs) throws IOException {
			this.accum[this.a_count++] = c;
			if (this.a_count >= 254) {
				this.flush_char(outs);
			}
		}

		// Flush the packet to disk, and reset the accumulator
		void flush_char(OutputStream outs) throws IOException {
			if (this.a_count > 0) {
				outs.write(this.a_count);
				outs.write(this.accum, 0, this.a_count);
				this.a_count = 0;
			}
		}
	}

	// ******************************************************************************
	// IndexGif89Frame.java
	// ******************************************************************************

	// ==============================================================================
	/**
	 * Instances of this Gif89Frame subclass are constructed from bitmaps in the form of
	 * color-index pixels, which accords with a GIF's native palettized color model. The
	 * class is useful when complete control over a GIF's color palette is desired. It is
	 * also much more efficient when one is using an algorithmic frame generator that isn't
	 * interested in RGB values (such as a cellular automaton).
	 * <p>
	 * Objects of this class are normally added to a Gif89Encoder object that has been
	 * provided with an explicit color table at construction. While you may also add them
	 * to "auto-map" encoders without an exception being thrown, there obviously must be at
	 * least one DirectGif89Frame object in the sequence so that a color table may be
	 * detected.
	 *
	 * @version 0.90 beta (15-Jul-2000)
	 * @author J. M. G. Elliott (tep@jmge.net)
	 * @see Gif89Encoder
	 * @see Gif89Frame
	 * @see DirectGif89Frame
	 */
	class IndexGif89Frame extends Gif89Frame {

		// ----------------------------------------------------------------------------
		/**
		 * Construct a IndexGif89Frame from color-index pixel data.
		 *
		 * @param width Width of the bitmap.
		 * @param height Height of the bitmap.
		 * @param ci_pixels Array containing at least width*height color-index pixels.
		 */
		public IndexGif89Frame(int width, int height, byte ci_pixels[]) {
			this.theWidth = width;
			this.theHeight = height;
			this.ciPixels = new byte[this.theWidth * this.theHeight];
			System.arraycopy(ci_pixels, 0, this.ciPixels, 0, this.ciPixels.length);
		}

		// ----------------------------------------------------------------------------
		@Override
		Object getPixelSource() {
			return this.ciPixels;
		}
	}

	// ----------------------------------------------------------------------------
	/**
	 * Write just the low bytes of a String. (This sucks, but the concept of an encoding
	 * seems inapplicable to a binary file ID string. I would think flexibility is just
	 * what we don't want - but then again, maybe I'm slow.)
	 */
	public static void putAscii(String s, OutputStream os) throws IOException {
		byte[] bytes = new byte[s.length()];
		for (int i = 0; i < bytes.length; ++i) {
			bytes[i] = (byte) s.charAt(i); // discard the high byte
		}
		os.write(bytes);
	}

	// ----------------------------------------------------------------------------
	/**
	 * Write a 16-bit integer in little endian byte order.
	 */
	public static void putShort(int i16, OutputStream os) throws IOException {
		os.write(i16 & 0xff);
		os.write(i16 >> 8 & 0xff);
	}
}
