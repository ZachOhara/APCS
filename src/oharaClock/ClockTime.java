package oharaClock;

import java.util.HashMap;
/*
TODO:
Refactor minutes to hours, and mins to hrs
Refactor seconds to minutes, and seconds to mins
 */

public class ClockTime implements Comparable<ClockTime> {

	private int hours;
	private int minutes;
	private Boolean isPM;

	private static HashMap<String, Boolean> ampmStrings;

	public ClockTime(int hrs, int mins, String ampm) {
		initStringMap();
		this.setHours(hrs);
		this.setMinutes(mins);
		this.setAmPm(ampm);
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

	public void setHours(int hrs) {
		this.hours = hrs;
	}

	public void setMinutes(int mins) {
		this.minutes = mins;
	}

	public void setAmPm(String ampm) {
		this.isPM = ampmStrings.get(ampm.toLowerCase());
		if (this.isPM == null)
			throw new IllegalArgumentException("\"" + ampm + "\" is valid");
	}
	
	public int getTotalMinutes() {
		int totalMins = isPM ? 720 : 0;
		int hrs = this.getHours() == 12 ? 0 : this.getHours();
		return totalMins + this.getMinutes() + (60 * hrs);
	}

	public int compareTo(ClockTime other) {
		return this.getTotalMinutes() - other.getTotalMinutes();
	}

	public boolean equals(ClockTime other) {
		return this.compareTo(other) == 0;
	}

	private static void initStringMap() {
		if (ampmStrings == null) {
			ampmStrings = new HashMap<String, Boolean>();
			ampmStrings.put("am",false);
			ampmStrings.put("a", false);
			ampmStrings.put("pm", true);
			ampmStrings.put("p", true);
		}
	}

	public String toString() {
		return this.getHours() + ":" + String.format("%02d", this.getMinutes()) + " " + this.getAmPm();
	}
}
