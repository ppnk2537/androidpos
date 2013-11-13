package com.example.androidpos.domian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductCatalog {
	
	private static ProductCatalog instance = null;
	
	private DatabaseHandler dbh;

	public ProductCatalog(DatabaseHandler dbh) {
		this.dbh = dbh;
	}
	
	/**
	 * Get instance of inventory.
	 * @return instance.
	 */
	public static ProductCatalog getInstance() {
		return instance;
	}
	
	/**
	 * Get instance of inventory.
	 * @return instance.
	 */
	public static void initInstance( DatabaseHandler dbc ) {
		instance = new ProductCatalog( dbc );
	}

	public Product getProduct(String _id) {
		return dbh.selectProduct(_id);
	}
	
	public boolean addProduct(Product product) {
		if ( dbh.insertProduct(product) > 0 )
			return true;
		return false;
	}
	
	public boolean editProduct(Product product) {
		if ( dbh.updateProduct(product) > 0 )
			return true;
		return false;
	}

	public boolean removeProduct(String _id) {
		if ( dbh.deleteProduct(_id) > 0 )
			return true;
		return false;
	}
	
	public boolean isProductExsit(String _id) {
		Product product = getProduct(_id);
		return product != null;
	}
	
	/**
	 * Get all item from inventory table.
	 * 
	 * @return Map of item.
	 */
	public List<HashMap<String, String>> getAllProduct() {
		String [][] data = dbh.selectAll(DatabaseHandler.CATALOG_TABLE_NAME);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (data != null)
			for (int i = 0; i < data.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", data[i][0]);
				map.put("name", data[i][1]);
				map.put("price", data[i][2]);
				map.put("lastedit", data[i][4]);
				list.add(map);
			}
		return list;
	}


}
