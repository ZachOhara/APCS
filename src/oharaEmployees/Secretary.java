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

package oharaEmployees;

// A class to represent secretaries.
public class Secretary extends Employee {

	public void takeDictation(String text) {
		System.out.println("Taking dictation of text: " + text);
	}

	public static void main(String[] args) {
		Employee[] employees = {new Employee(), new Lawyer(), new Secretary()};
		for (Employee e : employees) {
			System.out.println(e.getVacationDays());
		}

	}

}
