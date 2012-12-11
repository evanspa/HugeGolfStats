package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.action.MemberHomeAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the cancellation of adding a course
 * @author pevans
 *
 */
public class CancelRoundAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(CancelRoundAction.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		RoundForm form;
		
		form = (RoundForm)aForm;
		
		// un-initialize form bean...
		form.setInitialized(false);
		
		// remove the form bean and search results...
		NewUtil.removeFromSession(AttributeKeyConstants.SCORECARD_ID_PARAM, 
				aRequest);
		NewUtil.removeFromSession(AttributeKeyConstants.ROUNDS, aRequest);
		NewUtil.removeFromSession(aMapping.getName(), aRequest);
		
		// prepare the member-home screen...
		MemberHomeAction.process(aRequest, aResponse, getUtil(), getDAOUtils(), 
				getLocale(aRequest));
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}