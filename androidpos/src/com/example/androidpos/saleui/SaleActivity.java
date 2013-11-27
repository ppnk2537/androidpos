package com.example.androidpos.saleui;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.androidpos.R;
import com.example.androidpos.inventorylistener.ScanProductClickListener;
import com.example.androidpos.sale.SaleHandler;
import com.example.androidpos.sale.SaleLineItem;
import com.example.androidpos.salelistener.AddClickListener;
import com.example.androidpos.salelistener.PaymentClickListener;

public class SaleActivity extends Activity {

	private Button paymentButton;
	private Button clearButton;
	private Button addButton;
	private ImageButton scanButton;
	private EditText input;
	private TextView total;

	private List<HashMap<String, String>> listmap;
	private ListView list_item;
	private SimpleAdapter simAdapter;
	private SaleHandler sh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);

		list_item = (ListView) findViewById(R.id.itemlist);
		
		list_item.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final int position = arg2;
				final String _id = listmap.get(position).get("id");
				final String quantity = listmap.get(position).get("quantity");
				
				final AlertDialog.Builder adb = new AlertDialog.Builder(SaleActivity.this);
				adb.setTitle("Change price : ");
				adb.setMessage("Enter new price");
				final EditText input_price = new EditText(SaleActivity.this);
				input_price.setRawInputType(Configuration.KEYBOARD_12KEY);
				adb.setView(input_price);
				adb.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String price = input_price.getText().toString();
						sh.getSale().getSaleLineItem(_id, quantity).setPrice(price);
						updateListView();
					}
				});
				adb.setNegativeButton("Cancel", null);
				adb.show();
			}
			
		});
		
		list_item.setOnItemLongClickListener( new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				final int position = arg2;
				final String _id = listmap.get(position).get("id");
				final String quantity = listmap.get(position).get("quantity");
				
				final AlertDialog.Builder adb = new AlertDialog.Builder(SaleActivity.this);
				adb.setTitle("Delete this item : ");
				adb.setMessage("Confirm to delete");
				adb.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SaleLineItem sli = sh.getSale().getSaleLineItem(_id, quantity);
						sh.getSale().removeSaleLineItem(sli);
						updateListView();
					}
				});
				adb.setNegativeButton("Cancel", null);
				adb.show();
				return false;
			}
			
		});

		initComponent();

		updateListView();
	}

	public void updateListView() {

		listmap = sh.getSale().getAllItem();

		simAdapter = new SimpleAdapter(this, listmap,
				R.layout.activity_comlumn_sale, new String[] { "id", "name",
						"quantity", "price", "total" }, new int[] { R.id.ColID,
						R.id.ColName, R.id.ColQuantity, R.id.ColPrice,
						R.id.ColTotal });
		list_item.setAdapter(simAdapter);
		total.setText(Double.toString(sh.getSale().getTotal()));
	}

	private void initComponent() {

		sh = new SaleHandler();

		input = (EditText) findViewById(R.id.einput);
		total = (TextView) findViewById(R.id.total);

		paymentButton = (Button) findViewById(R.id.paymentButton);
		paymentButton.setOnClickListener(new PaymentClickListener(this, sh,
				total));

		scanButton = (ImageButton) findViewById(R.id.scanButton);
		scanButton.setOnClickListener(new ScanProductClickListener(this));

		clearButton = (Button) findViewById(R.id.clearButton);
		clearButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				sh.getSale().clear();
				total.setText("0.00");
				updateListView();
			}
		});

		addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener(new AddClickListener(this, input, sh,
				total));

		updateListView();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				input.setText(contents);
			}
		}
	}

	public void setPaymentEnabled(boolean bool) {
		this.paymentButton.setEnabled(bool);
	}

	@Override
	public void onBackPressed() {
		updateListView();
		super.onBackPressed();
	}
}
