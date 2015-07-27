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

package oharaClassesPractice;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Temperature extends JFrame implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField displayF, displayC;

	// Constructor
	public Temperature() {
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(2, 2, 10, 0));

		c.add(new JLabel("  Fahrenheit:"));
		c.add(new JLabel("  Celsius:"));

		this.displayF = new JTextField(6);
		this.displayF.setBackground(Color.YELLOW);
		this.displayF.addActionListener(this);
		c.add(this.displayF);

		this.displayC = new JTextField(6);
		this.displayC.setBackground(Color.YELLOW);
		this.displayC.addActionListener(this);
		c.add(this.displayC);
	}

	// Invoked when <Enter> is pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		FCConverter fc = new FCConverter();

		if ((JTextField) e.getSource() == this.displayF) {
			// Fahrenheit to Celsius
			double degrees = this.stringToDouble(this.displayF.getText());
			if ( !Double.isNaN(degrees)) {
				fc.setFahrenheit(degrees);
				degrees = fc.getCelsius();
				this.displayC.setText(String.format("%1.1f", degrees));
			} else {
				this.displayF.selectAll();
			}
		} else {
			// Celsius to Fahrenheit
			double degrees = this.stringToDouble(this.displayC.getText());
			if ( !Double.isNaN(degrees)) {
				fc.setCelsius(degrees);
				degrees = fc.getFahrenheit();
				this.displayF.setText(String.format("%1.1f", degrees));
			} else {
				this.displayC.selectAll();
			}
		}
	}

	// Extracts a double value from a string
	private double stringToDouble(String s) {
		double degrees;
		try {
			degrees = Double.parseDouble(s);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
			degrees = Double.NaN;
		}
		return degrees;
	}

	// **********************************************************************

	public static void main(String[] args) {
		Temperature window = new Temperature();
		window.setBounds(300, 300, 200, 80);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
