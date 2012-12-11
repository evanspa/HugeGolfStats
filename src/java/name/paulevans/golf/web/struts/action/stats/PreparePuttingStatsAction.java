package name.paulevans.golf.web.struts.action.stats;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.bean.chart.putting.AvgPuttsPerHoleTrend;
import name.paulevans.golf.web.struts.formbean.StatisticsForm;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PreparePuttingStatsAction extends StatsAdapterAction {
	
	/**
	 * Initialize the chart class
	 */
	public void hook(StatisticsForm aForm, HttpServletRequest aRequest) {
		aForm.setChartClass(AvgPuttsPerHoleTrend.class.getName());
	}
}