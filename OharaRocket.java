
public class OharaRocket {

	public static int scale = 3;
	
	public static void main(String[] args) {
		drawRocket();
	}
	
	public static void drawRocket() {
		System.out.println();
		drawCone();
		drawDivider();
		drawDownDesign();
		drawUpDesign();
		drawDivider();
		drawUpDesign();
		drawDownDesign();
		drawDivider();
		drawCone();
	}
	
	public static void drawCone() {
		for (int i = 1; i <= 2 * scale -1; i++) {
			for (int j = 1; j <=  2 * scale - i; j++)
				System.out.print(" ");
			for (int j = 0; j < i; j++)
				System.out.print("/");
			System.out.print("**");
			for (int j = 0; j < i; j++)
				System.out.print("\\");
			System.out.println();
		}
	}
	
	public static void drawDivider() {
		System.out.print("+");
		for (int i = 0; i < scale * 2; i++)
			System.out.print("=*");
		System.out.println("+");
	}
	
	public static void drawDownDesign() {
		for (int i = scale; i >= 1; i--) {
			System.out.println(designLine(i, "\\/"));
		}
	}
	
	public static void drawUpDesign() {
		for (int i = 1; i <= scale; i++) {
			System.out.println(designLine(i, "/\\"));
		}
	}
	
	public static String designLine(int i, String slash) {
		String dots = "";
		String slashes = "";
		for (int j = 1; j <= scale - i; j++)
			dots += ".";
		for (int j = 1; j <= i ; j++)
			slashes += slash;
		return "|" + dots + slashes + dots + dots + slashes + dots + "|";
	}

}
