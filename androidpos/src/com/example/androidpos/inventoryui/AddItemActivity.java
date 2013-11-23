package com.example.androidpos.inventoryui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.androidpos.R;
import com.example.androidpos.inventorylistener.AddItemClickListener;
import com.example.androidpos.inventorylistener.FindItemClickListener;
import com.example.androidpos.inventorylistener.ScanProductClickListener;

public class AddItemActivity extends Activity {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_cost;
	private EditText edit_price;
	private EditText edit_tag;
	private EditText edit_quantity;
	
	private Button addButton;
	private Button findButton;
	private ImageButton scanButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);

		initComponent();

	}

	private void initComponent() {
		scanButton = (ImageButton) findViewById(R.id.scanButton);
		edit_id = (EditText) findViewById(R.id.edit_id);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_cost = (EditText) findViewById(R.id.edit_cost);
		edit_quantity = (EditText) findViewById(R.id.edit_quantity);
		edit_price = (EditText) findViewById(R.id.edit_price);
		edit_tag= (EditText) findViewById(R.id.edit_tag);

		addButton = (Button) findViewById(R.id.addButton);

		addButton.setOnClickListener(new AddItemClickListener(this, edit_id,
				edit_name, edit_cost, edit_quantity,edit_price,edit_tag));
		
		findButton = (Button) findViewById(R.id.findButton);
		findButton.setOnClickListener(new FindItemClickListener(edit_id,
				edit_name,edit_price,edit_tag));
		ScanProductClickListener spcl = new ScanProductClickListener(this);
		scanButton.setOnClickListener(spcl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				edit_id.setText(contents);
			}
		}
	}

	
}
