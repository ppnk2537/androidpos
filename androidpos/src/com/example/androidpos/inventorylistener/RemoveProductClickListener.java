package com.example.androidpos.inventorylistener;

import com.example.androidpos.inventory.ProductCatalog;
import com.example.androidpos.inventoryui.EditProductActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

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
			Toast.makeText(epa, "Remove Success", Toast.LENGTH_LONG).show();	
		}
			
	}
	
}
