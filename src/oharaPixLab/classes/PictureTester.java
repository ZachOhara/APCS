package oharaPixLab.classes;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{

	/** Method to test zeroRed */
	public static void testZeroRed()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroRed();
		beach.explore();
	}

	/** Method to test zeroGreen */
	public static void testZeroGreen()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroGreen();
		beach.explore();
	}

	/** Method to test zeroBlue */
	public static void testZeroBlue()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

	/** Method to test keepOnlyRed */
	public static void testKeepOnlyRed()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.keepOnlyRed();
		beach.explore();
	}

	/** Method to test keepOnlyGreen */
	public static void testKeepOnlyGreen()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.keepOnlyGreen();
		beach.explore();
	}

	/** Method to test keepOnlyBlue */
	public static void testKeepOnlyBlue()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.keepOnlyBlue();
		beach.explore();
	}

	/** Method to test the negate */
	public static void testNegate()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.negate();
		beach.explore();
	}

	/** Method to test the grayscale */
	public static void testGrayscale()
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.grayscale();
		beach.explore();
	}

	/** Method to test the fixUnderwater */
	public static void testFixUnderwater() {
		Picture water = new Picture("water.jpg");
		water.explore();
		water.fixUnderwater();
		water.explore();
		water.saturate();
		water.explore();
	}

	/** Method to test mirrorVertical */
	public static void testMirrorVertical()
	{
		Picture caterpillar = new Picture("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorVertical();
		caterpillar.explore();
	}

	/** Method to test mirrorVerticalRightToLeft */
	public static void testMirrorVerticalRightToLeft() {
		Picture caterpillar = new Picture("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorVerticalRightToLeft();
		caterpillar.explore();
	}
	
	/** Method to test mirrorHorizontal */
	public static void testMirrorHorizontal() {
		Picture caterpillar = new Picture("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorHorizontal();
		caterpillar.explore();
	}
	
	/** Method to test mirrorHorizontalBotToTop */
	public static void testMirrorHorizontalBotToTop() {
		Picture caterpillar = new Picture("caterpillar.jpg");
		caterpillar.explore();
		caterpillar.mirrorHorizontalBotToTop();
		caterpillar.explore();
	}
	
	/** Method to test mirrorDiagonal */
	public static void testMirrorDiagonal() {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.mirrorDiagonal();
		beach.explore();
	}

	/** Method to test mirrorTemple */
	public static void testMirrorTemple()
	{
		Picture temple = new Picture("temple.jpg");
		temple.explore();
		temple.mirrorTemple();
		temple.explore();
	}
	
	/** Method to test mirrorArms */
	public static void testMirrorArms() {
		Picture snowman = new Picture("snowman.jpg");
		snowman.explore();
		snowman.mirrorArms();
		snowman.explore();
	}
	
	/** Method to test mirrorGull */
	public static void testMirrorGull() {
		Picture seagull = new Picture("seagull.jpg");
		seagull.explore();
		seagull.mirrorGull();
		seagull.explore();
	}

	/** Method to test the collage method */
	public static void testCollage()
	{
		Picture canvas = new Picture("640x480.jpg");
		canvas.explore();
		canvas.myCollage();
		canvas.explore();
	}
	
	/** Method to test the copy method */
	public static void testCopy() {
		Picture canvas = new Picture("640x480.jpg");
		canvas.explore();
		canvas.copy(new Picture("beach.jpg"), 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
		canvas.explore();
	}

	/** Method to test edgeDetection */
	public static void testEdgeDetection()
	{
		Picture swan = new Picture("swan.jpg");
		swan.edgeDetection(20);
		swan.explore();
	}

	/** Main method for testing.  Every class can have a main
	 * method in Java */
	public static void main(String[] args)
	{
		// uncomment a call here to run a test
		// and comment out the ones you don't want
		// to run
		/*
		testZeroBlue();
		testKeepOnlyBlue();
		testKeepOnlyRed();
		testKeepOnlyGreen();
		testNegate();
		testGrayscale();
		testFixUnderwater();

		testMirrorVertical();
		testMirrorVerticalRightToLeft();
		testMirrorHorizontal();
		testMirrorHorizontalBotToTop();
		testMirrorDiagonal();
		testMirrorTemple();
		testMirrorArms();
		testMirrorGull();
		testMirrorDiagonal();
		*/
//		testCollage();
//		testCopy();
		testEdgeDetection();
//		testEdgeDetection2();
		//testChromakey();
		//testEncodeAndDecode();
		//testGetCountRedOverValue(250);
		//testSetRedToHalfValueInTopHalf();
		//testClearBlueOverValue(200);
		//testGetAverageForColumn(0);
	}
}