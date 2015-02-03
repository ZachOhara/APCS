package oharaShopping;

import java.util.ArrayList;

public class Catalog {
	
	ArrayList<Item> localList;
	
	private String name;
	
	public Catalog(String name) {
		this.localList = new ArrayList<Item>();
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void add(Item i) {
		this.localList.add(i);
	}
	
	public int size() {
		return this.localList.size();
	}
	
	public Item get(int i) {
		return this.localList.get(i);
	}
	
	public String toString() {
		return this.localList.toString();
	}
	
}
