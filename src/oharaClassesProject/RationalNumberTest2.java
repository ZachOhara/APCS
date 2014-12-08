package oharaClassesProject;

public class RationalNumberTest2 {
	
	public static void main(String[] args) {
		//RationalNumber num = new RationalNumber(
				
		RationalNumber num1 = new RationalNumber(3, 8);
		RationalNumber num2 = new RationalNumber(3, 6);
		RationalNumber num3 = new RationalNumber(7, -8);
		RationalNumber num4 = new RationalNumber(-1, 4);
		RationalNumber num5 = new RationalNumber(1, 2);
		RationalNumber num6 = new RationalNumber(6, 16);
		RationalNumber num7 = new RationalNumber(-2, 8);
		RationalNumber num8 = new RationalNumber(0, 1);
		RationalNumber num9 = new RationalNumber(0, 2);
		
		System.out.println(num1.compareTo(num2));
		System.out.println(num2.compareTo(num3));
		System.out.println(num3.compareTo(num4));
		System.out.println(num4.compareTo(num5));
		System.out.println(num5.compareTo(num6));
		System.out.println(num6.compareTo(num7));

		System.out.println();
		System.out.println(num2.equals(num5));
		System.out.println(num1.equals(num6));
		System.out.println(num4.equals(num7));
		
		System.out.println();
		System.out.println(num8.compareTo(num9));
	}

}
