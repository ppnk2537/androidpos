package com.example.androidpos.ui;

import com.example.androidpos.domian.ProductCatalog;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class RemoveProductClickListener implements OnClickListener {

	private EditProductActivity epa;
	private String _id;
	
	public RemoveProductClickListener(EditProductActivity epa, String _id) {
		this.epa = epa;
		this._id = _id;
	}

	@Override
	public void onClick(View arg0) {
		ProductCatalog pc  = ProductCatalog.getInstance();
		
		if ( pc.removeProduct(_id) ) {
			Intent inventory = new Intent(epa,InventoryActivity.class);
			epa.startActivity(inventory);	
		}
			
	}
	
}
