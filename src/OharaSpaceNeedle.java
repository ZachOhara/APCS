public class OharaSpaceNeedle {
	
	public static final int scale = 3;
	
	public static void main(String[] args) {
		drawSpaceNeedle();
	}
	
	public static void drawSpaceNeedle() {
		drawSmallPole();
		drawTopDisk();
		drawDivider();
		drawBottomDisk();
		drawSmallPole();
		drawLargePole();
		drawTopDisk();
		drawDivider();
	}
	
	public static void drawSmallPole() {
		String spaces = "";
		for (int i = 0; i < scale; i++)
			spaces += "   ";
		for (int i = 0; i < scale; i++)
			System.out.println(spaces + "||");
	}
	
	public static void drawTopDisk() {
		String colons = "";
		String spaces = "";
		for (int i = 0; i < scale - 1; i++)
			spaces += "   ";
		for (int i = 1; i <= scale; i++) {
			System.out
					.println(spaces + "__/" + colons + "||" + colons + "\\__");
			colons += ":::";
			try {
				spaces = spaces.substring(3);
			} catch (StringIndexOutOfBoundsException expected) {
			}
			
		}
	}
	
	public static void drawDivider() {
		String quotes = "";
		for (int i = 0; i < 6 * scale; i++) {
			quotes += "\"";
		}
		System.out.println("|" + quotes + "|");
	}
	
	public static void drawBottomDisk() {
		String slashes = "";
		String spaces = "";
		for (int i = 0; i < 3 * scale - 1; i++)
			slashes += "/\\";
		for (int i = 0; i < scale; i++) {
			System.out.println(spaces + "\\_" + slashes + "_/");
			spaces += "  ";
			slashes = slashes.substring(4);
		}
	}
	
	public static void drawLargePole() {
		String moduli = "";
		String spaces = "";
		for (int i = 0; i < Math.ceil(((double) scale) / 2); i++) {
			// I'm a little unclear as to what the math is for the number of
			// moduli that appear based on scale, but this works for the two
			// examples
			moduli += "%";
		}
		for (int i = 0; i < scale; i++) {
			spaces += "   ";
		}
		spaces = spaces.substring(moduli.length() + 1);
		for (int i = 0; i < Math.pow(scale, 2); i++) {
			// Still unclear on the exact math used, but I'm confident this is
			// it
			System.out.println(spaces + "|" + moduli + "||" + moduli + "|");
		}
	}
	
}
