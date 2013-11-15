package com.example.androidpos.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sale {
	
	private String saleId;
	private List<SaleLineItem> itemList;

	public Sale() {
		this.saleId = "100000";
		this.itemList = new ArrayList<SaleLineItem>();
	}
	
	public boolean addSaleLineItem(SaleLineItem sli) {
		int pre = itemList.size();
		itemList.add(sli);
		int post = itemList.size();
		
		return post > pre;
	}

	/**
	 * Get all item from sale
	 * 
	 * @return Map of item.
	 */
	public List<HashMap<String, String>> getAllItem() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (SaleLineItem item : itemList) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", item.getId() );
			map.put("name", item.getName() );
			map.put("price", item.getPrice() );
			map.put("quantity", item.getQuanity() );
			map.put("total", item.getTotalPrice() );
			list.add(map);
		}
		return list;
	}
	
	public List<SaleLineItem> getItemList() {
		return this.itemList;
	}
	
	public double getTotal() {
		double total = 0;
		for (SaleLineItem item : itemList) {
			total += Double.parseDouble(item.getTotalPrice());
		} 
		return total;				
	}

	public void clear() {
		this.itemList = new ArrayList<SaleLineItem>();
	}

	public String getId() {
		return this.saleId;
	}

}
