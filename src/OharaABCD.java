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
