package oharaClassesProject;

public class RationalNumber {
	
	private int num;
	private int den;
	
	public RationalNumber(int n, int d) {
		if (d == 0)
			throw new IllegalArgumentException();
		this.num = n;
		this.den = d;
	}
	
	public RationalNumber() {
		this(0, 1);
	}
	
	public RationalNumber simplify() {
		int gcd = gcd(this.num, this.den);
		this.num /= gcd * signum(this.den);
		this.den /= gcd * signum(this.den);
		return this;
	}
	
	public int getNumerator() {
		return this.num;
	}
	
	public int getDenominator() {
		return this.den;
	}
	
	public RationalNumber negative(RationalNumber rn) {
		return new RationalNumber(-rn.num, rn.den);
	}
	
	public RationalNumber add(RationalNumber other) {
		int num = (this.num * other.den) + (other.num * this.den);
		int den = this.den * other.den;
		return new RationalNumber(num, den);
	}
	
	public RationalNumber addSimplify(RationalNumber other) {
		return this.add(other);
	}
	
	public RationalNumber subtract(RationalNumber other) {
		return this.add(negative(other));
	}
	
	public RationalNumber subtractSimplify(RationalNumber other) {
		return subtract(other).simplify();
	}
	
	public String toString() {
		if (this.den == 1)
			return "" + this.num;
		else
			return this.num + "/" + this.den;
	}
	
	static int gcd(int a, int b) { // greatest common divisor of two numbers
		a = Math.abs(a);
		b = Math.abs(b);
		int gcd = 1;
		for (int divisor = 1; divisor < Math.min(a, b); divisor++) {
			if (a % divisor == 0 && b % divisor == 0)
				gcd = divisor;
		}
		return gcd;
	}
	
	private static int signum(int i) {
		return (int)Math.signum((double)i);
	}
}
