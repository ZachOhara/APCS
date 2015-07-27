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

import javax.swing.JColorChooser;
import javax.swing.JFrame;

/**
 * A class to make working with a color chooser easier for students. It uses a
 * JColorChooser to let the user pick a color and returns the chosen color object.
 *
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class ColorChooser {

	/**
	 * Method to let the user pick a color and return the color object.
	 *
	 * @return the picked color or red if no color was picked
	 */
	public static Color pickAColor() {
		Color color = Color.white;

		// create a JFrame to be the parent of the color chooser open dialog
		// if you don't do this then you may not see the dialog.
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);

		// use the color chooser to pick the color
		color = JColorChooser.showDialog(frame, "Pick a color", color);

		return color;
	}

	/** Main method for testing the ColorChooser */
	public static void main(String[] args) {
		Color pickedColor = ColorChooser.pickAColor();
		System.out.println(pickedColor);
	}

}
