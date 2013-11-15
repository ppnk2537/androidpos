package com.example.androidpos.sale;

import com.example.androidpos.dao.DatabaseHandler;

public class SaleLedger {

	private static SaleLedger instance = null;

	private DatabaseHandler dbh;

	public SaleLedger(DatabaseHandler dbh) {
		this.dbh = dbh;
	}

	/**
	 * Get instance of inventory.
	 * 
	 * @return instance.
	 */
	public static SaleLedger getInstance() {
		return instance;
	}

	/**
	 * Get instance of inventory.
	 * 
	 * @return instance.
	 */
	public static void initInstance(DatabaseHandler dbc) {
		instance = new SaleLedger(dbc);
	}
}
