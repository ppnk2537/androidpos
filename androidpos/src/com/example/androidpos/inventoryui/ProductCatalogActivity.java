package com.example.androidpos.inventoryui;

import java.util.HashMap;
import java.util.List;

import com.example.androidpos.R;
import com.example.androidpos.inventory.ProductCatalog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ProductCatalogActivity extends Activity{

	private ProductCatalog pc;
	
	private Button addButton;
	private List<HashMap<String,String>> listmap;
	private ListView listview;
	private SimpleAdapter simAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_catalog);
		
		listview = (ListView) findViewById(R.id.itemlist);
		
		listview.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final int position = arg2;
				final String _id = listmap.get(position).get("id");
				Intent editProduct = new Intent(ProductCatalogActivity.this,EditProductActivity.class);
				editProduct.putExtra("id", _id);
				startActivity(editProduct);
			}
		});
		
		pc = ProductCatalog.getInstance();
		
		addButton = (Button) findViewById(R.id.addButton);
		
		addButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent addProduct = new Intent(ProductCatalogActivity.this,AddProductActivity.class);
				startActivity(addProduct);
			}
		});
		
		updateListView();
	}

	private void updateListView() {
		listmap = pc.getAllProduct();
		simAdapter = new SimpleAdapter(this, listmap, R.layout.activity_column_product_catalog, new String [] { "id", "name",
				"price","lastedit" },new int [] {R.id.colId, R.id.colName, R.id.colPrice, R.id.colLastEdit});
		listview.setAdapter(simAdapter);
	}

	@Override
	protected void onResume() {
		updateListView();
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
