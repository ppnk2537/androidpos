package com.example.androidpos.reportui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.report.SaleLedger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SaleLedgerActivity extends Activity {

	private SaleLedger sl;
	private List<HashMap<String, String>> listmap;
	private ListView listview;
	private SimpleAdapter simAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_ledger);
		listview = (ListView) findViewById(R.id.itemlist);

		sl = SaleLedger.getInstance();

		updateListView();
	}

	private void updateListView() {
		listmap = sl.getAllLedger();
		simAdapter = new SimpleAdapter(this, listmap,
				R.layout.activity_column_sale_ledger, new String[] { "id",
						"profit", "lastedit" }, new int[] { R.id.colId,
						R.id.colProfit, R.id.colDate });
		listview.setAdapter(simAdapter);
	}

}
