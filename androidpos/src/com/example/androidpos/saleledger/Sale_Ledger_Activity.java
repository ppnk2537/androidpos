package com.example.androidpos.saleledger;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Sale_Ledger_Activity extends Activity{
	
	private List<HashMap<String,String>> listmap;
	private ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_ledger);
		listview = (ListView) findViewById(R.id.itemlist);
	}

	private void updateListView() {
		//listmap = pc.getAllProduct();
//		simAdapter = new SimpleAdapter(this, listmap, R.layout.activity_column_product_catalog, new String [] { "id", "name",
//				"price","lastedit" },new int [] {R.id.colId, R.id.colName, R.id.colPrice, R.id.colLastEdit});
//		listview.setAdapter(simAdapter);
		
	}

}
