package name.paulevans.golf.bean.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Defines a logic group of charts
 * @author pevans
 *
 */
public class ChartGroup {
	
	private int sortOrder;
	private List<ChartWrapper> charts;
	
	/**
	 * Default constructor
	 *
	 */
	public ChartGroup() {
		charts = new ArrayList<ChartWrapper>();
	}
	
	/**
	 * Sets the locale and propagates to chart groups
	 * @param aLocale
	 */
	public void propagateLocale(Locale aLocale) {
		for (ChartWrapper chart : charts) {
			chart.setLocale(aLocale);
		}
	}
	
	/**
	 * Returns the collection of charts in this group
	 * @return
	 */
	public List<ChartWrapper> getCharts() {
		return charts;
	}
	
	/**
	 * Sets the collection of charts
	 * @param aCharts
	 */
	public void setCharts(List<ChartWrapper> aCharts) {
		charts = aCharts;
	}
	
	/**
	 * Returns the sort order of this group
	 * @return
	 */
	public int getSortOrder() {
		return sortOrder;
	}
	
	/**
	 * Sets the sort order of this group
	 * @param aSortOrder
	 */
	public void setSortOrder(int aSortOrder) {
		sortOrder = aSortOrder;
	}
}
