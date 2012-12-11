package name.paulevans.golf.web.struts.action.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.action.MemberHomeAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the cancellation of creating a team
 * @author pevans
 *
 */
public class CancelTeamAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private final static Logger logger = 
		Logger.getLogger(CancelTeamAction.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		logger.info("Create/Edit/View team operation has been canceled");
		
		// remove the form-bean from the session...
		NewUtil.removeFromSession(aMapping.getName(), aRequest);
		
		// prepare the member-home screen...
		MemberHomeAction.process(aRequest, aResponse, getUtil(), getDAOUtils(), 
				getLocale(aRequest));
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}