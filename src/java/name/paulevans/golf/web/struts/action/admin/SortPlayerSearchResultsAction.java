package name.paulevans.golf.web.struts.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.formatter.PlayerFormatter;
import name.paulevans.golf.util.SortUtils;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Sorts the courses search-results
 * @author pevans
 *
 */
public class SortPlayerSearchResultsAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		PlayerFormatter players[];
		
		// get the courses search-results...
		players = (PlayerFormatter[])aRequest.getSession().getAttribute(
				AttributeKeyConstants.PLAYERS);
		
		// sort the courses...
		SortUtils.sort(players, aRequest.getParameter(
				WebConstants.SORT_COLUMN_PARAM), aRequest);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
