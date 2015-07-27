import java.util.InputMismatchException;
import java.util.Scanner;

public class OharaRockPaperScissors {

	public static Difficulty gamemode = Difficulty.IMPOSSIBLE; // normal,
	// harder,
	// brutal, or
	// impossible

	/*
	 * Difficulties: This game is programmed with four unique levels of 'difficulty' The
	 * levels are normal, harder, brutal, and impossible. You may notice there is no 'easy'
	 * mode. Life is hard. Get over it.
	 *
	 * NORMAL: A 'standard' game of Rock Paper Scissors. The computer will indiscriminately
	 * choose a random weapon. Very boring.
	 *
	 * HARDER: The computer will track your history in a game, meaning that continuously
	 * selecting a single weapon "just to get through it" will always result in a loss.
	 * Your only hope is to keep your choices as varied as possible
	 *
	 * BRUTAL: The computer can see your choice before it makes its own. In any given turn,
	 * the computer has a 65% chance to cheat outright, and automatically select the weapon
	 * that will defeat you. In the other 35% of cases, the computer will select a weapon
	 * based on normal rules. Here, victory is still very possible, but the odds are most
	 * definitely against you.
	 *
	 * IMPOSSIBLE: The computer can see your choice before it makes its own. The computer
	 * will cheat. There is no avoiding it. You will lose every single game. You brought
	 * this upon yourself.
	 */

	public static Scanner console = new Scanner(System.in);
	public static int[] userInputs = new int[3]; // times the user has selected

	// {rock, paper scissors}

	public static void main(String[] args) {
		OharaRockPaperScissors.printIntro();
		int games = OharaRockPaperScissors.getNumGames();
		int[] stats = OharaRockPaperScissors.playAllGames(games);
		OharaRockPaperScissors.printStats(stats);
	}

	// print an intro to the program
	public static void printIntro() {
		System.out.println("This program plays games of Rock-Paper-Scissors\n"
				+ "against the computer. You'll type in your guess\n"
				+ "of (R)ock, (P)aper, or (S)cissors and try to\n"
				+ "beat the computer as many times as you can.\n");
	}

	// ask the user how many games they want to play
	public static int getNumGames() {
		int num = 0;
		boolean success = false;
		do {
			try {
				do {
					System.out.print("Best out of how many games (must be odd)? ");
					num = OharaRockPaperScissors.console.nextInt();
					if (num % 2 == 0 || num < 0) {
						System.out.println("Invalid number of games. Type a positive odd number!");
					}
				} while (num % 2 == 0 || num < 0);
				success = true; // we're done here
			} catch (InputMismatchException e) {
				System.out.println("Invalid input; must be an integer");
				OharaRockPaperScissors.console.nextLine(); // ignore the non-integer, and
				// remove it
				// from the heap
			}
		} while ( !success);
		return num;
	}

	// manage the games occuring, and prompt to quit after each match
	public static int[] playAllGames(int games) {
		String[] winMessages = {"Tie!", "You win!", "You lose!"};
		int[] stats = new int[3]; // times the user has {tied, won, lost}
		do {
			System.out.println();
			for (int i = 1; i <= games; i++) {
				int winner = OharaRockPaperScissors.playGame(i);
				stats[winner]++; // get the winner of the game and increment
				// that statistic
				System.out.println(winMessages[winner]);
			}
		} while (OharaRockPaperScissors.promptContinue());
		return stats;
	}

	// code to play a single game; returns int representing winner
	public static int playGame(int gameNum) {
		System.out.println("Game " + gameNum + ":");
		OharaRockPaperScissors.console.nextLine();
		String userWeapon = OharaRockPaperScissors.getUserWeapon();
		String computerWeapon = OharaRockPaperScissors.getComputerWeapon(userWeapon);
		OharaRockPaperScissors.storeUserWeapon(userWeapon);
		System.out.println("I chose the weapon: " + computerWeapon.toUpperCase());
		return OharaRockPaperScissors.winner(userWeapon, computerWeapon);
	}

	// ask the user to either continue or to quit playing
	public static boolean promptContinue() {
		System.out.print("Do you want to play again? ");
		return OharaRockPaperScissors.console.next().toLowerCase().startsWith("y");
	}

	// print final statistics about the session
	public static void printStats(int[] stats) {
		System.out.println();
		System.out.println();
		int totalGames = 0;
		for (int i : stats) {
			totalGames += i;
		}
		System.out.println("Overall results:" + "\ntotal games   = " + totalGames + "\nwins          = "
				+ stats[1] + "\nlosses        = " + stats[2] + "\nties          = " + stats[0]
						+ "\nwin %         = " + String.format("%.2f", (100 * (double) stats[1] / totalGames)));
	}

	// during a game, ask the user for their choice of weapon
	public static String getUserWeapon() {
		String weapon;
		boolean isValid;
		do {
			System.out.print("Choose your weapon? ");
			weapon = OharaRockPaperScissors.console.next().toLowerCase();
			isValid = weapon.startsWith("r") || weapon.startsWith("p") || weapon.startsWith("s");
			if ( !isValid) {
				System.out.println("Weapon must be rock, paper, or scissors. \"" + weapon
						+ "\" is not valid.");
			}
		} while ( !isValid);
		return weapon.substring(0, 1);
	}

	// during a game, generate a computer weapon, possibly based on the user's
	// choice
	public static String getComputerWeapon(String userInput) {
		String[] weapons = {"r", "p", "s"};
		if (OharaRockPaperScissors.gamemode == Difficulty.NORMAL) {
			return weapons[(int) (3 * Math.random())];
		} else if (OharaRockPaperScissors.gamemode == Difficulty.HARDER) {
			if (OharaRockPaperScissors.userInputs[0] >= OharaRockPaperScissors.userInputs[1]
					&& OharaRockPaperScissors.userInputs[0] >= OharaRockPaperScissors.userInputs[2]) {
				return "p";
			}
			if (OharaRockPaperScissors.userInputs[1] >= OharaRockPaperScissors.userInputs[0]
					&& OharaRockPaperScissors.userInputs[1] >= OharaRockPaperScissors.userInputs[2]) {
				return "s";
			}
			if (OharaRockPaperScissors.userInputs[2] >= OharaRockPaperScissors.userInputs[0]
					&& OharaRockPaperScissors.userInputs[2] >= OharaRockPaperScissors.userInputs[1]) {
				return "r";
			}
		} else if (OharaRockPaperScissors.gamemode == Difficulty.BRUTAL) {
			if (Math.random() > 0.35) {
				return OharaRockPaperScissors.cheatResponse(userInput);
			} else {
				return weapons[(int) (3 * Math.random())]; // play legitimately
			}
		} else { // gamemode == IMPOSSIBLE
			return OharaRockPaperScissors.cheatResponse(userInput);
		}
		return "r"; // Dead code, but required to compile. Might as well return
		// something useful
	}

	// given an input, return whatever would win against it
	public static String cheatResponse(String weapon) {
		if (weapon.equals("r")) {
			return "p";
		}
		if (weapon.equals("p")) {
			return "s";
		}
		if (weapon.equals("s")) {
			return "r";
		}
		return ""; // error
	}

	// log the user's weapon choice for later accessing
	public static void storeUserWeapon(String weapon) {
		if (weapon.equals("r")) {
			OharaRockPaperScissors.userInputs[0]++;
		}
		if (weapon.equals("p")) {
			OharaRockPaperScissors.userInputs[1]++;
		}
		if (weapon.equals("s")) {
			OharaRockPaperScissors.userInputs[2]++;
		}
	}

	@Deprecated
	public static boolean isValidWeapon(String weapon) {
		if (weapon.length() == 0) {
			return false;
		}
		weapon = weapon.toLowerCase();
		return weapon.startsWith("r") || weapon.startsWith("p") || weapon.startsWith("s");
	}

	// returns 1 or 2 if a string 'wins', 0 if they're the same, and -1 for
	// error
	static int winner(String a, String b) {
		a = a.toLowerCase();
		b = b.toLowerCase();
		// easier than using equalsIgnoreCase() on every comparison
		if (a.equals(b)) {
			return 0;
		} else if (a.equals("r")) {
			if (b.equals("s")) {
				return 1;
			} else {
				return 2;
			}
		} else if (a.equals("s")) {
			if (b.equals("p")) {
				return 1;
			} else {
				return 2;
			}
		} else if (a.equals("p")) {
			if (b.equals("r")) {
				return 1;
			} else {
				return 2;
			}
		} else {
			// unexpected
			return -1;
		}
	}

}

enum Difficulty {
	NORMAL,
	HARDER,
	BRUTAL,
	IMPOSSIBLE
}
