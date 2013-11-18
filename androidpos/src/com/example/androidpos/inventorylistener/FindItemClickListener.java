package com.example.androidpos.inventorylistener;

import com.example.androidpos.inventory.Product;
import com.example.androidpos.inventory.ProductCatalog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class FindItemClickListener implements OnClickListener {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_price;
	private EditText edit_tag;
	
	public FindItemClickListener(EditText edit_id, EditText edit_name,EditText edit_price,EditText edit_tag) {
		this.edit_id = edit_id;
		this.edit_name = edit_name;
		this.edit_price = edit_price;
		this.edit_tag = edit_tag;
	}

	@Override
	public void onClick(View v) {
		String _id = edit_id.getText().toString();
		
		ProductCatalog pc = ProductCatalog.getInstance();
		
		if ( pc.isProductExsit(_id) ) {
			Product product = pc.getProduct(_id);
			edit_name.setText( product.getName() );
			edit_id.setText(product.getId());
			edit_price.setText(product.getPrice()+"");
			edit_tag.setText(product.getTag());
			
			edit_price.setEnabled(false);
			edit_tag.setEnabled(false);
		}
		
	}

}
