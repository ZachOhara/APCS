package oharaEmployees;

// A class to represent secretaries.
public class Secretary extends Employee {
	
	public void takeDictation(String text) {
		System.out.println("Taking dictation of text: " + text);
	}
	
	public static void main(String[] args) {
		Employee[] employees = {new Employee(), new Lawyer(), new Secretary()};
		for (Employee e : employees) {
			System.out.println(e.getVacationDays());
		}

	}
	
}
