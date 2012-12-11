package name.paulevans.golf.web.struts.action.stats;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.StatisticsForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class PreparePrintFriendlyAction extends BaseAction {

	/**
	 * Prepares the approach-shot stats screen
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// locals...
		StatisticsForm form;
		
		// get the form...
		form = (StatisticsForm)aForm;
		
		// load the form descriptions...
		form.loadDescriptions(getDAOUtils(), getLocale(aRequest));		
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}