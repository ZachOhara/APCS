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

// A class to represent Lawyer that extends Employee class

// Add class header below
public class Lawyer extends Employee {

	@Override
	public String getVacationForm() {
		return "pink";
	}

	// Add method that overrides getVacationDays from Employee class
	// for 3 weeks of vacation
	@Override
	public int getVacationDays() {
		return 15; // 2 weeks' paid vacation
	}

	// Add method sue() that prints "I'll see you in court!"
	public void sue() {
		System.out.println("I'll see you in court!");
	}

}
