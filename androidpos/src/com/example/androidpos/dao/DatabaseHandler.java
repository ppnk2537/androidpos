package com.example.androidpos.dao;

import com.example.androidpos.inventory.Item;
import com.example.androidpos.inventory.Product;
import com.example.androidpos.report.Ledger;

public interface DatabaseHandler {

	public static final String CATALOG_TABLE_NAME = "CATALOG_TABLE";
	
	public static final String STOCK_TABLE_NAME = "STOCK_TABLE";
	
	public static final String LEDGER_TABLE_NAME = "LEDGER_TABLE";
	
	/**
	 * Select Product from database
	 * @param _id code identify where data is.
	 * @return database data , otherwise null.
	 */
	public Product selectProduct( String _id );
		
	
	/**
	 * Select Stockription from database
	 * @param _id code identify where data is.
	 * @param lastEdit 
	 * @return database data , otherwise null.
	 */
	public Item selectItem( String _id, String lastEdit );
	
	public Item [] selectStock( String _id);
		
	/**
	 * Select all data from database.
	 * @return all data from database, otherwise return null.
	 */
	public String[][] selectAll( String tableName );
	
	/**
	 * Insert data to database.
	 * @param _id code identify where data is.
	 * @param name of Product.
	 * @return number of row that has been insert, otherwise -1.
	 */
	public long insertProduct( Product product );
	
	/**
	 * Insert data to database.
	 * @param name name of Product.
	 * @param Stockription
	 * @param unit
	 * @param price
	 * @param cost
	 * @return number of row that has been insert, otherwise -1.
	 */
	public long insertItem( Item item );
	

	/**
	 * Update data to database.
	 * @param _id code identify where data is.
	 * @param name of Product.
	 * @return number of row that has been insert, otherwise -1.
	 */
	public long updateProduct( Product product );
	
	/**
	 * Update database data
	 * @param name where data is.
	 * @return number of row that has been update, otherwise -1.
	 */
	public long updateItem( Item item , String lastEdit );
	
	/**
	 * Update database data
	 * @param name where data is.
	 * @return number of row that has been update, otherwise -1.
	 */
	public long updateStock(String name, int quantity);
	
	/**
	 * Delete data from database.
	 * @param _id code identify where data is.
	 * @return number of row that has been delete, otherwise -1.
	 */
	public long deleteProduct( String _id );
	
	/**
	 * Delete data from database.
	 * @param name of Stockription.s
	 * @return number of row that has been delete, otherwise -1.
	 */
	public long deleteStock( String name );


	public long insertLedger(Ledger l);


	public Ledger selectLedger(String _id);
		
}
