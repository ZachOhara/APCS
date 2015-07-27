/* Copyright (C) 2014-2015 Zach Ohara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

 package oharaBankAccounts;

public class BankAccountMain {

	public static void main(String[] args) {
		BankAccount[] myAccount = new BankAccount[15];
		for (int i = 0; i < myAccount.length; i++) {
			myAccount[i] = new BankAccount("Customer " + i + 1);
		}

		int myId = myAccount[2].getID();
		System.out.println("My ID is " + myId + ".");
		// Uncomment the two statements below
		int numAccts = BankAccount.getNumAccounts();
		System.out.println("The number of bank accounts is " + numAccts);
	}
}
