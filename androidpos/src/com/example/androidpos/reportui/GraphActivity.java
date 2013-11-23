package com.example.androidpos.reportui;

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
	public Intent getIntent(Context context) {
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] y = { 30, 34, 45, 57, 77, 89, 100, 111, 123, 145 };

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
		mRenderer.setXTitle("Month");
		// Y axis
		mRenderer.setYTitle("Profit");

		Intent intent = ChartFactory.getLineChartIntent(context, dataset,
				mRenderer, "Profit Graph");
		return intent;

	}

}
