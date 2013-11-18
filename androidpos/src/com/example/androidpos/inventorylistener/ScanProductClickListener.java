package com.example.androidpos.inventorylistener;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.androidpos.inventoryui.AddProductActivity;

public class ScanProductClickListener implements OnClickListener {

	private Activity ac;
	
	public ScanProductClickListener(Activity ac){
		this.ac = ac;
	}

	@Override
	public void onClick(View v) {
		try {
			
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "BAR_CODE_MODE");
			ac.startActivityForResult(intent, 0);
		} catch (Exception e) {
			Toast.makeText(ac.getBaseContext(),"Please Install Barcode Scanner",Toast.LENGTH_SHORT).show();
		}
	}
	
}
