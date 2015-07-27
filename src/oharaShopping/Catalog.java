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

public class Catalog {

	/**
	 * Represents a list of Item object that could potentially be ordered
	 */

	ArrayList<Item> localList;

	private String name;

	/**
	 * Construct a new Catalog object with a name
	 *
	 * @param name the name of the catalog
	 */
	public Catalog(String name) {
		this.localList = new ArrayList<Item>();
		this.name = name;
	}

	/**
	 * Get the name of this catalog
	 *
	 * @return the catalog name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Add an item to the catalog
	 *
	 * @param i The Item object to be added
	 */
	public void add(Item i) {
		this.localList.add(i);
	}

	/**
	 * Get the size of the catalog
	 *
	 * @return the amount of items in the catalog
	 */
	public int size() {
		return this.localList.size();
	}

	/**
	 * Get an item at the specified index of the catalog
	 *
	 * @param i The index to return
	 * @return The Item object at index i
	 */
	public Item get(int i) {
		return this.localList.get(i);
	}

	@Override
	public String toString() {
		return this.localList.toString();
	}

}
