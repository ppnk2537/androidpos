package com.example.androidpos.reportui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SaleLedgerActivity extends Activity{
	
	private List<HashMap<String,String>> listmap;
	private ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_ledger);
		listview = (ListView) findViewById(R.id.itemlist);
	}

	private void updateListView() {
		
	}

}
