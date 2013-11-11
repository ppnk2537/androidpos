package com.example.androidpos.ui;

import com.example.androidpos.R;
import com.example.androidpos.R.layout;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class InventoryActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabSpec tab1 = tabHost.newTabSpec("First");
		TabSpec tab2 = tabHost.newTabSpec("Second");


		tab1.setIndicator("Inventory List");
		Intent ProductCatAc = new Intent(this,ProductCatalogActivity.class);
		tab1.setContent( ProductCatAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		tab1.setContent( ProductCatAc );

		tab2.setIndicator("Add Item");
		Intent addItemAc =  new Intent(this,StockActivity.class);
		tab2.setContent( addItemAc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		tab2.setContent( addItemAc );


		tabHost.addTab( tab1 );
		tabHost.addTab( tab2 );
	}

}
