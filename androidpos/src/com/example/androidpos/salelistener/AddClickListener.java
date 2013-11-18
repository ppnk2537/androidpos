package com.example.androidpos.salelistener;

import com.example.androidpos.sale.SaleHandler;
import com.example.androidpos.saleui.SaleActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddClickListener implements OnClickListener {

	private SaleActivity sa;
	private EditText input;
	private SaleHandler sh;
	private TextView total;

	public AddClickListener(SaleActivity sa, EditText input, SaleHandler sh, TextView total) {
		this.sa = sa;
		this.input = input;
		this.sh = sh;
		this.total = total;
	}

	@Override
	public void onClick(View arg0) {
		final String _id = input.getText().toString();
		final AlertDialog.Builder adb = new AlertDialog.Builder(sa);
		adb.setTitle("Add item to sale");
		adb.setMessage("How many?");
		final EditText input_quantity = new EditText(sa);
		input_quantity.setRawInputType(Configuration.KEYBOARD_12KEY);
		adb.setView(input_quantity);
		adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String quantity = input_quantity.getText().toString();
				if ( quantity.equals("") )
					quantity = "1";
				if ( sh.addSaleLineItem(_id, quantity) )
					Toast.makeText(sa, "Add Success", Toast.LENGTH_LONG).show();
				sa.updateListView();
				total.setText(Double.toString(sh.getSale().getTotal()));
			}

		});
		adb.setNegativeButton("No", null);
		adb.show();

	}

}
