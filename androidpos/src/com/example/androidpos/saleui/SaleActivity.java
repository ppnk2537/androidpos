package com.example.androidpos.saleui;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.androidpos.R;
import com.example.androidpos.inventorylistener.ScanProductClickListener;
import com.example.androidpos.sale.SaleHandler;
import com.example.androidpos.salelistener.AddClickListener;
import com.example.androidpos.salelistener.PaymentClickListener;

public class SaleActivity extends Activity {

	private Button paymentButton;
	private Button clearButton;
	private Button addButton;
	private Button scanButton;
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
	}

	private void initComponent() {

		sh = new SaleHandler();
		
		input = (EditText) findViewById(R.id.input);
		total = (TextView) findViewById(R.id.total);
		
		paymentButton = (Button) findViewById(R.id.paymentButton);
		paymentButton.setOnClickListener( new PaymentClickListener(this, sh, total));
		
		scanButton = (Button) findViewById(R.id.scanButton);
		scanButton.setOnClickListener( new ScanProductClickListener(this));
		
		clearButton = (Button) findViewById(R.id.clearButton);
		clearButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				sh.getSale().clear();
				total.setText("0.00");
				updateListView();
			}
		});
		
		addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener( new AddClickListener(this, input, sh, total));
		
		updateListView();
	}
	
	public void setPaymentDisable() { this.paymentButton.setEnabled(false); }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0)
		{
			if (resultCode == RESULT_OK) 
			{
				String contents = intent.getStringExtra("SCAN_RESULT");            
				input.setText(contents);
			}
		}
	}

}

