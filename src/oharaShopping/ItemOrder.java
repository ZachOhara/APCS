package oharaShopping;

public class ItemOrder {
	
	/**
	 * Represents an order of a single item with a given quantity
	 */
	
	private Item item;
	private int quantity;
	
	/**
	 * Construct a new ItemOrder object with an item and a quantity
	 * @param item The Item to buy
	 * @param quantity The amount of the specified item
	 */
	public ItemOrder(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	/**
	 * Get the price of the item for the set quantity
	 * @return The cost of the item order
	 */
	public double getPrice() {
		return this.item.priceFor(this.quantity);
	}
	
	/**
	 * Get the item that is being bought
	 * @return The item that is being bought
	 */
	public Item getItem() {
		return this.item;
	}
	
	public String toString() {
		return this.item.toString() + " " + this.quantity;
	}	

}
