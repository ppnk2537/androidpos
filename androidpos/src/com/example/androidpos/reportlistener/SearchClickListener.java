package com.example.androidpos.reportlistener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.androidpos.date.DateStrategy;
import com.example.androidpos.reportui.SaleLedgerActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;

public class SearchClickListener implements OnClickListener {

	private SaleLedgerActivity sla;
	private Spinner date;
	private Spinner month;
	private Spinner year;
	private List<HashMap<String, String>> listmap;
	
	public SearchClickListener(SaleLedgerActivity sla,
			Spinner date, Spinner month, Spinner year,
			List<HashMap<String, String>> listmap) {
		this.sla = sla;
		this.date = date;
		this.month = month;
		this.year = year;
		this.listmap = listmap;
	}

	@Override
	public void onClick(View v) {
		String d = date.getSelectedItem().toString();
		String m = month.getSelectedItem().toString();
		String y = year.getSelectedItem().toString();
		
		String checkDate = d + "." + m + "." + y;
		
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		if ( d.equals("None") ) {
			for ( HashMap<String, String> item : listmap ) {
				if ( DateStrategy.isMon(checkDate, item.get("lastedit")) )
						list.add(item);
			}
		}
		else {
			for ( HashMap<String, String> item : listmap ) {
				if ( DateStrategy.isDate(checkDate, item.get("lastedit")) )
						list.add(item);
			}
		}
		
		sla.updateListView(list);
	}

	
}
