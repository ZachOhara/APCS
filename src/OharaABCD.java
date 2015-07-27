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

 import java.util.ArrayList;

public class OharaABCD {

	public static void main(String[] args) {
		ArrayList<String> letterList = new ArrayList<String>();
		letterList.add("a");
		letterList.add("b");
		letterList.add("c");
		letterList.add("d");
		System.out.println(letterList);

		// Add the 'plus' with incrementing index

		for (int i = 1; i <= letterList.size() - 1; i += 2) {
			letterList.add(i, " plus ");
		}
		System.out.println(letterList);

		// Remove the 'plus' with incrementing index

		for (int i = 1; i < letterList.size(); i += 1) {
			letterList.remove(i);
		}
		System.out.println(letterList);

		// Add the 'plus' with decrementing index

		for (int i = letterList.size() - 1; i > 0; i--) {
			letterList.add(i, " plus ");
		}
		System.out.println(letterList);

		// Remove the 'plus' with decrementing index

		for (int i = letterList.size() - 2; i > 0; i -= 2) {
			letterList.remove(i);
		}
		System.out.println(letterList);

	}

}
