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

public class OharaSpaceNeedle {

	public static final int scale = 3;

	public static void main(String[] args) {
		OharaSpaceNeedle.drawSpaceNeedle();
	}

	public static void drawSpaceNeedle() {
		OharaSpaceNeedle.drawSmallPole();
		OharaSpaceNeedle.drawTopDisk();
		OharaSpaceNeedle.drawDivider();
		OharaSpaceNeedle.drawBottomDisk();
		OharaSpaceNeedle.drawSmallPole();
		OharaSpaceNeedle.drawLargePole();
		OharaSpaceNeedle.drawTopDisk();
		OharaSpaceNeedle.drawDivider();
	}

	public static void drawSmallPole() {
		String spaces = "";
		for (int i = 0; i < OharaSpaceNeedle.scale; i++) {
			spaces += "   ";
		}
		for (int i = 0; i < OharaSpaceNeedle.scale; i++) {
			System.out.println(spaces + "||");
		}
	}

	public static void drawTopDisk() {
		String colons = "";
		String spaces = "";
		for (int i = 0; i < OharaSpaceNeedle.scale - 1; i++) {
			spaces += "   ";
		}
		for (int i = 1; i <= OharaSpaceNeedle.scale; i++) {
			System.out.println(spaces + "__/" + colons + "||" + colons + "\\__");
			colons += ":::";
			try {
				spaces = spaces.substring(3);
			} catch (StringIndexOutOfBoundsException expected) {
			}

		}
	}

	public static void drawDivider() {
		String quotes = "";
		for (int i = 0; i < 6 * OharaSpaceNeedle.scale; i++) {
			quotes += "\"";
		}
		System.out.println("|" + quotes + "|");
	}

	public static void drawBottomDisk() {
		String slashes = "";
		String spaces = "";
		for (int i = 0; i < 3 * OharaSpaceNeedle.scale - 1; i++) {
			slashes += "/\\";
		}
		for (int i = 0; i < OharaSpaceNeedle.scale; i++) {
			System.out.println(spaces + "\\_" + slashes + "_/");
			spaces += "  ";
			slashes = slashes.substring(4);
		}
	}

	public static void drawLargePole() {
		String moduli = "";
		String spaces = "";
		for (int i = 0; i < Math.ceil(((double) OharaSpaceNeedle.scale) / 2); i++) {
			// I'm a little unclear as to what the math is for the number of
			// moduli that appear based on scale, but this works for the two
			// examples
			moduli += "%";
		}
		for (int i = 0; i < OharaSpaceNeedle.scale; i++) {
			spaces += "   ";
		}
		spaces = spaces.substring(moduli.length() + 1);
		for (int i = 0; i < Math.pow(OharaSpaceNeedle.scale, 2); i++) {
			// Still unclear on the exact math used, but I'm confident this is
			// it
			System.out.println(spaces + "|" + moduli + "||" + moduli + "|");
		}
	}

}
