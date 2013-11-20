package com.example.androidpos.salelistener;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidpos.sale.Sale;
import com.example.androidpos.sale.SaleHandler;
import com.example.androidpos.sale.SaleLineItem;
import com.example.androidpos.saleui.SaleActivity;

public class PaymentClickListener implements OnClickListener {

	private SaleActivity sa;
	private SaleHandler sh;
	private TextView total;
	private double cash;

	public PaymentClickListener(SaleActivity sa, SaleHandler sh, TextView total) {
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
		input_cash.setRawInputType(Configuration.KEYBOARD_12KEY);
		adb.setView(input_cash);
		adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				cash = Double.valueOf(input_cash.getText().toString());
				
				if (cash >= total) {
					if ( sh.updateStock() ) {
						sa.setPaymentDisable();
						showDialog();
					}
				} else
					Toast.makeText(sa, "Not Enough Money", Toast.LENGTH_LONG)
							.show();
			}

			private void showDialog() {
				final AlertDialog.Builder adb = new AlertDialog.Builder(sa);
				adb.setTitle("Transaction :");
				String s = "";

				Sale sale = sh.getSale();

				List<SaleLineItem> sli = sale.getItemList();

				double total = 0;
				
				for (SaleLineItem sl : sli) {
					s += sl.getId() + " " + sl.getName() + " " + sl.getPrice() + " " + sl.getQuanity() + " " + sl.getTotalPrice() + "\n";
					total += Double.valueOf(sl.getTotalPrice());
				}
				
				s += "Cash : " + cash + " Tax : " + (total * 0.07) + "\nTotal : " + total + " Change : " + (cash - total);

				adb.setMessage(s);
				adb.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								sh.makeNewSale();
								sa.updateListView();
							}
						});
				adb.show();
			}

		});
		adb.setNegativeButton("No", null);
		adb.show();
	}

}