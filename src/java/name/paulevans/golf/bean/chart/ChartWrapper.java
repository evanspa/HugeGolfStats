package name.paulevans.golf.bean.chart;

import java.util.Date;
import java.util.Locale;

import name.paulevans.golf.LabelStringFactory;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;

import org.jfree.chart.JFreeChart;

/**
 * Defines a chart interface
 * @author pevans
 *
 */
public interface ChartWrapper {
	
	/**
	 * Returns the title of the chart
	 * @return
	 */
	String getTitle();
	
	/**
	 * Sets the title key
	 * @param aTitleKey
	 */
	void setTitleKey(String aTitleKey);
	
	/**
	 * Returns the label string factory object
	 * @return
	 */
	LabelStringFactory getLabelStringFactory();
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer);
	
	/**
	 * Sets a locale object
	 * @param aLocale
	 */
	void setLocale(Locale aLocale);
}
