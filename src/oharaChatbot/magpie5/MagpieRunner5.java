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

 package oharaChatbot.magpie5;

import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 *
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner5 {

	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args) {
		Magpie5 maggie = new Magpie5();

		System.out.println(maggie.getGreeting());
		Scanner in = new Scanner(System.in);
		String statement = in.nextLine();

		while ( !statement.equals("Bye")) {
			System.out.println(maggie.getResponse(statement));
			statement = in.nextLine();
		}
		in.close();
	}

}
