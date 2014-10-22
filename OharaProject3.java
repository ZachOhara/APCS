import java.util.Scanner;

public class OharaProject3 extends ClassGrade {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		printIntro();
		double hwScore = setHomework(console);
		double exScore = setMidterm(console);
		double finScore = setFinal(hwScore, exScore, console);
	}
	
	public static void printIntro() {
		System.out.println("This program accepts your homework and exam\n"
			+ "scores as input, and computes your grade in\n"
			+ "the course or indicates what grade you need\n"
			+ "to earn on the final exam.\n");
	}
	
	// set the homework grade
	public static double setHomework(Scanner console) {
		double hwScore;
		System.out.println("Homework:");
		int weight = getInputInteger("What is its weight (0-100)? ", 4, console);
		int assignments = getInputInteger("How many homework assignments were there? ", 4, console);
		int[] points = new int[2];
		int cumPoints = 0; //cumulative points
		int cumPossible = 0; //cumulative possible points
		for (int i = 1; i <= assignments; i++) {
			points = getInputInteger("Homework " + i + " score and max score: ", 4, 2, console);
			cumPoints += points[0];
			cumPossible += points[1];
		}
		hwScore = ((double)weight/100) * (double)cumPoints/ cumPossible;
		System.out.println("    Weighted homework score: " + scoreToPercent(hwScore));
		// that mess of an expression is basically this.hwScore as a percent to two decimal places
		System.out.println();
		return hwScore;
	}

	// set the midterm grade
	public static double setMidterm(Scanner console) {
		System.out.println("Midterm exam:");
		int weight = getInputInteger("What is its weight (0-100)? ", 4);
		int score = getInputInteger("Exam score: ", 4);
		System.out.print("    Was there a curve? (1 for yes, 2 for no) ");
		String curved = console.next();
		boolean isCurved = strIsYes(curved);
		int curve = 0;
		if (isCurved) {
			curve = getInputInteger("How much was the curve? ", 4);
		}
		exScore = ((double)weight * Math.min(score+curve, 100))/10000;
		System.out.println("    Weighted exam score: " + scoreToPercent(exScore));
		// that mess of an expression is basically this.exScore as a percent to one decimal place
		System.out.println();
	}

	// set the final grade
	public static void setFinal(hwScore, exScore, console) {
		System.out.println("Final exam:");
		System.out.print("    Have you taken the final exam yet? (1 for yes, 2 for no) ");
		boolean isFinalTaken = strIsYes(console.next());
		if (!isFinalTaken) {
			calcFinalReq(hwScore, exScore, console);
			return;
		}
		int weight = getInputInteger("What is its weight (0-100)? ", 4, console);
		int score = getInputInteger("Exam score: ", 4, console);
		System.out.print("    Was there a curve? (1 for yes, 2 for no) ");
		boolean isCurved = strIsYes(console.next());
		int curve = 0;
		if (isCurved) 
			curve = getInputInteger("How much was the curve? ", 4, console);
		finScore = ((double)weight/100) * Math.min(score+curve, 100)/100;
		System.out.println("    Weighted exam score: " + scoreToPercent(finScore)); //(double)10*((int)this.finScore)/10);
		// that mess of an expression is basically this.finScore as a percent to one decimal place
		System.out.println();
		System.out.println("    Your course grade is " + scoreToPercent(score()));
	}

	// useful if the user wants to do calculations without a final grade	
	public static void calcFinalReq(hwScore, exScore, console) {
		int weight = getInputInteger("What is its weight (0-100)? ", 4, console);
		int goal = getInputInteger("What percentage would you like to earn in the course? ", 4, console);
		double reqWeightedFinal = ((double)goal/100) - hwMdScore();
		double reqFinalScore = reqWeightedFinal / ((double)weight / 100);
		finScore = reqFinalScore;
		reqFinalScore = scoreToPercent(reqFinalScore);
		System.out.println();
		System.out.println("    You need a score of " + reqFinalScore + " on the final exam.");
		if (reqFinalScore > 100) {
			finScore = (double)weight/100;
			System.out.println("    Sorry, it is impossible to achieve this percentage.");
			System.out.println("    The highest percentage you can get is " + scoreToPercent(score()));
		}

	}

	// takes score as decimal 0-1 and converts to percent with two decimal places
	public static double scoreToPercent(double score) {
		return round(10000 * score)/100;
	}
	
	// Math.round() doesn't want to play nice today
	public static double round(double n) {
		return n >= 0.5 ? Math.ceil(n) : Math.floor(n);
	}

	// score of combined homework and midterm
	public double hwMdScore(hwScore, exScore) {
		return exScore + hwScore;
	}

	// combined score
	public double score() {
		return exScore + finScore + hwScore;
	}

	// error checks a single integer input
	public static int getInputInteger(String prompt, int padding, console) {
		return getInputInteger(prompt, padding, 1, console)[0];
	}

	// error checks as many continuous inputs as you want simultaneously
	// only used in homework
	public static int[] getInputInteger(String prompt, int padding, int inputsToGet, console) {
		int[] result = new int[inputsToGet];
		String padStr = "";
		for (int i = 0; i < padding; i++)
			padStr += " ";
		boolean isSuccessful = false;
		System.out.print(padStr+prompt);
		while (!isSuccessful) {
			for (int i = 1; i <= inputsToGet; i++) {
				if (console.hasNextInt()) {
					result[i-1] = console.nextInt();
					if (i == inputsToGet)
						isSuccessful = true;
				} else {
					break;
				}
			}
			if (isSuccessful)
				break;
			System.out.println(padStr + "Please enter a valid integer...");
			console.nextLine(); //discard whole line
			System.out.print(padStr + prompt);
			if (console.hasNextInt())
				break;
		}
		return result;
	}

	// determines if a string contains an affirmative phrase
	public static boolean strIsYes(String s) {
		s = s.toLowerCase();
		boolean result = false;
		switch (s) {
		case "1": //continue all
		case "y":
		case "ye":
		case "yes":
		case "yup":
		case "yeah":
		case "oh yeah":
		case "yuppers": //deprecated
		case "yessir":
		case "affirmative":
			result = true;
			break;
		default:
			result = false; //yes not found
			break;
		}
		return result;
	}

}
