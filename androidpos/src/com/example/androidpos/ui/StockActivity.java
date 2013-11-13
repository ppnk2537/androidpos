package com.example.androidpos.ui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class StockActivity extends Activity{
	
	private List<HashMap<String,String>> listmap;
	private ListView listview;
	private SimpleAdapter simAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock);
		listview = (ListView) findViewById(R.id.itemlist);
	}
	
	private void updateListView() {

		simAdapter = new SimpleAdapter(this, listmap,
				R.layout.activity_column_stock, new String[] { "id", "name",
				"cost","quantity"},
				new int[] {R.id.colId, R.id.colName, R.id.colCost, R.id.colQuantity});
		listview.setAdapter(simAdapter);
	}
}
