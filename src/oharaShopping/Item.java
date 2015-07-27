package oharaShopping;

public class Item {

	/**
	 * Represents an entry of a single item, including the price, and the bulk price for
	 * the item. Does not store quantity.
	 */

	private String name;
	private double price;
	private int bulkQuantity;
	private double bulkPrice;

	/**
	 * Construct a new Item listing with a name, a price, and a bulk deal (x amount for $y
	 * cost)
	 *
	 * @param name The name of the item
	 * @param price The price of one of the item
	 * @param bulkQu The quantity required to pay for the item in bulk
	 * @param bulkPr The price to buy the bulk quantity of the item.
	 */
	public Item(String name, double price, int bulkQu, double bulkPr) {
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
		this.name = name;
		this.price = price;
		this.bulkQuantity = bulkQu;
		this.bulkPrice = bulkPr;
	}

	/**
	 * Construct a new Item listing with only a name and price, and no bulk option
	 *
	 * @param name The name of the item
	 * @param price The price of one of the item
	 */
	public Item(String name, double price) {
		this(name, price, 0, 0);
	}

	/**
	 * Construct a new Item listing by copying another Item object
	 *
	 * @param other The Item object to be copied
	 */
	public Item(Item other) {
		this(other.name, other.price, other.bulkQuantity, other.bulkPrice);
	}

	/**
	 * Calculate the cost for a given quantity of this item. Use the bulk price if
	 * applicable
	 *
	 * @param quantity The quantity of the item to buy
	 * @return The cost of the given quantity of this item
	 */
	public double priceFor(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be negative");
		}
		double price = 0;
		while (this.bulkQuantity != 0 && quantity > this.bulkQuantity) {
			quantity -= this.bulkQuantity;
			price += this.bulkPrice;
		}
		price += this.price * quantity;
		return price;
	}

	@Override
	public String toString() {
		String result = this.name + ", $" + String.format("%.2f", this.price);
		if (this.bulkQuantity != 0) {
			result += "(" + this.bulkQuantity + " for " + String.format("%.2f", this.bulkPrice) + ")";
		}
		return result;
	}

}
