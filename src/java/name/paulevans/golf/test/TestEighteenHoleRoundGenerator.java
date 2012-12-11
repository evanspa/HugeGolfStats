package name.paulevans.golf.test;

import junit.framework.TestCase;
import name.paulevans.golf.Constants;
import name.paulevans.golf.EighteenHoleRoundGenerator;
import name.paulevans.golf.dao.ScorecardSummaryDAO;

/**
 * Test class for EighteenHoleRoundGenerator
 * @author pevans
 *
 */
public class TestEighteenHoleRoundGenerator extends TestCase {
	
	private EighteenHoleRoundGenerator generator;

	/**
	 * Test method for {@link name.paulevans.golf.EighteenHoleRoundGenerator#
	 * processRound(name.paulevans.golf.dao.ScorecardSummaryDAO)}.
	 */
	public final void testProcessRound() {
		
		ScorecardSummaryDAO round1, round2, round3, round4, round5, round6, 
			round7;
		ScorecardSummaryDAO eighteenHoleRounds[];
		
		// create the rounds...
		round1 = TestUtils.createRound(44, 34.2F, 119, 
				Constants.HOME_SCORE_TYPE); 
		round2 = TestUtils.createRound(41, 34.2F, 119, 
				Constants.HOME_SCORE_TYPE);
		round3 = TestUtils.createRound(38, 32.1F, 112, 
				Constants.AWAY_SCORE_TYPE);
		round4 = TestUtils.createRound(39, 33.2F, 114, 
				Constants.AWAY_SCORE_TYPE);
		round5 = TestUtils.createRound(40, 34.2F, 119, 
				Constants.HOME_SCORE_TYPE);
		round6 = TestUtils.createRound(35, 31.2F, 105, 
				Constants.AWAY_SCORE_TYPE);
		round7 = TestUtils.createRound(44, 37.2F, 118, 
				Constants.HOME_SCORE_TYPE);

		// process rounds 1-5...
		generator = new EighteenHoleRoundGenerator();
		generator.processRound(round1);
		generator.processRound(round2);
		generator.processRound(round3);
		generator.processRound(round4);
		generator.processRound(round5);
		eighteenHoleRounds = generator.getRounds(20);
		assertEquals(2, eighteenHoleRounds.length);
		assertEquals(85, eighteenHoleRounds[0].getScore());
		assertEquals(68.4F, eighteenHoleRounds[0].getRating());
		assertEquals(119, eighteenHoleRounds[0].getSlope().intValue());
		assertEquals(77, eighteenHoleRounds[1].getScore());
		assertEquals(65.3F, eighteenHoleRounds[1].getRating());
		assertEquals(113, eighteenHoleRounds[1].getSlope().intValue());
		
		// process rounds 1-7...
		generator = new EighteenHoleRoundGenerator();
		generator.processRound(round1);
		generator.processRound(round2);
		generator.processRound(round3);
		generator.processRound(round4);
		generator.processRound(round5);
		generator.processRound(round6);
		generator.processRound(round7);
		eighteenHoleRounds = generator.getRounds(20);
		assertEquals(3, eighteenHoleRounds.length);
		assertEquals(85, eighteenHoleRounds[0].getScore()); // round 1
		assertEquals(68.4F, eighteenHoleRounds[0].getRating());
		assertEquals(119, eighteenHoleRounds[0].getSlope().intValue());
		assertEquals(84, eighteenHoleRounds[1].getScore()); // round 2
		assertEquals(71.4F, eighteenHoleRounds[1].getRating());
		assertEquals(119, eighteenHoleRounds[1].getSlope().intValue());
		assertEquals(77, eighteenHoleRounds[2].getScore()); // round 3
		assertEquals(65.3F, eighteenHoleRounds[2].getRating());
		assertEquals(113, eighteenHoleRounds[2].getSlope().intValue());
	}
}
