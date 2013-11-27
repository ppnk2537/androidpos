package com.example.androidpos.reportui;

import java.util.HashMap;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class GraphActivity {
	public Intent getIntent(Context context, List<HashMap<String, String>> listmap, String name) {
		
		int len = listmap.size() + 1;
		
		double [] x = new double[len];
		double [] y = new double[len];
		
		double [] xl = new double[32];
		double [] yl = new double[32];
		
		if ( !name.equals("Month") ) {
			xl = new double[len];
			yl = new double[len];
			for ( int i = 1 ; i < len ; i ++ ) {
				String [] tim = listmap.get(i-1).get("lastedit").split(" ");
				String [] s = tim[0].split(":");
				double hr = Double.valueOf(s[0]) + Double.valueOf(s[1])/100 + Double.valueOf(s[2])/10000;
				xl[i] = hr;
			}
			
			for ( int i = 1 ; i < len ; i ++ ) {
				double profit = Double.valueOf(listmap.get(i-1).get("profit"));
				yl[i] = profit;
			}
		}
		else {
			for ( int i = 1 ; i < len ; i ++ ) {
				String [] tim = listmap.get(i-1).get("lastedit").split(" ");
				String s = tim[1].substring(0,2);
				x[i] = Double.valueOf(s);
			}
			
			for ( int i = 1 ; i < len ; i ++ ) {
				double profit = Double.valueOf(listmap.get(i-1).get("profit"));
				y[i] = profit;
			}
			
			for ( int i = 1 ; i < len ; i ++ ) {
				yl[(int)x[i]] += y[i];
			}
			
			for ( int i = 0 ; i <= 31 ; i ++ ) {
				xl[i] = i;
			}
			
		}
		
		TimeSeries series = new TimeSeries("sale1");
		for (int i = 0; i < xl.length; i++) {
			series.add(xl[i], yl[i]);
		}

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);

		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.RED);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesTextSize(15);
		renderer.setLineWidth(3);
		// renderer.set

		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setChartTitle("Profit in the " + name);
		mRenderer.setChartTitleTextSize(25);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mRenderer.setMarginsColor(Color.BLACK);
		mRenderer.setAxisTitleTextSize(20);
		mRenderer.isDisplayValues();
		mRenderer.setLabelsTextSize(15);
		mRenderer.setLegendTextSize(20);
		// mRenderer.setAxesColor(Color.GREEN);
		mRenderer.setZoomButtonsVisible(true);
		
		if ( name.equals("Month") ) {
			name = "day";
			mRenderer.setXAxisMax(31);
			mRenderer.setXAxisMin(0);
		}
		else {
			name = "hour";
			mRenderer.setXAxisMax(24);
			mRenderer.setXAxisMin(0);
		}
			
		// X axis
		mRenderer.setXTitle(name);
		// Y axis
		mRenderer.setYTitle("Profit");

		Intent intent = ChartFactory.getLineChartIntent(context, dataset,
				mRenderer, "Profit Graph");
		return intent;

	}

}
