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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A program to carry on conversations with a human user. This version:
 * <ul>
 * <li>Uses advanced search for keywords</li>
 * <li>Will transform statements as well as react to keywords</li>
 * </ul>
 * This version uses an array to hold the default responses.
 *
 * @author Laurie White
 * @version April 2012
 */
public class Magpie5 {

	private static final String[] negativityTriggers = {"no"};
	private static final String[] familyTriggers = {"mother", "mom", "father", "dad", "sister", "brother"};
	private static final String[] wantingTriggers = {"I want"};
	private static final String[] wantingToTriggers = {"I want to"};

	private static final String[] randomResponses = {"Interesting, tell me more", "Hmmm.",
		"Do you really think so?", "You don't say."};
	private static final ArrayList<String> randomResponseList = new ArrayList<String>();

	/**
	 * Get a default greeting
	 *
	 * @return a greeting
	 */
	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param statement the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement) {
		String response = "";
		if (statement.length() == 0) {
			response = "Say something, please.";
		} else if (this.mentions(statement, Magpie5.negativityTriggers)) {
			response = "Why so negative?";
		} else if (this.mentions(statement, Magpie5.familyTriggers)) {
			response = "Tell me more about your family.";
		} else if (this.mentions(statement, Magpie5.wantingToTriggers)) {
			response = Magpie5.transformIWantToStatement(statement);
		} else if (this.mentions(statement, Magpie5.wantingTriggers)) {
			response = Magpie5.transformIWantStatement(statement);
		} else {

			int pos = Magpie5.findKeyword(statement, "you", 0);

			if (pos >= 0 && Magpie5.findKeyword(statement, "me", pos) >= 0) {
				response = Magpie5.transformYouMeStatement(statement);
			} else {
				pos = Magpie5.findKeyword(statement, "i", 0);
				if (pos >= 0 && Magpie5.findKeyword(statement, "you", pos) >= 0) {
					response = Magpie5.transformIYouStatement(statement);
				} else {
					response = Magpie5.getRandomResponse();
				}
			}
		}
		return response;
	}

	private static String transformIWantToStatement(String statement) {
		return Magpie5.transformToQuestion(statement, "I want to", "What would it mean to");
	}

	private static String transformIWantStatement(String statement) {
		return Magpie5.transformToQuestion(statement, "I want", "Would you really be happy if you had");
	}

	private static String transformYouMeStatement(String statement) {
		return Magpie5.transformQuestionTense(statement, "you", "me", "What makes you think that I", "you");
	}

	private static String transformIYouStatement(String statement) {
		return Magpie5.transformQuestionTense(statement, "I", "you", "Why do you", "me");
	}

	private static String transformToQuestion(String statement, String keyword, String question) {
		statement = Magpie5.removeFinalPeriod(statement);
		int pos = Magpie5.findKeyword(statement, keyword, 0);
		String rest = statement.substring(pos + keyword.length()).trim();
		return question + " " + rest + "?";
	}

	private static String transformQuestionTense(String s, String key1, String key2, String question,
			String endTense) {
		s = Magpie5.removeFinalPeriod(s);
		int pos1 = Magpie5.findKeyword(s, key1);
		int pos2 = Magpie5.findKeyword(s, key2);
		String rest = s.substring(pos1 + key1.length(), pos2).trim();
		return question + " " + rest + " " + endTense + "?";
	}

	private static int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int pos = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (pos >= 0) {
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (pos > 0) {
				before = phrase.substring(pos - 1, pos);
			}
			if (pos + goal.length() < phrase.length()) {
				after = phrase.substring(pos + goal.length(), pos + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before
					// is
					// not
					// a
					// letter
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
				return pos;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			pos = phrase.indexOf(goal, pos + 1);

		}

		return -1;
	}

	private static int findKeyword(String statement, String goal) {
		return Magpie5.findKeyword(statement, goal, 0);
	}

	private static String getRandomResponse() {
		if (Magpie5.randomResponseList.size() == 0) {
			Magpie5.randomResponseList.addAll(Arrays.asList(Magpie5.randomResponses));
		}
		return Magpie5.randomResponseList.get((int) (Math.random() * Magpie5.randomResponseList.size()));
	}

	private boolean mentions(String phrase, String[] triggers) {
		return this.mentionIndex(phrase, triggers) != -1;
	}

	private int mentionIndex(String phrase, String[] triggers) {
		for (int i = 0; i < triggers.length; i++) {
			if (Magpie5.findKeyword(phrase, triggers[i]) != -1) {
				return i;
			}
		}
		return -1;
	}

	private static String removeFinalPeriod(String s) {
		s = s.trim();
		String lastChar = s.substring(s.length() - 1);
		if (lastChar.equals(".")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

}
