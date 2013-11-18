package com.example.androidpos.inventorylistener;

import com.example.androidpos.inventory.ProductCatalog;
import com.example.androidpos.inventoryui.EditProductActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
		final AlertDialog.Builder adb = new AlertDialog.Builder(epa);
		adb.setTitle("Remove Item in Product Catalog");
		adb.setMessage("Confirm to Remove Item in Product Catalog.");
		adb.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				ProductCatalog pc = ProductCatalog.getInstance();

				if (pc.removeProduct(_id)) {
					Toast.makeText(epa, "Remove Success", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
		adb.setNegativeButton("Cancel", null);
		adb.show();

	}

}
