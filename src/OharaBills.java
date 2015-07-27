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
