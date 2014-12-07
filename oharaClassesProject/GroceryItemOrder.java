package oharaClassesProject;

public class GroceryItemOrder {
	
	private String name = "";
	private int quantity = 0;
	private double pricePerUnit = 0;
	
	public GroceryItemOrder(String name, int quantity, double pricePerUnit) {
		this.name = name;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}
	
	public GroceryItemOrder() {
		// default
	}
	
	public double getCost() {
		return this.quantity * this.pricePerUnit;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return "GroceryItemOrder[" + this.quantity + " of " + this.name + " at " + this.pricePerUnit + "]";
	}
}
