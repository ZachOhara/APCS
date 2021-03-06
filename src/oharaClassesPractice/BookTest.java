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

package oharaClassesPractice;

public class BookTest {

	public static void main(String[] args) {
		Book myBook = new Book(3);
		System.out.println(myBook.getCurrentPage());
		for (int i = 0; i < 3; i++) {
			myBook.nextPage();
			System.out.println(myBook.getCurrentPage());
		}
	}

}
