package oharaBankAccounts;

public class BankAccountMain {
	
   public static void main(String[] args) {
      BankAccount[] myAccount = new BankAccount[15];
      for (int i = 0; i < myAccount.length; i++)
         myAccount[i] = new BankAccount("Customer " + i+1);
      
      int myId = myAccount[2].getID();
      System.out.println("My ID is " + myId + ".");
      // Uncomment the two statements below
      int numAccts = BankAccount.getNumAccounts();
      System.out.println("The number of bank accounts is " + numAccts);
   }
}