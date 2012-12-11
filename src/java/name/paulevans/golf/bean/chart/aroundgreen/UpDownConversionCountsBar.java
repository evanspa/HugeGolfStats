package name.paulevans.golf.bean.chart.aroundgreen;

import java.util.Date;

import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.chart.ChartAdapter;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class UpDownConversionCountsBar extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {

		DefaultCategoryDataset dataset;
		JFreeChart chart;
		int numUpDownAttempts, numUpDownConverts;
		
		numUpDownAttempts = aDAOUtils.getUpDownAttemptCount(aFromDate, aToDate, 
				aCircumstances, aPlayer);
		numUpDownConverts = aDAOUtils.getUpDownConvertCount(aFromDate, aToDate,
				aCircumstances, aPlayer);
		dataset = new DefaultCategoryDataset();
		dataset.addValue(numUpDownAttempts, 
				getLabelStringFactory().getString("chart.aroundgreen.updown.counts.xaxis"), 
				getLabelStringFactory().getString(
						"chart.aroundgreen.updown.counts.attempts"));
		dataset.addValue(numUpDownConverts, 
				getLabelStringFactory().getString("chart.aroundgreen.updown.counts.xaxis"), 
				getLabelStringFactory().getString(
						"chart.aroundgreen.updown.counts.conversions"));
		chart = ChartFactory.createBarChart3D(
				getLabelStringFactory().getString("chart.aroundgreen.updown.counts.title"), 
				"", getLabelStringFactory().getString(
						"chart.aroundgreen.updown.counts.yaxis"), dataset,
				PlotOrientation.VERTICAL, true, false, false);
		return chart;
	}
}
