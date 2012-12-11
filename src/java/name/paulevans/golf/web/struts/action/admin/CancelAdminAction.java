package name.paulevans.golf.web.struts.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the cancellation of adding a course
 * @author pevans
 *
 */
public class CancelAdminAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private final static Logger logger = 
		Logger.getLogger(CancelAdminAction.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		logger.info("View player operation has been canceled");
		
		// remove the form-bean and other objects from the session...
		NewUtil.removeFromSession(AttributeKeyConstants.PLAYERS, aRequest);
		NewUtil.removeFromSession(aMapping.getName(), aRequest);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}