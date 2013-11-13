package com.example.androidpos.inventorylistener;

import com.example.androidpos.inventory.Product;
import com.example.androidpos.inventory.ProductCatalog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class FindItemClickListener implements OnClickListener {

	private EditText edit_id;
	private EditText edit_name;
	
	public FindItemClickListener(EditText edit_id, EditText edit_name) {
		this.edit_id = edit_id;
		this.edit_name = edit_name;
	}

	@Override
	public void onClick(View v) {
		String _id = edit_id.getText().toString();
		
		ProductCatalog pc = ProductCatalog.getInstance();
		
		if ( pc.isProductExsit(_id) ) {
			Product product = pc.getProduct(_id);
			edit_name.setText( product.getName() );
		}
	}

}
