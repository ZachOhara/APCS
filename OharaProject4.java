import java.util.*;

public class OharaProject4 {
	
	public static final String ROCK = "r";
	public static final String PAPER = "p";
	public static final String SCISSORS = "s";
	
	public static Scanner console = new Scanner(System.in);

	public static void main(String[] args) {
		printIntro();
		int games = getNumGames();
		for (int i = 1; i <= games; i++) {
			playGame(i);
		}
		printStats();
	}
	
	public static void printIntro() {
		System.out.println("This program plays games of Rock-Paper-Scissors\n"
				+ "against the computer. You'll type in your guess\n"
				+ "of (R)ock, (P)aper, or (S)cissors and try to\n"
				+ "beat the computer as many times as you can.");
	}
	
	public static int getNumGames() {
		System.out.print("Best out of how many games (must be odd)? ");
		return console.nextInt();
	}
	
	public static void playGame(int gameNum) {
		// code to play a single game of RPS
	}
	
	public static void printStats() {
		// code to print final statistics
	}
	
	// returns 1 or 2 if a string 'wins', 0 if they're the same, and -1 for error
	static int winner(String a, String b) {
		a = a.toLowerCase();
		b = b.toLowerCase();
		// easier than using equalsIgnoreCase() on every comparison
		if (a.equals(ROCK)) {
			if (b.equals(SCISSORS))
				return 1;
			else
				return 2;
		} else if (a.equals(SCISSORS)) {
			if (b.equals(PAPER))
				return 1;
			else
				return 2;
		} else if (a.equals(PAPER)) {
			if (b.equals(ROCK))
				return 1;
			else
				return 2;
		} else if (a.equals(b)) {
			return 0;
		} else //unexpected
			return -1;
	}

}
