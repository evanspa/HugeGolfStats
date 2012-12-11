package name.paulevans.golf.bean.chart.putting;

import java.util.Date;

import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.chart.ChartAdapter;
import name.paulevans.golf.bean.chart.ChartUtils;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;

import org.jfree.chart.JFreeChart;

public class Num1PuttsPerRoundTrend extends ChartAdapter {
		
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer) {
		
		return ChartUtils.newNumPuttsChart(aFromDate, aToDate, 
				aCircumstances, aDAOUtils, aPlayer, 1, false, false, 
				"chart.numputtstimechart.1putts", 
				"chart.num1puttstimechart.title", getLabelStringFactory());	
	}
}
