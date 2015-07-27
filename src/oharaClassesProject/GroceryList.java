package oharaClassesProject;

public class GroceryList {

	private GroceryItemOrder[] list = new GroceryItemOrder[10];
	private int items = 0;

	public GroceryList() {
		// default constructor
	}

	public void add(GroceryItemOrder item) {
		if (this.items < 10) {
			this.list[this.items] = item;
			this.items++;
		}
	}

	public double getTotalCost() {
		double total = 0;
		for (int i = 0; i < this.items; i++) {
			total += this.list[i].getCost();
		}
		return total;
	}

}
