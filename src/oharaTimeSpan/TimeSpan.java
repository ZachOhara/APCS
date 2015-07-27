package oharaTimeSpan;

public class TimeSpan {

	private int hours;
	private int minutes;

	public TimeSpan(int hours1, int minutes1) {
		this.hours = 0;
		this.minutes = 0;
		this.add(hours1, minutes1);
	}

	public void add(int hours1, int minutes1) {
		this.hours += hours1;
		this.minutes += minutes1;
		while (this.minutes >= 60) {
			this.minutes -= 60; // convert 60 min --> 1 hour
			this.hours++;
		}
	}

	public void add(TimeSpan time) {
		this.add(time.hours, time.minutes);
	}

	public double getTotalHours() {
		return this.hours + this.minutes / 60.0;
	}

	@Override
	public String toString() {
		return this.hours + "h" + this.minutes + "m";
	}

	@Override
	public boolean equals(Object o) {
		if ( !(o instanceof TimeSpan)) {
			return false;
		}
		TimeSpan ts = (TimeSpan) o;
		return (this.getTotalHours() == ts.getTotalHours());
	}
}
