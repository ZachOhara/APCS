public class OharaIfElseExercisesPart2 {

	public static void main(String[] args) {
		System.out.println(OharaIfElseExercisesPart2.swapPairs("badcfehgkimlon"));
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
		if (a + b < c || a + c < b || b + c < a) {
			throw new IllegalArgumentException();
		}
		if (a == b || a == c || b == c) {
			if (a == b && b == c) { // also a==c
				System.out.println("equilatiral");
			} else {
				System.out.println("isosceles");
			}
		} else {
			System.out.println("scalene");
		}
	}

	public static String season(int m, int d) {
		if ((m < 3 || (m == 3 && d <= 15)) || (m == 12 && d >= 16)) {
			return "winter";
		}
		if (m < 6 || (m == 6 && d <= 15)) {
			return "spring";
		}
		if (m < 9 || (m == 9 && d <= 15)) {
			return "summer";
		}
		return "fall";
	}

	public static double getGrade(int grade) {
		if (0 > grade || grade > 100) {
			throw new IllegalArgumentException();
		}
		if (grade < 60) {
			return 0.0;
		}
		grade -= 62;
		if (grade <= 0) {
			return 0.7;
		}
		return Math.min((double) grade / 10 + 0.7, 4.0);
		// return grade/10; return 4.0 if grade/10 is more than 4.0;
	}

	// just for giggles. Return of -1.0 signifies illegal arguments
	public static double getGradeOneLine(int grade) {
		return (grade >= 0 && grade <= 100) ? (grade < 60 ? 0.0 : (grade <= 62 ? 0.7 : (grade >= 95 ? 4.0
				: ((double) (grade - 62) / 10 + 0.7)))) : -1;
		// Explanation:
		// return -1 if OoR, else (0.0 if failing, else (0.7 if grade<=62, else
		// (4.0 if grade>=95, else (grade on linear scale) ) ) )
	}

	public static String swapPairs(String swapStr) {
		String result = "";
		int swaps = swapStr.length() / 2;
		for (int i = 0; i < swaps; i++) {
			result += swapStr.substring(2 * i + 1, 2 * i + 2) + swapStr.substring(2 * i, 2 * i + 1);
		}
		return result;
	}

}
