package com.example.androidpos.ui;

import com.example.androidpos.R;
import com.example.androidpos.domian.Product;
import com.example.androidpos.domian.ProductCatalog;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class EditProductActivity extends Activity {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_price;
	private EditText edit_tag;
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
		edit_price = (EditText) findViewById(R.id.edit_price);
		edit_tag = (EditText) findViewById(R.id.edit_tag);
		
		String _id = getIntent().getExtras().getString("id");
		
		ProductCatalog pc = ProductCatalog.getInstance();
		Product product = pc.getProduct(_id);
		
		String name = product.getName();
		
		edit_id.setText( _id );
		edit_id.setEnabled(false);
		edit_name.setText(name);
		edit_name.setEnabled(false);
		
		Log.d("id", _id);
		Log.d("name", name);
		Log.d("price", Double.toString(product.getPrice()));
		Log.d("tag", product.getTag());
		Log.d("lastedit", product.getLastEdit());
		
		edit_price.setText(Double.toString(product.getPrice()));
		edit_tag.setText(product.getTag());
		
		doneButton = (Button) findViewById(R.id.doneButton);
		doneButton.setOnClickListener( new EditProductClickListener(this, _id, name, edit_price, edit_tag));
		removeButton = (Button) findViewById(R.id.removeButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
