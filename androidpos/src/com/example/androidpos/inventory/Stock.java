package com.example.androidpos.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.androidpos.dao.DatabaseHandler;
import com.example.androidpos.sale.SaleLineItem;

public class Stock {

	private static Stock instance = null;

	private DatabaseHandler dbh;

	public Stock(DatabaseHandler dbh) {
		this.dbh = dbh;
	}

	/**
	 * Get instance of inventory.
	 * 
	 * @return instance.
	 */
	public static Stock getInstance() {
		return instance;
	}

	/**
	 * Get instance of inventory.
	 * 
	 * @return instance.
	 */
	public static void initInstance(DatabaseHandler dbc) {
		instance = new Stock(dbc);
	}

	public boolean addItem(Item item) {
		if ( dbh.insertItem(item) > 0 )
			return true;
		return false;
	}

	public boolean editItem(Item item, String lastEdit) {
		if ( dbh.updateItem(item, lastEdit) > 0)
			return true;
		return false;
	}
	
	public Item getItem(String _id,String lastEdit) {
		return dbh.selectItem(_id,lastEdit);
	}


	/**
	 * Get all item from inventory table.
	 * 
	 * @return Map of item.
	 */
	public List<HashMap<String, String>> getAllItem() {
		String[][] data = dbh.selectAll(DatabaseHandler.STOCK_TABLE_NAME);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (data != null)
			for (int i = 0; i < data.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", data[i][0]);
				map.put("name", data[i][1]);
				map.put("cost", data[i][2]);
				map.put("quantity", data[i][3]);
				map.put("lastEdit", data[i][4]);
								
				list.add(map);
			}
		return list;
	}

	public Item [] getItem(SaleLineItem lineItem) {
		return dbh.selectStock(lineItem.getId());
	}
	
}
