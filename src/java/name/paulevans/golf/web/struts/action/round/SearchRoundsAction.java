package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Searches for a round
 * @author pevans
 *
 */
public class SearchRoundsAction extends BaseAction {
	
	// logger object...
	private static final Logger logger = Logger.getLogger(
			SearchRoundsAction.class);

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		RoundForm form;
		ScorecardSummaryDAO searchResults[];
		PlayerDAO player;
		DAOUtils daoUtils;
		HttpSession session;
		
		// downcast the form...
		form = (RoundForm)aForm;
		
		// get the player...
		player = NewUtil.getPlayer(aRequest);
		
		// get the session...
		session = aRequest.getSession();
		
		// get the daoUtils object...
		daoUtils = getDAOUtils();
		
		// get the search results...
		searchResults = daoUtils.searchRounds(form.getCity(), 
				form.getCountryId(), form.getStateProvinceId(), 
				form.getCourseName(), form.getDate(), (Integer)player.getId(), 
				form.getFirstSearchResultNum(), form.getMaxSearchResultsNum());
		
		// add the total result count into the session if this isn't a 
		// paging-action...
		if (StringUtils.equals(form.getPageAction(), Constants.FALSE)) {
			session.setAttribute(AttributeKeyConstants.TOTAL_RESULTS_COUNT, 
					daoUtils.searchRoundsCount(form.getCity(), 
							form.getCountryId(), form.getStateProvinceId(), 
							form.getCourseName(), form.getDate(), 
							(Integer)player.getId()));
		}
		
		// add the results to the session...
		session.setAttribute(AttributeKeyConstants.ROUNDS, searchResults);	
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
