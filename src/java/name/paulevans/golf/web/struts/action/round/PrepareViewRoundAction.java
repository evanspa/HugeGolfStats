package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.commons.lang.BooleanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Prepare to view a round
 * @author pevans
 *
 */
public class PrepareViewRoundAction extends BaseAction {

	/**
	 * Processes the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		RoundForm form;
		ScorecardDAO scorecard;
		ScorecardSummaryDAO scorecardSummary;
	
		form = (RoundForm)aForm;
		
		// create and load the scorecard dao...
		scorecard = BeanFactory.newScorecardDAO();
		scorecard.setId(Integer.parseInt(aRequest.getParameter(
				AttributeKeyConstants.SCORECARD_ID_PARAM)));
		scorecard.load();

		// load the summary object...
		scorecardSummary = BeanFactory.newScorecardSummaryDAO();
		scorecardSummary.setId(scorecard.getId());
		scorecardSummary.load();
		
		// init form bean using the course DAO...
		form.initialize(getUtil(), scorecard, scorecardSummary);
		
		// init the lookup display maps...
		RoundForm.initDisplayLookupMaps(getDAOUtils(), aRequest.getLocale());
		
		// load the descriptions...
		form.loadDescriptions();
		form.loadHoles();
		
		// put the scorecard ID into the request...
		aRequest.setAttribute(AttributeKeyConstants.SCORECARD_ID_PARAM, 
				scorecard.getId());
		
		if (BooleanUtils.toBoolean(form.getCollectPerHoleStats())) { 
			return aMapping.findForward(WebConstants.COLLECT_STATS);
		} else {
			return aMapping.findForward(WebConstants.NOT_COLLECT_STATS);
		}
	}
}
