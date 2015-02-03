package oharaShopping;

public class Item {
	
	private String name;
	private double price;
	private int bulkQuantity;
	private double bulkPrice;
	
	public Item(String name, double price) {
		this(name, price, 0, 0);
	}
	
	public Item(Item other) {
		this(other.name, other.price, other.bulkQuantity, other.bulkPrice);
	}
	
	public Item(String name, double price, int bulkQu, double bulkPr) {
		if (price < 0)
			throw new IllegalArgumentException("Price cannot be negative");
		this.name = name;
		this.price = price;
		this.bulkQuantity = bulkQu;
		this.bulkPrice = bulkPr;
	}
	
	public double priceFor(int quantity) {
		if (quantity < 0)
			throw new IllegalArgumentException("Quantity cannot be negative");
		double price = 0;
		while (this.bulkQuantity != 0 && quantity > this.bulkQuantity) {
			quantity -= this.bulkQuantity;
			price += this.bulkPrice;
		}
		price += this.price * quantity;
		return price;
	}
	
	public String toString() {
		String result = this.name + ", $" + String.format("%.2f", this.price);
		if (this.bulkQuantity != 0)
			result += "(" + this.bulkQuantity + " for " + String.format("%.2f", this.bulkPrice) + ")";
		return result;
	}
	
	public static void main(String[] args) {
		Item i = new Item("silly putty", 3.95, 10, 19.99);
		System.out.println(i.priceFor(20));
	}

}
