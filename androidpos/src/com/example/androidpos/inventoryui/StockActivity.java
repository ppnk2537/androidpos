package com.example.androidpos.inventoryui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.inventory.Stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

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
		

		listview.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final int position = arg2;
				final String _id = listmap.get(position).get("id");
				final String lastEdit = listmap.get(position).get("lastEdit");
				Intent editItem = new Intent(StockActivity.this,EditItemActivity.class);
				editItem.putExtra("id", _id);
				editItem.putExtra("lastEdit", lastEdit);
				startActivity(editItem);
			}
		});
		
		updateListView();
	}
	
	private void updateListView() {
		listmap = stock.getAllItem();
		simAdapter = new SimpleAdapter(this, listmap,
				R.layout.activity_column_stock, new String[] { "id", "name",
				"cost","quantity","lastEdit"},
				new int[] {R.id.colId, R.id.colName, R.id.colCost, R.id.colQuantity, R.id.colLastEdit});
		listview.setAdapter(simAdapter);
	}
}
