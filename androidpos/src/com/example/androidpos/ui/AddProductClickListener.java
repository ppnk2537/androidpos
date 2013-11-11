package com.example.androidpos.ui;

import com.example.androidpos.domian.Product;
import com.example.androidpos.domian.ProductCatalog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductClickListener implements OnClickListener {

	private String _id;
	private String name;
	private String price;
	private String tag;
	
	private AddProductActivity apa;
	
	public AddProductClickListener( AddProductActivity apa, EditText edit_id, EditText edit_name,
			EditText edit_price, EditText edit_tag) {
		this.apa = apa;
		this._id = edit_id.getText().toString();
		this.name = edit_name.getText().toString();
		this.price = edit_price.getText().toString();
		this.tag = edit_tag.getText().toString();
	}

	@Override
	public void onClick(View v) {
		ProductCatalog pc = ProductCatalog.getInstance();
		
		String [] data = new String[4];
		data[0] = _id;
		data[1] = name;
		data[2] = tag;
		data[3] = price;
		data[4] = ""+System.currentTimeMillis();
				
		Product product = new Product(data);
		
		if ( pc.addProduct(product) )
			Toast.makeText(apa, "Add Success", Toast.LENGTH_LONG).show();
		
	}

}
