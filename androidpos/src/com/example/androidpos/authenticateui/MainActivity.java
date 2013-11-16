package com.example.androidpos.authenticateui;

import com.example.androidpos.R;
import com.example.androidpos.dao.DatabaseHandler;
import com.example.androidpos.dao.SQLiteDatabaseHandler;
import com.example.androidpos.inventory.ProductCatalog;
import com.example.androidpos.inventory.Stock;
import com.example.androidpos.inventoryui.InventoryActivity;
import com.example.androidpos.report.SaleLedger;
import com.example.androidpos.reportui.SaleLedgerActivity;
import com.example.androidpos.saleui.SaleActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button inventoryBtn;
	private Button saleBtn;
	private Button reportBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHandler dbh = new SQLiteDatabaseHandler(this);
		ProductCatalog.initInstance(dbh);
		Stock.initInstance(dbh);
		SaleLedger.initInstance(dbh);
		
		initComponent();
		
		inventoryBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent inventory = new Intent(MainActivity.this,InventoryActivity.class);
				startActivity(inventory);				
			}
		});
		
		saleBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent sale = new Intent(MainActivity.this,SaleActivity.class);
				startActivity(sale);				
			}
		});
		
		reportBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent report = new Intent(MainActivity.this,SaleLedgerActivity.class);
				startActivity(report);				
			}
		});

	}

	private void initComponent() {
		inventoryBtn = (Button)findViewById(R.id.inventory_button);
		saleBtn = (Button)findViewById(R.id.sale_button);
		reportBtn = (Button)findViewById(R.id.report_button);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
