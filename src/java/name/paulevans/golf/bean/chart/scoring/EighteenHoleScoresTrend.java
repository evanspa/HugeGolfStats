package name.paulevans.golf.bean.chart.scoring;

import java.util.Date;

import name.paulevans.golf.Constants;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.chart.ChartAdapter;
import name.paulevans.golf.bean.chart.ChartUtils;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;

public class EighteenHoleScoresTrend extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {

		TimeSeries series;
		ScorecardSummaryDAO scores[];
		
		series = new TimeSeries(getLabelStringFactory().getString(
				"chart.scorehistory.seriesname"));
		scores = (ScorecardSummaryDAO[])ChartUtils.filterRounds(aFromDate, aToDate, 
				aPlayer.getAllRounds(Constants.EIGHTEEN, aCircumstances));
		for (ScorecardSummaryDAO score : scores) {
			series.add(new Day(score.getDatePlayed()), 
					new Double(score.getScore()));
		}	
		return ChartUtils.newTimeSeriesChart(series, 
				getLabelStringFactory().getString("chart.scorehistory.title." + Constants.EIGHTEEN), 
				getLabelStringFactory().getString("chart.scorehistory.title.yaxis"), null,
				false);
	}
}
