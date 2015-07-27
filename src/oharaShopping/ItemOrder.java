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

 package oharaShopping;

public class ItemOrder {

	/**
	 * Represents an order of a single item with a given quantity
	 */

	private Item item;
	private int quantity;

	/**
	 * Construct a new ItemOrder object with an item and a quantity
	 *
	 * @param item The Item to buy
	 * @param quantity The amount of the specified item
	 */
	public ItemOrder(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	/**
	 * Get the price of the item for the set quantity
	 *
	 * @return The cost of the item order
	 */
	public double getPrice() {
		return this.item.priceFor(this.quantity);
	}

	/**
	 * Get the item that is being bought
	 *
	 * @return The item that is being bought
	 */
	public Item getItem() {
		return this.item;
	}

	@Override
	public String toString() {
		return this.item.toString() + " " + this.quantity;
	}

}
