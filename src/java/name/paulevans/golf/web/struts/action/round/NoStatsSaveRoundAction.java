package name.paulevans.golf.web.struts.action.round;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeId;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
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
public class NoStatsSaveRoundAction extends BaseAction {
	
	// logger object...
	private static Logger logger = Logger.getLogger(NoStatsSaveRoundAction.class);

	/**
	 * Process the request
	 */
	public ActionForward executeAction(ActionMapping aMapping,
            ActionForm aForm,
            HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception {
		
		// local declarations...
		RoundForm roundForm;
		ScorecardDAO newScorecard, oldScorecard;
		ScorecardSummaryDAO scorecardSummary;
		PlayerDAO player;
		
		// downcast form...
		roundForm = (RoundForm)aForm;
		
		// load the descriptions...
		roundForm.loadDescriptions();
		
		// create the scorecard dao and initialize its state...
		newScorecard = BeanFactory.newScorecardDAO();
		setState(newScorecard, roundForm, aRequest);
			
		// get the player...
		player = NewUtil.getPlayer(aRequest);
		
		// create and initialize the scorecard summary dao...
		scorecardSummary = getSummary(player, getUtil(), roundForm, 
				getDAOUtils());
		scorecardSummary.setScoreType(NewUtil.getScoreType(newScorecard));
		
		// save the scorecard and scores...
		if (roundForm.isEditMode()) {
			oldScorecard = roundForm.getScorecard();
			getDAOUtils().deleteAndSave(oldScorecard, newScorecard, 
					scorecardSummary, player, roundForm.getStartingHoles());
		} else {
			getDAOUtils().saveScorecard(newScorecard, scorecardSummary, 
				player, roundForm.getStartingHoles(), null);
		}
		player.refreshSummary(getDAOUtils(), getUtil(), getLocale(aRequest));
		
		// put the scorecard ID into the session...
		aRequest.getSession().setAttribute(
				AttributeKeyConstants.SCORECARD_ID_PARAM, newScorecard.getId());
		
		// return success...
		return aMapping.findForward(WebConstants.SUCCESS);
	}
	
	/**
	 * Creates the scorecard summary dao based on the form bean data.
	 * @param aForm
	 * @return
	 */
	private static final ScorecardSummaryDAO getSummary(PlayerDAO aPlayer, 
			NewUtil aNewUtil, RoundForm aForm, DAOUtils aDAOUtils) {
		
		ScorecardSummaryDAO scorecardSummary;
		int numHolesPlayed;
		int factor;
		
		factor = 1;
		numHolesPlayed = aForm.getTotalNumHolesPlayed();
		if (numHolesPlayed == 9) {
			factor = 2;
		}
		scorecardSummary = BeanFactory.newScorecardSummaryDAO();
		scorecardSummary.setHandicap(NewUtil.calculateHandicapIndex(
				aPlayer.getSummary(), aPlayer.getRecentMergedRounds(), 
				aDAOUtils));	
		scorecardSummary.setNumHolesPlayed(numHolesPlayed);
		scorecardSummary.setScore(Integer.parseInt(aForm.getOverallScore())
				* factor);
		scorecardSummary.setDatePlayed(aNewUtil.parse(aForm.getDate()));
		return scorecardSummary;
	}
	
	/**
	 * Set the state of aScorecard using data from aForm
	 * @param aScorecard
	 * @param aForm
	 */
	private final void setState(ScorecardDAO aScorecard, RoundForm aForm, 
			HttpServletRequest aRequest) {
		
		TeeDAO tee;
		TeeId teeId;
		CourseDAO homeCourse;
		boolean isHomeScore;
		
		homeCourse = NewUtil.getPlayer(aRequest).getCourse();
		isHomeScore = false;
		if (homeCourse != null) {
			isHomeScore = ((Integer)homeCourse.getId()) == aForm.getCourseId();
		}
		aScorecard.setIsHome(isHomeScore);
		aScorecard.setDate(getUtil().parse(aForm.getDate()));
		aScorecard.setWeatherCondition(aForm.getWeatherConditionDAO());
		aScorecard.setEyeWear(aForm.getEyeWearDAO());
		aScorecard.setHeadWear(aForm.getHeadWearDAO());
		aScorecard.setPantWear(aForm.getPantWearDAO());
		aScorecard.setWoreLongSleeves(aForm.getLongSleevesWorn());
		aScorecard.setWoreVest(aForm.getVestWorn());
		aScorecard.setIsTournament(aForm.getIsTournamentScore());
		tee = BeanFactory.newTeeDAO();
		tee.setCourse(aForm.getCourseDAO());
		teeId = new TeeId();
		teeId.setCourseId(aForm.getCourseId());
		teeId.setTeeNameId(aForm.getCourseTeeNameId());
		tee.setId(teeId);
		aScorecard.setTee(tee);
		aScorecard.setPlayer(NewUtil.getPlayer(aRequest));
		aScorecard.setAttestedBy(aForm.getAttestedBy());
		aScorecard.setScorer(aForm.getScorer());
		aScorecard.setUsedCaddie(aForm.getUsedCaddie());
		aScorecard.setUsedCart(aForm.getUsedCart());
		aScorecard.setJournalNotes(aForm.getJournalNotes());
	}
}