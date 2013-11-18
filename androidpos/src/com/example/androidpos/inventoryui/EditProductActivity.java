package com.example.androidpos.inventoryui;

import com.example.androidpos.R;
import com.example.androidpos.inventory.Product;
import com.example.androidpos.inventory.ProductCatalog;
import com.example.androidpos.inventorylistener.EditProductClickListener;
import com.example.androidpos.inventorylistener.RemoveProductClickListener;

import android.os.Bundle;
import android.app.Activity;
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
		setContentView(R.layout.activity_edit_product);
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
		
		edit_price.setText(Double.toString(product.getPrice()));
		edit_tag.setText(product.getTag());
		
		doneButton = (Button) findViewById(R.id.doneButton);
		doneButton.setOnClickListener( new EditProductClickListener(this, _id, name, edit_price, edit_tag));
		
		removeButton = (Button) findViewById(R.id.removeButton);
		removeButton.setOnClickListener( new RemoveProductClickListener(this, _id) );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
