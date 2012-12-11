package name.paulevans.golf.bean.chart.approachshot;

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

public class APSDistanceFullBreakdownTrend extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {

		TimeSeries series[];
		List<Object[]> leftLine;
		List<Object[]> greenLine;
		List<Object[]> rightLine;
		int numAPSDistanceTypes;
		Date datePlayed;
		
		series = new TimeSeries[3];
		leftLine = aDAOUtils.getAPSDistanceValues(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.APPROACH_SHOT_DISTANCE_SHORT);
		greenLine = aDAOUtils.getAPSDistanceValues(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.APPROACH_SHOT_DISTANCE_PIN_HIGH);
		rightLine = aDAOUtils.getAPSDistanceValues(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.APPROACH_SHOT_DISTANCE_LONG);
		
		series = new TimeSeries[3];
		series[0] = new TimeSeries(getLabelStringFactory().getString(
				"chart.aps.distance.fullbreakdowntrend.short")); 
		series[1] = new TimeSeries(getLabelStringFactory().getString(
		"chart.aps.distance.fullbreakdowntrend.pinhigh")); 
		series[2] = new TimeSeries(getLabelStringFactory().getString(
		"chart.aps.distance.fullbreakdowntrend.long")); 
		for (Object rowData[] : leftLine) {
			datePlayed = (Date)rowData[0];
			numAPSDistanceTypes = (Integer)rowData[1];
			series[0].add(new Day(datePlayed), new Double(numAPSDistanceTypes));
		}
		for (Object rowData[] : greenLine) {
			datePlayed = (Date)rowData[0];
			numAPSDistanceTypes = (Integer)rowData[1];
			series[1].add(new Day(datePlayed), new Double(numAPSDistanceTypes));
		}
		for (Object rowData[] : rightLine) {
			datePlayed = (Date)rowData[0];
			numAPSDistanceTypes = (Integer)rowData[1];
			series[2].add(new Day(datePlayed), new Double(numAPSDistanceTypes));
		}
		return ChartUtils.newTimeSeriesChart(series, 
				getLabelStringFactory().getString("chart.aps.distance.fullbreakdowntrend.title"), 
				getLabelStringFactory().getString(
						"chart.aps.distance.fullbreakdowntrend.title.yaxis"), null, 
						false);
	}
}
