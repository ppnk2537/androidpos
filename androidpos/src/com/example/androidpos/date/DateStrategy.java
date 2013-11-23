package com.example.androidpos.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class DateStrategy {

	private static DateStrategy instance = null;
	
	private Calendar calendar;
	
	public DateStrategy() {
		calendar = Calendar.getInstance();
	}
	
	/**
	 * Get instance of inventory.
	 * @return instance.
	 */
	public static DateStrategy getInstance() {
		if ( instance == null ) instance = new DateStrategy();
		return instance;
	}
	
	public static boolean isDate(String checkDate, String currentDate) {
		/** Prepare current date for comparing. */
		String [] c = currentDate.split(" ");
		String date = c[1];
		Log.d("date",date);
		Log.d("cdate",checkDate);
		return checkDate.equals(date);
	}
	
	public String getDate() {
		calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss dd.MMM.yyyy");
		return String.format("" + sdf.format(date));
	}
}
