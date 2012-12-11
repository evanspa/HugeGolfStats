package name.paulevans.golf.util;

import gen.jaxb.name.paulevans.golf.Menu;
import gen.jaxb.name.paulevans.golf.Menubar;
import gen.jaxb.name.paulevans.golf.Menuitem;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.EighteenHoleRoundGenerator;
import name.paulevans.golf.LabelStringFactory;
import name.paulevans.golf.bean.MasterSummary;
import name.paulevans.golf.bean.NumberPair;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

/**
 * Singleton with a collection of utility methods.
 * @author pevans
 *
 */
public class NewUtil {
	
	// logger object...
	private static Logger logger = Logger.getLogger(NewUtil.class);

	// link-to-menu mappings...
	private Map<String,String> linkToMenuMappings;
	
	// number formatters...
	private static NumberFormat numberFormatter1Digit;
	private static NumberFormat numberFormatter2Digits;
	private static NumberFormat numberFormatter3Digits;
	private static NumberFormat percentFormatter1Digit;
	
	// init the number formatters...
	static {
		numberFormatter1Digit = NumberFormat.getInstance();
		numberFormatter1Digit.setMaximumFractionDigits(1);
		numberFormatter2Digits = NumberFormat.getInstance();
		numberFormatter2Digits.setMaximumFractionDigits(2);
		numberFormatter3Digits = NumberFormat.getInstance();
		numberFormatter3Digits.setMaximumFractionDigits(3);		
		percentFormatter1Digit = NumberFormat.getPercentInstance();
		percentFormatter1Digit.setMaximumFractionDigits(1);
	}
	
	// date formatters...
	private SimpleDateFormat simpleDateFormat;
	private SimpleDateFormat simpleDatetimeFormat;
	private SimpleDateFormat simpleDateFormatInput;
	
	// number of course holes...
	private List<String> numCourseHoles;
	
	// number of par values...
	private List<String> parValues;
	
	// number of putt values...
	private List<String> puttValues;
	
	// number of score values...
	private List<String> scoreValuesList;
	
	// default number of course holes...
	private int defaultNumCourseHoles;
	
	// default par value...
	private int defaultParValue;
	
	// default putt value...
	private int defaultPuttValue;
	
	// default country ID...
	private String countryId;
	
	// default state-province ID...
	private String stateProvinceId;
	
	// used for lazy-initialization...
	private boolean isInitialized;
	
	// the admin email address value...
	private String adminEmail;
	
	// the suggestions-inbox email address value...
	private String suggestionsEmail;
	
	// available front-9/back-9 starting hole numbers...
	private List<String> front9StartingHolesList;
	private List<String> back9StartingHolesList;
	
	// the menubar objects...
	private Menubar loggedInMenubar, notLoggedInMenubar;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public NewUtil() {
		createMenubars();
	}
	
	/**
	 * Returns true if aDate matches the date information passed; otherwise 
	 * returns false.
	 * @param aDate
	 * @param aMonth
	 * @param aDay
	 * @param aYear
	 * @return
	 */
	public boolean datesMatch(Date aDate, int aMonth, String aDay, 
			String aYear) {
		
		Date birthdate;
		
		birthdate = parse(aMonth + "/" + aDay + "/" + aYear);
		return aDate.equals(birthdate);
	}
	
	/**
	 * Returns true if aId is found within the list of aStateProvs
	 * @param aId
	 * @param aStateProvs
	 * @return
	 */
	public static final boolean isInList(int aId, StateProvinceDAO aStateProvs[]) {
		for (StateProvinceDAO stateProv : aStateProvs) {
			if (((StateProvinceId)stateProv.getId()).getId() == aId) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the starting hole given the value XX-YY; this method returns the
	 * XX portion.
	 * @param aParameterValue
	 * @return
	 */
	public static int getStartingHole(String aParameterValue) {
		return getHolesPlayedRange(aParameterValue)[0];
	}
	
	/**
	 * aHolesPlayedRange will be of the form XX-YY and this method will
	 * return an int array such that int[0] = XX and int[1] = YY
	 * @param aHolesPlayedRange
	 * @return
	 */
	public static final int[] getHolesPlayedRange(String aHolesPlayedRange) {
		
		int holesPlayedRange[];
		int index;
		
		holesPlayedRange = new int[2];
		index = aHolesPlayedRange.indexOf(WebConstants.DELIMETER);
		holesPlayedRange[0] = Integer.parseInt(aHolesPlayedRange.substring(0, 
				index));
		holesPlayedRange[1] = Integer.parseInt(aHolesPlayedRange.substring(
				index + 1, aHolesPlayedRange.length()));
		return holesPlayedRange;
	}
	
	/**
	 * Returns the value of aQuotient divided by aDivisor.  If aDivisor is 
	 * not greater than zero, then null is returned.
	 * @param aQuotient
	 * @param aDivisor
	 * @return
	 */
	public static final Float divide(Integer aQuotient, Integer aDivisor) {
		
		Float returnVal;
		
		returnVal = null;
		if ((aQuotient != null) && (aDivisor != null) && (aDivisor > 0)) {
			returnVal = (float)aQuotient / aDivisor;
		}
		return returnVal;
	}

	/**
	 * Returns the formatter version of aValue to 1 decimal place
	 * @param aValue
	 * @return
	 */
	public static String formatNumber1Digit(Float aValue, Locale aLocale) {
		if (aValue == null) {
			//aValue = 0.0F;
			return LabelStringFactory.getInstance(aLocale).getString(
					LabelStringFactory.GENERAL_LABEL_NOTAPPLICABLE);
		}
		return numberFormatter1Digit.format(aValue);
	}
	
	/**
	 * Returns the formatter version of aValue to 2 decimal places
	 * @param aValue
	 * @return
	 */
	public static String formatNumber2Digits(Float aValue, Locale aLocale) {
		if (aValue == null) {
			//aValue = 0.0F;
			return LabelStringFactory.getInstance(aLocale).getString(
					LabelStringFactory.GENERAL_LABEL_NOTAPPLICABLE);
		}
		return numberFormatter2Digits.format(aValue);
	}
	
	/**
	 * Returns the formatter version of aValue to 3 decimal places
	 * @param aValue
	 * @return
	 */
	public static String formatNumber3Digits(Float aValue, Locale aLocale) {
		if (aValue == null) {
			//aValue = 0.0F;
			return LabelStringFactory.getInstance(aLocale).getString(
					LabelStringFactory.GENERAL_LABEL_NOTAPPLICABLE);
		}
		return numberFormatter3Digits.format(aValue);
	}
	
	/**
	 * Returns the formatter version of aValue to 3 decimal places
	 * @param aValue
	 * @return
	 */
	public static String formatPercent1Digit(Float aValue, Locale aLocale) {
		if (aValue == null) {
			//aValue = 0.0F;
			return LabelStringFactory.getInstance(aLocale).getString(
					LabelStringFactory.GENERAL_LABEL_NOTAPPLICABLE);
		}
		return percentFormatter1Digit.format(aValue);
	}
	
	/**
	 * Returns true if the current user is logged in; otherwise returns false.
	 * @param aRequest
	 * @return
	 */
	public static boolean isLoggedIn(HttpServletRequest aRequest) {
		return getPlayer(aRequest) != null && aRequest.isUserInRole(
						Constants.USER_ROLE);
	}
	
	/**
	 * Re-adds the "no-menu" request attribute to the request object.
	 * @param aRequest
	 */
	public static void setNoMenuAttribute(HttpServletRequest aRequest) {
		if (BooleanUtils.toBoolean(
				aRequest.getParameter(AttributeKeyConstants.NO_MENU))) {
			aRequest.setAttribute(AttributeKeyConstants.NO_MENU, 
					Constants.TRUE);
		}
	}
	
	/**
	 * Re-adds request parameter value as attribute if the value is not null.
	 * @param aRequest
	 */
	public static void addParamAsAttribute(HttpServletRequest aRequest,
			String aParameterName) {
		
		String parameterValue;
		
		parameterValue = aRequest.getParameter(aParameterName);
		if (StringUtils.isNotEmpty(parameterValue)) {
			aRequest.setAttribute(aParameterName, parameterValue);
		}
	}
	
	/**
	 * Returns the string value from aMap keyed on aKey.  If no value exists for 
	 * aKey, then aDefaultVal is returned.
	 * @param aMap
	 * @param aKey
	 * @param aDefaultVal
	 * @return
	 */
	public static String getString(Map aMap, String aKey, String aDefaultVal) {
		
		String value;
		
		value = (String)aMap.get(aKey);
		if (value == null) {
			value = aDefaultVal;
		}
		return value;
	}
	
	/**
	 * Returns the string value from aMap keyed on aKey.  If no value exists for 
	 * aKey, then aDefaultVal is returned.
	 * @param aMap
	 * @param aKey
	 * @param aDefaultVal
	 * @return
	 */
	public static String getString(Map aMap, String aKey, int aDefaultVal) {
		return getString(aMap, aKey, Integer.toString(aDefaultVal));
	}
	
	/**
	 * Removes an attribute from the session keyed on aAttributeName
	 * @param aForm
	 * @param aRequest
	 */
	public static void removeFromSession(String aAttributeName, 
			HttpServletRequest aRequest) {
		
		// remove the form-bean from the session...
		logger.info("removing from session: " + aAttributeName);
		aRequest.getSession().removeAttribute(aAttributeName);
	}
	
	/**
	 * Removes various "stray" objects from session.
	 * @param aRequest
	 */
	public static void removeStrayObjectsFromSession(
			HttpServletRequest aRequest) {
		removeFromSession(AttributeKeyConstants.COURSES, aRequest);
		removeFromSession(AttributeKeyConstants.ROUNDS, aRequest);
		removeFromSession(AttributeKeyConstants.PLAYERS, aRequest);
		removeFromSession(AttributeKeyConstants.COURSE, aRequest);
		removeFromSession(AttributeKeyConstants.SCORECARD_ID_PARAM, aRequest);
	}
	
	/**
	 * Returns the score type based on the state of the aScorecard DAO
	 * @param aScorecard
	 * @return
	 */
	public static final String getScoreType(ScorecardDAO aScorecard) {
		
		String scoreType;
		
		scoreType = Constants.AWAY_SCORE_TYPE;
		if (aScorecard.getIsTournament()) {
			scoreType = Constants.TOURNAMENT_INTERNET_SCORE_TYPE;
		} else if (aScorecard.getIsHome()) {
			scoreType = Constants.HOME_SCORE_TYPE;
		}
		return scoreType;
	}
	
	/**
	 * Calculates the handicap index reduction value as per the USGA Handicap
	 * System Manual, section: 10-3
	 * @param aHandicapIndex
	 * @param aRounds
	 * @param aDAOUtils
	 * @return
	 */
	public static BigDecimal calculateHandicapIndexReductionValue(
			BigDecimal aHandicapIndex, ScorecardSummaryDAO aRounds[], 
			DAOUtils aDAOUtils) {
		
		BigDecimal reductionValue;
		ScorecardSummaryDAO tournamentScores[];
		BigDecimal diff1, diff2, avgDiff;
		BigDecimal result;
		
		reductionValue = null;
		tournamentScores = getEligibleTournamentScores(aRounds);
		if (tournamentScores.length >= 2) {
			Arrays.sort(tournamentScores); // sort by differentials...
			diff1 = tournamentScores[0].getDifferential();
			diff2 = tournamentScores[1].getDifferential();
			result = aHandicapIndex.subtract(diff2); // subtract 2nd-diff value...
			if (result.floatValue() >= 3.0) {
				avgDiff = BigDecimal.valueOf(diff1.floatValue() + 
						diff2.floatValue()).divide(BigDecimal.valueOf(2), 3, 
								BigDecimal.ROUND_HALF_UP);
				result = aHandicapIndex.subtract(avgDiff).setScale(1, 
						BigDecimal.ROUND_HALF_UP);
				
				// get value from tournament scores table...
				reductionValue = aDAOUtils.getTournamentScoreTableValue(
						tournamentScores.length, result);
			}
		}
		return reductionValue;
	}
	
	/**
	 * Returns the collection of elible tournament rounds within aRounds
	 * @param aRounds
	 * @return
	 */
	public static final ScorecardSummaryDAO[] getEligibleTournamentScores(
			ScorecardSummaryDAO aRounds[]) {
		
		int loop;
		List<ScorecardSummaryDAO> tournamentScores;
		
		tournamentScores = new ArrayList<ScorecardSummaryDAO>();
		for (loop = 0; loop < aRounds.length; loop++) {
			if (isEligibleTournamentRound(aRounds[loop])) {
				tournamentScores.add(aRounds[loop]);
			}
		}
		return tournamentScores.toArray(
				new ScorecardSummaryDAO[tournamentScores.size()]);
	}
	
	/**
	 * Returns true if aRound is considered to be an eligible tournament
	 * round.  "Eligible" meaning the round has a score type of T or TI and
	 * is not older than 12 months.
	 * @param aRound
	 * @return
	 */
	private static final boolean isEligibleTournamentRound(
			ScorecardSummaryDAO aRound) {
		
		boolean isEligibleTournamentRound;
		String scoreType;
		Calendar expirationDate, datePlayed;
		
		expirationDate = Calendar.getInstance();
		datePlayed = Calendar.getInstance();
		expirationDate.add(Calendar.MONTH, 12); // add the 12-months...
		datePlayed.setTime(aRound.getDatePlayed());
		isEligibleTournamentRound = false;
		scoreType = aRound.getScoreType();
		if (StringUtils.equals(scoreType, 
				Constants.TOURNAMENT_INTERNET_SCORE_TYPE) ||
			StringUtils.equals(scoreType, Constants.TOURNAMENT_SCORE_TYPE)) {
			if (datePlayed.compareTo(expirationDate) <= 0) {
				isEligibleTournamentRound = true;
			}
		}
		return isEligibleTournamentRound;
	}
	
	/**
	 * Calculates the player's reduced handicap value
	 * @param aMasterSummary
	 * @param aRounds
	 * @param aHomeCourseId
	 * @param aDAOUtils
	 * @return
	 */
	public static String calculateHandicapIndexReduced(
			BigDecimal aHandicapIndex, BigDecimal aReductionValue) {
		
		String handicapIndexReduced;

		if (aReductionValue != null) {
			aHandicapIndex = aHandicapIndex.subtract(aReductionValue);
			handicapIndexReduced = aHandicapIndex.toString() + "R";
		} else {
			handicapIndexReduced = aHandicapIndex.toString();
		}
		return handicapIndexReduced;
	}
	
	/**
	 * Calculates the player's handicap index
	 * @param aMasterSummary
	 * @param aMergedRounds
	 * @param aHomeCourseId
	 * @param aDAOUtils
	 * @return
	 */
	public static BigDecimal calculateHandicapIndex(MasterSummary 
			aMasterSummary, ScorecardSummaryDAO aMergedRounds[], 
			DAOUtils aDAOUtils) {
		
		NumberPair differentials[];
		BigDecimal averageDifferential, totalDifferential;
		int loop, numRounds;
		BigDecimal handicapIndex, numDifferentialsToUse;
		
		handicapIndex = null; // init to null...
		
		// first check to see if we have at least 5 scores posted (this # 
		// doesn't differentiate between 18 and 9-hole scores; so, even though
		// we have at least 5 scores, we still may not be able to compute the
		// index if there aren't at least 5 18-hole rounds...In addition, only
		// those rounds with valid slope/rating values associated with them
		// can be used in the index calcualation...
		if (aMasterSummary != null && aMasterSummary.getScorecardCount() >= 5) {
			
			numRounds = aMergedRounds.length;
			
			// only continue if we have at least 5 rounds...
			if (numRounds >= 5) { 
				if (numRounds > 20) {
					numRounds = 20;
				}

				// calculate differentials according to the USGA Handicap System
				// Manual, section 10-1
				differentials = new NumberPair[numRounds];
				for (loop = 0; loop < numRounds; loop++) {
					differentials[loop] = new NumberPair(
							aMergedRounds[loop].getDifferential(), loop);
				}
				// sort so that we're using the best differential values...
				Arrays.sort(differentials);

				// determine the number of differentials to use...
				numDifferentialsToUse = new BigDecimal(
						Constants.DIFFERENTIALS_TABLE.get(numRounds));
				
				// sum and calculate the average differential...
				totalDifferential = BigDecimal.ZERO;
				for (loop = 0; loop < numDifferentialsToUse.intValue(); 
				loop++) {
					aMergedRounds[differentials[loop].getIntegerValue()]
					              .setUsedInHandicapCalculation(true);
					totalDifferential = totalDifferential.add(
							differentials[loop].getBigDecimal());
				}
				averageDifferential = totalDifferential.divide(
						numDifferentialsToUse, 3, BigDecimal.ROUND_HALF_UP);
				
				// multiply by the "Bonus for Excellence" value...
				averageDifferential = averageDifferential.multiply(
						new BigDecimal(.96F));
				
				// drop the digits after the tenths-position...
				handicapIndex = truncateAvgDifferential(averageDifferential);
			}
		}
		return handicapIndex;
	}
	
	/**
	 * Calculates the differential based on the USGA Handicap System Manual, 
	 * section: 10-2
	 * @param aScore
	 * @param aRating
	 * @param aSlope
	 * @return
	 */
	public static final BigDecimal calculateDifferential(int aScore, 
			float aRating, int aSlope) {
		return BigDecimal.valueOf(((aScore - aRating) * 113) / aSlope).setScale(
				1, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Returns aAvgDifferential with any digits beyond the tenths-position
	 * dropped.  If 74.19382 is passed in, 74.1 is returned.
	 * @param aAvgDifferential
	 * @return
	 */
	private static final BigDecimal truncateAvgDifferential(
			BigDecimal aAvgDifferential) {
		
		String truncatedValue;
		String avgDiff;
		int index;
		
		avgDiff = aAvgDifferential.toString();
		index = avgDiff.indexOf(".");
		if (index != -1) {
			truncatedValue = avgDiff.substring(0, index + 2);
		} else {
			truncatedValue = avgDiff;
		}
		return NumberUtils.createBigDecimal(truncatedValue);
	}
	
	/**
	 * Merges aRounds into a collection of 18-hole rounds; 9-hole rounds will
	 * be merged together to form 18-hole rounds.
	 * @param aCourseId
	 * @param aMostRecentRounds
	 * @return
	 */
	public static ScorecardSummaryDAO[] mergeNineHoleRounds(
			ScorecardSummaryDAO aMostRecentRounds[]) {
	
		int loop;
		EighteenHoleRoundGenerator roundGenerator;
		
		// merge the 9-hole rounds...	
		roundGenerator = new EighteenHoleRoundGenerator();
		for (loop = 0; loop < aMostRecentRounds.length; loop++) {
			roundGenerator.processRound(aMostRecentRounds[loop]);
		}	
		return roundGenerator.getRounds(20); // return the 18-hole rounds...
	}
	
	/**
	 * Setter for the date format object
	 * @param aDateFormat
	 */
	public void setDateFormatDisplay(SimpleDateFormat aDateFormat) {
		simpleDateFormat = aDateFormat;
	}
	
	/**
	 * Setter for the datetime format object
	 * @param aDatetimeFormat
	 */
	public void setDatetimeFormatDisplay(SimpleDateFormat aDatetimeFormat) {
		simpleDatetimeFormat = aDatetimeFormat;
	}
	
	/**
	 * Returns the datetime formatter object
	 * @return
	 */
	public SimpleDateFormat getDatetimeFormatDisplay() {
		return simpleDatetimeFormat;
	}
	
	/**
	 * Returns the date formatter object
	 * @return
	 */
	public SimpleDateFormat getDateFormatDisplay() {
		return simpleDateFormat;
	}
	
	/**
	 * Setter for the input date format object
	 * @param aDateFormat
	 */
	public void setDateFormatInput(SimpleDateFormat aDateFormat) {
		simpleDateFormatInput = aDateFormat;
	}
	
	/**
	 * Sets the number of course holes
	 * @param aNumCourseHoles
	 */
	public void setNumCourseHolesList(List<String> aNumCourseHoles) {
		numCourseHoles = aNumCourseHoles;
	}
	
	/**
	 * Generated getter
	 * @return
	 */
	public String[] getNumCourseHoles() {
		return numCourseHoles.toArray(new String[numCourseHoles.size()]);
	}
	
	/**
	 * Setter
	 * @param aParValues
	 */
	public void setParValuesList(List<String> aParValues) {
		parValues = aParValues;
	}
	
	/**
	 * Setter
	 * @param aParValues
	 */
	public void setPuttValuesList(List<String> aPuttValues) {
		puttValues = aPuttValues;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String[] getPuttValues() {
		return puttValues.toArray(new String[puttValues.size()]);
	}
	
	/**
	 * Setter
	 * @param aValues
	 */
	public void setFront9StartingHolesList(List<String> aValues) {
		front9StartingHolesList = aValues;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String[] getFront9StartingHoles() {
		return front9StartingHolesList.toArray(
				new String[front9StartingHolesList.size()]);
	}
	
	/**
	 * Setter
	 * @param aValues
	 */
	public void setBack9StartingHolesList(List<String> aValues) {
		back9StartingHolesList = aValues;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String[] getBack9StartingHoles() {
		return back9StartingHolesList.toArray(
				new String[back9StartingHolesList.size()]);
	}
	
	/**
	 * Setter
	 * @param aScoreValues
	 */
	public void setScoreValuesList(List<String> aScoreValues) {
		scoreValuesList = aScoreValues;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String[] getScoreValues() {
		return scoreValuesList.toArray(new String[scoreValuesList.size()]);
	}
	
	/**
	 * Setter
	 * @param aCountryId
	 */
	public void setCountryId(String aCountryId) {
		countryId = aCountryId;
	}
	
	/**
	 * Setter
	 * @param aStateProvinceId
	 */
	public void setStateProvinceId(String aStateProvinceId) {
		stateProvinceId = aStateProvinceId;
	}
	
	/**
	 * Setter
	 * @param aNumCourseHoles
	 */
	public void setDefaultNumCourseHoles(int aNumCourseHoles) {
		defaultNumCourseHoles = aNumCourseHoles;
	}
	
	/**
	 * Setter
	 * @param aDefaultParValue
	 */
	public void setDefaultParValue(int aDefaultParValue) {
		defaultParValue = aDefaultParValue;
	}
	
	/**
	 * Setter
	 * @param aDefaultPuttValue
	 */
	public void setDefaultPuttValue(int aDefaultPuttValue) {
		defaultPuttValue = aDefaultPuttValue;
	}
	
	/**
	 * Setter
	 * @param aAdminEmail
	 */
	public void setAdminEmail(String aAdminEmail) {
		adminEmail = aAdminEmail;
	}
	
	/**
	 * Setter
	 * @param aSuggestionsEmail
	 */
	public void setSuggestionsEmail(String aSuggestionsEmail) {
		suggestionsEmail = aSuggestionsEmail;
	}
	
	/**
	 * Initializes this object.
	 * @param aServletCtx
	 */
	public synchronized void initialize() {
		
		if (!isInitialized) {
			
			// create menubars...
			createMenubars();
			isInitialized = true;
		}
	}
	
	/**
	 * Creates the logged-in and not-logged-in menubar objects
	 *
	 */
	private void createMenubars() {
		loggedInMenubar = createMenubar(Constants.MENU_XSD, 
				Constants.MENU_LOGGEDIN_XML);
		notLoggedInMenubar = createMenubar(Constants.MENU_XSD, 
				Constants.MENU_NOTLOGGEDIN_XML);
	}
	
	/**
	 * Creates and returns a menubar object based on the inputted XSD and 
	 * XML file names
	 * @param aXSDFile
	 * @param aXMLFile
	 * @return
	 */
	private Menubar createMenubar(String aXSDFile, String aXMLFile) {
		
		JAXBContext jc;
		Unmarshaller um;
		Menubar menubar;
		
		try {
			jc = JAXBContext.newInstance("gen.jaxb.name.paulevans.golf");
			um = jc.createUnmarshaller();
			menubar = (Menubar)um.unmarshal(getClass().getResourceAsStream(
					"/" + aXMLFile));
			return menubar;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the link-to-menu mappings map
	 * @return
	 */
	public final Map<String, String> getLinkToMenuMappings() {
		return linkToMenuMappings;
	}

	/**
	 * Sets the link-to-menu mappings map
	 * @param aLinkToMenuMappings
	 */
	public final void setLinkToMenuMappings(Map<String, String> aLinkToMenuMappings) {
		linkToMenuMappings = aLinkToMenuMappings;
	}

	/**
	 * Set the selected menu using the link-to-menu mappings map
	 * @param aMapping
	 * @param aMenubar
	 */
	public void setSelectedMenu(String aMapping, Menubar aMenubar) {

		String name;
		List<Menu> menus;

		name = linkToMenuMappings.get(aMapping);
		menus = aMenubar.getMenu();
		for (Menu menu : menus) {
			if (StringUtils.equals(name, menu.getName())) {
				menu.setSelected(true);
				break;
			}
		}
	}
	
	/**
	 * Returns the key corresponding to the given value.
	 * @param aMap
	 * @param aValue
	 * @return
	 */
	public static final String getKey(Map<String,String> aMap, String aValue) {
		
		Set<Map.Entry<String,String>> entrySet = aMap.entrySet();
		Map.Entry<String,String> entry;
		Iterator<Map.Entry<String,String>> iterator;
		String key;
		
		key = null;
		iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			entry = iterator.next();
			if (entry.getValue().equals(aValue)) {
				key = entry.getKey();
				break;
			}
		}
		return key;
	}
	
	/**
	 * Return the state-province object associated with the home course of the 
	 * current player in the session; or, if no home course is set, use the 
	 * state-province information from the servlet context.
	 * @param aRequest
	 * @return
	 */
	public StateProvinceDAO getStateProvince(HttpServletRequest aRequest) {
		
		CourseDAO homeCourse;
		StateProvinceDAO stateProvince;
		PlayerDAO player;
		
		player = getPlayer(aRequest);
		if (player != null) {
			homeCourse = player.getCourse();
			
			if (homeCourse != null) {
				stateProvince = homeCourse.getStateProvince();
				logger.info("country/state-province IDs initialized from " + 
				"player's home course address information");
			} else {
				// init state province object with data from the servlet ctx...
				stateProvince = getStateProvince();
			}
		} else {
			// init state province object with data from the servlet ctx...
			stateProvince = getStateProvince();
		}
		return stateProvince;
	}
	
	/**
	 * Returns a state province object initialized from the servlet ctx.
	 *
	 */
	private StateProvinceDAO getStateProvince() {
		
		StateProvinceDAO stateProvince; 
		name.paulevans.golf.dao.StateProvinceId stateProvinceId;
		
		// init the state province object with data from the servlet ctx...
		stateProvince = BeanFactory.newStateProvinceDAO();
		stateProvinceId = new name.paulevans.golf.dao.StateProvinceId();
		stateProvinceId.setId(Integer.parseInt(getStateProvinceId()));
		stateProvinceId.setCountryId(Integer.parseInt(getCountryId()));
		stateProvince.setId(stateProvinceId);
		logger.info("country/state-province IDs initialized from " + 
			"servlet context init parameter values");
		return stateProvince;
	}
	
	/**
	 * Parses aValues into a string array
	 * @param aValues
	 * @return
	 */
	public static String[] getValues(String aValues) {
		
		Scanner scanner;
		List<String> values;
		
		values = new ArrayList<String>();
		scanner = new Scanner(aValues).useDelimiter(",");
		while (scanner.hasNext()) {
			values.add(scanner.next());
		}
		return values.toArray(new String[values.size()]);
	}

	/**
	 * Returns todays date as a formatted string.
	 * @return
	 */
	public String getTodaysDate() {
		return simpleDateFormat.format(new java.util.Date());
	}
	
	/**
	 * Returns todays date as a formatted string using the "input" pattern
	 * described in web.xml.
	 * @return
	 */
	public String getTodaysDateInput() {
		return simpleDateFormatInput.format(new java.util.Date());
	}
	
	/**
	 * Returns the date object given an inputted date string.  The inputted
	 * date string should be of the format: MM/DD/YYYY
	 * @param aDate
	 * @return
	 */
	public Date parse(String aDate) {
		try {
			return simpleDateFormatInput.parse(aDate);
		} catch (ParseException aException) {
			throw new RuntimeException(aException);
		}
	}
	
	/**
	 * Returns the string representation of aDate using the form mm/dd/yyyy
	 * @param aDate
	 * @return
	 */
	public String format(Date aDate) {
		return simpleDateFormatInput.format(aDate);
	}

	/**
	 * Getter
	 * @return
	 */
	public String[] getParValues() {
		return parValues.toArray(new String[parValues.size()]);
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCountryId() {
		return countryId;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getStateProvinceId() {
		return stateProvinceId;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getDefaultNumCourseHoles() {
		return defaultNumCourseHoles;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getDefaultParValue() {
		return defaultParValue;
	}

	/**
	 * Returns the player object from the session
	 * @param aRequest
	 * @return
	 */
	public static final PlayerDAO getPlayer(HttpServletRequest aRequest) {
		return (PlayerDAO)aRequest.getSession().getAttribute(
				AttributeKeyConstants.PLAYER);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getAdminEmail() {
		return adminEmail;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getSuggestionsEmail() {
		return suggestionsEmail;
	}

	/**
	 * Getter
	 * @return
	 */
	public int getDefaultPuttValue() {
		return defaultPuttValue;
	}
	
	/**
	 * Returns the menubar
	 * @param aRequest
	 * @return
	 */
	public final Menubar getMenubar(HttpServletRequest aRequest) {
		return isLoggedIn(aRequest) ? loggedInMenubar : notLoggedInMenubar;
	}
	
	/**
	 * Returns the menuitem list associated with the selected menu
	 * @param aMenubar
	 * @return
	 */
	public final List<Menuitem> getMenuItems(Menubar aMenubar) {
		
		List<Menu> menuList;
		
		menuList = aMenubar.getMenu();
		for (Menu menu : menuList) {
			if (menu.isSelected()) {
				return menu.getMenuitem();
			}
		}
		return new ArrayList<Menuitem>();
	}
	
	/**
	 * Returns true only if aVal1 and aVal2 are non-null and contain equal integer
	 * values.
	 * @param aVal1
	 * @param aVal2
	 * @return
	 */
	public static final boolean equal(Integer aVal1, Integer aVal2) {
		
		boolean areEqual;
		int ival1, ival2;
		
		areEqual = false;
		if (aVal1 != null) {
			ival1 = aVal1.intValue();
			if (aVal2 != null) {
				ival2 = aVal2.intValue();
				areEqual = ival1 == ival2;
			}
		}
		return areEqual;
	}

	/**
	 * Returns true only if aVal1 is not-null and the values are equal.
	 * @param aVal1
	 * @param aVal2
	 * @return
	 */
	public static final boolean equal(Integer aVal1, int aVal2) {
		
		boolean areEqual;
		int ival1;
		
		areEqual = false;
		if (aVal1 != null) {
			ival1 = aVal1.intValue();
			areEqual = ival1 == aVal2;
		}
		return areEqual;
	}	
}
