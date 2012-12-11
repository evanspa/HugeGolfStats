package name.paulevans.golf.web.struts.action.stats;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.bean.chart.scoring.EighteenHoleScoresTrend;
import name.paulevans.golf.web.struts.formbean.StatisticsForm;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PrepareScoringStatsAction extends StatsAdapterAction {
	
	/**
	 * Create the summary object and add it to the session
	 */
	public void hook(StatisticsForm aForm, HttpServletRequest aRequest) {
		
		// init form to a specific chart id...
		aForm.setChartClass(EighteenHoleScoresTrend.class.getName());
	}
}