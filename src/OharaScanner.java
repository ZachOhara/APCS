import java.util.Scanner;

public class OharaScanner {

	public static void main(String[] args) {
		OharaScanner.inputBirthday();
		OharaScanner.processName(new Scanner(System.in));
	}

	public static void inputBirthday() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("On what day of the month were you born? ");
		String day = input.next();
		System.out.print("What is the name of the month in which you were born? ");
		String month = input.next();
		System.out.print("During what year were you born? ");
		String year = input.next();
		System.out.println("You were born on " + month + " " + day + ", " + year + ". You're mighty old!");
		// input.close(); //uncommenting this line causes an exception; not sure
		// why
	}

	public static void processName(Scanner s) {
		System.out.print("Please enter your full name: ");
		String firstName = s.next();
		String revName = s.next() + ", " + firstName;

		System.out.println("Your name in reverse order is " + revName);
	}

}
