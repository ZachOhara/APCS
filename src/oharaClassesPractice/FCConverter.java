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

 package oharaClassesPractice;

public class FCConverter {

	private double degF;
	private double degC;

	public double getFahrenheit() {
		return this.degF;
	}

	public double getCelsius() {
		return this.degC;
	}

	public void setFahrenheit(double newDegF) {
		this.degF = newDegF;
		this.degC = this.fToC(newDegF);
	}

	public void setCelsius(double newDegC) {
		this.degC = newDegC;
		this.degF = this.cToF(newDegC);
	}

	private double fToC(double f) {
		return (5.0 / 9.0) * (f - 32);
	}

	private double cToF(double c) {
		return ((9.0 / 5.0) * c) + 32;
	}

}
