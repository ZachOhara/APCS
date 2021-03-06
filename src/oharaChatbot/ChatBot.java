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

package oharaChatbot;

// Exam 4 Question 3

import java.util.Scanner;

public abstract class ChatBot {

	private static Scanner kboard = new Scanner(System.in);

	/**
	 * Displays the prompt string, captures and returns user input.
	 *
	 * @param prompt a string to be displayed as the user prompt
	 * @return a string that represents user input
	 */
	public static String readInput(String prompt) {
		System.out.print(prompt + " ");
		return ChatBot.kboard.nextLine();
	}

	/** The name of this bot */
	private String name;

	/** Constructs a bot with a given name */
	public ChatBot(String nm) {
		this.name = nm;
	}

	/** Returns the name of this bot */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns a string that represents a response to a greeting
	 */
	public abstract String respondToGreeting(String greeting);
}
