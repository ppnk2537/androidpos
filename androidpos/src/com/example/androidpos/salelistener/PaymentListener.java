package com.example.androidpos.salelistener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidpos.sale.SaleHandler;
import com.example.androidpos.saleui.SaleActivity;

public class PaymentListener implements OnClickListener {

	private SaleActivity sa;
	private SaleHandler sh;
	private TextView total;

	public PaymentListener(SaleActivity sa, SaleHandler sh, TextView total) {
		this.sa = sa;
		this.sh = sh;
		this.total = total;
	}

	@Override
	public void onClick(View arg0) {
		final double total = Double.valueOf(this.total.getText().toString());
		final AlertDialog.Builder adb = new AlertDialog.Builder(sa);
		adb.setTitle("Your charge is " + total);
		adb.setMessage("How many?");
		final EditText input_cash = new EditText(sa);
		adb.setView(input_cash);
		adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				double cash = Double.valueOf(input_cash.getText().toString());
				if ( cash >= total ) {
					Toast.makeText(sa, "Your Change is: " + (cash - total), Toast.LENGTH_LONG).show();
					if ( sh.updateStock() )
						sa.setPaymentDisable();
				}
				else
					Toast.makeText(sa, "Not Enough Money", Toast.LENGTH_LONG).show();
			}

		});
		adb.setNegativeButton("No", null);
		adb.show();
	}

}