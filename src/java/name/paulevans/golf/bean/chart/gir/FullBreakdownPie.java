package name.paulevans.golf.bean.chart.gir;

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

public class FullBreakdownPie extends ChartAdapter {
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {

		DefaultPieDataset dataset;
		int girIds[];
		PiePlot3D plot;
		JFreeChart chart;
		String[] GIR_CATEGORIES = {
			getLabelStringFactory().getString("chart.girfullbreakdownpiechart.less15feet"),
			getLabelStringFactory().getString("chart.girfullbreakdownpiechart.greater15feet"),
			getLabelStringFactory().getString("chart.girfullbreakdownpiechart.nogir")
		};
		
		dataset = new DefaultPieDataset();
		girIds = aDAOUtils.getGIRBreakdownValues(aFromDate, aToDate, 
				aCircumstances, aPlayer);
		dataset = new DefaultPieDataset();
		dataset.setValue(GIR_CATEGORIES[0], girIds[0]);
		dataset.setValue(GIR_CATEGORIES[1], girIds[1]);
		dataset.setValue(GIR_CATEGORIES[2], girIds[2]);	
		chart = ChartFactory.createPieChart3D(getLabelStringFactory().getString(
				"chart.girfullbreakdownpiechart.title"), dataset, true, false, 
				false);
		plot = (PiePlot3D) chart.getPlot();
		plot.setForegroundAlpha(0.5f);
		plot.setSectionPaint(0, Color.blue);
		plot.setSectionPaint(1, Color.green);
		plot.setSectionPaint(2, Color.red);
		return chart;
	}
}
