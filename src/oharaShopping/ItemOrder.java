package oharaShopping;

public class ItemOrder {
	
	private Item item;
	private int quantity;
	
	public ItemOrder(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return this.item.priceFor(this.quantity);
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public String toString() {
		return this.item.toString() + " " + this.quantity;
	}
	
	public static void main(String[] args) {
		Item i = new Item("silly putty", 3.95, 10, 19.99);
		ItemOrder o = new ItemOrder(i, 20);
		System.out.println(o.getPrice());
	}
	
	

}
