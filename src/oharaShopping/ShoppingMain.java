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

/* ShoppingMain provides method main for a simple shopping program.
 */
public class ShoppingMain {

	public static void main(String[] args) {
		Catalog list = new Catalog("CS Gift Catalog");
		list.add(new Item("silly putty", 3.95, 10, 19.99));
		list.add(new Item("silly string", 3.50, 10, 14.95));
		list.add(new Item("bottle o bubbles", 0.99));
		list.add(new Item("Nintendo Wii system", 389.99));
		list.add(new Item("Mario Computer Science Party 2 (Wii)", 49.99));
		list.add(new Item("Don Knuth Code Jam Challenge (Wii)", 49.99));
		list.add(new Item("Computer Science pen", 3.40));
		list.add(new Item("Rubik's cube", 9.10));
		list.add(new Item("Computer Science Barbie", 19.99));
		list.add(new Item("'Java Rules!' button", 0.99, 10, 5.0));
		list.add(new Item("'Java Rules!' bumper sticker", 0.99, 20, 8.95));
		System.out.println(list);

		ShoppingFrame f = new ShoppingFrame(list);
		f.setVisible(true);
	}
}
