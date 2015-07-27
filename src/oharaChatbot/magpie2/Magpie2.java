package oharaChatbot.magpie2;

/**
 * A program to carry on conversations with a human user. This is the initial
 * version that:
 * <ul>
 * <li>
 * Uses indexOf to find strings</li>
 * <li>
 * Handles responding to simple words and phrases</li>
 * </ul>
 * This version uses a nested if to handle default responses.
 * 
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2 {
	
	/**
	 * Get a default greeting
	 * 
	 * @return a greeting
	 */
	
	private static final String[] negativityTriggers = {
		"no"
	};
	private static final String[] familyTriggers = {
			"mother", "mom", "father", "dad", "sister", "brother"
	};
	private static final String[] petTriggers = {
			"dog", "cat"
	};
	private static final String[] teacherTriggers = {
			"mr.", "mrs.", "ms."
	};
	private static final String[] zachTriggers = {
			"zach", "ohara", "o'hara"
	};
	
	private static final String[] teacherPronouns = {
			"He", "She", "She"
	};
	
	public String getGreeting() {
		return "Hello, let's talk.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param input
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String input) {
		String response = "";
		input = input.toLowerCase();
		if (input.trim().length() == 0)
			response = "Say something, please.";
		else if (mentions(input, negativityTriggers))
			response = "Why so negative?";
		else if (mentions(input, familyTriggers))
			response = "Tell me more about your family.";
		else if (mentions(input, petTriggers))
			response = "Tell me more about your pets";
		else if (mentions(input, teacherTriggers))
			response = teacherPronouns[mentionIndex(input, teacherTriggers)]
					+ " sounds like a good teacher.";
		else if (mentions(input, zachTriggers))
			response = "That name! That's my programmer";
		else
			response = getRandomResponse();
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
		return mentionIndex(phrase, triggers) != -1;
	}
	
	private static int mentionIndex(String phrase, String[] triggers) {
		for (int i = 0; i < triggers.length; i++)
			if (phrase.indexOf(triggers[i]) != -1)
				return i;
		return -1;
	}
}
