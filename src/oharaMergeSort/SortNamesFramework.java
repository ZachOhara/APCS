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

 package oharaMergeSort;

// Program to sort an input file of names, ignoring case, using the mergesort
// algorithm.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortNamesFramework {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.print("What is the input file? ");
		String fileName = console.nextLine();

		Scanner input = null;
		try {
			input = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			SortNamesFramework instance = new SortNamesFramework();
			input = new Scanner(new File(instance.getClass().getResource(fileName).getFile()));
		}

		System.out.println();

		String[] names = SortNamesFramework.readNames(input);
		console.close();

		OharaMerge.mergeSort(names);

		for (String s : names) {
			System.out.println(s);
		}
	}

	public static String[] readNames(Scanner input) {
		List<String> data = new ArrayList<String>();
		while (input.hasNextLine()) {
			data.add(input.nextLine());
		}
		String[] result = new String[data.size()];
		data.toArray(result);
		return result;
	}

	// Add your three merge sort methods below.

}
