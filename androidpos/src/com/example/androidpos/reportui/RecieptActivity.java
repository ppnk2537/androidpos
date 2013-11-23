package com.example.androidpos.reportui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.R.layout;
import com.example.androidpos.R.menu;
import com.example.androidpos.dao.DatabaseHandler;
import com.example.androidpos.report.Ledger;
import com.example.androidpos.report.SaleLedger;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class RecieptActivity extends Activity {

	private TextView TP;
	private TextView cash;
	private TextView change;
	private List<HashMap<String,String>> listmap;
	private ListView listview;
	private SimpleAdapter simAdapter;
	private SaleLedger sl;
	private Ledger l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reciept);
		sl = SaleLedger.getInstance();
		listview = (ListView) findViewById(R.id.itemlist);

		l = sl.getLedger(getIntent().getExtras().getString("_id"));
		
		updateListView();
		
		TP = (TextView) findViewById(R.id.TP);
		cash = (TextView) findViewById(R.id.cash);
		change = (TextView) findViewById(R.id.change);
		
		TP.setText(Double.toString(l.getTotalPrice()));
		cash.setText(l.getCash());
		change.setText( Double.toString((Double.valueOf( l.getCash() ) - l.getTotalPrice())) );
		
	}

	public void updateListView() {
		listmap = l.getDetail();
		
		simAdapter = new SimpleAdapter(this, listmap, R.layout.activity_column_reciept, new String[]{"quantity", "name", "price"}, 
				new int[] {R.id.colQuan, R.id.colName, R.id.colPrice});
		
		listview.setAdapter(simAdapter);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
