package name.paulevans.golf.web.struts.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.ProfileForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Creates an account
 * @author pevans
 *
 */
public class PrepareCreateAccountAction extends BaseAction {
	
	// logger object...
	private final static Logger logger = Logger.getLogger(
			PrepareCreateAccountAction.class);

	/**
	 * Processes the account-create request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		ProfileForm form;
		HttpSession httpSession;
		
		// get the session...
		httpSession = aRequest.getSession();
		
		// down-cast the action form...
		form = (ProfileForm)aForm;	
		
		// clear and init the form...
		form.clear();
		form.init(getUtil(), aRequest);
		
		// set ctx-indicator...
		form.setCreateNewAccount(true);
		form.setEditProfile(false);
		
		// put the calendar months into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getMonths(
				getLocale(aRequest)), AttributeKeyConstants.CALENDAR_MONTHS);
		
		// put the collection of eye-wear types into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getEyeWearTypes(
				getLocale(aRequest)),
				AttributeKeyConstants.EYE_WEAR_TYPES);
	
		// put the collection of head-wear types into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getHeadWearTypes(
				getLocale(aRequest)),
				AttributeKeyConstants.HEAD_WEAR_TYPES);
		
		// put the collection of pant-wear types into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getPantWearTypes(
				getLocale(aRequest)),
				AttributeKeyConstants.PANT_WEAR_TYPES);
		
		// put the collection of tee colors into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getTeeNames(),
				AttributeKeyConstants.TEE_NAMES);
		
		// put the collection of countries into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getCountries(),
				AttributeKeyConstants.COUNTRIES);
		
		// put the collection state-provinces into the session...
		httpSession.setAttribute(AttributeKeyConstants.STATE_PROVINCES, 
				getDAOUtils().getStateProvinces(form.getCountryId()));
		
		// put the collection of courses into the session...
		httpSession.setAttribute(AttributeKeyConstants.COURSES, 
				getDAOUtils().getCourses(form.getCountryId(), 
						form.getStateProvinceId()));
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
