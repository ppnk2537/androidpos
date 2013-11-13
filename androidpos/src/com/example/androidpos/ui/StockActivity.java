package com.example.androidpos.ui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.domian.Stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class StockActivity extends Activity{
	
	private List<HashMap<String,String>> listmap;
	private ListView listview;
	private SimpleAdapter simAdapter;
	private Button addButton;
	private Stock stock;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock);
		listview = (ListView) findViewById(R.id.itemlist);
		
		stock = Stock.getInstance();
		
		addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inventory = new Intent(StockActivity.this,AddItemActivity.class);
				startActivity(inventory);
			}
		});
		
		updateListView();
	}
	
	private void updateListView() {
		listmap = stock.getAllItem();
		simAdapter = new SimpleAdapter(this, listmap,
				R.layout.activity_column_stock, new String[] { "id", "name",
				"cost","quantity"},
				new int[] {R.id.colId, R.id.colName, R.id.colCost, R.id.colQuantity});
		listview.setAdapter(simAdapter);
	}
}
