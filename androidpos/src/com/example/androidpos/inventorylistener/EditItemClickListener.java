package com.example.androidpos.inventorylistener;

import com.example.androidpos.date.DateStrategy;
import com.example.androidpos.inventory.Item;
import com.example.androidpos.inventory.Stock;
import com.example.androidpos.inventoryui.EditItemActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemClickListener implements OnClickListener {

	private String _id;
	private String name;

	private String lastEdit;

	private EditText edit_cost;
	private EditText edit_quantity;

	private EditItemActivity eia;

	public EditItemClickListener(EditItemActivity eia, String _id, String name,
			EditText edit_cost, EditText edit_quantity, String lastEdit) {
		this.eia = eia;
		this._id = _id;
		this.name = name;
		this.edit_cost = edit_cost;
		this.edit_quantity = edit_quantity;
		this.lastEdit = lastEdit;
	}

	@Override
	public void onClick(View arg0) {
		final AlertDialog.Builder adb = new AlertDialog.Builder(eia);
		adb.setTitle("Edit Item in Stock");
		adb.setMessage("Confirm to Edit.");
		adb.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
			private String cost;
			private String quantity;

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Stock stock = Stock.getInstance();

				this.cost = edit_cost.getText().toString();
				this.quantity = edit_quantity.getText().toString();

				String[] data = new String[5];
				data[0] = _id;
				data[1] = name;
				data[2] = cost;
				data[3] = quantity;
				data[4] = DateStrategy.getInstance().getDate();

				Item item = new Item(data);

				if (stock.editItem(item, lastEdit)) {
					Toast.makeText(eia, "Edit Success", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
		adb.setNegativeButton("Cancel", null);
		adb.show();
	}

}
