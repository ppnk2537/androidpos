package com.example.androidpos.domian;

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



	public boolean addProduct(Product product) {
		if ( dbh.insertProduct(product) > 0 )
			return true;
		return false;
	}
}
