package com.example.androidpos.inventory;

public class Item implements Comparable<Item> {

	private String _id;
	private String name;
	private double cost;
	private int quantity;
	private String lastEdit;
	
	public Item(String[] data) {
		this._id = data[0];
		this.name = data[1];
		this.cost = Double.parseDouble(data[2]);
		this.quantity = Integer.parseInt(data[3]);
		this.lastEdit = data[4];
	}
	
	public String getId() {
		return this._id;
	}

	public String getName() {
		return this.name;
	}

	public int getQuantity() {
		return this.quantity;
	}
	public double getCost() {
		return this.cost;
	}

	public String getLastEdit() {
		return this.lastEdit;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compareTo(Item another) {
		String [] tim = lastEdit.split(" ");
		String [] s = tim[0].split(":");
		double hr = Double.valueOf(s[0]);
		double min = Double.valueOf(s[1]);
		double sec = Double.valueOf(s[2]);
		hr += min/100 + sec/1000;
		
		tim = another.getLastEdit().split(" ");
		s = tim[0].split(":");
		double ahr = Double.valueOf(s[0]);
		double amin = Double.valueOf(s[1]);
		double asec = Double.valueOf(s[2]);
		ahr += amin/100 + asec/1000;
		return (int)(hr - ahr);
	}

}
