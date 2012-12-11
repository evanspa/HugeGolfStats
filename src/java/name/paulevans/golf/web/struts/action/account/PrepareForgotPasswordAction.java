package name.paulevans.golf.web.struts.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.SessionUtils;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.ForgotPasswordForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Prepares the forgot-userid form
 * @author pevans
 *
 */
public class PrepareForgotPasswordAction extends BaseAction {
	
	// logger object...
	private final static Logger logger = Logger.getLogger(
			PrepareForgotPasswordAction.class);

	/**
	 * Prepares the forgot-userid form
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		ForgotPasswordForm form;
		HttpSession httpSession;
		
		// get the session...
		httpSession = aRequest.getSession();
		
		// down-cast the action form...
		form = (ForgotPasswordForm)aForm;	
		
		// clear the form...
		form.clear();
				
		// put the calendar months into the session...
		SessionUtils.addToSession(httpSession, getDAOUtils().getMonths(
				getLocale(aRequest)), AttributeKeyConstants.CALENDAR_MONTHS);
				
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
