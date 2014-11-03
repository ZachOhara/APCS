import java.util.*;

public class OharaRockPaperScissors {
	
	public static Difficulty gamemode = Difficulty.NORMAL; //normal, harder, brutal, or impossible
	
	public static Scanner console = new Scanner(System.in);
	public static int[] userInputs = new int[3]; //{rock, paper scissors}

	public static void main(String[] args) {
		printIntro();
		int games = getNumGames();
		int[] stats = playAllGames(games);
		printStats(stats);
	}
	
	public static void printIntro() {
		System.out.println("This program plays games of Rock-Paper-Scissors\n"
				+ "against the computer. You'll type in your guess\n"
				+ "of (R)ock, (P)aper, or (S)cissors and try to\n"
				+ "beat the computer as many times as you can.\n");
	}
	
	public static int getNumGames() {
		int num = 0;
		boolean success = false;
		do {
			try {
				do {
					System.out.print("Best out of how many games (must be odd)? ");
					num = console.nextInt();
					if (num % 2 == 0 || num < 0)
						System.out.println("Invalid number of games. Type a positive odd number!");
				} while (num % 2 == 0 || num < 0);
				success = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input; must be an integer");
				console.nextLine(); // ignore the non-integer, and remove it from the heap
			}
		} while (!success);
		return num;
	}
	
	public static int[] playAllGames(int games) {
		String[] winMessages = {"Tie!", "You win!", "You lose!"};
		int[] stats = new int[3]; // {ties, wins, losses}
		do {
			System.out.println();
			for (int i = 1; i <= games; i++) {
				int winner = playGame(i);
				stats[winner]++; //get the winner of the game and increment that statistic
				System.out.println(winMessages[winner]);
			}
			//System.out.println();
		} while (promptContinue());
		return stats;
	}
	
	// returns int representing winner
	public static int playGame(int gameNum) {
		// code to play a single game of RPS
		System.out.println("Game " + gameNum + ":");
		console.nextLine();
		String userWeapon = getUserWeapon();
		storeUserWeapon(userWeapon);
		String computerWeapon = getComputerWeapon(userWeapon);
		System.out.println("I chose the weapon: " + computerWeapon.toUpperCase());
		return winner(userWeapon, computerWeapon);
	}
	
	public static boolean promptContinue() {
		System.out.print("Do you want to play again? ");
		return console.next().toLowerCase().startsWith("y");
	}
	
	public static void printStats(int[] stats) {
		System.out.println();
		System.out.println();
		int totalGames = 0;
		for (int i : stats)
			totalGames += i;
		System.out.println("Overall results:"
				+ "\ntotal games   = " + totalGames
				+ "\nwins          = " + stats[1]
				+ "\nlosses        = " + stats[2]
				+ "\nties          = " + stats[0]
				+ "\nwin %         = "
				+ String.format("%.2f", (100 * (double)stats[1] / totalGames)));
	}
	
	public static String getUserWeapon() {
		String weapon;
		boolean isValid;
		do {
			System.out.print("Choose your weapon? ");
			weapon = console.next().toLowerCase();
			isValid = weapon.startsWith("r") || weapon.startsWith("p") || weapon.startsWith("s");
			if (!isValid)
				System.out.println("Weapon must be rock, paper, or scissors. \"" + weapon + "\" is not valid.");
		} while (!isValid);
		return weapon.substring(0, 1);
	}
	
	public static String getComputerWeapon(String userInput) {
		String[] weapons = {"r", "p", "s"};
		if (gamemode == Difficulty.NORMAL) {
			return weapons[(int)(3 * Math.random())];
		} else if (gamemode == Difficulty.HARDER) {
			if (userInputs[0] >= userInputs[1] && userInputs[0] >= userInputs[2])
				return "p";
			if (userInputs[1] >= userInputs[0] && userInputs[1] >= userInputs[2])
				return "s";
			if (userInputs[2] >= userInputs[0] && userInputs[2] >= userInputs[1])
				return "r";
		} else if (gamemode == Difficulty.BRUTAL) {
			if (Math.random() > 0.3)
				return cheatResponse(userInput);
			else
				return weapons[(int)(3 * Math.random())]; //play legitimately
		} else { // gamemode == IMPOSSIBLE
			return cheatResponse(userInput);
		}
		return ""; //Dead code, but required to compile.
	}
	
	public static String cheatResponse(String weapon) {
		if (weapon.equals("r"))
			return "p";
		if (weapon.equals("p"))
			return "s";
		if (weapon.equals("s"))
			return "r";
		return ""; // error
	}
	
	public static void storeUserWeapon(String weapon) {
		if (weapon.equals("r"))
			userInputs[0]++;
		if (weapon.equals("p"))
			userInputs[1]++;
		if (weapon.equals("s"))
			userInputs[2]++;
	}
	
	@Deprecated
	public static boolean isValidWeapon(String weapon) {
		if (weapon.length() == 0)
			return false;
		weapon = weapon.toLowerCase();
		return weapon.startsWith("r") || weapon.startsWith("p") || weapon.startsWith("s");
	}
	
	// returns 1 or 2 if a string 'wins', 0 if they're the same, and -1 for error
	static int winner(String a, String b) {
		a = a.toLowerCase();
		b = b.toLowerCase();
		// easier than using equalsIgnoreCase() on every comparison
		if (a.equals(b))
		        return 0;
	        else if (a.equals("r")) {
			if (b.equals("s"))
				return 1;
			else
				return 2;
		} else if (a.equals("s")) {
			if (b.equals("p"))
				return 1;
			else
				return 2;
		} else if (a.equals("p")) {
			if (b.equals("r"))
				return 1;
			else
				return 2;
		} else //unexpected
			return -1;
	}

}

enum Difficulty {
	NORMAL, HARDER, BRUTAL, IMPOSSIBLE
}