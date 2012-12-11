package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.util.SortUtils;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Sorts the rounds search-results
 * @author pevans
 *
 */
public class SortSearchResultsAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		ScorecardSummaryDAO rounds[];
		
		// get the rounds search-results...
		rounds = (ScorecardSummaryDAO[])aRequest.getSession().getAttribute(
				AttributeKeyConstants.ROUNDS);
		
		// sort the rounds...
		SortUtils.sort(rounds, aRequest.getParameter(
				WebConstants.SORT_COLUMN_PARAM), aRequest);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
