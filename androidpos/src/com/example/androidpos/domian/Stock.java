package com.example.androidpos.domian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	public boolean addProduct(Item item) {
		if ( dbh.insertStock(item) > 0 )
			return true;
		return false;
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
				list.add(map);
			}
		return list;
	}

}
