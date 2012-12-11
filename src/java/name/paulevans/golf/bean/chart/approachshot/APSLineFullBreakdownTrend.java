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

public class APSLineFullBreakdownTrend extends ChartAdapter {
	
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
		int numAPSLineTypes;
		Date datePlayed;
		
		series = new TimeSeries[3];
		leftLine = aDAOUtils.getAPSLineValues(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.APPROACH_SHOT_LINE_LEFT);
		greenLine = aDAOUtils.getAPSLineValues(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.APPROACH_SHOT_LINE_GREEN);
		rightLine = aDAOUtils.getAPSLineValues(aFromDate, aToDate, 
				aCircumstances, aPlayer, Constants.APPROACH_SHOT_LINE_RIGHT);
		
		series = new TimeSeries[3];
		series[0] = new TimeSeries(getLabelStringFactory().getString(
				"chart.aps.line.fullbreakdowntrend.left")); 
		series[1] = new TimeSeries(getLabelStringFactory().getString(
		"chart.aps.line.fullbreakdowntrend.green")); 
		series[2] = new TimeSeries(getLabelStringFactory().getString(
		"chart.aps.line.fullbreakdowntrend.right")); 
		for (Object rowData[] : leftLine) {
			datePlayed = (Date)rowData[0];
			numAPSLineTypes = (Integer)rowData[1];
			series[0].add(new Day(datePlayed), new Double(numAPSLineTypes));
		}
		for (Object rowData[] : greenLine) {
			datePlayed = (Date)rowData[0];
			numAPSLineTypes = (Integer)rowData[1];
			series[1].add(new Day(datePlayed), new Double(numAPSLineTypes));
		}
		for (Object rowData[] : rightLine) {
			datePlayed = (Date)rowData[0];
			numAPSLineTypes = (Integer)rowData[1];
			series[2].add(new Day(datePlayed), new Double(numAPSLineTypes));
		}
		return ChartUtils.newTimeSeriesChart(series, 
				getLabelStringFactory().getString("chart.aps.line.fullbreakdowntrend.title"), 
				getLabelStringFactory().getString(
						"chart.aps.line.fullbreakdowntrend.title.yaxis"), null, 
						false);
	}
}
