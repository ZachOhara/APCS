import java.util.*;

public class OharaProject4 {
	
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

}

enum Weapon {
	
	ROCK, PAPER, SCISSORS;
	
	static int winner(Weapon a, Weapon b) {
		if (a == ROCK) {
			if (b == SCISSORS)
				return 1;
			else
				return 2;
		} else if (a == SCISSORS) {
			if (b == PAPER)
				return 1;
			else
				return 2;
		} else if (a == PAPER) {
			if (b == ROCK)
				return 1;
			else
				return 2;
		} else if (a == b) {
			return 0;
		} else //unexpected
			return -1;
	}
}
