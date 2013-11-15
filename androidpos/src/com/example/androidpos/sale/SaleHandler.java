package com.example.androidpos.sale;

import com.example.androidpos.inventory.Product;
import com.example.androidpos.inventory.ProductCatalog;

public class SaleHandler {

	private SaleLedger ledger;
	private Sale sale;
	
	public SaleHandler() {
		this.sale = new Sale();
	}
	
	public boolean addSaleLineItem(String _id, String quantity) {
		ProductCatalog pc = ProductCatalog.getInstance();
		Product product = pc.getProduct(_id);
		
		SaleLineItem sli = new SaleLineItem(product, quantity);
		
		if ( sale.addSaleLineItem(sli) )
			return true;
		return false;
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	public boolean updateStock() {
		return false;
	}
}
