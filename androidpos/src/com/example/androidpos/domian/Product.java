package com.example.androidpos.domian;

public class Product {

	private String _id;
	private String name;
	private String tag;
	private double price;
	private String lastEdit;
	
	public Product(String[] data) {
		this._id = data[0];
		this.name = data[1];
		this.tag = data[2];
		this.price = Double.parseDouble(data[3]);
		this.lastEdit = data[4];
	}
	
	public String getId() {
		return this._id;
	}

	public String getName() {
		return this.name;
	}

	public String getTag() {
		return this.tag;
	}

	public double getPrice() {
		return this.price;
	}

	public String getLastEdit() {
		return this.lastEdit;
	}

}
