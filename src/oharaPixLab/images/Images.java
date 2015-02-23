package oharaPixLab.images;

import oharaPixLab.classes.Picture;

public enum Images {
	
	BL_640x480 ("640x480"),
	BL_7inX95in ("7inX95in"),
	ARCH ("arch"),
	BARBARAS ("barbaraS"),
	BEACH ("beach"),
	BLUE_MARK ("blue-mark"),
	BLUEMOTORCYCLE ("blueMotorcycle"),
	BUTTERFLY1 ("butterfly1"),
	CATERPILLAR ("caterpillar"),
	CUMBERLANDISLAND ("CumberlandIsland"),
	FEMALELIONANDHALL ("femaleLionAndHall"),
	FLOWER1 ("flower1"),
	FLOWER2 ("flower2"),
	GORGE ("gorge"),
	JENNY_RED ("jenny-red"),
	KATIEFANCY ("KatieFancy"),
	KITTEN2 ("kitten2"),
	KOALA ("koala"),
	MOON_SURFACE ("moon-surface"),
	MSG ("msg"),
	REDMOTORCYCLE ("redMotorcycle"),
	ROBOT ("robot"),
	SEAGUL ("seagull"),
	SMALLTERRAIN ("smallTerrain", "png"),
	SNOWMAN ("snowman"),
	SWAN ("swan"),
	TEMPLE ("temple"),
	TERRAIN ("terrain", "png"),
	THRUDOOR ("thruDoor"),
	WALL ("wall"),
	WATER ("water"),
	WHITEFLOWER ("whiteflower");
	
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
