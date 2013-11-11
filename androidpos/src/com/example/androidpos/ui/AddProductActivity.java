package com.example.androidpos.ui;

import com.example.androidpos.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends Activity {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_price;
	private EditText edit_tag;
	private Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);

		initComponent();

	}

	private void initComponent() {
		edit_id = (EditText) findViewById(R.id.edit_id);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_price = (EditText) findViewById(R.id.edit_price);
		edit_tag = (EditText) findViewById(R.id.edit_tag);
		addButton = (Button) findViewById(R.id.addButton);
		
		AddProductClickListener apcl = new AddProductClickListener(this,edit_id,edit_name,edit_price,edit_tag);
		
		addButton.setOnClickListener(apcl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}
