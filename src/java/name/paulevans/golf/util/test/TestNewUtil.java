package name.paulevans.golf.util.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.bean.MasterSummary;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.test.TestUtils;
import name.paulevans.golf.util.NewUtil;

/**
 * Test fixture for NewUtil class
 * @author pevans
 *
 */
public class TestNewUtil extends TestCase {

	/**
	 * Test case for getStartingHole()
	 *
	 */
	public final void testGetStartingHole() {
		assertEquals(1, NewUtil.getStartingHole("1-9"));
		assertEquals(10, NewUtil.getStartingHole("10-18"));
	}
	
	//public static String calculateHandicapIndexReduced(Float aHandicapIndex, 
	//		ScorecardSummaryDAO aRounds[], DAOUtils aDAOUtils) {
	
	/**
	 * Test fixture for calculateHandicapIndexReduced(Float, 
	 * ScorecardSummaryDAO[], DAOUtils)
	 */
	public final void testCalculateHandicapIndexReduced() {
		
		ScorecardSummaryDAO rounds[];
		DAOUtils daoUtils;
		BigDecimal currentHandicapIndex, reductionValue;
		
		// get the daoUtils object...
		daoUtils = BeanFactory.getDAOUtils();
		
		rounds = new ScorecardSummaryDAO[3];
		rounds[0] = TestUtils.createRound(85, 70.6F, 130, new Date(), 
				Constants.TOURNAMENT_SCORE_TYPE);
		rounds[1] = TestUtils.createRound(82, 70.6F, 130, new Date(), 
				Constants.TOURNAMENT_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(83, 70.6F, 130, new Date(), 
				Constants.TOURNAMENT_SCORE_TYPE);
		currentHandicapIndex = BigDecimal.valueOf(17.3);
		reductionValue = NewUtil.calculateHandicapIndexReductionValue(
				currentHandicapIndex, rounds, daoUtils);
		assertEquals("12.3R", NewUtil.calculateHandicapIndexReduced(
				currentHandicapIndex, reductionValue));
	}
	
	/**
	 * Test fixture for calculateHandicapIndexReductionValue(float, 
	 * ScorecardSummaryDAO[], DAOUtils)
	 */
	public final void testCalculateHandicapIndexReductionValue() {
		
		ScorecardSummaryDAO rounds[];
		DAOUtils daoUtils;
		BigDecimal reductionValue;
		BigDecimal currentHandicapIndex;
		
		// get the daoUtils object...
		daoUtils = BeanFactory.getDAOUtils();
		
		rounds = new ScorecardSummaryDAO[3];
		rounds[0] = TestUtils.createRound(85, 70.6F, 130, new Date(), 
				Constants.TOURNAMENT_SCORE_TYPE);
		rounds[1] = TestUtils.createRound(82, 70.6F, 130, new Date(), 
				Constants.TOURNAMENT_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(83, 70.6F, 130, new Date(), 
				Constants.TOURNAMENT_SCORE_TYPE);
		currentHandicapIndex = BigDecimal.valueOf(17.3);
		reductionValue = NewUtil.calculateHandicapIndexReductionValue(
				currentHandicapIndex, rounds, daoUtils);
		assertEquals(5.0F, reductionValue.floatValue());
	}

	/**
	 * Test fixture for getEligibleTournamentScores(ScorecardSummaryDAO[])
	 * @param aRounds
	 */
	public final void testGetEligibleTournamentScores() {
		
		ScorecardSummaryDAO rounds[], tournamentRounds[];
		Calendar cal;
		
		rounds = new ScorecardSummaryDAO[1];
		rounds[0] = TestUtils.createRound(74, 69.2F, 109, new Date(), 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE);		
		tournamentRounds = NewUtil.getEligibleTournamentScores(rounds);
		assertEquals(1, tournamentRounds.length);
		
		rounds = new ScorecardSummaryDAO[3];
		rounds[0] = TestUtils.createRound(74, 69.2F, 109, new Date(), 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE);
		rounds[1] = TestUtils.createRound(74, 69.2F, 109, new Date(), 
				Constants.AWAY_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(74, 69.2F, 109, new Date(), 
				Constants.HOME_SCORE_TYPE);
		tournamentRounds = NewUtil.getEligibleTournamentScores(rounds);
		assertEquals(1, tournamentRounds.length);
	
		rounds = new ScorecardSummaryDAO[8];
		rounds[0] = TestUtils.createRound(74, 69.2F, 109, new Date(), 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE); // T=YES
		rounds[1] = TestUtils.createRound(74, 69.2F, 109, new Date(), 
				Constants.AWAY_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(74, 69.2F, 109, new Date(), 
				Constants.HOME_SCORE_TYPE);
		rounds[3] = TestUtils.createRound(75, 69.2F, 109, new Date(), 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE); // T=YES
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 13); // add 13 months...
		rounds[4] = TestUtils.createRound(76, 69.2F, 109, cal.getTime(), 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE);
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 12); // add 12 months...
		rounds[5] = TestUtils.createRound(77, 69.2F, 109, cal.getTime(), 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE); //T=YES
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 12); // add 12 months...
		cal.add(Calendar.DATE, 1); // add 1 day...
		rounds[6] = TestUtils.createRound(78, 69.2F, 109, cal.getTime(), 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE);
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 11); // add 11 months...
		cal.add(Calendar.DATE, 26); // add 26 days...
		rounds[7] = TestUtils.createRound(79, 69.2F, 109, cal.getTime(), 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE); //T=YES
		tournamentRounds = NewUtil.getEligibleTournamentScores(rounds);
		assertEquals(4, tournamentRounds.length);
	}
	
	/**
	 * Test case for calculateHandicapIndex(MasterSummary, ScorecardSummaryDAO, 
	 * 	int);
	 */
	public final void testCalculateHandicapIndex() {
		
		MasterSummary summary;
		ScorecardSummaryDAO rounds[];
		int homeCourseId;
		BigDecimal handicapIndex;
		DAOUtils daoUtils;
		
		// get the DAO utils object...
		daoUtils = BeanFactory.getDAOUtils();
		
		// set the home course id...
		homeCourseId = 0;
		
		// create the summary object and rounds...
		summary = TestUtils.newSummary(20);
		rounds = new ScorecardSummaryDAO[20];
		rounds[0] = TestUtils.createRound(79, 71.4F, 118, 
				Constants.AWAY_SCORE_TYPE);
		rounds[1] = TestUtils.createRound(72, 70.2F, 123, 
				Constants.AWAY_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(81, 68.4F, 121, 
				Constants.AWAY_SCORE_TYPE);
		rounds[3] = TestUtils.createRound(76, 72.9F, 104, 
				Constants.AWAY_SCORE_TYPE);
		rounds[4] = TestUtils.createRound(77, 69.9F, 108, 
				Constants.HOME_SCORE_TYPE);
		rounds[5] = TestUtils.createRound(92, 62.4F, 111, 
				Constants.AWAY_SCORE_TYPE);
		rounds[6] = TestUtils.createRound(88, 72.1F, 127, 
				Constants.HOME_SCORE_TYPE);
		rounds[7] = TestUtils.createRound(81, 74.6F, 118, 
				Constants.AWAY_SCORE_TYPE);
		rounds[8] = TestUtils.createRound(77, 74.1F, 129, 
				Constants.AWAY_SCORE_TYPE);
		rounds[9] = TestUtils.createRound(80, 68.4F, 121, 
				Constants.AWAY_SCORE_TYPE);
		rounds[10] = TestUtils.createRound(81, 70.0F, 109, 
				Constants.AWAY_SCORE_TYPE);
		rounds[11] = TestUtils.createRound(74, 67.9F, 105, 
				Constants.HOME_SCORE_TYPE);
		rounds[12] = TestUtils.createRound(72, 66.4F, 104, 
				Constants.AWAY_SCORE_TYPE);
		rounds[13] = TestUtils.createRound(80, 71.5F, 118, 
				Constants.HOME_SCORE_TYPE);
		rounds[14] = TestUtils.createRound(77, 72.2F, 113, 
				Constants.HOME_SCORE_TYPE);
		rounds[15] = TestUtils.createRound(82, 66.6F, 119, 
				Constants.AWAY_SCORE_TYPE);
		rounds[16] = TestUtils.createRound(88, 74.1F, 129, 
				Constants.AWAY_SCORE_TYPE);
		rounds[17] = TestUtils.createRound(87, 74.2F, 110, 
				Constants.HOME_SCORE_TYPE);
		rounds[18] = TestUtils.createRound(88, 69.8F, 113, 
				Constants.AWAY_SCORE_TYPE);
		rounds[19] = TestUtils.createRound(87, 71.1F, 117, 
				Constants.HOME_SCORE_TYPE);
		handicapIndex = NewUtil.calculateHandicapIndex(summary, rounds, 
				daoUtils);
		assertEquals(5.1F, handicapIndex.floatValue());

		// create the summary object and rounds...
		summary = TestUtils.newSummary(15);
		rounds = new ScorecardSummaryDAO[15];
		rounds[0] = TestUtils.createRound(79, 71.4F, 118, 
				Constants.AWAY_SCORE_TYPE);
		rounds[1] = TestUtils.createRound(72, 70.2F, 123, 
				Constants.AWAY_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(81, 68.4F, 121, 
				Constants.AWAY_SCORE_TYPE);
		rounds[3] = TestUtils.createRound(76, 72.9F, 104, 
				Constants.AWAY_SCORE_TYPE);
		rounds[4] = TestUtils.createRound(77, 69.9F, 108, 
				Constants.HOME_SCORE_TYPE);
		rounds[5] = TestUtils.createRound(92, 62.4F, 111, 
				Constants.AWAY_SCORE_TYPE);
		rounds[6] = TestUtils.createRound(88, 72.1F, 127, 
				Constants.HOME_SCORE_TYPE);
		rounds[7] = TestUtils.createRound(81, 74.6F, 118, 
				Constants.AWAY_SCORE_TYPE);
		rounds[8] = TestUtils.createRound(77, 74.1F, 129, 
				Constants.AWAY_SCORE_TYPE);
		rounds[9] = TestUtils.createRound(80, 68.4F, 121, 
				Constants.AWAY_SCORE_TYPE);
		rounds[10] = TestUtils.createRound(81, 70.0F, 109, 
				Constants.AWAY_SCORE_TYPE);
		rounds[11] = TestUtils.createRound(74, 67.9F, 105, 
				Constants.HOME_SCORE_TYPE);
		rounds[12] = TestUtils.createRound(72, 66.4F, 104, 
				Constants.AWAY_SCORE_TYPE);
		rounds[13] = TestUtils.createRound(80, 71.5F, 118, 
				Constants.HOME_SCORE_TYPE);
		rounds[14] = TestUtils.createRound(77, 72.2F, 113, 
				Constants.HOME_SCORE_TYPE);
		handicapIndex = NewUtil.calculateHandicapIndex(summary, rounds,
				daoUtils);
		assertEquals(3.9F, handicapIndex.floatValue());		
		
		// create the summary object and rounds...
		summary = TestUtils.newSummary(7);
		rounds = new ScorecardSummaryDAO[7];
		rounds[0] = TestUtils.createRound(90, 71.3F, 122, 
				Constants.AWAY_SCORE_TYPE);
		rounds[1] = TestUtils.createRound(82, 71.0F, 127, 
				Constants.AWAY_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(83, 71.0F, 127, 
				Constants.AWAY_SCORE_TYPE);
		rounds[3] = TestUtils.createRound(82, 69.1F, 113, 
				Constants.AWAY_SCORE_TYPE);
		rounds[4] = TestUtils.createRound(86, 71.8F, 116, 
				Constants.HOME_SCORE_TYPE);
		rounds[5] = TestUtils.createRound(87, 72.9F, 130, 
				Constants.AWAY_SCORE_TYPE);
		rounds[6] = TestUtils.createRound(83, 69.5F, 113, 
				Constants.HOME_SCORE_TYPE);
		handicapIndex = NewUtil.calculateHandicapIndex(summary, rounds, 
				daoUtils);
		assertEquals(9.8F, handicapIndex.floatValue());
		
		// create the summary object and rounds...
		summary = TestUtils.newSummary(5);
		rounds = new ScorecardSummaryDAO[5];
		rounds[0] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		rounds[1] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		rounds[3] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		rounds[4] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		handicapIndex = NewUtil.calculateHandicapIndex(summary, rounds, 
				daoUtils);
		assertEquals(5.9F, handicapIndex.floatValue());
		
		// create the summary object and rounds...
		summary = TestUtils.newSummary(0);
		rounds = new ScorecardSummaryDAO[0];
		handicapIndex = NewUtil.calculateHandicapIndex(summary, rounds, 
				daoUtils);
		assertNull(handicapIndex);
		
		// create the summary object and rounds...
		summary = TestUtils.newSummary(4);
		rounds = new ScorecardSummaryDAO[4];
		rounds[0] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		rounds[1] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		rounds[2] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		rounds[3] = TestUtils.createRound(75, 68.4F, 121, 
				Constants.HOME_SCORE_TYPE);
		handicapIndex = NewUtil.calculateHandicapIndex(summary, rounds, 
				daoUtils);
		assertNull(handicapIndex);
	}
}
