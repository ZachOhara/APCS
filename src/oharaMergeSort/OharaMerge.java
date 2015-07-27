package oharaMergeSort;

public class OharaMerge {

	/**
	 * Sort an array of integers into ascending order.
	 *
	 * @param elements an array containing the items to be sorted. Postcondition: elements
	 * contains its original items and items in elements are sorted in ascending order.
	 */
	public static void mergeSort(String[] elements) {
		int n = elements.length;
		String[] temp = new String[n];
		OharaMerge.mergeSortHelper(elements, 0, n - 1, temp);
	}

	/**
	 * Sorts elements[from] ... elements[to] inclusive into ascending order.
	 *
	 * @param elements an array containing the items to be sorted.
	 * @param from the beginning index of the items in elements to be sorted.
	 * @param to the ending index of the items in elements to be sorted.
	 * @param temp a temporary array to use during the merge process. Precondition:
	 * (elements.length == 0 or 0 <= from <= to <= elements.length) and elements.length ==
	 * temp.length Postcondition: elements contains its original items and the items in
	 * elements [from] ... <= elements[to] are sorted in ascending order.
	 */
	private static void mergeSortHelper(String[] elements, int from, int to, String[] temp) {
		if (from < to) {
			int middle = (from + to) / 2;
			OharaMerge.mergeSortHelper(elements, from, middle, temp);
			OharaMerge.mergeSortHelper(elements, middle + 1, to, temp);
			OharaMerge.merge(elements, from, middle, to, temp);
		}
	}

	/**
	 * Merges two adjacent array parts, each of which has been sorted into ascending order,
	 * into one array part that is sorted into ascending order.
	 *
	 * @param elements an array containing the parts to be merged.
	 * @param from the beginning index in elements of the first part.
	 * @param mid the ending index in elements of the first part. mid+1 is the beginning
	 * index in elements of the second part.
	 * @param to the ending index in elements of the second part.
	 * @param temp a temporary array to use during the merge process. Precondition: 0 <=
	 * from <= mid <= to <= elements.length and elements[from] ... <= elements[mid] are
	 * sorted in ascending order and elements[mid + 1] ... <= elements[to] are sorted in
	 * ascending order and elements.length == temp.length Postcondition: elements contains
	 * its original items and elements[from] ... <= elements[to] are sorted in ascending
	 * order and elements[0] ... elements[from − 1] are in original order and elements[to
	 * + 1] ... elements[elements.length − 1] are in original order.
	 */
	private static void merge(String[] elements, int from, int mid, int to, String[] temp) {
		int i = from;
		int j = mid + 1;
		int k = from;
		while (i <= mid && j <= to) {
			if (elements[i].compareTo(elements[j]) < 1) {
				temp[k] = elements[i];
				i++;
			} else {
				temp[k] = elements[j];
				j++;
			}
			k++;
		}
		while (i <= mid) {
			temp[k] = elements[i];
			i++;
			k++;
		}
		while (j <= to) {
			temp[k] = elements[j];
			j++;
			k++;
		}
		for (k = from; k <= to; k++) {
			elements[k] = temp[k];
		}
	}

}
