package oharaChatbot;

public class StudentBot extends ChatBot {

	public StudentBot(String nm) {
		super(nm);
	}

	@Override
	public String respondToGreeting(String greeting) {
		if (greeting.indexOf("morning") != -1) {
			return "Good morning";
		}
		if (greeting.indexOf("afternon") != -1) {
			return "Good afternoon";
		}
		return "Hello";
	}

}
