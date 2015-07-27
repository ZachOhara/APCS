package oharaShopping;

import java.util.ArrayList;

public class Catalog {
	
	/**
	 * Represents a list of Item object that could potentially be ordered
	 */
	
	ArrayList<Item> localList;
	
	private String name;
	
	/**
	 * Construct a new Catalog object with a name
	 * 
	 * @param name
	 *            the name of the catalog
	 */
	public Catalog(String name) {
		this.localList = new ArrayList<Item>();
		this.name = name;
	}
	
	/**
	 * Get the name of this catalog
	 * 
	 * @return the catalog name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Add an item to the catalog
	 * 
	 * @param i
	 *            The Item object to be added
	 */
	public void add(Item i) {
		this.localList.add(i);
	}
	
	/**
	 * Get the size of the catalog
	 * 
	 * @return the amount of items in the catalog
	 */
	public int size() {
		return this.localList.size();
	}
	
	/**
	 * Get an item at the specified index of the catalog
	 * 
	 * @param i
	 *            The index to return
	 * @return The Item object at index i
	 */
	public Item get(int i) {
		return this.localList.get(i);
	}
	
	public String toString() {
		return this.localList.toString();
	}
	
}
