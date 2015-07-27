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

// A class to represent legal secretaries.

// Add class header below
public class LegalSecretary extends Secretary {

	public void fileLegalBriefs() {
		System.out.println("I could file all day!");
	}

	// Add method that overrides getSalary from Employee class
	// for salary of 45000.0 per year

	@Override
	public double getSalary() {
		return 45000.0; // $40,000.00 / year
	}

}
