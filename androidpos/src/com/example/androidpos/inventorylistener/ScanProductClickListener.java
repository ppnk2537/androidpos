package com.example.androidpos.inventorylistener;


import com.example.androidpos.inventoryui.AddProductActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ScanProductClickListener implements OnClickListener {

	private AddProductActivity apa;
	
	public ScanProductClickListener(AddProductActivity apa){
		this.apa = apa;
	}

	@Override
	public void onClick(View v) {
		try {
			
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "BAR_CODE_MODE");
			apa.startActivityForResult(intent, 0);
		} catch (Exception e) {
			Toast.makeText(apa.getBaseContext(),"Please Install Barcode Scanner",Toast.LENGTH_SHORT).show();
		}
	}
	
}
