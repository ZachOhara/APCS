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
