
public class OharaIfElseExercisesPart2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static double fractionSum(int n) {
		double sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += 1.0 / i;
		}
		return sum;
	}
	
	public static String repl(String template, int reps) {
		String result = "";
		for (int i = 0; i < reps; i++) {
			result += template;
		}
		return result;
	}
	
	public static void printTriangleType(int a, int b, int c) {
		if (a + b < c || a + c < b || b + c < a)
			throw new IllegalArgumentException();
		if (a == b || a == c || b == c) {
			if (a==b && b==c) { //also a==c
				System.out.println("equilatiral");
			} else
				System.out.println("isosceles");
		} else
			System.out.println("scalene");
	}
	
	public static String season(int m, int d) {
		if ((m < 3 || (m==3 && d <= 15)) || (m == 12 && d >= 16))
			return "winter";
		if (m < 6 || (m == 6 && d <= 15))
			return "spring";
		if (m < 9 || (m == 9 && d <= 15))
			return "summer";
		return "fall";
	}
	
	public static getGrade(it)

}
