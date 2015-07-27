public class OharaMidpoint {
	
	public static void main(String[] args) {
		if (hasMidpoint(7, 4, 10))
			System.out.println("7, 4, 10 has a midpoint.");
		else
			System.out.println("7, 4, 10 does not have a midpoint.");
		if (hasMidpoint(9, 15, 8))
			System.out.println("9, 15, 8 has a midpoint.");
		else
			System.out.println("9, 15, 8 does not have a midpoint.");
		
	}
	
	public static boolean hasMidpoint(int a, int b, int c) {
		return (a - b == c - a) || (a - b == b - c) || (b - c == c - a);
	}
	
}
