package com.example.androidpos.inventoryui;

import com.example.androidpos.R;
import com.example.androidpos.inventorylistener.AddItemClickListener;
import com.example.androidpos.inventorylistener.FindItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends Activity {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_cost;
	private EditText edit_price;
	private EditText edit_tag;
	private EditText edit_quantity;
	
	private Button addButton;
	private Button findButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);

		initComponent();

	}

	private void initComponent() {
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
		findButton.setOnClickListener(new FindItemClickListener(this, edit_id,
				edit_name,edit_price,edit_tag));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}
