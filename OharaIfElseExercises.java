import java.util.Scanner;


public class OharaIfElseExercises {
	
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		problem5();
		problem6();
		problem7();
	}
	
	public static void problem5() {
		System.out.print("Please enter an integer: ");
		int n = input.nextInt();
		if (n % 2 == 0) {
			System.out.println("even");
		} else {
			System.out.println("odd");
		}
	}
	
	public static void problem6() {
		/* (We did this exact same problem on last night's homework)
		 * The program will print 'odd' for any number that is even but not 
		 * divisible by three. For example, if the user enters 8, the program
		 * will print that the number is odd. Corrected code is shown below.
		 * The only change that has been made is the name of the scanner instance used.
		 */
		System.out.print("Type a number: ");
		int number = input.nextInt();
		if (number % 2 == 0) {
			if (number % 3 == 0)
				System.out.println("Divisible by 6.");
		} else
			System.out.println("Odd.");
	}
	
	public static void problem7() {
		/* I'm a little confused on what to do here...
		 * Is this like a jeopardy-style question?
		 * Do I just have to come up with a prompt that the given code is a solution for?
		 * Porque no importa, voy a responder en español. Pero ¿va a traducir?
		 * 
		 * "Escribe un programa que pregunte el usuario por su color favorito. Si el usuario
		 * responde que azul es su color favorito, imprima "¡a mi tambien!" a la pantalla."
		 */
	}

}
