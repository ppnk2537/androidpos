package com.example.androidpos.inventory;

public class Item {

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

}
