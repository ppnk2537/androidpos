package com.example.androidpos.inventorylistener;

import com.example.androidpos.inventory.Product;
import com.example.androidpos.inventory.ProductCatalog;
import com.example.androidpos.inventoryui.AddProductActivity;
import com.example.androidpos.inventoryui.InventoryActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class AddProductClickListener implements OnClickListener {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_price;
	private EditText edit_tag;
	private String _id;
	private String name;
	private String price;
	private String tag;
	
	private AddProductActivity apa;
	
	public AddProductClickListener( AddProductActivity apa, EditText edit_id, EditText edit_name,
			EditText edit_price, EditText edit_tag) {
		this.apa = apa;
		this.edit_id = edit_id;
		this.edit_name = edit_name;
		this.edit_price = edit_price;
		this.edit_tag = edit_tag;
	}

	@Override
	public void onClick(View v) {
		ProductCatalog pc = ProductCatalog.getInstance();

		this._id = edit_id.getText().toString();
		this.name = edit_name.getText().toString();
		this.price = edit_price.getText().toString();
		this.tag = edit_tag.getText().toString();
		
		String [] data = new String[5];
		data[0] = _id;
		data[1] = name;
		data[2] = price;
		data[3] = tag;
		data[4] = ""+System.currentTimeMillis();
		
		Product product = new Product(data);
		
		if ( pc.addProduct(product) ) {
			Intent inventory = new Intent(apa,InventoryActivity.class);
			apa.startActivity(inventory);	
		}
		
	}

}
