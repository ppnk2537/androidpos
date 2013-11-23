package com.example.androidpos.reportui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.report.SaleLedger;
import com.example.androidpos.reportlistener.SearchClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class SaleLedgerActivity extends Activity {

	private SaleLedger sl;
	private List<HashMap<String, String>> listmap;
	private ListView listview;
	private SimpleAdapter simAdapter;
	private Spinner date;
	private Spinner month;
	private Spinner year;
	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_ledger);
		listview = (ListView) findViewById(R.id.itemlist);
		
		sl = SaleLedger.getInstance();

		updateListView();
		
		initComponent();
	}

	private void initComponent() {
		date = (Spinner) findViewById(R.id.date_spinner);
		month = (Spinner) findViewById(R.id.mon_spinner);
		year = (Spinner) findViewById(R.id.year_spinner);
		searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener( new SearchClickListener(this,date,month,year,listmap) );
	}

	private void updateListView() {
		listmap = sl.getAllLedger();
		simAdapter = new SimpleAdapter(this, listmap,
				R.layout.activity_column_sale_ledger, new String[] { "id",
						"profit", "lastedit" }, new int[] { R.id.colId,
						R.id.colProfit, R.id.colDate });
		listview.setAdapter(simAdapter);
	}
	
	public void updateListView(List<HashMap<String, String>> listmap) {
		simAdapter = new SimpleAdapter(this, listmap,
				R.layout.activity_column_sale_ledger, new String[] { "id",
						"profit", "lastedit" }, new int[] { R.id.colId,
						R.id.colProfit, R.id.colDate });
		listview.setAdapter(simAdapter);
	}
	
	@Override
	public void onBackPressed() {
		updateListView();
		super.onBackPressed();
	}

}
