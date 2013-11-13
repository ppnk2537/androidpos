package com.example.androidpos.ui;

import com.example.androidpos.domian.Product;
import com.example.androidpos.domian.ProductCatalog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class EditProductClickListener implements OnClickListener {

	private EditText edit_price;
	private EditText edit_tag;

	private String _id;
	private String name;
	private String price;
	private String tag;
	
	private EditProductActivity epa;
	
	public EditProductClickListener( EditProductActivity epa, String _id, String name,
			EditText edit_price, EditText edit_tag) {
		this.epa = epa;
		this._id = _id;
		this.name = name;
		this.edit_price = edit_price;
		this.edit_tag = edit_tag;
	}
	
	@Override
	public void onClick(View v) {
		ProductCatalog pc = ProductCatalog.getInstance();

		this.price = edit_price.getText().toString();
		this.tag = edit_tag.getText().toString();
		
		String [] data = new String[5];
		data[0] = _id;
		data[1] = name;
		data[2] = price;
		data[3] = tag;
		data[4] = ""+System.currentTimeMillis();
				
		Product product = new Product(data);
		
		if ( pc.editProduct(product) )
			Toast.makeText(epa, "Edit Success", Toast.LENGTH_LONG).show();
	}

}
