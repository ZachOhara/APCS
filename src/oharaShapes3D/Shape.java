package oharaShapes3D;

/* The shape class is a generic class that all other shapes are indirectly derived
 * from. It only contains methods implemented from Comparable<>, and it passes on
 * the implementation of the methods in Shape3D
 */
public abstract class Shape implements Shape3D, Comparable<Shape> {

	@Override
	public int compareTo(Shape other) {
		return compare(this, other);
	}
	
	public static int compare(Shape a, Shape b) {
		double aVol = a.getVolume();
		double bVol = b.getVolume();
		if (aVol < bVol)
			return -1;
		else if (aVol > bVol)
			return 1;
		else {
			double aArea = a.getSurfaceArea();
			double bArea = b.getSurfaceArea();
			if (aArea < bArea)
				return -1;
			else if (aArea > bArea)
				return 1;
			else
				return 0;
		}
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Shape))
			throw new IllegalArgumentException("Compared object must be of type Shape");
		else
			return this.compareTo((Shape) other) == 0;
	}

}
