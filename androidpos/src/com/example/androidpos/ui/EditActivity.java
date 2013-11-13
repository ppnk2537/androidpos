package com.example.androidpos.ui;

import com.example.androidpos.R;
import com.example.androidpos.R.layout;
import com.example.androidpos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_price;
	private EditText edit_tag;
	// private EditText edit_quantity;
	// private EditText edit_lastedit;
	private Button doneButton;
	private Button removeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		initComponent();
	}

	private void initComponent() {
		edit_id = (EditText) findViewById(R.id.edit_id);
		edit_name = (EditText) findViewById(R.id.edit_name);
		// edit_cost = (EditText) findViewById(R.id.edit_cost);
		// edit_quantity = (EditText) findViewById(R.id.edit_quantity);
		// edit_lastedit = (EditText) findViewById(R.id.edit_lastedit);
		edit_price = (EditText) findViewById(R.id.edit_price);
		edit_tag = (EditText) findViewById(R.id.edit_tag);
		doneButton = (Button) findViewById(R.id.doneButton);
		removeButton = (Button) findViewById(R.id.removeButton);
		// AddProductClickListener apcl = new AddProductClickListener(this,
		// edit_id, edit_name, edit_price, edit_tag);

		// editButton.setOnClickListener(apcl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
