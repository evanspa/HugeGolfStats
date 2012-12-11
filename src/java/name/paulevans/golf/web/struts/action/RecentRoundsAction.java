package name.paulevans.golf.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.util.SortUtils;
import name.paulevans.golf.web.WebConstants;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes the view recent-rounds request
 * @author pevans
 *
 */
public class RecentRoundsAction extends BaseAction {
	
	// logger object...
	private static Logger logger = Logger.getLogger(
			RecentRoundsAction.class);

	/**
	 * Processes the recent-rounds request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		String sortField;
		
		// get the sort column from the request...
		sortField = aRequest.getParameter(WebConstants.SORT_COLUMN_PARAM);
		
		// if the sort column is not null, then sort the rounds...
		if (sortField != null) {
			SortUtils.sort(NewUtil.getPlayer(aRequest).getRounds(),
					sortField, aRequest);
		}
		
		// clear "stray" objects from session...
		NewUtil.removeStrayObjectsFromSession(aRequest);
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}