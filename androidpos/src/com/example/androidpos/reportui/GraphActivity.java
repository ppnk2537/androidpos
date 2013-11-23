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
	public Intent getIntent(Context context, List<HashMap<String, String>> listmap) {
		
		int len = listmap.size();
		
		double [] x = new double[len];
		int [] y = new int[len];
		
		for ( int i = 0 ; i < len ; i ++ ) {
			String [] tim = listmap.get(i).get("lastedit").split(" ");
			String [] s = tim[0].split(":");
			double hr = Double.valueOf(s[0]) + Double.valueOf(s[1])/100;
			x[i] = hr;
		}
		
		for ( int i = 0 ; i < len ; i ++ ) {
			int profit = Double.valueOf(listmap.get(i).get("profit")).intValue();
			y[i] = profit;
		}
		
		TimeSeries series = new TimeSeries("sale1");
		for (int i = 0; i < x.length; i++) {
			series.add(x[i], y[i]);
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
		mRenderer.setChartTitle("Profit in the day");
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
		mRenderer.setPanEnabled(false);

		// X axis
		mRenderer.setXTitle("Hour");
		// Y axis
		mRenderer.setYTitle("Profit");

		Intent intent = ChartFactory.getLineChartIntent(context, dataset,
				mRenderer, "Profit Graph");
		return intent;

	}

}
