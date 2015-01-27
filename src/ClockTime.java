/*
TODO:
Refactor minutes to hours, and mins to hrs
Refactor seconds to minutes, and seconds to mins
*/

public class ClockTime implements Comparable<ClockTime> {
	
	private int minutes;
	private int seconds;
	private boolean isPM;
	
	private static HashMap<String, Boolean> ampmStrings;
	
	public ClockTime(int mins, int seconds, String ampm) {
		initStringMap();
		this.minutes = mins;
		this.seconds = seconds;
		this.isPM = ampmStrings.get(ampm.toLowerCase());
		if (this.isPM == null)
			throw new IllegalArgumentException("\"" + ampm + "\" is valid");
		
	}
	
	public int getHours() {
		return this.hours;
	}
	
	public int getMinutes() {
		return this.minutes;
	}
	
	public String getAmPm() {
		return isPM ? "PM" : "AM";
	}
	
	public int compareTo(ClockTime other) {
		return (other.getMinutes() - this.getMinutes)
				+ (60 * (other.getHours() - this.getHours()));
	}
	
	public boolean equals(ClockTime other) {
		return this.compareTo(other) == 0;
	}
	
	private static void iniStringMap() {
		if (ampmStrings == null) {
			ampmStrings = new HashMap<String, Boolean>();
			ampmStrings.add("am", false);
			ampmStrings.add("a", false):
			ampmStrings.add("pm", true);
			ampmStrings.add("p", true);
		}
	}
	
	public String toString() {
		return this.getHours() + ":" + this.getMinutes() + " " + this.getAmPm();
	}
}
