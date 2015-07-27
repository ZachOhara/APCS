import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OharaSwapMin {

	public static void main(String[] args) {
		String[] hamletWords = {"to", "be", "or", "not", "to", "be", "hamlet"};
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(hamletWords)); // copy all values in
		// hamletWords to list
		System.out.println(list);
		OharaSwapMin.swapPairs(list);
		System.out.println(list);

		Integer[] unordered = {3, 8, 92, 4, 2, 17, 9};
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.addAll(Arrays.asList(unordered));
		System.out.println(list2);
		OharaSwapMin.minToFront(list2);
		System.out.println(list2);
	}

	// Generic version of the method
	// Works with any type of ArrayList, including String
	public static <T> void swapPairs(ArrayList<T> list) {
		for (int i = 0; i < 2 * (list.size() / 2); i += 2) {
			T placeholder = list.get(i);
			list.set(i, list.get(i + 1));
			list.set(i + 1, placeholder);
		}
	}

	// String-only version of the method
	public static void swapPairsString(ArrayList<String> list) {
		for (int i = 0; i < 2 * (list.size() / 2); i += 2) {
			String placeholder = list.get(i);
			list.set(i, list.get(i + 1));
			list.set(i + 1, placeholder);
		}
	}

	public static void minToFront(ArrayList<Integer> list) {
		// Find the min of the array
		ArrayList<Integer> ordered = new ArrayList<Integer>(list); // copy
		// 'list'
		// elements
		// to new
		// object
		Collections.sort(ordered);
		// The min is at ordered[0];
		list.remove(list.indexOf(ordered.get(0))); // find the index of the
		// minimum, then remove it
		list.add(0, ordered.get(0)); // add the min to the front
	}

}
