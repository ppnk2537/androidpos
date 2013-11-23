package com.example.androidpos.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.example.androidpos.dao.DatabaseHandler;
import com.example.androidpos.sale.Sale;
import com.example.androidpos.sale.SaleLineItem;

public class Ledger {

	private String _id;
	private String ledger;
	private String ledger_cost;
	private Sale sale;
	private String lastEdit;
	private double cash;
	private List<Double> cost;
	
	public Ledger(Sale sale, List<Double> cost,double cash) {
		this.sale = sale;
		this.lastEdit = sale.getLastEdit();
		this.cost = cost;
		this.cash = cash;
		
		initLedger();
		
		initCost();
	}

	public Ledger(String[] data) {
		this._id = data[0];
		this.ledger = data[1];
		this.ledger_cost = data[2];
		this.cash = Double.valueOf(data[4]);
	}

	private void initLedger() {
		ledger = "";
		List<SaleLineItem> list = sale.getItemList();

		for (SaleLineItem sli : list) {
			ledger += String.format("%s %s %s %s %s&", sli.getId(),
					sli.getName(), sli.getPrice(), sli.getQuanity(),
					sli.getTotalPrice());
		}
	}
	
	private void initCost(){
		ledger_cost = "";
		for ( Double c : cost ) {
			ledger_cost += String.format("%.2f&", c);
		}
	}
	
	public String getId() {	return this._id; }
	
	public String getLedger() { return this.ledger; }
	
	public String getCost() { return this.ledger_cost; }
	
	public String getLastEdit() { return this.lastEdit; }

	public String getCash() { return Double.toString(this.cash); }
	
	public List<HashMap<String, String>> getDetail() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		String [] s = ledger.split("&");
		
		for ( String item : s ) {
			String [] d = item.split(" ");
			Log.d("d", Arrays.toString(d));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("quantity", d[3]);
			map.put("name", d[1]);
			map.put("price", d[2]);
			list.add(map);
		}
		return list;
	}

	public double getTotalPrice() {
		String [] s = ledger.split("&");
		
		double total = 0;
		for ( String item : s ) {
			String [] d = item.split(" ");
			total += Double.valueOf(d[4]);
		}
		
		return total;
	}

}
