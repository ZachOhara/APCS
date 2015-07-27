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

package oharaPixLab.images;

import oharaPixLab.classes.Picture;

public enum Images {

	BL_640x480("640x480"),
	BL_7inX95in("7inX95in"),
	ARCH("arch"),
	BARBARAS("barbaraS"),
	BEACH("beach"),
	BLUE_MARK("blue-mark"),
	BLUEMOTORCYCLE("blueMotorcycle"),
	BUTTERFLY1("butterfly1"),
	CATERPILLAR("caterpillar"),
	CUMBERLANDISLAND("CumberlandIsland"),
	FEMALELIONANDHALL("femaleLionAndHall"),
	FLOWER1("flower1"),
	FLOWER2("flower2"),
	GORGE("gorge"),
	JENNY_RED("jenny-red"),
	KATIEFANCY("KatieFancy"),
	KITTEN2("kitten2"),
	KOALA("koala"),
	MOON_SURFACE("moon-surface"),
	MSG("msg"),
	REDMOTORCYCLE("redMotorcycle"),
	ROBOT("robot"),
	SEAGUL("seagull"),
	SMALLTERRAIN("smallTerrain", "png"),
	SNOWMAN("snowman"),
	SWAN("swan"),
	TEMPLE("temple"),
	TERRAIN("terrain", "png"),
	THRUDOOR("thruDoor"),
	WALL("wall"),
	WATER("water"),
	WHITEFLOWER("whiteflower");

	private String name;
	private Picture pic = null;

	private Images(String filename) {
		this(filename, "jpg");
	}

	private Images(String filename, String type) {
		this.name = filename + "." + type;
		this.pic = this.getNew();
	}

	public Picture get() {
		return this.pic;
	}

	public Picture getNew() {
		return new Picture(this.name);
	}

	public static void main(String[] args) {
		Images.SNOWMAN.get().explore();
	}

}
