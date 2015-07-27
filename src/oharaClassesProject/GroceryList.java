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

public class GroceryList {

	private GroceryItemOrder[] list = new GroceryItemOrder[10];
	private int items = 0;

	public GroceryList() {
		// default constructor
	}

	public void add(GroceryItemOrder item) {
		if (this.items < 10) {
			this.list[this.items] = item;
			this.items++;
		}
	}

	public double getTotalCost() {
		double total = 0;
		for (int i = 0; i < this.items; i++) {
			total += this.list[i].getCost();
		}
		return total;
	}

}
