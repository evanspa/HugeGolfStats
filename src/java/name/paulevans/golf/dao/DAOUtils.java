package name.paulevans.golf.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.CourseWrapperBean;
import name.paulevans.golf.bean.TeeWrapperBean;

import org.apache.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Collection of DAO-related utility methods
 * 
 * @author pevans
 * 
 */
public abstract class DAOUtils {

	// logger instance...
	private static final Logger logger = Logger.getLogger(DAOUtils.class);

	// instance members...
	private PlatformTransactionManager transactionManager;
	
	/**
	 * Returns true if a player exists within the system with the email address
	 * aEmailAddress
	 * @param aEmailAddress
	 * @return
	 */
	public abstract boolean doesEmailAddressExist(String aEmailAddress); 
	
	/**
	 * Returns true if there are no players in the database with the inputted
	 * user id; otherwise returns false.
	 * @param aUserId
	 * @return
	 */
	public abstract boolean doesUserIdExist(String aUserId);
	
	/**
	 * Returns an array of size 3; subscript [0] is the total number of
	 * left-rough tee shots;  [1] is the total number of fairway-hit tee shots; 
	 * [2] is the total number of right-rough tee shots
	 * @param aPlayer
	 * @return
	 */
	public abstract int[] getTeeShotAccuracyValues(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, PlayerDAO aPlayer);
	
	/**
	 * Returns an array of size 2; subscript [0] is the total number of
	 * GIRs;  [1] is the total number of non-GIRs
	 * @param aPlayer
	 * @return
	 */
	public abstract int[] getGIRBreakdownValues(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, PlayerDAO aPlayer);
	
	/**
	 * Returns the count of up-and-down attempts for the inputted player and
	 * date range
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	public abstract int getUpDownAttemptCount(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, PlayerDAO aPlayer);
	
	/**
	 * Returns the count of up-and-down converts for this inputted player and
	 * date range
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	public abstract int getUpDownConvertCount(Date aFromDate, Date aToDate,
			CircumstancesBean aCircumstances, PlayerDAO aPlayer);
	
	/**
	 * Returns the count of sand-save attempts for the inputted player and
	 * date range
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	public abstract int getSandSaveAttemptCount(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, PlayerDAO aPlayer);
	
	/**
	 * Returns the count of sand-save converts for this inputted player and
	 * date range
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	public abstract int getSandSaveConvertCount(Date aFromDate, Date aToDate,
			CircumstancesBean aCircumstances, PlayerDAO aPlayer);
	
	/**
	 * Returns an array of size 2; subscript [0] is the total number of
	 * on-line approach shots;  [1] is the total number of approach shots not
	 * on-line
	 * @param aPlayer
	 * @return
	 */
	public abstract int[] getAPSLineBreakdownValues(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, PlayerDAO aPlayer);

	/**
	 * Returns the collection of holes given the inputted aCourseId.
	 * 
	 * @param aCourseId
	 * @return
	 */
	public abstract int getNumHoles(int aCourseId);
	
	/**
	 * Returns the locale id given the runtime locale of the user
	 * @param aLocale
	 * @return
	 */
	public abstract int getLocaleId(Locale aLocale);
	
	/**
	 * Returns an array of size 4; subscript [0] is the total number of 1-putts
	 * made;  [1] is the total number of 2-putts; [2] is the total number of
	 * 3-putts; [3] is the total number of 4-putts and worse
	 * @param aPlayer
	 * @return
	 */
	public abstract int[] getNumPutts(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, PlayerDAO aPlayer, boolean aIsGIR);
	
	/**
	 * Returns the number of instances of a "putt type" grouped by date.  return[0]
	 * will contain a date and return[1] will contain the number putt types
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aIsGIR
	 * @param aNumPutts 1, 2, 3 or 4 (ie, 1 putts, 2 putts, 3 putts, 4 putts)
	 * @param aGreaterThanEqualTo if true will return the number of instances
	 * of putt types that are equal to aNumPutts or greater than
	 * @return
	 */
	public abstract List<Object[]> getPutts(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, PlayerDAO aPlayer, boolean aIsGIR, 
			int aNumPutts, boolean aGreaterThanEqualTo);
	
	/**
	 * Returns the number instances of an "accuracy type" grouped by date.  
	 * return[0] will contain a date and return[1] will contain the number of 
	 * accuracy types
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aAccuracyTypeId 
	 * @return
	 */
	public abstract List<Object[]> getTeeShotAccuracyTypes(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, PlayerDAO aPlayer, 
			int aAccuracyTypeId);
	
	/**
	 * Returns the number instances of an GIR types grouped by date.  
	 * return[0] will contain a date and return[1] will contain the number of 
	 * occurances
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aGIRTypeId 
	 * @return
	 */
	public abstract List<Object[]> getGIRValues(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, PlayerDAO aPlayer, int aGIRTypeId);
	
	/**
	 * Returns the number instances of an approach-shot-distance value grouped 
	 * by date.  Return[0] will contain a date and return[1] will contain the 
	 * number of occurances
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aAPSDistanceTypeId 
	 * @return
	 */
	public abstract List<Object[]> getAPSDistanceValues(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, PlayerDAO aPlayer, 
			int aAPSDistanceTypeId);	
	
	/**
	 * Returns the number instances of an approach-shot-line value grouped by 
	 * date.  Return[0] will contain a date and return[1] will contain the 
	 * number of occurances
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aAPSLineTypeId 
	 * @return
	 */
	public abstract List<Object[]> getAPSLineValues(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, PlayerDAO aPlayer, 
			int aAPSLineTypeId);	
	
	/**
	 * Returns an array of size 2; subscript [0] is the total number of
	 * pin-high approach shots;  [1] is the total number of approach shots not
	 * pin-high
	 * @param aPlayer
	 * @return
	 */
	public abstract int[] getAPSDistanceBreakdownValues(Date aFromDate, 
			Date aToDate, CircumstancesBean aCircumstances, PlayerDAO aPlayer);

	/**
	 * Returns a map of holes keyed on the hole number.
	 * 
	 * @param aCourse
	 * @param aTeeColorId
	 * @return
	 */
	public Map<String, HoleDAO> getHoles(CourseDAO aCourse, int aTeeColorId) {

		Set<TeeDAO> tees;
		Set<HoleDAO> holes;
		TeeDAO thisTee;
		Integer teeColorId;
		Map<String, HoleDAO> holesMap;

		holesMap = new HashMap<String, HoleDAO>();
		thisTee = null;
		tees = aCourse.getTees();
		for (TeeDAO tee : tees) {
			teeColorId = (Integer) tee.getTeeName().getId();
			if (teeColorId == aTeeColorId) {
				thisTee = tee;
				break;
			}
		}
		holes = thisTee.getHoles();
		for (HoleDAO hole : holes) {
			holesMap.put(Integer.toString(hole.getNumber()), hole);
		}
		return holesMap;
	}
	
	/**
	 * Returns the tournament reduction value based on the inputs
	 * @param aNumTournamentScores
	 * @param aAvgTScoreDifferential
	 * @return
	 */
	public abstract BigDecimal getTournamentScoreTableValue(
			int aNumTournamentScores, BigDecimal aAvgTScoreDifferential);

	/**
	 * Returns the collection of courses given the inputted country and 
	 * state/province ID.
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @return
	 */
	public abstract CourseDAO[] getCourses(int aCountryId, 
			int aStateProvinceId);

	/**
	 * Returns the collection of course tees for the given aCourseId.
	 * 
	 * @param aCourseId
	 * @return
	 */
	//public abstract TeeDAO[] getCourseTees(int aCourseId);

	/**
	 * Returns the set of weather condition types.
	 * 
	 * @return
	 */
	public abstract WeatherConditionDAO[] getWeatherConditionTypes(
			Locale aLocale);

	/**
	 * Returns the set of tee shot accuracy types
	 * 
	 * @return
	 */
	public abstract TeeShotAccuracyDAO[] getTeeShotAccuracyTypes(
			Locale aLocale);

	/**
	 * Returns the set of approach shot line types
	 * 
	 * @return
	 */
	public abstract ApproachShotLineDAO[] getApproachShotLineTypes(
			Locale aLocale);

	/**
	 * Returns the set of approach shot distance types
	 * 
	 * @return
	 */
	public abstract ApproachShotDistanceDAO[] getApproachShotDistanceTypes(
			Locale aLocale);

	/**
	 * Returns the set of golf club types
	 * 
	 * @return
	 */
	public abstract GolfClubDAO[] getGolfClubTypes(
			Locale aLocale);

	/**
	 * Returns the set of green-in-regulation types
	 * 
	 * @return
	 */
	public abstract GreenInRegulationDAO[] getGreenInRegulationTypes(
			Locale aLocale);

	/**
	 * Returns the set of head wear types.
	 * 
	 * @return
	 */
	public abstract HeadWearDAO[] getHeadWearTypes(Locale aLocale);

	/**
	 * Returns the set of pant wear types.
	 * 
	 * @return
	 */
	public abstract PantWearDAO[] getPantWearTypes(Locale aLocale);

	/**
	 * Returns the set of eye wear types.
	 * 
	 * @return
	 */
	public abstract EyeWearDAO[] getEyeWearTypes(Locale aLocale);

	/**
	 * Returns the set of countries.
	 * 
	 * @return
	 */
	public abstract CountryDAO[] getCountries();
	
	/**
	 * Returns the set of calendar-months
	 * @param aLocale
	 * @return
	 */
	public abstract CalendarMonthDAO[] getMonths(Locale aLocale);

	/**
	 * Returns the collection of state provinces for the given aCountryId.
	 * 
	 * @param aSession
	 * @param aCountryId
	 * @return StateProvinceDAO[]
	 */
	public abstract StateProvinceDAO[] getStateProvinces(int aCountryId);
	
	/**
	 * Returns the collection of courses for the given aPlayerId
	 * @param aPlayerId
	 * @return
	 */
	public abstract CourseDAO[] getCoursesPlayed(int aPlayerId);

	/**
	 * Returns the collection of tee colors
	 * 
	 * @return
	 */
	public abstract TeeNameDAO[] getTeeNames();

	/**
	 * Finds and returns player from the database
	 * 
	 * @param aUserId
	 * @return
	 */
	public abstract PlayerDAO getPlayerByUserId(String aUserId);
	
	/**
	 * Finds and returns player from the database
	 * 
	 * @param aUserId
	 * @return
	 */
	public abstract PlayerDAO getPlayerByEmailAddress(String aEmailAddress);

	/**
	 * Get the transaction manager
	 * 
	 * @return
	 */
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * Set the transaction manager
	 * 
	 * @param aTransactionManager
	 */
	public void setTransactionManager(
			PlatformTransactionManager aTransactionManager) {
		transactionManager = aTransactionManager;
	}

	/**
	 * Returns the set of holes given the inputted aTeeColorId. aHoles contains
	 * ALL holes for a given course; this method filters the list based on tee
	 * color.
	 * 
	 * @param aHoles
	 * @param aTeeColorId
	 * @return
	 */
	public Set<HoleDAO> getHoles(Set<HoleDAO> aHoles, int aTeeColorId) {

		Set<HoleDAO> aNewHoles;

		aNewHoles = new HashSet<HoleDAO>();
		for (HoleDAO hole : aHoles) {
			if (((TeeId) hole.getTee().getId()).getTeeNameId() == aTeeColorId) {
				aNewHoles.add(hole);
			}
		}
		return aNewHoles;
	}

	/**
	 * Deletes aOldScorecard and saves aNewScorecard
	 */
	public abstract void deleteAndSave(ScorecardDAO aOldScorecard,
			ScorecardDAO aNewScorecard, ScoreDAO aNewScores[], 
			ScorecardSummaryDAO aNewSummary, PlayerDAO aPlayer, 
			Integer aStartingHoles[]);

	/**
	 * Deletes aOldScorecard and saves aNewScorecard; aNewScorecard has no-stats
	 * associated with it
	 */
	public abstract void deleteAndSave(ScorecardDAO aOldScorecard,
			ScorecardDAO aNewScorecard, ScorecardSummaryDAO aNewSummary, 
			PlayerDAO aPlayer, Integer aStartingHoles[]);
	
	/**
	 * Returns an array of unique email addresses stored in the player table
	 * @return
	 */
	public abstract String[] getAllUniqueEmailAddresses();
	
	/**
	 * Saves the scorecard
	 * 
	 * @param aScorecard
	 */
	public void saveScorecard(final ScorecardDAO aScorecard,
			final ScorecardSummaryDAO aScorecardSummary, 
			final PlayerDAO aPlayer,
			final Integer aStartingHoles[],
			final Date aCreateDate) {
		saveScorecard(aScorecard, null, aScorecardSummary, aPlayer, 
				aStartingHoles, aCreateDate);
	}

	/**
	 * Saves the scorecard
	 * @param aScorecard
	 * @param aScores
	 * @param aScorecardSummary
	 * @param aPlayer
	 * @param aStartingHoles
	 * @param aCreateDate Since updating a scorecard involves first deleting it,
	 * this method takes in the create date of the original scorecard so it can
	 * be reused when persisting the new scorecard row.  If aCreateDate is
	 * null, this means it truly is a new scorecard and the create date has
	 * to be set with the current date
	 */
	public void saveScorecard(final ScorecardDAO aScorecard,
			final ScoreDAO aScores[],
			final ScorecardSummaryDAO aScorecardSummary, 
			final PlayerDAO aPlayer,
			final Integer aStartingHoles[],
			final Date aCreateDate) {

		TransactionTemplate txTemplate;

		aScorecard.setDateCreated(new Date());
		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus aStatus) {
				
				CourseDAO coursePlayed;
				CourseSlopeRatingDAO slopeRatingDAO;
				TeeId teePlayedId;
				TeeDAO teePlayed;
				Integer slope;
				Float rating;
				HoleDAO startingHole;
				
				teePlayedId = (TeeId)aScorecard.getTee().getId();
				coursePlayed = BeanFactory.newCourseDAO();
				coursePlayed.setId(teePlayedId.getCourseId());
				coursePlayed.load();
				slope = null;
				rating = null;
				
				// set create/last modified dates...
				aScorecard.setDateCreated(aCreateDate != null ? aCreateDate : 
					new Date());
				aScorecard.setLastUpdateDate(new Date());
				
				// if 9-hole round we need to get the slope/rating values
				// for the nine holes...
				if (aStartingHoles.length == 1) {
					slopeRatingDAO = coursePlayed.getSlopeRating(
							teePlayedId.getTeeNameId(), aStartingHoles[0]);
					startingHole = BeanFactory.newHoleDAO();
					startingHole.setNumber(aStartingHoles[0]);
					aScorecard.setStartingHole(startingHole);
					if (slopeRatingDAO != null) {
						slope = slopeRatingDAO.getSlope();
						rating = slopeRatingDAO.getRating();
					}
				} else { // if 18-hole round, we need to get the overall 
						 // slope/rating values...
					teePlayed = coursePlayed.getTee(teePlayedId);
					slope = teePlayed.getOverallSlope();
					rating = teePlayed.getOverallRating();
				}				
				
				aScorecard.save(); // save the scorecard...
				if (aScores != null) {
					for (ScoreDAO score : aScores) {
						score.setId(new ScoreId(
								(Integer) score.getHole().getId(),
								(Integer) aScorecard.getId())); // set the id...
						score.save(); // save each score...
					}
				}
				aScorecardSummary.setScorecard(aScorecard); // save the summary
				aScorecardSummary.setScorecardId((Integer) aScorecard.getId());
				aScorecardSummary.setCourseId(teePlayedId.getCourseId());
				aScorecardSummary.setSlope(slope);
				aScorecardSummary.setRating(rating);
				aScorecardSummary.save();
			}
		});
	}
	
	/**
	 * Saves a user role and player
	 * 
	 * @param aUserRole
	 * @param aPlayer
	 */
	public void saveAccount(final PlayerDAO aPlayer) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(
					TransactionStatus aStatus) {
				aPlayer.save();
			}
		});
	}	

	/**
	 * Saves a user role and player
	 * 
	 * @param aUserRole
	 * @param aPlayer
	 */
	public void saveAccount(final UserRoleDAO aUserRole, 
			final PlayerDAO aPlayer) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(
					TransactionStatus aStatus) {
				aUserRole.save();
				aPlayer.save();
			}
		});
	}
	
	/**
	 * Load the tee colors for each tee contained within aTees
	 * @param aTees
	 */
	public void load(final Set<TeeWrapperBean> aTees, 
			final StateProvinceDAO aStateProvince) {
		
		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus aStatus) {
				for (TeeWrapperBean tee : aTees) {
					tee.getTee().getTeeName().load();
				}
				aStateProvince.load();
			}
		});
	}
	
	/**
	 * Returns the course slope/rating DAO that represents an "overall" 
	 * slope/course rating value for the inputted tee color id
	 * @param aTeeColorId
	 * @param aCourseSlopeRatingDAOs
	 * @return
	 */
	public CourseSlopeRatingDAO getOverallSlopeRating(int aTeeColorId,
			CourseSlopeRatingDAO aCourseSlopeRatingDAOs[]) {
		
		CourseSlopeRatingId slopeRatingId;
		
		for (CourseSlopeRatingDAO slopeRatingDAO : aCourseSlopeRatingDAOs) {
			slopeRatingId = (CourseSlopeRatingId)slopeRatingDAO.getId();
			
			// an "overall" dao will have a null starting hole...
			if (slopeRatingId.getNineType() == null) {
				if (slopeRatingId.getTeeNameId() == aTeeColorId) {
					return slopeRatingDAO;
				}
			}
		}
		return null;
	}

	/**
	 * Saves a course and its associated objects
	 * 
	 * @param aCourseAddress
	 * @param aForm
	 * @param aDAOUtils
	 * @param aCourse
	 * @param aUtil
	 * @param aRequest
	 */
	public void saveCourse(final CourseWrapperBean aCourse,
			final String aTeeNameIds[],
			final Set<HoleDAO> aHoles,
			final Set<TeeWrapperBean> aTees,
			final CourseSlopeRatingDAO aCourseSlopeRatingDAOs[],
			final boolean aMakeHome,
			final PlayerDAO aPlayer) {
		
		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus aStatus) {
				
				Integer courseId;
				TeeId teeId;
				CourseSlopeRatingId courseSlopeRatingId;
				CourseSlopeRatingDAO overallSlopeRatingDAO;
				
				// save the course...
				aCourse.getCourse().save();
				courseId = (Integer)aCourse.getCourse().getId();

				// save each tee...
				for (TeeWrapperBean tee : aTees) {
					teeId = (TeeId)tee.getTee().getId();
					teeId.setCourseId(courseId);
					tee.getTee().setId(teeId);
					overallSlopeRatingDAO = getOverallSlopeRating(
							teeId.getTeeNameId(), aCourseSlopeRatingDAOs);
					// set the "overall" rating/slope values...
					tee.getTee().setOverallRating(
							overallSlopeRatingDAO.getRating());
					tee.getTee().setOverallSlope(
							overallSlopeRatingDAO.getSlope());
					tee.getTee().save();
				}

				// save each hole...
				for (HoleDAO hole : aHoles) {
					teeId = (TeeId)hole.getTee().getId();
					teeId.setCourseId(courseId);
					hole.getTee().setId(teeId);
					hole.save();
				}
				
				// save each course slope-rating...
				for (CourseSlopeRatingDAO courseSlopeRatingDAO : 
					aCourseSlopeRatingDAOs) {
					courseSlopeRatingId = (CourseSlopeRatingId)
					courseSlopeRatingDAO.getId();
					
					// make sure the DAO does not represent the "overall"
					// slope/rating values...
					if (courseSlopeRatingId.getNineType() != null) {
						courseSlopeRatingId.setCourseId(courseId);
						courseSlopeRatingDAO.setId(courseSlopeRatingId);
						courseSlopeRatingDAO.save();
					}
				}

				// set the player's home course and save the player...
				if (aMakeHome) {
					aPlayer.setCourse(aCourse.getCourse());
					aPlayer.save();
				}
			}
		});
	}

	/**
	 * Re-loads the lookup objects based on the IDs to get the descriptions to
	 * display on the "viewprofile" screen.
	 * 
	 * @param aSession
	 */
	public void loadObjects(final CalendarMonthDAO aMonth, 
			final EyeWearDAO aEyeWear,
			final HeadWearDAO aHeadWear, final PantWearDAO aPantWear,
			final TeeNameDAO aTeeColor, final Locale aLocale) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(
					TransactionStatus aStatus) {
				if (aMonth != null) aMonth.load(aLocale);
				if (aEyeWear != null) aEyeWear.load();
				if (aHeadWear != null) aHeadWear.load();
				if (aPantWear != null) aPantWear.load();
				if (aTeeColor != null) aTeeColor.load();
			}
		});
	}
	
	/**
	 * Re-loads the lookup objects based on the IDs to get the descriptions
	 * 
	 * @param aSession
	 */
	public void loadObjects(final CalendarMonthDAO aMonth, 
			final Locale aLocale) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(
					TransactionStatus aStatus) {
				if (aMonth != null) aMonth.load(aLocale);
			}
		});
	}

	/**
	 * Loads objects from database
	 * 
	 * @param aCourse
	 * @param aTeeName
	 */
	public void loadObjects(final StateProvinceDAO aStateProvince) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(
					TransactionStatus aStatus) {
				if (aStateProvince != null) aStateProvince.load();
			}
		});
	}	
	
	/**
	 * Loads objects from database
	 * 
	 * @param aCourse
	 * @param aTeeName
	 */
	public void loadObjects(final CourseDAO aCourse, 
			final TeeNameDAO aTeeName) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(
					TransactionStatus aStatus) {
				if (aCourse != null) aCourse.load();
				if (aTeeName != null) aTeeName.load();
			}
		});
	}

	/**
	 * Loads objects from the database
	 * 
	 * @param aEyeWearDAO
	 * @param aHeadWearDAO
	 * @param aPantWearDAO
	 * @param aWeatherConditionDAO
	 */
	public void loadObjects(final EyeWearDAO aEyeWearDAO,
			final HeadWearDAO aHeadWearDAO, final PantWearDAO aPantWearDAO,
			final WeatherConditionDAO aWeatherConditionDAO) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus aStatus) {
				if (aEyeWearDAO != null) aEyeWearDAO.load();
				if (aHeadWearDAO != null) aHeadWearDAO.load();
				if (aPantWearDAO != null) aPantWearDAO.load();
				if (aWeatherConditionDAO != null) aWeatherConditionDAO.load();
			}
		});
	}

	/**
	 * Loads objects from the database
	 * 
	 * @param aEyeWearDAO
	 * @param aHeadWearDAO
	 * @param aPantWearDAO
	 * @param aWeatherConditionDAO
	 */
	public void loadObjects(final EyeWearDAO aEyeWearDAO,
			final HeadWearDAO aHeadWearDAO, final PantWearDAO aPantWearDAO,
			final WeatherConditionDAO aWeatherConditionDAO,
			final CourseDAO aCourseDAO) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(transactionManager);
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus aStatus) {
				if (aEyeWearDAO != null) aEyeWearDAO.load();
				if (aHeadWearDAO != null) aHeadWearDAO.load();
				if (aPantWearDAO != null) aPantWearDAO.load();
				if (aWeatherConditionDAO != null) aWeatherConditionDAO.load();
				if (aCourseDAO != null) aCourseDAO.load();
			}
		});
	}

	/**
	 * Searches the data source for the set of courses that match the search
	 * criteria
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	public abstract CourseDAO[] searchCourses(String aCity, int aCountryId,
			int aStateProvinceId, String aCourseName, int aFirstResultNum, 
			int aMaxResultsNum);
	
	/**
	 * Returns the total result count of the search-courses query
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	public abstract int searchCoursesCount(String aCity, int aCountryId,
			int aStateProvinceId, String aCourseName);
	
	/**
	 * Searches the data source for the set of players that match the search
	 * criteria
	 * @param aLastName
	 * @param aFirstName
	 * @param aPostalCode
	 * @param aFirstResultNum
	 * @param aMaxResultsNum
	 * @return
	 */
	public abstract PlayerDAO[] searchPlayers(String aLastName,
			String aFirstName, String aPostalCode, int aFirstResultNum, 
			int aMaxResultsNum);
	
	/**
	 * Returns the total result count of the search-players query
	 * @param aLastName
	 * @param aFirstName
	 * @param aPostalCode
	 * @return
	 */
	public abstract int searchPlayersCount(String aLastName,
			String aFirstName, String aPostalCode);	

	/**
	 * Searchs the data source for the set of rounds that match the search
	 * criteria
	 * 
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	public abstract ScorecardSummaryDAO[] searchRounds(String aCity,
			int aCountryId, int aStateProvinceId, String aCourseName,
			String aDateFilter, int aPlayerId, int aFirstResultNum, 
			int aMaxResultsNum);
	
	/**
	 * Returns the row-count of the search rounds where-clause
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	public abstract int searchRoundsCount(final String aCity, 
			final int aCountryId, final int aStateProvinceId, 
			final String aCourseName, final String aDateFilter, 
			final int aPlayerId);

	/**
	 * Returns true if aCourseId is found within the aCourses collection.
	 * 
	 * @param aCourseId
	 * @param aCourses
	 * @return
	 */
	public boolean inCollection(int aCourseId, CourseDAO aCourses[]) {

		boolean found;
		int loop;

		found = false;
		for (loop = 0; loop < aCourses.length; loop++) {
			if (aCourseId == (Integer) aCourses[loop].getId()) {
				found = true;
				break;
			}
		}
		return found;
	}
}
