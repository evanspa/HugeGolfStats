package name.paulevans.golf.bean.chart.teeshot;

import java.awt.Color;
import java.util.Date;

import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.chart.ChartAdapter;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

public class AccuracyFullBreakdownPie extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {

		DefaultPieDataset dataset;
		int accuracyIds[];
		PiePlot3D plot;
		JFreeChart chart;
		String[] TEE_SHOT_ACCURACY_CATEGORIES = {
			getLabelStringFactory().getString(
					"chart.teeshotaccuracyfullbreakdownpiechart.leftrough"),
					getLabelStringFactory().getString(
					"chart.teeshotaccuracyfullbreakdownpiechart.fairway"),
					getLabelStringFactory().getString(
					"chart.teeshotaccuracyfullbreakdownpiechart.rightrough")
		};
		
		dataset = new DefaultPieDataset();
		accuracyIds = aDAOUtils.getTeeShotAccuracyValues(aFromDate, aToDate, 
				aCircumstances, aPlayer);
		dataset = new DefaultPieDataset();
		dataset.setValue(TEE_SHOT_ACCURACY_CATEGORIES[0], accuracyIds[0]);
		dataset.setValue(TEE_SHOT_ACCURACY_CATEGORIES[1], accuracyIds[1]);
		dataset.setValue(TEE_SHOT_ACCURACY_CATEGORIES[2], accuracyIds[2]);	
		chart = ChartFactory.createPieChart3D(getLabelStringFactory().getString(
				"chart.teeshotaccuracyfullbreakdownpiechart.title"), dataset, 
				true, false, false);
		plot = (PiePlot3D) chart.getPlot();
		plot.setForegroundAlpha(0.5f);
		plot.setSectionPaint(0, Color.red);
		plot.setSectionPaint(1, Color.green);
		plot.setSectionPaint(2, Color.yellow);
		return chart;
	}
}
