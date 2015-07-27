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
		this.degC = fToC(newDegF);
	}
	
	public void setCelsius(double newDegC) {
		this.degC = newDegC;
		this.degF = cToF(newDegC);
	}
	
	private double fToC(double f) {
		return (5.0 / 9.0) * (f - 32);
	}
	
	private double cToF(double c) {
		return ((9.0 / 5.0) * c) + 32;
	}
	
}
