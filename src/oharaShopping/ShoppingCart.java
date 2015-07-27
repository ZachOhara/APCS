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

import java.util.ArrayList;

public class ShoppingCart {

	private ArrayList<ItemOrder> localList;
	private boolean isDiscount;

	/**
	 * Construct a new, empty, shopping cart
	 */
	public ShoppingCart() {
		this.localList = new ArrayList<ItemOrder>();
		this.isDiscount = false;
	}

	/**
	 * Add an item order to the cart
	 *
	 * @param newOrder The IemOrder to be added
	 */
	public void add(ItemOrder newOrder) {
		if (this.containsItem(newOrder.getItem())) {
			this.removeItem(newOrder.getItem());
		}
		this.localList.add(newOrder);
	}

	/**
	 * Set whether to apply a 10% discount on the final price
	 *
	 * @param b {@code true} to use the discount; {@code false} to pay full price
	 */
	public void setDiscount(boolean b) {
		this.isDiscount = b;
	}

	/**
	 * Calculate the total cost of the orders in the cart
	 *
	 * @return The combined cost of all orders
	 */
	public double getTotal() {
		double discountFactor = this.isDiscount ? 0.9 : 1.0;
		double total = 0;
		for (ItemOrder io : this.localList) {
			total += io.getPrice();
			System.out.println(io + ", " + io.getPrice());
		}
		return total * discountFactor;
	}

	private boolean containsItem(Item i) {
		for (ItemOrder order : this.localList) {
			if (i.toString().equals(order.getItem().toString())) {
				return true;
			}
		}
		return false;
	}

	private void removeItem(Item item) {
		for (int i = 0; i < this.localList.size(); i++) {
			if (item.toString().equals(this.localList.get(i).toString())) {
				this.localList.remove(i);
			}
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (ItemOrder io : this.localList) {
			result += io.toString() + "\n";
		}
		if (this.isDiscount) {
			result += "Qualify for discount";
		} else {
			result += "Does not qualify for discount";
		}
		return result;
	}

}
