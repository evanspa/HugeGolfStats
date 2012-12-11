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

public class APSLineFullBreakdownPie extends ChartAdapter {
	
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
		String[] APS_LINE_CATEGORIES = {
			getLabelStringFactory().getString("chart.aps.line.fullbreakdownpiechart.left"),	
			getLabelStringFactory().getString("chart.aps.line.fullbreakdownpiechart.online"),
			getLabelStringFactory().getString("chart.aps.line.fullbreakdownpiechart.right")
		};
		
		dataset = new DefaultPieDataset();
		apsLineIds = aDAOUtils.getAPSLineBreakdownValues(aFromDate, aToDate, 
				aCircumstances, aPlayer);
		dataset = new DefaultPieDataset();
		dataset.setValue(APS_LINE_CATEGORIES[0], apsLineIds[0]);
		dataset.setValue(APS_LINE_CATEGORIES[1], apsLineIds[1]);
		dataset.setValue(APS_LINE_CATEGORIES[2], apsLineIds[2]);
		chart = ChartFactory.createPieChart3D(getLabelStringFactory().getString(
				"chart.aps.line.fullbreakdownpiechart.title"), dataset, true, 
				false, 	false);
		plot = (PiePlot3D) chart.getPlot();
		plot.setForegroundAlpha(0.5f);
		plot.setSectionPaint(0, Color.blue);
		plot.setSectionPaint(1, Color.green);
		plot.setSectionPaint(2, Color.red);
		return chart;
	}
}
