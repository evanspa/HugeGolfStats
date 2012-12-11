package name.paulevans.golf.web.struts.action.stats;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.MasterSummary;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.StatisticsForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PrepareSummaryStatsAction extends BaseAction {
	
	/**
	 * Creates a circumstances bean from the statistics form.  As of this 
	 * writing this is the only action class that needs this function.  All of
	 * the other "stats" pages using the ChartRenderingServlet to create an 
	 * image, and it is this servlet that requires the circumstances parmaeter
	 * data.  These data are passed to the servlet via get parameters; this is
	 * the only action that actually consumes the circumstances, so this
	 * function is required to create a circumstances bean from the form bean.
	 * @param aForm
	 * @return
	 */
	private static CircumstancesBean createCircumstancesFromFormBean(
			StatisticsForm aForm) {
		
		CircumstancesBean circumstances;
		
		circumstances = new CircumstancesBean();
		circumstances.setBagCarryingMechanismTypeId(aForm.getBagCarryingMechanismId());
		circumstances.setEyeWearId(aForm.getEyeWearTypeId());
		circumstances.setHeadWearId(aForm.getHeadWearTypeId());
		circumstances.setLocomotionTypeId(aForm.getLocomotionTypeId());
		circumstances.setPantWearId(aForm.getPantWearTypeId());
		circumstances.setWeatherConditionId(aForm.getWeatherConditionTypeId());
		circumstances.setTournamentTypeId(aForm.getTournamentTypeId());
		circumstances.setCoursePlayedId(aForm.getCoursePlayedId());
		return circumstances;
	}
	
	/**
	 * Prepares the approach-shot stats screen
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// locals...
		StatisticsForm form;
		PlayerDAO player;
		Locale locale;
		HttpSession session;
		MasterSummary summary;
		
		// get the session...
		session = aRequest.getSession();
		
		// get the form...
		form = (StatisticsForm)aForm;
		
		// get the player...
		player = NewUtil.getPlayer(aRequest);
		
		// get the locale...
		locale = getLocale(aRequest);
		
		// prep the form...
		StatsAdapterAction.prepareForm(aRequest, getDAOUtils(), locale);
		
		// put the collection of courses played into the request...
		SessionUtils.addToSession(session, getDAOUtils().getCoursesPlayed((Integer)player.getId()),
				AttributeKeyConstants.COURSES_PLAYED);
		
		// create a master summary object...
		summary = player.calculateNewSummary(
				parseDate(aRequest, form.getFromDate()), 
				parseDate(aRequest, form.getToDate()), 
				createCircumstancesFromFormBean(form), getLocale(aRequest));
		
		// add the stats object to the request...
		aRequest.setAttribute(AttributeKeyConstants.SUMMARY_STATS, summary);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}

