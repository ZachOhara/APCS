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

import java.util.ArrayList;
import java.util.List;

public class Classroom {

	/**
	 * Test the ChatBot FRQ
	 */
	public static void main(String[] args) {
		List<ChatBot> bots = new ArrayList<ChatBot>();
		bots.add(new StudentBot("Ada"));
		bots.add(new TeachersPet("Boris"));
		bots.add(new StudentBot("Claire"));

		Classroom.greetAll(bots);
		System.out.println();
	}

	/**
	 * Prompts the user with the prompt "Enter greeting:", and displays the appropriate
	 * response for each student in the list.
	 *
	 * @param students a list of ChatBots
	 */
	public static void greetAll(List<ChatBot> bots) {
		// String greeting = ChatBot.readInput("Enter greeting:");
		for (int i = 0; i < bots.size(); i++) {
			String greeting = ChatBot.readInput("Enter greeting:");
			System.out.println(bots.get(i).getName() + ": " + bots.get(i).respondToGreeting(greeting));
		}
	}

}
