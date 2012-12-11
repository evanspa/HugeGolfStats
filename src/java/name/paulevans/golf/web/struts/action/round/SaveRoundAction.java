package name.paulevans.golf.web.struts.action.round;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScoreDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeId;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;
import name.paulevans.golf.web.struts.action.BaseAction;
import name.paulevans.golf.web.struts.formbean.BaseForm;
import name.paulevans.golf.web.struts.formbean.RoundForm;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes a request
 * @author pevans
 *
 */
public class SaveRoundAction extends BaseAction {
	
	// logger object...
	private static Logger logger = Logger.getLogger(SaveRoundAction.class);

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
		ScoreDAO scores[];
		PlayerDAO player;
		
		// downcast form...
		roundForm = (RoundForm)aForm;
		
		// load the descriptions...
		roundForm.loadDescriptions();
		
		// create the scorecard dao and initialize its state...
		newScorecard = BeanFactory.newScorecardDAO();
		setState(newScorecard, roundForm, aRequest);
		
		// create the scores...
		scores = createScores(roundForm);
		
		// get the player...
		player = NewUtil.getPlayer(aRequest);
		
		// create and initialize the scorecard summary dao...
		scorecardSummary = getSummary(player, getUtil(), roundForm, 
				getDAOUtils());
		scorecardSummary.setScoreType(NewUtil.getScoreType(newScorecard));
		
		// save the scorecard and scores...
		if (roundForm.isEditMode()) {
			oldScorecard = roundForm.getScorecard();
			getDAOUtils().deleteAndSave(oldScorecard, newScorecard, scores, 
					scorecardSummary, player, roundForm.getStartingHoles());
		} else {
			getDAOUtils().saveScorecard(newScorecard, scores, scorecardSummary, 
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
	public static final ScorecardSummaryDAO getSummary(PlayerDAO aPlayer, 
			NewUtil aNewUtil, RoundForm aForm, DAOUtils aDAOUtils) {
		
		ScorecardSummaryDAO scorecardSummary;
		int numHolesPlayed;
		int factor;
		Integer totalPuttsGIR;
		
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
		scorecardSummary.setNumEagles(aForm.getNumEagles() * factor);
		scorecardSummary.setNumBirdies(aForm.getNumBirdies() * factor);
		scorecardSummary.setNumPars(aForm.getNumPars() * factor);
		scorecardSummary.setNumBogeys(aForm.getNumBogeys() * factor);
		scorecardSummary.setNumDblBogeys(aForm.getNumDblBogeys() * factor);
		scorecardSummary.setNumTplBogeys(aForm.getNumTplBogeys() * factor);
		scorecardSummary.setPar3Avg(aForm.getPar3Avg());
		scorecardSummary.setPar4Avg(aForm.getPar4Avg());
		scorecardSummary.setPar5Avg(aForm.getPar5Avg());
		if (BooleanUtils.toBoolean(aForm.getCollectFairwayHit())) {
			scorecardSummary.setTeeShotAccuracy(
					aForm.getFairwaysHitPercentage());
		}
		if (BooleanUtils.toBoolean(aForm.getCollectSandSave())) {
			scorecardSummary.setSandSaveConvert(
					aForm.getSandSaveConversionPercentage());
			scorecardSummary.setNumSandSaveOpportunities(
					new Float(aForm.getNumSandSaveAttempts() * factor));
		}
		if (BooleanUtils.toBoolean(aForm.getCollectUpDown())) {
			scorecardSummary.setUpDownConvert(
					aForm.getUpAndDownConversionPercentage());
			scorecardSummary.setNumUpDownOpportunities(
					new Float(aForm.getNumUpAndDownAttempts() * factor));
		}
		if (BooleanUtils.toBoolean(aForm.getCollectNumPutts())) {
			scorecardSummary.setTotalPutts(new Float(
					aForm.getTotalPutts() * factor));
			scorecardSummary.setAvgPutts(aForm.getAvgPuttsPerHole());
		}
		if (BooleanUtils.toBoolean(aForm.getCollectNumPutts()) &&
			BooleanUtils.toBoolean(aForm.getCollectGir())) {
			totalPuttsGIR = aForm.getTotalPuttsGIR();
			scorecardSummary.setTotalPuttsGir(totalPuttsGIR != null ? (
					new Float(totalPuttsGIR * factor)) : null);
			scorecardSummary.setAvgPuttsGir(aForm.getAvgPuttsPerHoleGIR());
		}
		if (BooleanUtils.toBoolean(aForm.getCollectGir())) {
			scorecardSummary.setGirAvg(aForm.getGIRsPercentage());
		}
		if (BooleanUtils.toBoolean(aForm.getCollectTeeShotDistance())) {
			scorecardSummary.setDrivingDistanceAvg(
					aForm.getAvgTeeShotDistance());
			scorecardSummary.setLongestDrive(new Float(
					aForm.getLongestDrive()));
		}
		if (BooleanUtils.toBoolean(aForm.getCollectApproachShotLine())) {
			scorecardSummary.setApproachShotLine(
					aForm.getCenterLineApproachShotPercentage());
		}
		if (BooleanUtils.toBoolean(aForm.getCollectApproachShotDistance())) {
			scorecardSummary.setApproachShotDistance(
					aForm.getPinHighApproachShotPercentage());
		}
		scorecardSummary.setScore(aForm.getTotalScore() * factor);
		scorecardSummary.setDatePlayed(aNewUtil.parse(aForm.getDate()));
		return scorecardSummary;
	}
	
	/**
	 * Creates the score objects from the form bean.
	 * @param aForm
	 * @return
	 */
	private final ScoreDAO[] createScores(RoundForm aForm) {
		
		HoleDAO holesPlayed[];
		List<ScoreDAO> scores;
		ScoreDAO score;
		int holeNum, driveDistance;
		String holeNumStr;
		
		scores = new ArrayList<ScoreDAO>();
		// get the collection of holes played...
		holesPlayed = aForm.getHoleDAOsPlayed();
		for (HoleDAO hole : holesPlayed) {
			holeNum = hole.getNumber();
			holeNumStr = Integer.toString(holeNum);
			score = BeanFactory.newScoreDAO();
			score.setHole(hole);
			score.setScore(Integer.parseInt(aForm.getScore(holeNumStr)));
			if (BooleanUtils.toBoolean(
					aForm.getCollectApproachShotDistance())) {
				score.setApproachShotDistance(aForm.getApproacheShotDistanceDAO(
					holeNum));
			}
			if (BooleanUtils.toBoolean(aForm.getCollectApproachShotLine())) {
				score.setApproachShotLine(aForm.getApproacheShotLineDAO(
						holeNum));
			}
			if (BooleanUtils.toBoolean(aForm.getCollectGir())) {
				score.setGreenInRegulation(aForm.getGreenInRegulationDAO(
						holeNum));
			}
			if (BooleanUtils.toBoolean(aForm.getCollectFairwayHit())) {
				score.setTeeShotAccuracy(aForm.getTeeShotAccuracyDAO(holeNum));
			}
			if (BooleanUtils.toBoolean(aForm.getCollectNumPutts())) {
				score.setNumPutts(NumberUtils.createInteger(aForm.getNumPutts(
						holeNumStr)));
			}
			if (BooleanUtils.toBoolean(aForm.getCollectSandSave())) {
				score.setSandSaveAttempted(BooleanUtils.toBoolean(
						aForm.getSandSaveAttempt(holeNumStr)));
				score.setIfSandSaveConversion(BooleanUtils.toBoolean(
						aForm.getSandSaveConvert(holeNumStr)));
			}
			if (BooleanUtils.toBoolean(aForm.getCollectUpDown())) {
				score.setUpAndDownAttempted(BooleanUtils.toBoolean(
						aForm.getUpDownAttempt(holeNumStr)));
				score.setIfUpAndDownConversion(BooleanUtils.toBoolean(
						aForm.getUpDownConvert(holeNumStr)));
			}
			if (BooleanUtils.toBoolean(aForm.getCollectClubUsedOffTee())) {
				score.setGolfClub(aForm.getGolfClubDAO(holeNum));
			}
			if (BooleanUtils.toBoolean(aForm.getCollectTeeShotDistance())) {
				driveDistance = NumberUtils.toInt(
						aForm.getTeeShotDistance(holeNumStr));
				if (driveDistance != BaseForm.NA_VALUE &&
						driveDistance > 0) {
					score.setDriveDistance(driveDistance);
				}
			}
			scores.add(score);
		}
		return scores.toArray(new ScoreDAO[scores.size()]);
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
		aScorecard.setCollectApproachShotDistance(BooleanUtils.toBoolean(
				aForm.getCollectApproachShotDistance()));
		aScorecard.setCollectApproachShotLine(BooleanUtils.toBoolean(
				aForm.getCollectApproachShotLine()));
		aScorecard.setCollectGir(BooleanUtils.toBoolean(
				aForm.getCollectGir()));
		aScorecard.setCollectClubUsedOffTee(BooleanUtils.toBoolean(
				aForm.getCollectClubUsedOffTee()));
		aScorecard.setCollectTeeShotDistance(BooleanUtils.toBoolean(
				aForm.getCollectTeeShotDistance()));
		aScorecard.setCollectFairwayHit(BooleanUtils.toBoolean(
				aForm.getCollectFairwayHit()));
		aScorecard.setCollectNumPutts(BooleanUtils.toBoolean(
				aForm.getCollectNumPutts()));
		aScorecard.setCollectSandSave(BooleanUtils.toBoolean(
				aForm.getCollectSandSave()));
		aScorecard.setCollectUpDown(BooleanUtils.toBoolean(
				aForm.getCollectUpDown()));
	}
}