package com.example.androidpos.inventoryui;

import com.example.androidpos.R;
import com.example.androidpos.inventory.Item;
import com.example.androidpos.inventory.Stock;
import com.example.androidpos.inventorylistener.EditItemClickListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends Activity {

	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_cost;
	private EditText edit_quantity;
	private Button doneButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		initComponent();
	}

	private void initComponent() {
		edit_id = (EditText) findViewById(R.id.edit_id);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_cost = (EditText) findViewById(R.id.edit_cost);
		edit_quantity = (EditText) findViewById(R.id.edit_quantity);
		
		String _id = getIntent().getExtras().getString("id");
		String lastEdit = getIntent().getExtras().getString("lastEdit");
		
		Stock stock = Stock.getInstance();
		Item item = stock.getItem(_id,lastEdit);
		
		String name = item.getName();
		
		edit_id.setText( _id );
		edit_id.setEnabled(false);
		edit_name.setText(name);
		edit_name.setEnabled(false);

		edit_cost.setText(Double.toString(item.getCost()));
		edit_quantity.setText(Integer.toString(item.getQuantity()));
		
		doneButton = (Button) findViewById(R.id.doneButton);
		doneButton.setOnClickListener( new EditItemClickListener(this, _id, name, edit_cost, edit_quantity, lastEdit));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
