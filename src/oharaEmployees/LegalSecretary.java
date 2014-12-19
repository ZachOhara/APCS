package oharaEmployees;

// A class to represent legal secretaries.

// Add class header below
public class LegalSecretary extends Secretary {

	public void fileLegalBriefs() {
		System.out.println("I could file all day!");
	}

	// Add method that overrides getSalary from Employee class
	// for salary of 45000.0 per year

	public double getSalary() {
		return 45000.0;      // $40,000.00 / year
	}

}