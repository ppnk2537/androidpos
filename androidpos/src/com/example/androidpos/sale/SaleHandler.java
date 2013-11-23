package com.example.androidpos.sale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.androidpos.inventory.Item;
import com.example.androidpos.inventory.Product;
import com.example.androidpos.inventory.ProductCatalog;
import com.example.androidpos.inventory.Stock;
import com.example.androidpos.report.Ledger;
import com.example.androidpos.report.SaleLedger;

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

		if (sale.addSaleLineItem(sli))
			return true;
		return false;
	}

	public Sale getSale() {
		return this.sale;
	}
	
	public boolean updateLedger(List<Double> cost) {
		ledger = SaleLedger.getInstance();
		Ledger l = new Ledger(sale, cost);
		
		return	ledger.addLedger(l);
	}

	public boolean updateStock() {
		Stock stock = Stock.getInstance();

		List<SaleLineItem> list = sale.getItemList();
		List<Double> cost = new ArrayList<Double>();

		for (SaleLineItem sli : list) {
			Item[] items = stock.getItem(sli);
			Arrays.sort(items);

			int sli_quantity = Integer.valueOf(sli.getQuanity());
			double total_cost = 0;

			for (Item item : items) {
				if (item.getQuantity() == 0)
					continue;
				if (item.getQuantity() >= sli_quantity) {
					item.setQuantity(item.getQuantity() - sli_quantity);
					total_cost += sli_quantity * item.getCost();
					sli_quantity = 0;
				} else if (item.getQuantity() < sli_quantity) {
					sli_quantity -= item.getQuantity();
					total_cost += item.getQuantity() * item.getCost();
					item.setQuantity(0);
				}
			}

			if (sli_quantity != 0) {
				items[items.length - 1].setQuantity(items[items.length - 1].getQuantity() - sli_quantity);
				total_cost += items[items.length - 1].getCost() * sli_quantity;
			}

			for (Item item : items) {
				stock.editItem(item, item.getLastEdit());
			}
			
			cost.add(Double.valueOf(total_cost));
			
		}
		
		return updateLedger(cost);
	}

	public void makeNewSale() {
		this.sale = new Sale();
	}
}
