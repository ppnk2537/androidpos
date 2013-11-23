package com.example.androidpos.reportui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.inventoryui.EditProductActivity;
import com.example.androidpos.inventoryui.ProductCatalogActivity;
import com.example.androidpos.report.SaleLedger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		
		listview.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final int position = arg2;
				final String _id = listmap.get(position).get("id");
				Intent ra = new Intent(SaleLedgerActivity.this,RecieptActivity.class);
				ra.putExtra("_id", _id);
				startActivity(ra);
				
			}
		});
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

}
