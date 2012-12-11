package name.paulevans.golf.bean.chart;

import java.util.Date;
import java.util.Locale;

import name.paulevans.golf.LabelStringFactory;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;

import org.jfree.chart.JFreeChart;

public abstract class ChartAdapter implements ChartWrapper {
	
	private LabelStringFactory strFactory;
	private String titleKey;
	
	/**
	 * Sets the title key
	 */
	public void setTitleKey(String aTitleKey) {
		titleKey = aTitleKey;
	}
	
	/**
	 * Returns the label string factory
	 * @return
	 */
	public LabelStringFactory getLabelStringFactory() {
		return strFactory;
	}
	
	/**
	 * Returns the title of the chart
	 * @return
	 */
	public String getTitle() {
		return strFactory.getString(titleKey);
	}
	
	/**
	 * Returns the chart object itself
	 * @return
	 */
	public abstract JFreeChart getChart(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, DAOUtils aDAOUtils, PlayerDAO aPlayer);
	
	/**
	 * Sets the locale
	 * @param aLocale
	 */
	public void setLocale(Locale aLocale) {
		strFactory = LabelStringFactory.getInstance(aLocale);
	}
}
