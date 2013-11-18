package com.example.androidpos.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	
	public String getDate() {
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss dd.MM.yy");
		return String.format("" + sdf.format(date));
	}
}
