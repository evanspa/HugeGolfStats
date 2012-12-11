package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PostRoundPageTwoSubmitAction extends BaseAction {
	
	/**
	 * Logger object
	 */
	private static Logger logger = Logger.getLogger(
			PostRoundPageTwoSubmitAction.class);

	/**
	 * Process the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// init the lookup display maps...
		RoundForm.initDisplayLookupMaps(getDAOUtils(), aRequest.getLocale());
		
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}