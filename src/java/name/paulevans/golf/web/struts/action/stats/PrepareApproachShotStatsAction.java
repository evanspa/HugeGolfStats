package name.paulevans.golf.web.struts.action.stats;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.bean.chart.approachshot.APSLinePercentagesTrend;
import name.paulevans.golf.web.struts.formbean.StatisticsForm;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PrepareApproachShotStatsAction extends StatsAdapterAction {
	
	/**
	 * Initialize the chart ID
	 */
	public void hook(StatisticsForm aForm, HttpServletRequest aRequest) {
		aForm.setChartClass(APSLinePercentagesTrend.class.getName());
	}
}