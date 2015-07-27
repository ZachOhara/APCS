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

 package oharaClassesProject;

public class RationalNumberTest {

	public static void main(String[] args) {

		RationalNumber num0 = new RationalNumber();
		RationalNumber num1 = new RationalNumber(3, 8);
		RationalNumber num2 = new RationalNumber(3, 6);
		RationalNumber num3 = new RationalNumber(7, -8);
		RationalNumber num4 = new RationalNumber( -1, 4);

		System.out.println("Number 0 is " + num0);

		int denom1 = num1.getDenominator();
		int numer1 = num1.getNumerator();
		System.out.println("Number 1 denominator is " + denom1);
		System.out.println("Number 1 numerator is " + numer1);
		System.out.println("Number 1 is " + num1);

		RationalNumber result1 = num2.add(num3).simplify();
		System.out.println("Addition Result is " + result1);

		RationalNumber result2 = num3.subtract(num4).simplify();
		System.out.println("Subtraction Result is " + result2);
	}
}
