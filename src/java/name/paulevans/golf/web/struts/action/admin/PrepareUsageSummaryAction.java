package name.paulevans.golf.web.struts.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.UsageSummaryDAO;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Prepares the usage summary screen
 * @author pevans
 *
 */
public class PrepareUsageSummaryAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		UsageSummaryDAO usageSummaryDAO;
		HttpSession httpSession;
		
		// get the usage summary DAO...
		usageSummaryDAO = BeanFactory.getUsageSummaryDAO();
		
		// load the usage summary data...
		usageSummaryDAO.load();
		
		// get the HTTP session...
		httpSession = aRequest.getSession();
		
		// put usage summary DAO into the session...
		httpSession.setAttribute(AttributeKeyConstants.USAGE_SUMMARY, 
				usageSummaryDAO);
			
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
