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
		ClockTime.initStringMap();
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
		return this.isPM ? "PM" : "AM";
	}

	public void setHours(int hrs) {
		this.hours = hrs;
	}

	public void setMinutes(int mins) {
		this.minutes = mins;
	}

	public void setAmPm(String ampm) {
		this.isPM = ClockTime.ampmStrings.get(ampm.toLowerCase());
		if (this.isPM == null) {
			throw new IllegalArgumentException("\"" + ampm + "\" is valid");
		}
	}

	public int getTotalMinutes() {
		int totalMins = this.isPM ? 720 : 0;
		int hrs = this.getHours() == 12 ? 0 : this.getHours();
		return totalMins + this.getMinutes() + (60 * hrs);
	}

	@Override
	public int compareTo(ClockTime other) {
		return this.getTotalMinutes() - other.getTotalMinutes();
	}

	public boolean equals(ClockTime other) {
		return this.compareTo(other) == 0;
	}

	private static void initStringMap() {
		if (ClockTime.ampmStrings == null) {
			ClockTime.ampmStrings = new HashMap<String, Boolean>();
			ClockTime.ampmStrings.put("am", false);
			ClockTime.ampmStrings.put("a", false);
			ClockTime.ampmStrings.put("pm", true);
			ClockTime.ampmStrings.put("p", true);
		}
	}

	@Override
	public String toString() {
		return this.getHours() + ":" + String.format("%02d", this.getMinutes()) + " " + this.getAmPm();
	}
}
