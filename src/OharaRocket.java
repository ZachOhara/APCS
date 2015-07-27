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

 public class OharaRocket {

	public static int scale = 3;

	public static void main(String[] args) {
		OharaRocket.drawRocket();
	}

	public static void drawRocket() {
		System.out.println();
		OharaRocket.drawCone();
		OharaRocket.drawDivider();
		OharaRocket.drawDownDesign();
		OharaRocket.drawUpDesign();
		OharaRocket.drawDivider();
		OharaRocket.drawUpDesign();
		OharaRocket.drawDownDesign();
		OharaRocket.drawDivider();
		OharaRocket.drawCone();
	}

	public static void drawCone() {
		for (int i = 1; i <= 2 * OharaRocket.scale - 1; i++) {
			for (int j = 1; j <= 2 * OharaRocket.scale - i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("/");
			}
			System.out.print("**");
			for (int j = 0; j < i; j++) {
				System.out.print("\\");
			}
			System.out.println();
		}
	}

	public static void drawDivider() {
		System.out.print("+");
		for (int i = 0; i < OharaRocket.scale * 2; i++) {
			System.out.print("=*");
		}
		System.out.println("+");
	}

	public static void drawDownDesign() {
		for (int i = OharaRocket.scale; i >= 1; i--) {
			System.out.println(OharaRocket.designLine(i, "\\/"));
		}
	}

	public static void drawUpDesign() {
		for (int i = 1; i <= OharaRocket.scale; i++) {
			System.out.println(OharaRocket.designLine(i, "/\\"));
		}
	}

	public static String designLine(int i, String slash) {
		String dots = "";
		String slashes = "";
		for (int j = 1; j <= OharaRocket.scale - i; j++) {
			dots += ".";
		}
		for (int j = 1; j <= i; j++) {
			slashes += slash;
		}
		return "|" + dots + slashes + dots + dots + slashes + dots + "|";
	}

}
