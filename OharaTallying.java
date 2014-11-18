
public class OharaTallying {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int mode(int[] arr) {
		int[] occurances = new int[max(arr)];
		for (int i : arr) {
			occurances[i]++;
		}
		return indexOf(occurances, max(occurances));
	}
	
	public static int[] vowelCount(String str) {
		int[] vowels = new int[5];
		String[] vowelStrings = {"a", "e", "i", "o", "u"};
		str = str.toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			//TODO: tally vowel into vowels[]
		}
		return vowels;
	}
	
	public static int max(int[] arr) {
		int max = arr[0];
		for (int i : arr)
			max = max > i? max : i;
		return max;
	}
	
	public static int indexOf(int[] arr, int n) {
		int i = 0;
		while (arr[i] != n && i < arr.length)
			i++;
		return i;
	}
	
	public static int indexOf(String[] arr, String s) {
		int i = 0;
		while (!arr[i].equals(s) && i < arr.length) {
			i++;
		}
		return i;
	}
	
	public static boolean contains(String[] arr, String s) {
		//TODO : method stub
		return true;
	}

}
