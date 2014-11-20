import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OharaPersonalityTest {

	public static void main(String[] args) {
		String[] surveyResults = getFile("personality.txt");
		Personality[] people = new Personality[surveyResults.length/2];
		for (int i = 0; i < people.length; i++) {
			people[i] = new Personality(surveyResults[i*2]);
		}
	}
	
	public static double getExtrovertIntrovert(String answers) {
		//TODO: method stub
	}
	
	public static double getSensingiNtuition(String answers) {
		//TODO: method stub
	}
	
	public static double getThinkingFeeling(String answers) {
		//TODO: method stub
	}
	
	public static double getJudgingPerceiving(String answers) {
		//TODO: method stub
	}
	
	public static double getRelevantAnswers(String answers, int[] relevantQs) {
		String currentAns;
		int answerA = 0;
		int answerB = 0;
		for (int i : releventQs) {
			currentAns = answers.substring(i-1, i).toLowerCase();
			if (currentAns.equals("a"))
				answerA++;
			else if (currentAns.equals("b"))
				answerB++;
			else if (!currentAns.equals("-")) {
				System.out.println("Unexpected answer: \"" + currentAns + "\". No action taken.");
			}
		}
		return (double)answerA / answerB;
	}
	
	public static String[] getFile(String address) {
		File f = new File(address);
		ArrayList<String> output = new ArrayList<String>();
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				output.add(s.nextLine());
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found: " + address);
			e.printStackTrace();
		}
		return output.toArray(new String[output.size()]);
	}

}

class Personality {
	
	public String name;
	
	public Personality(String name)	{
		this.name = name;
	}
}
