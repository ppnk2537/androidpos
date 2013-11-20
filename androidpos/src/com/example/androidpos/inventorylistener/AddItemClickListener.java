package com.example.androidpos.inventorylistener;

import com.example.androidpos.date.DateStrategy;
import com.example.androidpos.inventory.Item;
import com.example.androidpos.inventory.Product;
import com.example.androidpos.inventory.ProductCatalog;
import com.example.androidpos.inventory.Stock;
import com.example.androidpos.inventoryui.AddItemActivity;
import com.example.androidpos.inventoryui.InventoryActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemClickListener implements OnClickListener {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_cost;
	private EditText edit_price;
	private EditText edit_quantity;
	private EditText edit_tag;

	private AddItemActivity aia;

	public AddItemClickListener(AddItemActivity aia, EditText edit_id,
			EditText edit_name, EditText edit_cost, EditText edit_quantity,
			EditText edit_price, EditText edit_tag) {
		this.aia = aia;
		this.edit_id = edit_id;
		this.edit_name = edit_name;
		this.edit_cost = edit_cost;
		this.edit_price = edit_price;
		this.edit_quantity = edit_quantity;
		this.edit_tag = edit_tag;
	}

	@Override
	public void onClick(View v) {
		final AlertDialog.Builder adb = new AlertDialog.Builder(aia);
		adb.setTitle("Add Item to Stock");
		adb.setMessage("Confirm to add Item to stock.");
		adb.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

			private String _id;
			private String name;
			private String cost;
			private String quantity;
			private String price;
			private String tag;

			@Override
			public void onClick(DialogInterface dialog, int which) {

				Stock stock = Stock.getInstance();
				ProductCatalog pc = ProductCatalog.getInstance();

				this._id = edit_id.getText().toString();
				this.name = edit_name.getText().toString();
				this.cost = edit_cost.getText().toString();
				this.quantity = edit_quantity.getText().toString();
				this.price = edit_price.getText().toString();
				this.tag = edit_tag.getText().toString();

				String[] data = new String[5];
				data[0] = _id;
				data[1] = name;
				data[2] = cost;
				data[3] = quantity;
				data[4] = DateStrategy.getInstance().getDate();

				Item item = new Item(data);

				if (pc.isProductExsit(_id)) {
					if (stock.addItem(item)) {
						Intent inventory = new Intent(aia,
								InventoryActivity.class);
						aia.startActivity(inventory);
					} else
						Toast.makeText(aia, "Add Failed", Toast.LENGTH_LONG)
								.show();
				} else {
					String[] pdata = new String[5];
					pdata[0] = _id;
					pdata[1] = name;
					pdata[2] = price;
					pdata[3] = tag;
					pdata[4] = DateStrategy.getInstance().getDate();

					Product product = new Product(pdata);

					if (pc.addProduct(product) && stock.addItem(item)) {
						Toast.makeText(aia, "Add Success", Toast.LENGTH_LONG)
								.show();
					} else
						Toast.makeText(aia, "Add Failed", Toast.LENGTH_LONG)
								.show();
				}
				aia.onBackPressed();
			}
		});
		adb.setNegativeButton("Cancel", null);
		adb.show();
	}

}
