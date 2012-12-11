package name.paulevans.golf.bean.chart.teeshot;

import java.util.Date;
import java.util.List;

import name.paulevans.golf.Constants;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.chart.ChartAdapter;
import name.paulevans.golf.bean.chart.ChartUtils;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;

public class AccuracyFullBreakdownTrend extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {

		TimeSeries series[];
		List<Object[]> leftRough;
		List<Object[]> fairway;
		List<Object[]> rightRough;
		int numAccuracyTypes;
		Date datePlayed;
		
		series = new TimeSeries[3];
		leftRough = aDAOUtils.getTeeShotAccuracyTypes(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.LEFT_ROUGH_ID);
		fairway = aDAOUtils.getTeeShotAccuracyTypes(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.FAIRWAY_HIT_ID);
		rightRough = aDAOUtils.getTeeShotAccuracyTypes(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.RIGHT_ROUGH_ID);
		
		series = new TimeSeries[3];
		series[0] = new TimeSeries(getLabelStringFactory().getString(
				"chart.teeshotbreakdowntrend.leftrough")); 
		series[1] = new TimeSeries(getLabelStringFactory().getString(
		"chart.teeshotbreakdowntrend.fairway")); 
		series[2] = new TimeSeries(getLabelStringFactory().getString(
		"chart.teeshotbreakdowntrend.rightrough")); 
		for (Object rowData[] : leftRough) {
			datePlayed = (Date)rowData[0];
			numAccuracyTypes = (Integer)rowData[1];
			series[0].add(new Day(datePlayed), new Double(numAccuracyTypes));
		}
		for (Object rowData[] : fairway) {
			datePlayed = (Date)rowData[0];
			numAccuracyTypes = (Integer)rowData[1];
			series[1].add(new Day(datePlayed), new Double(numAccuracyTypes));
		}
		for (Object rowData[] : rightRough) {
			datePlayed = (Date)rowData[0];
			numAccuracyTypes = (Integer)rowData[1];
			series[2].add(new Day(datePlayed), new Double(numAccuracyTypes));
		}
		return ChartUtils.newTimeSeriesChart(series, 
				getLabelStringFactory().getString("chart.teeshotbreakdowntrend.title"), 
				getLabelStringFactory().getString("chart.teeshotbreakdowntrend.title.yaxis"), 
				null, false);
	}
}
