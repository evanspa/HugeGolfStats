package name.paulevans.golf.bean.chart.approachshot;

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

public class APSDistanceFullBreakdownPie extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {

		DefaultPieDataset dataset;
		int apsLineIds[];
		PiePlot3D plot;
		JFreeChart chart;
		String[] APS_DISTANCE_CATEGORIES = {
			getLabelStringFactory().getString(
				"chart.aps.distance.fullbreakdownpiechart.short"),	
			getLabelStringFactory().getString(
				"chart.aps.distance.fullbreakdownpiechart.pinhigh"),
			getLabelStringFactory().getString(
				"chart.aps.distance.fullbreakdownpiechart.long")
		};
		
		dataset = new DefaultPieDataset();
		apsLineIds = aDAOUtils.getAPSDistanceBreakdownValues(aFromDate, aToDate, 
				aCircumstances, aPlayer);
		dataset = new DefaultPieDataset();
		dataset.setValue(APS_DISTANCE_CATEGORIES[0], apsLineIds[0]);
		dataset.setValue(APS_DISTANCE_CATEGORIES[1], apsLineIds[1]);
		dataset.setValue(APS_DISTANCE_CATEGORIES[2], apsLineIds[2]);
		chart = ChartFactory.createPieChart3D(getLabelStringFactory().getString(
				"chart.aps.distance.fullbreakdownpiechart.title"), dataset, true, 
				false, 	false);
		plot = (PiePlot3D) chart.getPlot();
		plot.setForegroundAlpha(0.5f);
		plot.setSectionPaint(0, Color.blue);
		plot.setSectionPaint(1, Color.green);
		plot.setSectionPaint(2, Color.red);
		return chart;
	}
}
