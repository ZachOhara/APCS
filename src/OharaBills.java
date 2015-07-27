import java.util.Scanner;

public class OharaBills {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		double johnBills = OharaBills.calcSpending("John", console);
		double janeBills = OharaBills.calcSpending("Jane", console);
		System.out.println("John needs " + johnBills + " bills");
		System.out.println("Jane needs " + janeBills + " bills");
		console.close();
	}

	public static int calcSpending(String name, Scanner console) {
		System.out.print("How much will " + name + " be spending? ");
		double amount = console.nextDouble();
		System.out.println();
		int numBills = (int) (amount / 20.0);
		if (numBills * 20.0 < amount) {
			numBills++;
		}
		return numBills;
	}

}
