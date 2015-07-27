package oharaChatbot;

public class TeachersPet extends StudentBot {
	
	public TeachersPet(String nm) {
		super(nm);
	}
	
	@Override
	public String respondToGreeting(String greeting) {
		String response = super.respondToGreeting(greeting);
		if (greeting.indexOf("name is") != -1)
			response += greeting.substring(greeting.indexOf("name is") + 7);
		else
			response += ".";
		response += "\nWhat are we going to do in class today?";
		return response;
	}
	
}
