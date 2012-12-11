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

public class AccuracyBreakdownPie extends ChartAdapter {
	
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
					"chart.teeshotaccuracybreakdownpiechart.fairway"),
					getLabelStringFactory().getString(
					"chart.teeshotaccuracybreakdownpiechart.nofairway")
		};
		
		dataset = new DefaultPieDataset();
		accuracyIds = aDAOUtils.getTeeShotAccuracyValues(aFromDate, aToDate, 
				aCircumstances, aPlayer);
		dataset = new DefaultPieDataset();
		dataset.setValue(TEE_SHOT_ACCURACY_CATEGORIES[0], accuracyIds[1]);
		dataset.setValue(TEE_SHOT_ACCURACY_CATEGORIES[1], accuracyIds[0] + 
				accuracyIds[2]);
		chart = ChartFactory.createPieChart3D(getLabelStringFactory().getString(
				"chart.teeshotaccuracybreakdownpiechart.title"), dataset, 
				true, false, false);
		plot = (PiePlot3D) chart.getPlot();
		plot.setForegroundAlpha(0.5f);
		plot.setSectionPaint(0, Color.green);
		plot.setSectionPaint(1, Color.red);
		return chart;
	}
}
