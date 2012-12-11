package name.paulevans.golf.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the view-screenshot
 * @author pevans
 *
 */
public class PrepareViewScreenshotAction extends BaseAction {
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(PrepareViewScreenshotAction.class);

	/**
	 * Processes the view-screenshot request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		aRequest.setAttribute(AttributeKeyConstants.SCREENSHOT_IMAGEFILE_ATTR, 
				aRequest.getParameter("imagefile"));
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}