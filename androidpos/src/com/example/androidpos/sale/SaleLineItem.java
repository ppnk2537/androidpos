package com.example.androidpos.sale;

import com.example.androidpos.inventory.Product;

public class SaleLineItem {

	private String _id;
	private String name;
	private String price;
	private String quantity;
	
	public SaleLineItem(Product product , String quantity) {
		this._id = product.getId();
		this.name = product.getName();
		this.price = Double.toString(product.getPrice());
		this.quantity = quantity;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = Integer.toString(quantity);
	}
	
	public String getId() {
		return this._id;
	}

	public String getName() {
		return this.name;
	}

	public String getQuanity() {
		return this.quantity;
	}

	public String getPrice() {
		return this.price;
	}

	public String getTotalPrice() {
		double price = Double.parseDouble(this.price);
		double quantity = Double.parseDouble(this.quantity);
		return ""+(price*quantity);
	}

}
