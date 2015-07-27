package oharaBankAccounts;

public class BankAccount {

	private static int objectCount = 0;

	@SuppressWarnings("unused")
	private String name;
	private int id;

	public BankAccount(String myName) {
		this.name = myName;
		this.id = BankAccount.objectCount;
		BankAccount.objectCount++;
	}

	public static int getNumAccounts() {
		return BankAccount.objectCount;
	}

	public int getID() {
		return this.id;
	}
}
