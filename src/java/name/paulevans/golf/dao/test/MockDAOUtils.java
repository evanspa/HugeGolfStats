package name.paulevans.golf.dao.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import name.paulevans.golf.dao.ApproachShotDistanceDAO;
import name.paulevans.golf.dao.ApproachShotLineDAO;
import name.paulevans.golf.dao.CalendarMonthDAO;
import name.paulevans.golf.dao.CountryDAO;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.EyeWearDAO;
import name.paulevans.golf.dao.GolfClubDAO;
import name.paulevans.golf.dao.GreenInRegulationDAO;
import name.paulevans.golf.dao.HeadWearDAO;
import name.paulevans.golf.dao.PantWearDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScoreDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeNameDAO;
import name.paulevans.golf.dao.TeeShotAccuracyDAO;
import name.paulevans.golf.dao.WeatherConditionDAO;

/**
 * Mock object
 * @author pevans
 *
 */
public class MockDAOUtils { //extends DAOUtils {
	
	/**
	 * Needed to satisfy contract
	 */
	public List<Object[]> getPutts(Date aFromDate, Date aToDate, 
			PlayerDAO aPlayer, boolean aIsGIR, int aNumPutts, 
			boolean aGreaterThanEqualTo) {
		return null;
	}
	
	/**
	 * Returns the collection of courses for the given aPlayerId
	 * @param aPlayerId
	 * @return
	 */
	public CourseDAO[] getCoursesPlayed(int aPlayerId) {
		return null;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public PlayerDAO[] searchPlayers(final String aLastName,
			final String aFirstName, final String aPostalCode,
			final int aFirstResultNum, final int aMaxResultsNum) {
		return null;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int searchPlayersCount(final String aLastName,
			final String aFirstName, final String aPostalCode) {
		return -1;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int getUpDownAttemptCount(Date aFromDate, Date aToDate, 
			PlayerDAO aPlayer) {
		return -1;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int getUpDownConvertCount(Date aFromDate, Date aToDate, 
			PlayerDAO aPlayer) {
		return -1;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int getSandSaveAttemptCount(Date aFromDate, Date aToDate, 
			PlayerDAO aPlayer) {
		return -1;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int getSandSaveConvertCount(Date aFromDate, Date aToDate, 
			PlayerDAO aPlayer) {
		return -1;
	}	
	
	/**
	 * Needed to satisfy contract
	 */
	public List<Object[]> getGIRValues(Date aFromDate, Date aToDate, 
			PlayerDAO aPlayer, int aGIRTypeId) {
		return null;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int[] getAPSDistanceBreakdownValues(Date aFromDate, Date aToDate, 
			PlayerDAO aPlayer) {
		return null;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public List<Object[]> getAPSLineValues(Date aFromDate, 
			Date aToDate, PlayerDAO aPlayer, int aAPSLineTypeId) {
		return null;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public List<Object[]> getAPSDistanceValues(Date aFromDate, 
			Date aToDate, PlayerDAO aPlayer, int aAPSDistanceTypeId) {
		return null;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int[] getAPSLineBreakdownValues(Date aFromDate, 
			Date aToDate, PlayerDAO aPlayer) {
		return null;
	}
	
	/**
	 * Needed to satisfy contact
	 */
	public List<Object[]> getTeeShotAccuracyTypes(Date aFromDate, 
			Date aToDate, PlayerDAO aPlayer, int aAccuracyTypeId) {
		return null;
	}
	
	/**
	 * Needed to satisfy contact
	 */
	public int[] getGIRBreakdownValues(Date aFromDate, Date aToDate, PlayerDAO aPlayer) {
		return null;
	}
		
	/**
	 * Needed to satisfy contact
	 */
	public int[] getTeeShotAccuracyValues(Date aFromDate, Date aToDate, 
			PlayerDAO aPlayer) {
		return null;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int getNumHoles(int aCourseId) {
		// does nothing...
		return -1;
	}

	/**
	 * Needed to satisfy contract
	 */
	public CourseDAO[] getCourses(int aCountryId, int aStateProvinceId) {
		return null;
	}

	/**
	 * Needed to satisfy contract
	 */
	public TeeDAO[] getCourseTees(int aCourseId) {
		return null;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int getLocaleId(Locale aLocale) {
		return -1;
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public CalendarMonthDAO[] getMonths(Locale aLocale) {
		return null;
	}

	/**
	 * Needed to satisfy contract
	 */
	public WeatherConditionDAO[] getWeatherConditionTypes(Locale aLocale) {
		return null;
	}

	/**
	 * Needed to satisfy contract
	 */
	public TeeShotAccuracyDAO[] getTeeShotAccuracyTypes(Locale aLocale) { 
		return null; 
	}

	/**
	 * Needed to satisfy contract
	 */
	public ApproachShotLineDAO[] getApproachShotLineTypes(Locale aLocale) { 
		return null; 
	}

	/**
	 * Needed to satisfy contract
	 */
	public ApproachShotDistanceDAO[] getApproachShotDistanceTypes(
			Locale aLocale) { 
		return null; 
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public int[] getNumPutts(Date aFromDate, Date aToDate, PlayerDAO aPlayer,
			boolean aIsGIR) {
		return null;
	}

	/**
	 * Needed to satisfy contract
	 */
	public GolfClubDAO[] getGolfClubTypes(Locale aLocale) { return null; }

	/**
	 * Needed to satisfy contract
	 */
	public GreenInRegulationDAO[] getGreenInRegulationTypes(Locale aLocale) { 
		return null; 
	}

	/**
	 * Needed to satisfy contract
	 */
	public HeadWearDAO[] getHeadWearTypes(Locale aLocale) { return null; }

	/**
	 * Needed to satisfy contract
	 */
	public PantWearDAO[] getPantWearTypes(Locale aLocale) { return null; }

	/**
	 * Needed to satisfy contract
	 */
	public EyeWearDAO[] getEyeWearTypes(Locale aLocale) { return null; }

	/**
	 * Needed to satisfy contract
	 */
	public CountryDAO[] getCountries() { return null; }

	/**
	 * Needed to satisfy contract
	 */
	public StateProvinceDAO[] getStateProvinces(int aCountryId) { return null; }

	/**
	 * Needed to satisfy contract
	 */
	public TeeNameDAO[] getTeeNames() { return null; }

	/**
	 * Needed to satisfy contract
	 */
	public PlayerDAO getPlayerByUserId(String aUserId) { return null; }
	
	/**
	 * Needed to satisfy contract
	 */
	public PlayerDAO getPlayerByEmailAddress(String aEmailAddress) { 
		return null; 
	}
	
	/**
	 * Needed to satisfy contract
	 */
	public void deleteAndSave(ScorecardDAO aOldScorecard,
			ScorecardDAO aNewScorecard, ScoreDAO aNewScores[], 
			ScorecardSummaryDAO aNewSummary, PlayerDAO aPlayer, 
			Integer aStartingHoles[]) { }
	
	/**
	 * Needed to satisfy contract
	 */
	public void deleteAndSave(ScorecardDAO aOldScorecard,
			ScorecardDAO aNewScorecard, 
			ScorecardSummaryDAO aNewSummary, PlayerDAO aPlayer, 
			Integer aStartingHoles[]) { }
	
	/**
	 * Needed to satisfy contract
	 */
	public CourseDAO[] searchCourses(String aCity, int aCountryId,
			int aStateProvinceId, String aCourseName, int aFirstResultNum, 
			int aMaxResultsNum) { return null; }
	
	/**
	 * Needed to satisfy contract
	 */
	public int searchCoursesCount(String aCity, int aCountryId,
			int aStateProvinceId, String aCourseName) { return -1; }
	
	/**
	 * Needed to satisfy contract
	 */
	public ScorecardSummaryDAO[] searchRounds(String aCity,
			int aCountryId, int aStateProvinceId, String aCourseName,
			String aDateFilter, int aPlayerId, int aFirstResultNum, 
			int aMaxResultsNum) { return null; }
	
	/**
	 * Needed to satisfy contract
	 */
	public int searchRoundsCount(final String aCity, 
			final int aCountryId, final int aStateProvinceId, 
			final String aCourseName, final String aDateFilter, 
			final int aPlayerId) { return -1; }
	
	/**
	 * Needed to satisfy contract
	 */
	public BigDecimal getTournamentScoreTableValue(
			final int aNumTournamentScores, 
			final BigDecimal aAvgTScoreDifferential) { return null; }
	
	/**
	 * Needed to satisfy contract
	 */
	public boolean doesUserIdExist(String aEmailAddress) { return false; }	
	
	/**
	 * Needed to satisfy contract
	 */
	public boolean doesEmailAddressExist(String aEmailAddress) { return false; }
}
