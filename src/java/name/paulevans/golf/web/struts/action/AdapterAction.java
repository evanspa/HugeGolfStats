package name.paulevans.golf.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.web.WebConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class AdapterAction extends BaseAction {

	/**
	 * Empty implementation.
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}