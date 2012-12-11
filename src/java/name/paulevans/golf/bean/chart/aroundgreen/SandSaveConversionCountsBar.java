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

public class SandSaveConversionCountsBar extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {
		
		DefaultCategoryDataset dataset;
		JFreeChart chart;
		int numSandSaveAttempts, numSandSaveConverts;
		
		numSandSaveAttempts = aDAOUtils.getSandSaveAttemptCount(aFromDate, 
				aToDate, aCircumstances, aPlayer);
		numSandSaveConverts = aDAOUtils.getSandSaveConvertCount(aFromDate, 
				aToDate, aCircumstances, aPlayer);
		dataset = new DefaultCategoryDataset();
		dataset.addValue(numSandSaveAttempts, 
				getLabelStringFactory().getString("chart.aroundgreen.sandsave.counts.xaxis"), 
				getLabelStringFactory().getString(
						"chart.aroundgreen.sandsave.counts.attempts"));
		dataset.addValue(numSandSaveConverts, 
				getLabelStringFactory().getString("chart.aroundgreen.sandsave.counts.xaxis"), 
				getLabelStringFactory().getString(
						"chart.aroundgreen.sandsave.counts.conversions"));
		chart = ChartFactory.createBarChart3D(
				getLabelStringFactory().getString("chart.aroundgreen.sandsave.counts.title"), 
				"", getLabelStringFactory().getString(
						"chart.aroundgreen.sandsave.counts.yaxis"), dataset,
				PlotOrientation.VERTICAL, true, false, false);
		return chart;
	}
}
