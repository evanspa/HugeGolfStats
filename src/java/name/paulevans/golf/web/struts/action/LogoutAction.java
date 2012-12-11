package name.paulevans.golf.web.struts.action;

import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the logout
 * @author pevans
 *
 */
public class LogoutAction extends BaseAction {
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(LogoutAction.class);

	/**
	 * Processes the logout request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		Principal principal;
		HttpSession httpSession;
		NewUtil util;
		
		// get the util object...
		util = getUtil();
		
		// empty the session...
		httpSession = aRequest.getSession();
		emptySession(httpSession);
		
		principal = aRequest.getUserPrincipal();
		if (principal != null) {
			httpSession.invalidate();
			logger.info("User: [" + principal.getName() + 
					"] has been logged-out");
		}
		
		// re-create the session and re-add the util object to the session...
		httpSession = aRequest.getSession(true);
		httpSession.setAttribute(AttributeKeyConstants.UTIL, util);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
	
	/**
	 * Empties aSession of all its attribute objects.
	 * @param aSession
	 */
	private void emptySession(HttpSession aSession) {
		
		Enumeration attributeNames;
		String attrName;
		
		attributeNames = aSession.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			attrName = (String)attributeNames.nextElement();
			aSession.removeAttribute(attrName);
		}
	}
}