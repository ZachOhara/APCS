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

 package oharaElevens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class provides a GUI for solitaire games related to Elevens.
 */
public class CardGameGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** Height of the game frame. */
	private static final int DEFAULT_HEIGHT = 302;
	/** Width of the game frame. */
	private static final int DEFAULT_WIDTH = 800;
	/** Width of a card. */
	private static final int CARD_WIDTH = 73;
	/** Height of a card. */
	private static final int CARD_HEIGHT = 97;
	/** Row (y coord) of the upper left corner of the first card. */
	private static final int LAYOUT_TOP = 30;
	/** Column (x coord) of the upper left corner of the first card. */
	private static final int LAYOUT_LEFT = 30;
	/**
	 * Distance between the upper left x coords of two horizonally adjacent cards.
	 */
	private static final int LAYOUT_WIDTH_INC = 100;
	/**
	 * Distance between the upper left y coords of two vertically adjacent cards.
	 */
	private static final int LAYOUT_HEIGHT_INC = 125;
	/** y coord of the "Replace" button. */
	private static final int BUTTON_TOP = 30;
	/** x coord of the "Replace" button. */
	private static final int BUTTON_LEFT = 570;
	/** Distance between the tops of the "Replace" and "Restart" buttons. */
	private static final int BUTTON_HEIGHT_INC = 50;
	/** y coord of the "n undealt cards remain" label. */
	private static final int LABEL_TOP = 160;
	/** x coord of the "n undealt cards remain" label. */
	private static final int LABEL_LEFT = 540;
	/**
	 * Distance between the tops of the "n undealt cards" and the "You lose/win" labels.
	 */
	private static final int LABEL_HEIGHT_INC = 35;

	/** The board (Board subclass). */
	private Board board;

	/** The main panel containing the game components. */
	private JPanel panel;
	/** The Replace button. */
	private JButton replaceButton;
	/** The Restart button. */
	private JButton restartButton;
	/** The "number of undealt cards remain" message. */
	private JLabel statusMsg;
	/** The "you've won n out of m games" message. */
	private JLabel totalsMsg;
	/** The card displays. */
	private JLabel[] displayCards;
	/** The win message. */
	private JLabel winMsg;
	/** The loss message. */
	private JLabel lossMsg;
	/** The coordinates of the card displays. */
	private Point[] cardCoords;

	/** kth element is true iff the user has selected card #k. */
	private boolean[] selections;
	/** The number of games won. */
	private int totalWins;
	/** The number of games played. */
	private int totalGames;

	/**
	 * Initialize the GUI.
	 *
	 * @param gameBoard is a <code>Board</code> subclass.
	 */
	public CardGameGUI(Board gameBoard) {
		this.board = gameBoard;
		this.totalWins = 0;
		this.totalGames = 0;

		// Initialize cardCoords using 5 cards per row
		this.cardCoords = new Point[this.board.size()];
		int x = CardGameGUI.LAYOUT_LEFT;
		int y = CardGameGUI.LAYOUT_TOP;
		for (int i = 0; i < this.cardCoords.length; i++) {
			this.cardCoords[i] = new Point(x, y);
			if (i % 5 == 4) {
				x = CardGameGUI.LAYOUT_LEFT;
				y += CardGameGUI.LAYOUT_HEIGHT_INC;
			} else {
				x += CardGameGUI.LAYOUT_WIDTH_INC;
			}
		}

		this.selections = new boolean[this.board.size()];
		this.initDisplay();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.repaint();
	}

	/**
	 * Run the game.
	 */
	public void displayGame() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				CardGameGUI.this.setVisible(true);
			}
		});
	}

	/**
	 * Draw the display (cards and messages).
	 */
	@Override
	public void repaint() {
		for (int k = 0; k < this.board.size(); k++) {
			String cardImageFileName = this.imageFileName(this.board.cardAt(k), this.selections[k]);
			URL imageURL = this.getClass().getResource(cardImageFileName);
			if (imageURL != null) {
				ImageIcon icon = new ImageIcon(imageURL);
				this.displayCards[k].setIcon(icon);
				this.displayCards[k].setVisible(true);
			} else {
				throw new RuntimeException("Card image not found: \"" + cardImageFileName + "\"");
			}
		}
		this.statusMsg.setText(this.board.deckSize() + " undealt cards remain.");
		this.statusMsg.setVisible(true);
		this.totalsMsg.setText("You've won " + this.totalWins + " out of " + this.totalGames + " games.");
		this.totalsMsg.setVisible(true);
		this.pack();
		this.panel.repaint();
	}

	/**
	 * Initialize the display.
	 */
	private void initDisplay() {
		this.panel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
			}
		};

		// If board object's class name follows the standard format
		// of ...Board or ...board, use the prefix for the JFrame title
		String className = this.board.getClass().getSimpleName();
		int classNameLen = className.length();
		int boardLen = "Board".length();
		String boardStr = className.substring(classNameLen - boardLen);
		if (boardStr.equals("Board") || boardStr.equals("board")) {
			int titleLength = classNameLen - boardLen;
			this.setTitle(className.substring(0, titleLength));
		}

		// Calculate number of rows of cards (5 cards per row)
		// and adjust JFrame height if necessary
		int numCardRows = (this.board.size() + 4) / 5;
		int height = CardGameGUI.DEFAULT_HEIGHT;
		if (numCardRows > 2) {
			height += (numCardRows - 2) * CardGameGUI.LAYOUT_HEIGHT_INC;
		}

		this.setSize(new Dimension(CardGameGUI.DEFAULT_WIDTH, height));
		this.panel.setLayout(null);
		this.panel.setPreferredSize(new Dimension(CardGameGUI.DEFAULT_WIDTH - 20, height - 20));
		this.displayCards = new JLabel[this.board.size()];
		for (int k = 0; k < this.board.size(); k++) {
			this.displayCards[k] = new JLabel();
			this.panel.add(this.displayCards[k]);
			this.displayCards[k].setBounds(this.cardCoords[k].x, this.cardCoords[k].y,
					CardGameGUI.CARD_WIDTH, CardGameGUI.CARD_HEIGHT);
			this.displayCards[k].addMouseListener(new MyMouseListener());
			this.selections[k] = false;
		}
		this.replaceButton = new JButton();
		this.replaceButton.setText("Replace");
		this.panel.add(this.replaceButton);
		this.replaceButton.setBounds(CardGameGUI.BUTTON_LEFT, CardGameGUI.BUTTON_TOP, 100, 30);
		this.replaceButton.addActionListener(this);

		this.restartButton = new JButton();
		this.restartButton.setText("Restart");
		this.panel.add(this.restartButton);
		this.restartButton.setBounds(CardGameGUI.BUTTON_LEFT, CardGameGUI.BUTTON_TOP
				+ CardGameGUI.BUTTON_HEIGHT_INC, 100, 30);
		this.restartButton.addActionListener(this);

		this.statusMsg = new JLabel(this.board.deckSize() + " undealt cards remain.");
		this.panel.add(this.statusMsg);
		this.statusMsg.setBounds(CardGameGUI.LABEL_LEFT, CardGameGUI.LABEL_TOP, 250, 30);

		this.winMsg = new JLabel();
		this.winMsg.setBounds(CardGameGUI.LABEL_LEFT, CardGameGUI.LABEL_TOP + CardGameGUI.LABEL_HEIGHT_INC,
				200, 30);
		this.winMsg.setFont(new Font("SansSerif", Font.BOLD, 25));
		this.winMsg.setForeground(Color.GREEN);
		this.winMsg.setText("You win!");
		this.panel.add(this.winMsg);
		this.winMsg.setVisible(false);

		this.lossMsg = new JLabel();
		this.lossMsg.setBounds(CardGameGUI.LABEL_LEFT, CardGameGUI.LABEL_TOP + CardGameGUI.LABEL_HEIGHT_INC,
				200, 30);
		this.lossMsg.setFont(new Font("SanSerif", Font.BOLD, 25));
		this.lossMsg.setForeground(Color.RED);
		this.lossMsg.setText("Sorry, you lose.");
		this.panel.add(this.lossMsg);
		this.lossMsg.setVisible(false);

		this.totalsMsg =
				new JLabel("You've won " + this.totalWins + " out of " + this.totalGames + " games.");
		this.totalsMsg.setBounds(CardGameGUI.LABEL_LEFT, CardGameGUI.LABEL_TOP + 2
				* CardGameGUI.LABEL_HEIGHT_INC, 250, 30);
		this.panel.add(this.totalsMsg);

		if ( !this.board.anotherPlayIsPossible()) {
			this.signalLoss();
		}

		this.pack();
		this.getContentPane().add(this.panel);
		this.getRootPane().setDefaultButton(this.replaceButton);
		this.panel.setVisible(true);
	}

	/**
	 * Deal with the user clicking on something other than a button or a card.
	 */
	private void signalError() {
		Toolkit t = this.panel.getToolkit();
		t.beep();
	}

	/**
	 * Returns the image that corresponds to the input card. Image names have the format
	 * "[Rank][Suit].GIF" or "[Rank][Suit]S.GIF", for example "aceclubs.GIF" or
	 * "8heartsS.GIF". The "S" indicates that the card is selected.
	 *
	 * @param c Card to get the image for
	 * @param isSelected flag that indicates if the card is selected
	 * @return String representation of the image
	 */
	private String imageFileName(Card c, boolean isSelected) {
		String str = "cards/";
		if (c == null) {
			return "cards/back1.GIF";
		}
		str += c.rank() + c.suit();
		if (isSelected) {
			str += "S";
		}
		str += ".GIF";
		return str;
	}

	/**
	 * Respond to a button click (on either the "Replace" button or the "Restart" button).
	 *
	 * @param e the button click action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.replaceButton)) {
			// Gather all the selected cards.
			List<Integer> selection = new ArrayList<Integer>();
			for (int k = 0; k < this.board.size(); k++) {
				if (this.selections[k]) {
					selection.add(new Integer(k));
				}
			}
			// Make sure that the selected cards represent a legal replacement.
			if ( !this.board.isLegal(selection)) {
				this.signalError();
				return;
			}
			for (int k = 0; k < this.board.size(); k++) {
				this.selections[k] = false;
			}
			// Do the replace.
			this.board.replaceSelectedCards(selection);
			if (this.board.isEmpty()) {
				this.signalWin();
			} else if ( !this.board.anotherPlayIsPossible()) {
				this.signalLoss();
			}
			this.repaint();
		} else if (e.getSource().equals(this.restartButton)) {
			this.board.newGame();
			this.getRootPane().setDefaultButton(this.replaceButton);
			this.winMsg.setVisible(false);
			this.lossMsg.setVisible(false);
			if ( !this.board.anotherPlayIsPossible()) {
				this.signalLoss();
				this.lossMsg.setVisible(true);
			}
			for (int i = 0; i < this.selections.length; i++) {
				this.selections[i] = false;
			}
			this.repaint();
		} else {
			this.signalError();
			return;
		}
	}

	/**
	 * Display a win.
	 */
	private void signalWin() {
		this.getRootPane().setDefaultButton(this.restartButton);
		this.winMsg.setVisible(true);
		this.totalWins++;
		this.totalGames++;
	}

	/**
	 * Display a loss.
	 */
	private void signalLoss() {
		this.getRootPane().setDefaultButton(this.restartButton);
		this.lossMsg.setVisible(true);
		this.totalGames++;
	}

	/**
	 * Receives and handles mouse clicks. Other mouse events are ignored.
	 */
	private class MyMouseListener implements MouseListener {

		/**
		 * Handle a mouse click on a card by toggling its "selected" property. Each card is
		 * represented as a label.
		 *
		 * @param e the mouse event.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			for (int k = 0; k < CardGameGUI.this.board.size(); k++) {
				if (e.getSource().equals(CardGameGUI.this.displayCards[k])
						&& CardGameGUI.this.board.cardAt(k) != null) {
					CardGameGUI.this.selections[k] = !CardGameGUI.this.selections[k];
					CardGameGUI.this.repaint();
					return;
				}
			}
			CardGameGUI.this.signalError();
		}

		/**
		 * Ignore a mouse exited event.
		 *
		 * @param e the mouse event.
		 */
		@Override
		public void mouseExited(MouseEvent e) {
		}

		/**
		 * Ignore a mouse released event.
		 *
		 * @param e the mouse event.
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
		}

		/**
		 * Ignore a mouse entered event.
		 *
		 * @param e the mouse event.
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		/**
		 * Ignore a mouse pressed event.
		 *
		 * @param e the mouse event.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
		}
	}
}
