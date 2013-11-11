package com.example.androidpos.ui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.domian.ProductCatalog;
import com.example.androidpos.domian.SQLiteDatabaseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ProductCatalogActivity extends Activity{
	
	private List<HashMap<String,String>> listmap;
	private ListView listview;
	private SimpleAdapter simAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_catalog);

		ProductCatalog.initInstance( new SQLiteDatabaseHandler(this) );
		
		listview = (ListView) findViewById(android.R.id.list);
		
		updateListView();
	}

	private void updateListView() {
		
		simAdapter = new SimpleAdapter(this, listmap,
				R.layout.activity_column_product_catalog, new String[] { "id", "name",
				"price","last_edit"},
				new int[] {R.id.colId, R.id.colName, R.id.colPrice, R.id.colLastEdit});
		listview.setAdapter(simAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
