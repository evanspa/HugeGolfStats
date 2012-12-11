package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Prepare to edit a round
 * @author pevans
 *
 */
public class PrepareEditRoundAction extends BaseAction {

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
		CourseDAO courses[];
	
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

		// prepare the form...
		courses = PreparePostRoundAction.prepareForm(aRequest.getSession(), 
				getDAOUtils(), getUtil(), form, aRequest, scorecard,
				getLocale(aRequest));
		
		// prepare the holes-played info...
		PreparePostRoundAction.prepareHolesPlayed(aRequest.getSession(), 
				getDAOUtils(), getUtil(), form, aRequest, null, courses);
		
		// put the scorecard id param back into the request...
		aRequest.getSession().setAttribute(
				AttributeKeyConstants.SCORECARD_ID_PARAM, 
				scorecard.getId().toString());
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
}
