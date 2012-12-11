package name.paulevans.golf.bean.chart.putting;

import java.util.Date;

import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.chart.ChartAdapter;
import name.paulevans.golf.bean.chart.ChartUtils;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;

public class AvgPuttsPerHoleTrend extends ChartAdapter {
		
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {
		
		TimeSeries series;
		ScorecardSummaryDAO scores[];
		
		series = new TimeSeries(getLabelStringFactory().getString(
				"chart.avgputts.seriesname"));
		scores = (ScorecardSummaryDAO[])ChartUtils.filterRounds(aFromDate, aToDate, 
				aPlayer.getAllRounds(aCircumstances));
		for (ScorecardSummaryDAO score : scores) {
			if (score.getAvgPutts() != null) {
				series.add(new Day(score.getDatePlayed()), new Double(
						score.getAvgPutts()));
			}
		}		
		return ChartUtils.newTimeSeriesChart(series, 
				getLabelStringFactory().getString("chart.avgputts.title"), 
				getLabelStringFactory().getString("chart.avgputts.title.yaxis"), null, 
				false);
	}
}
