package com.example.androidpos.report;

import java.util.List;

import com.example.androidpos.sale.Sale;
import com.example.androidpos.sale.SaleLineItem;

public class Ledger {

	private String _id;
	private String ledger;
	private String ledger_cost;
	private Sale sale;
	private String lastEdit;
	private List<Double> cost;
	
	public Ledger(Sale sale, List<Double> cost) {
		this.sale = sale;
		this._id = sale.getId();
		this.lastEdit = sale.getLastEdit();
		this.cost = cost;
		
		initLedger();
		initCost();
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

}
