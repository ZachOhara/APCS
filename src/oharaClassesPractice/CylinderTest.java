package oharaClassesPractice;

import java.util.Scanner;

public class CylinderTest {
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("Radius: ");
		double r = console.nextDouble();
		System.out.print("Height: ");
		double h = console.nextDouble();
		Cylinder myCyl = new Cylinder(r, h);
		System.out.println("Volume: "
				+ String.format("%.2f", myCyl.getVolume()));
		console.close();
	}
	
}
