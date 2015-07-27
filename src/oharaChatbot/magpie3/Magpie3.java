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

package oharaChatbot.magpie3;

public class Magpie3 {

	/**
	 * Get a default greeting
	 *
	 * @return a greeting
	 */

	private static final String[] negativityTriggers = {"no"};
	private static final String[] familyTriggers = {"mother", "mom", "father", "dad", "sister", "brother"};
	private static final String[] petTriggers = {"dog", "cat"};
	private static final String[] teacherTriggers = {"mr.", "mrs.", "ms."};
	private static final String[] zachTriggers = {"zach", "ohara", "o'hara"};

	private static final String[] teacherPronouns = {"He", "She", "She"};

	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param input the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String input) {
		String response = "";
		input = input.toLowerCase();
		if (input.trim().length() == 0) {
			response = "Say something, please.";
		} else if (Magpie3.mentions(input, Magpie3.negativityTriggers)) {
			response = "Why so negative?";
		} else if (Magpie3.mentions(input, Magpie3.familyTriggers)) {
			response = "Tell me more about your family.";
		} else if (Magpie3.mentions(input, Magpie3.petTriggers)) {
			response = "Tell me more about your pets";
		} else if (Magpie3.mentions(input, Magpie3.teacherTriggers)) {
			response =
					Magpie3.teacherPronouns[Magpie3.mentionIndex(input, Magpie3.teacherTriggers)]
							+ " sounds like a good teacher.";
		} else if (Magpie3.mentions(input, Magpie3.zachTriggers)) {
			response = "That name! That's my programmer";
		} else {
			response = this.getRandomResponse();
		}
		return response;
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 *
	 * @return a non-committal string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		}

		return response;
	}

	private static boolean mentions(String phrase, String[] triggers) {
		return Magpie3.mentionIndex(phrase, triggers) != -1;
	}

	private static int mentionIndex(String phrase, String[] triggers) {
		for (int i = 0; i < triggers.length; i++) {
			if (Magpie3.findKeyword(phrase, triggers[i]) != -1) {
				return i;
			}
		}
		return -1;
	}

	private static int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0) {
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before
					// is
					// not
					// a
					// letter
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase. The search is not case sensitive. This method will
	 * check that the given goal is not a substring of a longer string (so, for example,
	 * "I know" does not contain "no"). The search begins at the beginning of the string.
	 *
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not
	 * found
	 */
	private static int findKeyword(String statement, String goal) {
		return Magpie3.findKeyword(statement, goal, 0);
	}

}
