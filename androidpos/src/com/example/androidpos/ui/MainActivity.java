package com.example.androidpos.ui;

import com.example.androidpos.R;
import com.example.androidpos.R.id;
import com.example.androidpos.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button inventoryBtn;
	private Button saleBtn;
	private Button reportBtn;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
				Intent inventory = new Intent(MainActivity.this,SaleActivity.class);
				startActivity(inventory);				
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
