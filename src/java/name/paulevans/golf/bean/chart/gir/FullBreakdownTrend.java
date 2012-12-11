package name.paulevans.golf.bean.chart.gir;

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

public class FullBreakdownTrend extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {

		TimeSeries series[];
		List<Object[]> less15Feet;
		List<Object[]> greater15Feet;
		List<Object[]> noGIR;
		int numGIRTypes;
		Date datePlayed;
		
		series = new TimeSeries[3];
		less15Feet = aDAOUtils.getGIRValues(aFromDate, aToDate, aCircumstances, 
				aPlayer, 
				Constants.GIR_LESS_THAN_15_FEET);
		greater15Feet = aDAOUtils.getGIRValues(aFromDate, aToDate, aCircumstances, 
				aPlayer, Constants.GIR_GREATER_THAN_15_FEET);
		noGIR = aDAOUtils.getGIRValues(aFromDate, aToDate, aCircumstances, aPlayer, 
				Constants.GIR_NO_GIR);
		
		series = new TimeSeries[3];
		series[0] = new TimeSeries(getLabelStringFactory().getString(
				"chart.girbreakdowntrend.less15feet")); 
		series[1] = new TimeSeries(getLabelStringFactory().getString(
		"chart.girbreakdowntrend.greater15feet")); 
		series[2] = new TimeSeries(getLabelStringFactory().getString(
		"chart.girbreakdowntrend.nogir")); 
		for (Object rowData[] : less15Feet) {
			datePlayed = (Date)rowData[0];
			numGIRTypes = (Integer)rowData[1];
			series[0].add(new Day(datePlayed), new Double(numGIRTypes));
		}
		for (Object rowData[] : greater15Feet) {
			datePlayed = (Date)rowData[0];
			numGIRTypes = (Integer)rowData[1];
			series[1].add(new Day(datePlayed), new Double(numGIRTypes));
		}
		for (Object rowData[] : noGIR) {
			datePlayed = (Date)rowData[0];
			numGIRTypes = (Integer)rowData[1];
			series[2].add(new Day(datePlayed), new Double(numGIRTypes));
		}
		return ChartUtils.newTimeSeriesChart(series, 
				getLabelStringFactory().getString("chart.girbreakdowntrend.title"), 
				getLabelStringFactory().getString("chart.girbreakdowntrend.title.yaxis"), 
				null, false);
	}
}
