package com.example.androidpos.report;

import java.util.List;

import com.example.androidpos.sale.Sale;
import com.example.androidpos.sale.SaleLineItem;

public class Ledger {

	private String _id;
	private String ledger;
	private Sale sale;
	private String lastEdit;

	public Ledger(Sale sale) {
		this.sale = sale;
		this._id = sale.getId();
		this.lastEdit = sale.getLastEdit();
		
		initLedger();
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
	
	public String getId() {	return this._id; }
	
	public String getLedger() { return this.ledger; }
	
	public String getLastEdit() { return this.lastEdit; }

}
