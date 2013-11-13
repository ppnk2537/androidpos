package com.example.androidpos.ui;

import com.example.androidpos.domian.Item;
import com.example.androidpos.domian.ProductCatalog;
import com.example.androidpos.domian.Stock;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemClickListener implements OnClickListener {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_cost;
	private EditText edit_quantity;
	private String _id;
	private String name;
	private String cost;
	private String quantity;
	
	private AddItemActivity aia;
	
	public AddItemClickListener( AddItemActivity aia, EditText edit_id, EditText edit_name,
			EditText edit_cost, EditText edit_quantity) {
		this.aia = aia;
		this.edit_id = edit_id;
		this.edit_name = edit_name;
		this.edit_cost = edit_cost;
		this.edit_quantity = edit_quantity;
	}

	@Override
	public void onClick(View v) {
		Stock stock = Stock.getInstance();
		ProductCatalog pc = ProductCatalog.getInstance();

		this._id = edit_id.getText().toString();
		this.name = edit_name.getText().toString();
		this.cost = edit_cost.getText().toString();
		this.quantity = edit_quantity.getText().toString();
		
		String [] data = new String[5];
		data[0] = _id;
		data[1] = name;
		data[2] = cost;
		data[3] = quantity;
		data[4] = ""+System.currentTimeMillis();
		
		Item item = new Item(data);
		
		//if ( pc.isProductExsit(_id) )
			if ( stock.addProduct(item) ) {
				Intent inventory = new Intent(aia,InventoryActivity.class);
				aia.startActivity(inventory);	
			}
			else 
				Toast.makeText(aia, "Add Failed", Toast.LENGTH_LONG).show();
		}
	
}

