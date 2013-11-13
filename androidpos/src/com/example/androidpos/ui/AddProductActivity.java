package com.example.androidpos.ui;

import com.example.androidpos.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

/*
 * Add product to Product catalog
 * @author Undone team
 */

public class AddProductActivity extends Activity {

	/**Text to get id*/
	private EditText edit_id;
	/**Text to get name*/
	private EditText edit_name;
	/**Text to get price*/
	private EditText edit_price;
	/**Text to get tag*/
	private EditText edit_tag;
	/**The add button*/
	private Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);

		initComponent();

	}

	/*
	 * Initialize components and add listener.
	 */
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
