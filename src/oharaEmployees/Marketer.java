package oharaEmployees;

// A class to represent marketers.

// Add class header below
public class Marketer extends Employee
{
	public void advertise() {
		System.out.println("Act now while supplies last!");
	}

	// Add method that overrides getSalary from Employee class
	// for salary of $50000.00 per year

	public double getSalary() {
		return 50000.0;      // $40,000.00 / year
	}

}