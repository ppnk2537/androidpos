package com.example.androidpos.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

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

	public boolean addLedger(Ledger l) {
		if ( dbh.insertLedger(l) > 0 )
			return true;
		return false;
	}

	/**
	 * Get all item from inventory table.
	 * 
	 * @return Map of item.
	 */
	public List<HashMap<String, String>> getAllLedger() {
		String [][] data = dbh.selectAll(DatabaseHandler.LEDGER_TABLE_NAME);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (data != null)
			for (int i = 0; i < data.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", data[i][0]);
				String [] s = data[i][1].split("&");
				double total_price = 0;
				for ( String item : s ) {
					String [] d = item.split(" ");
					total_price += Double.valueOf(d[4]);
				}
				s = data[i][2].split("&");
				double total_cost = 0;
				for ( String cost : s ) {
					total_cost += Double.valueOf(cost);
				}
				double profit = total_price - total_cost;
				map.put("profit", Double.toString(profit));
				map.put("lastedit", data[i][3]);
				list.add(map);
			}
		return list;
	}

	public Ledger getLedger(String _id) {
		return dbh.selectLedger(_id);
	}

}
