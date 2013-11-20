package com.example.androidpos.reportui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.inventoryui.EditProductActivity;
import com.example.androidpos.inventoryui.ProductCatalogActivity;
import com.example.androidpos.report.SaleLedger;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SaleLedgerActivity extends Activity {

	private SaleLedger sl;
	private List<HashMap<String, String>> listmap;
	private ListView listview;
	private SimpleAdapter simAdapter;
	private Button showGraph;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_ledger);
		listview = (ListView) findViewById(R.id.itemlist);

		sl = SaleLedger.getInstance();
		showGraph = (Button) findViewById(R.id.button1);

		showGraph.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 lineGraphHandler(v);
			}
		});

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

	@Override
	public void onBackPressed() {
		updateListView();
		super.onBackPressed();
	}

	public void lineGraphHandler(View view) {
		GraphActivity line = new GraphActivity();
		Intent lineIntent = line.getIntent(this);
		startActivity(lineIntent);
	}

}
