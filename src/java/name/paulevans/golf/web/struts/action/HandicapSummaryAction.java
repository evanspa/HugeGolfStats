package name.paulevans.golf.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.WebConstants;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the handicap summary request
 * @author pevans
 *
 */
public class HandicapSummaryAction extends BaseAction {
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(
			HandicapSummaryAction.class);

	/**
	 * Processes the handicap-summary request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}