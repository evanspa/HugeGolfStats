package name.paulevans.golf.bean.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import name.paulevans.golf.LabelStringFactory;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

/**
 * Contains static methods for creating chart objects
 * @author pevans
 *
 */
public class ChartUtils {
	
	// singleton instance...
	private static ChartUtils chartUtils;
	
	/**
	 * Returns the singleton instance
	 * @param aLocale
	 * @return
	 */
	public static final synchronized ChartUtils getInstance() {
		if (chartUtils == null) {
			chartUtils = new ChartUtils();
		}
		return chartUtils;
	}
	
	/**
	 * Private no-arg constructor to prevent instantiation
	 *
	 */
	private ChartUtils() {
		// does nothing...
	}
		
	/**
	 * Creates a new time series chart based on the inputted parameters
	 * @param aDataSeries
	 * @param aChartTitle
	 * @param aYAxisLabel
	 * @param aXAxisLabel
	 * @param aFirst
	 * @param aLast
	 * @return
	 */
	public static final JFreeChart newTimeSeriesChart(TimeSeries aDataSeries, 
			String aChartTitle, String aYAxisLabel, String aXAxisLabel,
			boolean aIsPercentage) {
		
		List<TimeSeries> listOfOne;
		
		listOfOne = new ArrayList<TimeSeries>();
		listOfOne.add(aDataSeries);
		return newTimeSeriesChart(listOfOne.toArray(new TimeSeries[1]),
				aChartTitle, aYAxisLabel, aXAxisLabel, aIsPercentage);
	}
	
	/**
	 * Creates a new time series chart based on the inputted parameters
	 * @param aDataSeries
	 * @param aChartTitle
	 * @param aYAxisLabel
	 * @param aXAxisLabel
	 * @param aDateTickUnit
	 * @param aDateFormat
	 * @return
	 */
	public static final JFreeChart newTimeSeriesChart(TimeSeries aDataSeries[], 
			String aChartTitle, String aYAxisLabel, String aXAxisLabel,
			boolean aIsPercentage) {
		
		TimeSeriesCollection dataset;
		JFreeChart chart;
		XYPlot plot;
		DateAxis axis;
		ValueAxis rangeAxis;
		XYItemRenderer itemRenderer;
		XYLineAndShapeRenderer renderer;
		
		dataset = new TimeSeriesCollection();
		for (TimeSeries series : aDataSeries) {
			dataset.addSeries(series);
		}
		chart = ChartFactory.createTimeSeriesChart(aChartTitle, 
				aXAxisLabel, aYAxisLabel, dataset, true, true, false);
		//chart.setBackgroundPaint(Color.white);
		plot = chart.getXYPlot();
		plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
		axis = (DateAxis) plot.getDomainAxis();
		axis.setVerticalTickLabels(true);
		//axis.setTickUnit(new DateTickUnit(tickUnit, 1, dateFormat));
		itemRenderer = plot.getRenderer();
		renderer = (XYLineAndShapeRenderer)itemRenderer;
		renderer.setShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setFillPaint(Color.white);
		rangeAxis = plot.getRangeAxis();
		if (aIsPercentage) {
			rangeAxis.setRange(0.0, 1.05);
		} else {
			rangeAxis.setAutoRangeMinimumSize(1.0);
		}
		return chart;
	}
	
	/**
	 * Returns a chart of num-putts given the inputted date range.  This chart
	 * will render a single data series
	 * @param aFromDate If not null, no date-filter will be applied
	 * @param aToDate If not null, no date-filter will be applied
	 * @param aPlayer
	 * @return
	 */
	public static final JFreeChart newNumPuttsChart(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer, 
			int aNumPutts, boolean aIsGIR, boolean aGreaterThanEqualTo,
			String aSeriesLabelKey, String aChartTitleKey, LabelStringFactory aStrFactory) {
		
		TimeSeries series[];
		List<Object[]> puttCounts;
		int count;
		Date datePlayed;
		
		series = new TimeSeries[4];
		puttCounts = aDAOUtils.getPutts(aFromDate, aToDate, aCircumstances, aPlayer, aIsGIR,
			aNumPutts, aGreaterThanEqualTo);
		
		series = new TimeSeries[1];
		series[0] = new TimeSeries(aStrFactory.getString(aSeriesLabelKey)); 
		
		for (Object rowData[] : puttCounts) {
			datePlayed = (Date)rowData[0];
			count = (Integer)rowData[1];
			series[0].add(new Day(datePlayed), new Double(count));
		}
		return newTimeSeriesChart(series, 
				aStrFactory.getString(aChartTitleKey), 
				aStrFactory.getString("chart.numputtstimechart.title.yaxis"), 
				null, false);
	}
	
	/**
	 * Returns a chart of num-putts given the inputted date range.  This chart
	 * will render 4 data series' (4/+ putts, 3 putts, 2 putts, 1 putts)
	 * @param aFromDate If not null, no date-filter will be applied
	 * @param aToDate If not null, no date-filter will be applied
	 * @param aPlayer
	 * @return
	 */
	public static final JFreeChart newNumPuttsChart(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer,
			boolean aIsGIR, LabelStringFactory aStrFactory) {
		
		TimeSeries series[];
		List<Object[]> onePutts;
		List<Object[]> twoPutts;
		List<Object[]> threePutts;
		List<Object[]> fourPutts;
		int numPutts;
		Date datePlayed;
		String labelPrefixKey, titlePrefixKey;
		
		labelPrefixKey = aIsGIR ? "chart.numputtsgirtimechart." : 
			"chart.numputtstimechart.";
		titlePrefixKey = aIsGIR ? "chart.numputtsgirtimechart." : 
			"chart.numputtstimechart.";
		series = new TimeSeries[4];
		onePutts = aDAOUtils.getPutts(aFromDate, aToDate, aCircumstances, aPlayer, aIsGIR, 1, 
			false);
		twoPutts = aDAOUtils.getPutts(aFromDate, aToDate, aCircumstances, aPlayer, aIsGIR, 2, 
			false);
		threePutts = aDAOUtils.getPutts(aFromDate, aToDate, aCircumstances, aPlayer, aIsGIR, 3, 
			false);
		fourPutts = aDAOUtils.getPutts(aFromDate, aToDate, aCircumstances, aPlayer, aIsGIR, 4, 
			true);
		
		series = new TimeSeries[4];
		series[0] = new TimeSeries(aStrFactory.getString(
			labelPrefixKey + "1putts")); 
		series[1] = new TimeSeries(aStrFactory.getString(
			labelPrefixKey + "2putts")); 
		series[2] = new TimeSeries(aStrFactory.getString(
			labelPrefixKey + "3putts")); 
		series[3] = new TimeSeries(aStrFactory.getString(
			labelPrefixKey + "4putts")); 
		
		for (Object rowData[] : onePutts) {
			datePlayed = (Date)rowData[0];
			numPutts = (Integer)rowData[1];
			series[0].add(new Day(datePlayed), new Double(numPutts));
		}
		for (Object rowData[] : twoPutts) {
			datePlayed = (Date)rowData[0];
			numPutts = (Integer)rowData[1];
			series[1].add(new Day(datePlayed), new Double(numPutts));
		}
		for (Object rowData[] : threePutts) {
			datePlayed = (Date)rowData[0];
			numPutts = (Integer)rowData[1];
			series[2].add(new Day(datePlayed), new Double(numPutts));
		}
		for (Object rowData[] : fourPutts) {
			datePlayed = (Date)rowData[0];
			numPutts = (Integer)rowData[1];
			series[3].add(new Day(datePlayed), new Double(numPutts));
		}
		return newTimeSeriesChart(series, 
				aStrFactory.getString(titlePrefixKey + "title"), 
				aStrFactory.getString("chart.numputtstimechart.title.yaxis"), 
				null, false);
	}
	
	/**
	 * Returns a pie chart of num-putts given the inputted date range.
	 * @param aFromDate If not null, no date-filter will be applied
	 * @param aToDate If not null, no date-filter will be applied
	 * @param aPlayer
	 * @return
	 */
	public static final JFreeChart newNumPuttsPieChart(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer, 
			boolean aIsGIR, LabelStringFactory aStrFactory) {
		
		DefaultPieDataset dataset;
		int numPutts[];
		String labelPrefixKey, titlePrefixKey;
		JFreeChart chart;
		PiePlot3D plot;
		
		labelPrefixKey = aIsGIR ? "chart.numputtsgirpiechart." : 
			"chart.numputtspiechart.";
		titlePrefixKey = aIsGIR ? "chart.numputtsgirpiechart.title" : 
			"chart.numputtspiechart.title";
		String[] NUM_PUTTS_CATEGORIES = {
				aStrFactory.getString(labelPrefixKey + "1putts"),
				aStrFactory.getString(labelPrefixKey + "2putts"),
				aStrFactory.getString(labelPrefixKey + "3putts"),
				aStrFactory.getString(labelPrefixKey + "4putts")
		};
		
		numPutts = aDAOUtils.getNumPutts(aFromDate, aToDate, aCircumstances, aPlayer, aIsGIR);
		dataset = new DefaultPieDataset();
		dataset.setValue(NUM_PUTTS_CATEGORIES[0], numPutts[0]);
		dataset.setValue(NUM_PUTTS_CATEGORIES[1], numPutts[1]);
		dataset.setValue(NUM_PUTTS_CATEGORIES[2], numPutts[2]);
		dataset.setValue(NUM_PUTTS_CATEGORIES[3], numPutts[3]);	
		chart = ChartFactory.createPieChart3D(aStrFactory.getString(
			titlePrefixKey), dataset, true, false, false);
		plot = (PiePlot3D) chart.getPlot();
        plot.setForegroundAlpha(0.5f);
		return chart;
	}

	
	/**
	 * Returns a filtered-collection of dateable objects based on the from and 
	 * to dates specified of aDateables
	 * @param aFromDate
	 * @param aToDate
	 * @param aScores
	 * @return
	 */
	public static final ScorecardSummaryDAO[] filterRounds(Date aFromDate,
			Date aToDate, ScorecardSummaryDAO aScores[]) {
		
		List<ScorecardSummaryDAO> scores;
		long time, fromTime, toTime;
		boolean fromCond, toCond, isFromDateNull, isToDateNull;
		
		isFromDateNull = aFromDate == null;
		isToDateNull = aToDate == null;
		fromTime = isFromDateNull ? 0 : aFromDate.getTime();
		toTime = isToDateNull ? 0 : aToDate.getTime();
		scores = new ArrayList<ScorecardSummaryDAO>();
		for (ScorecardSummaryDAO score : aScores) {
			time = score.getDatePlayed().getTime();
			fromCond = false;
			toCond = false;
			if (isFromDateNull || time >= fromTime) {
				fromCond = true;
			}
			if (isToDateNull || time <= toTime) {
				toCond = true;
			}
			if (fromCond && toCond) {
				scores.add(score);
			}
		}
		return scores.toArray(new ScorecardSummaryDAO[scores.size()]);
	}
	
	/**
	 * Returns true if aDataSeries contains a single unique value; false
	 * otherwise
	 * @param aDataSeries
	 * @return
	 */
	/*private static boolean containsSingleUniqueValue(TimeSeries aDataSeries) {
		
		Number value;
		int count;
		
		count = aDataSeries.getItemCount();
		value = aDataSeries.getValue(0);
		for (int i = 1; i < count; i++) {
			if (value.doubleValue() != aDataSeries.getValue(i).doubleValue()) {
				return false;
			}
		}
		return true;
	}*/
}
