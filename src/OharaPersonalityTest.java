import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OharaPersonalityTest {
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		String[] surveyResults = null;
		do {
			System.out.print("Input file name: ");
			surveyResults = getFile(console.next());
			if (surveyResults == null)
				System.out.print("File not found. ");
		} while (surveyResults == null);
		console.close();
		double[][][] personalities = new double[surveyResults.length/2][4][3];
		String[] names = new String[surveyResults.length / 2];
		for (int i = 0; i < personalities.length; i++) {
			names[i] = surveyResults[i*2];
			String answers = surveyResults[i*2 + 1];
			personalities[i][0] = getExtrovertIntrovert(answers);
			personalities[i][1] = getSensingIntuition(answers);
			personalities[i][2] = getThinkingFeeling(answers);
			personalities[i][3] = getJudgingPerceiving(answers);
		}
		outputToFile(names, personalities);
	}
	
	public static double[] getExtrovertIntrovert(String answers) {
		int[] relevant = new int[10];
		for (int i = 1; i <= 10; i++)
			relevant[i - 1] = (i-1)*7 + 1;
		return getRelevantAnswers(answers, relevant);
	}
	
	public static double[] getSensingIntuition(String answers) {
		int[] relevant = new int[20];
		for (int i = 1; i <= 10; i++) {
			relevant[2*i - 2] = (i-1)*7 + 2;
			relevant[2*i - 1] = (i-1)*7 + 3;
		}
		return getRelevantAnswers(answers, relevant);
	}
	
	public static double[] getThinkingFeeling(String answers) {
		int[] relevant = new int[20];
		for (int i = 1; i <= 10; i++) {
			relevant[2*i - 2] = (i-1)*7 + 4;
			relevant[2*i - 1] = (i-1)*7 + 5;
		}
		return getRelevantAnswers(answers, relevant);
	}
	
	public static double[] getJudgingPerceiving(String answers) {
		int[] relevant = new int[20];
		for (int i = 1; i <= 10; i++) {
			relevant[2*i - 2] = (i-1)*7 + 6;
			relevant[2*i - 1] = (i-1)*7 + 7;
		}
		return getRelevantAnswers(answers, relevant);
	}
	
	public static double[] getRelevantAnswers(String answers, int[] relevantQs) {
		String currentAns;
		double answerA = 0;
		double answerB = 0;
		for (int i : relevantQs) {
			currentAns = answers.substring(i-1, i).toLowerCase();
			if (currentAns.equals("a"))
				answerA++;
			else if (currentAns.equals("b"))
				answerB++;
			else if (!currentAns.equals("-")) {
				System.out.println("Unexpected answer: \"" + currentAns + "\". No action taken.");
			}
		}
		double[] arr = {answerA, answerB, answerB / (answerA + answerB)};
		return arr;
	}
	
	public static String getAppropriateCharacter(String a, String b, double[] tally) {
		if (tally[0] > tally[1])
			return a;
		else if (tally[0] < tally[1])
			return b;
		return "X";
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
			//e.printStackTrace();
			return null;
		}
		return output.toArray(new String[output.size()]);
	}
	
	public static void outputToFile(String[] names, double[][][] personalities) {
		PrintStream output = null;
		try {
			output = new PrintStream(new File("output.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < personalities.length; i++) {
			output.println(names[i] + ":");
			for (int j = 0; j < personalities[i].length; j++) {
				output.print((int)(personalities[i][j][0]) + "A-");
				output.print((int)(personalities[i][j][1]) + "B ");
			}
			output.println();
			output.print("[");
			for (int j = 0; j < personalities[i].length; j++) {
				output.print((int)(personalities[i][j][2] * 100) + "%");
				if (j + 1 != personalities[i].length)
					output.print(", ");
			}
			output.print("] = ");
			output.print(getAppropriateCharacter("E", "I", personalities[i][0]));
			output.print(getAppropriateCharacter("S", "N", personalities[i][1]));
			output.print(getAppropriateCharacter("T", "F", personalities[i][2]));
			output.print(getAppropriateCharacter("J", "P", personalities[i][3]));
			if (i + 1 < personalities.length) { //if not last repetition
				output.println();
				output.println();
			}
		}
	}

}
