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

public class RationalNumberTest2 {

	public static void main(String[] args) {
		// RationalNumber num = new RationalNumber(

		RationalNumber num1 = new RationalNumber(3, 8);
		RationalNumber num2 = new RationalNumber(3, 6);
		RationalNumber num3 = new RationalNumber(7, -8);
		RationalNumber num4 = new RationalNumber( -1, 4);
		RationalNumber num5 = new RationalNumber(1, 2);
		RationalNumber num6 = new RationalNumber(6, 16);
		RationalNumber num7 = new RationalNumber( -2, 8);
		RationalNumber num8 = new RationalNumber(0, 1);
		RationalNumber num9 = new RationalNumber(0, 2);

		System.out.println(num1.compareTo(num2));
		System.out.println(num2.compareTo(num3));
		System.out.println(num3.compareTo(num4));
		System.out.println(num4.compareTo(num5));
		System.out.println(num5.compareTo(num6));
		System.out.println(num6.compareTo(num7));

		System.out.println();
		System.out.println(num2.equals(num5));
		System.out.println(num1.equals(num6));
		System.out.println(num4.equals(num7));

		System.out.println();
		System.out.println(num8.compareTo(num9));
	}

}
