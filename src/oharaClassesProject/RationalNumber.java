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

 package oharaClassesProject;

public class RationalNumber implements Comparable<RationalNumber> {

	private int num;
	private int den;

	public RationalNumber(int n, int d) {
		if (d == 0) {
			throw new IllegalArgumentException();
		}
		this.num = n;
		this.den = d;
	}

	public RationalNumber() {
		this(0, 1);
	}

	public int getNumerator() {
		return this.num;
	}

	public int getDenominator() {
		return this.den;
	}

	public double decimalValue() {
		return (double) this.num / this.den;
	}

	public RationalNumber simplify() {
		if (this.num == 0) {
			this.den = 1;
		}
		int gcd = OharaMath.gcd(this.num, this.den);
		this.num /= gcd * OharaMath.signum(this.den);
		this.den /= gcd * OharaMath.signum(this.den);
		return this;
	}

	public RationalNumber negative(RationalNumber rn) {
		return new RationalNumber( -rn.num, rn.den);
	}

	public RationalNumber add(RationalNumber other) {
		int num = (this.num * other.den) + (other.num * this.den);
		int den = this.den * other.den;
		return new RationalNumber(num, den);
	}

	public RationalNumber subtract(RationalNumber other) {
		return this.add(this.negative(other));
	}

	public RationalNumber addSimplify(RationalNumber other) {
		return this.add(other);
	}

	public RationalNumber subtractSimplify(RationalNumber other) {
		return this.subtract(other).simplify();
	}

	public RationalNumber copyData() {
		return new RationalNumber(this.num, this.den);
	}

	@Override
	public String toString() {
		if (this.den == 1) {
			return "" + this.num;
		} else {
			return this.num + "/" + this.den;
		}
	}

	public boolean equals(RationalNumber other) {
		/*
		 * RationalNumber simplifyThis = this.copyData().simplify(); RationalNumber
		 * simplifyOther = other.copyData().simplify(); return (simplifyThis.num ==
		 * simplifyOther.num && simplifyThis.den == simplifyOther.den);
		 */
		if (other == null) {
			return false;
		}
		return this.compareTo(other) == 0;
	}

	@Override
	public int compareTo(RationalNumber other) {
		double thisdecimal = this.decimalValue();
		double otherdecimal = other.decimalValue();
		if (thisdecimal < otherdecimal) {
			return -1;
		} else if (otherdecimal < thisdecimal) {
			return 1;
		}
		return 0;
	}

	static class OharaMath {

		static int signum(int i) {
			return (int) Math.signum((double) i);
		}

		static int gcd(int a, int b) { // greatest common divisor of two numbers
			a = Math.abs(a);
			b = Math.abs(b);
			int gcd = 1;
			for (int divisor = 1; divisor < Math.min(a, b); divisor++) {
				if (a % divisor == 0 && b % divisor == 0) {
					gcd = divisor;
				}
			}
			return gcd;
		}
	}
}
