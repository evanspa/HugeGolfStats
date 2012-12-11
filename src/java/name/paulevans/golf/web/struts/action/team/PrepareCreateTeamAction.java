package name.paulevans.golf.web.struts.action.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.TeamForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PrepareCreateTeamAction extends BaseAction {
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(
			PrepareCreateTeamAction.class);

	/**
	 * Empty implementation.
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		TeamForm form;
		HttpSession httpSession;
		PlayerDAO player;
		CourseDAO homeCourse;
		
		// downcast the form object and 'reset' it only if this request wasn't
		// a result of the user choosing a different course/state/etc from a
		// drop-down on the initial input screen...
		form = (TeamForm)aForm;
		
		// get the session...
		httpSession = aRequest.getSession();
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// put the collection of countries into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getCountries(),
				AttributeKeyConstants.COUNTRIES);
		
		// put the collection state-provinces into the session...
		httpSession.setAttribute(AttributeKeyConstants.STATE_PROVINCES, 
				getDAOUtils().getStateProvinces(form.getCountryId()));
		
		// initialize country/state-province drop-downs to the country/state-
		// province of the user's home course...
		player = getUtil().getPlayer(aRequest);
		homeCourse = player.getCourse();
		if (homeCourse != null) {
			form.setCountryId((Integer)
					homeCourse.getStateProvince().getCountry().getId());
			form.setStateProvinceId(((StateProvinceId)
					homeCourse.getStateProvince().getId()).getId());
		}
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}