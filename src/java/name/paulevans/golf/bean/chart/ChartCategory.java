package name.paulevans.golf.bean.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import name.paulevans.golf.LabelStringFactory;

/**
 * Defines a logical group of chart categories
 * @author pevans
 *
 */
public class ChartCategory {
	
	// these constants need to match what's in Spring's bean configuration file
	// defining the category types...
	public static final String CHART_CATEGORY_PUTTING = "puttingChartCategory";
	public static final String CHART_CATEGORY_SCORING = "scoringChartCategory";
	public static final String CHART_CATEGORY_TEESHOT = "teeshotChartCategory";
	public static final String CHART_CATEGORY_GIR	  = "girChartCategory";
	public static final String CHART_CATEGORY_APPROACHSHOT = "approachshotChartCategory";
	public static final String CHART_CATEGORY_AROUNDGREEN = "aroundgreenChartCategory";
	
	private int sortOrder;
	private List<ChartGroup> chartGroups;
	private String titleKey;
	private LabelStringFactory strFactory;

	/**
	 * Sets the locale and propagates to chart groups
	 * @param aLocale
	 */
	public void propagateLocale(Locale aLocale) {
		
		strFactory = LabelStringFactory.getInstance(aLocale);
		
		for (ChartGroup group : chartGroups) {
			group.propagateLocale(aLocale);
		}
	}

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return strFactory.getString(titleKey);
	}

	/**
	 * @param aTitle the title to set
	 */
	public final void setTitleKey(String aTitleKey) {
		this.titleKey = aTitleKey;
	}

	/**
	 * Default constructor
	 *
	 */
	public ChartCategory() {
		chartGroups = new ArrayList<ChartGroup>();
	}
	
	/**
	 * Returns the collection of chartGroups in this group
	 * @return
	 */
	public List<ChartGroup> getChartGroups() {
		return chartGroups;
	}
	
	/**
	 * Sets the collection of chartGroups
	 * @param aChartGroups
	 */
	public void setChartGroups(List<ChartGroup> aChartGroups) {
		chartGroups = aChartGroups;
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
