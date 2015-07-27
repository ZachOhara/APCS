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

public class GroceryItemOrder {

	private String name = "";
	private int quantity = 0;
	private double pricePerUnit = 0;

	public GroceryItemOrder(String name, int quantity, double pricePerUnit) {
		this.name = name;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}

	public GroceryItemOrder() {
		// default
	}

	public double getCost() {
		return this.quantity * this.pricePerUnit;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "GroceryItemOrder[" + this.quantity + " of " + this.name + " at " + this.pricePerUnit + "]";
	}
}
