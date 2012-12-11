package name.paulevans.golf.bean.chart.putting;

import java.util.Date;

import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.chart.ChartAdapter;
import name.paulevans.golf.bean.chart.ChartUtils;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;

import org.jfree.chart.JFreeChart;

public class Num3PuttsPerRoundTrend extends ChartAdapter {
		
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {
		
		return ChartUtils.newNumPuttsChart(aFromDate, aToDate, 
				aCircumstances, aDAOUtils, aPlayer, 3, false, false, 
				"chart.numputtstimechart.3putts", 
				"chart.num3puttstimechart.title", getLabelStringFactory());	
	}
}
