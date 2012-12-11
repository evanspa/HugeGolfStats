package name.paulevans.golf.web.struts.formbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.LabelStringFactory;
import name.paulevans.golf.dao.ApproachShotDistanceDAO;
import name.paulevans.golf.dao.ApproachShotLineDAO;
import name.paulevans.golf.dao.CountryDAO;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.EyeWearDAO;
import name.paulevans.golf.dao.GolfClubDAO;
import name.paulevans.golf.dao.GreenInRegulationDAO;
import name.paulevans.golf.dao.HeadWearDAO;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.PantWearDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScoreDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeId;
import name.paulevans.golf.dao.TeeNameDAO;
import name.paulevans.golf.dao.TeeShotAccuracyDAO;
import name.paulevans.golf.dao.WeatherConditionDAO;
import name.paulevans.golf.util.NewUtil;
import name.paulevans.golf.web.AttributeKeyConstants;
import name.paulevans.golf.web.WebConstants;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for adding a round.
 * @author pevans
 *
 */
public class RoundForm extends BaseForm {
	
	// logger...
	private static final Logger logger = Logger.getLogger(RoundForm.class);
	
	// lookup maps for display values...
	private static final Map<String,String> GOLF_CLUB_DISPLAY = new HashMap();
	private static final Map<String,String> TEE_SHOT_ACCURACY_DISPLAY = new HashMap();
	
	// Used to store if the display-maps has been initialized or not...
	private static boolean DISPLAY_MAPS_INITTED = false;
	
	// instance members...
	private int courseTeeNameId;
	private EyeWearDAO eyeWearDAO;
	private HeadWearDAO headWearDAO;
	private PantWearDAO pantWearDAO;
	private WeatherConditionDAO weatherConditionDAO;
	private boolean isInitialized;
	private boolean longSleevesWorn;
	private boolean vestWorn;
	private boolean jacketWorn;
	private boolean isTournamentScore;
	private int numAvailableHoles;
	private String date;
	private Map<String,String> holesPlayed;
	private StateProvinceDAO stateProvince;
	private String city;	   // used for searching...
	private String courseName; // used for searching...
	private CourseDAO courseDAO;
	private Map<String,String> scores;
	private Map<String,String> putts;
	private Map<String,HoleDAO> holes;
	private Map<String,String> teeShotAccuracyMap;
	private Map<String,String> girMap;
	private Map<String,String> approachShotLineMap;
	private Map<String,String> approachShotDistanceMap;
	private Map<String,String> sandSaveAttemptMap, sandSaveConvertMap;
	private Map<String,String> upDownAttemptMap, upDownConvertMap;
	private Map<String,String> teeShotGolfClubMap;
	private Map<String,String> teeShotDistanceMap;
	private NewUtil util;
	private TeeNameDAO teeNameDAO;
	private int defaultPuttValue;
	private String scorer;
	private String attestedBy;
	private boolean usedCart;
	private boolean usedCaddie;
	private String journalNotes;
	private int front9StartingHole;
	private int back9StartingHole;
	private boolean isEditMode;
	private ScorecardDAO scorecard; // only used in edit-mode...
	private String collectClubUsedOffTee;
	private String collectTeeShotDistance;
	private String collectNumPutts;
	private String collectFairwayHit;
	private String collectGir;
	private String collectApproachShotLine;
	private String collectApproachShotDistance;
	private String collectSandSave;
	private String collectUpDown;
	private String collectPerHoleStats;
	private String overallScore;
	
	/**
	 * no-arg constructor
	 *
	 */
	public RoundForm() {
		initStructures();
	}
	
	/**
	 * Init the member variables
	 *
	 */
	public void initStructures() {
		courseTeeNameId = 0;
		isInitialized = false;
		longSleevesWorn = false;
		vestWorn = false;
		jacketWorn = false;
		isTournamentScore = false;
		numAvailableHoles = 0;
		date = null;
		city = null;
		courseName = null;
		defaultPuttValue = 0;
		scorer = null;
		attestedBy = null;
		usedCart = false;
		usedCaddie = false;
		journalNotes = null;
		front9StartingHole = 0;
		back9StartingHole = 0;
		isEditMode = false;
		scorecard = null;
		collectClubUsedOffTee = null;
		collectTeeShotDistance = null;
		collectNumPutts = null;
		collectFairwayHit = null;
		collectGir = null;
		collectApproachShotLine = null;
		collectApproachShotDistance = null;
		collectSandSave = null;
		collectUpDown = null;
		holesPlayed = new HashMap<String,String>();
		stateProvince = BeanFactory.newStateProvinceDAO();
		courseDAO = BeanFactory.newCourseDAO();
		scores = new HashMap<String,String>();
		putts = new HashMap<String,String>();
		holes = new HashMap<String,HoleDAO>();
		teeShotAccuracyMap = new HashMap<String,String>();
		girMap = new HashMap<String,String>();
		approachShotLineMap = new HashMap<String,String>();
		teeShotGolfClubMap = new HashMap<String,String>();
		teeShotDistanceMap = new HashMap<String,String>();
		approachShotDistanceMap = new HashMap<String,String>();
		sandSaveAttemptMap = new HashMap<String,String>();
		sandSaveConvertMap = new HashMap<String,String>();
		upDownAttemptMap = new HashMap<String,String>();
		upDownConvertMap = new HashMap<String,String>();
		eyeWearDAO = BeanFactory.newEyeWearDAO();
		headWearDAO = BeanFactory.newHeadWearDAO();
		pantWearDAO = BeanFactory.newPantWearDAO();
		weatherConditionDAO = BeanFactory.newWeatherConditionDAO();		
	}
	
	/**
	 * Getter
	 * @return
	 */
	public boolean getUsedCaddie() {
		return usedCaddie;
	}

	/**
	 * Setter
	 * @param aUsedCaddie
	 */
	public void setUsedCaddie(boolean aUsedCaddie) {
		usedCaddie = aUsedCaddie;
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean getUsedCart() {
		return usedCart;
	}

	/**
	 * Setter
	 * @param aUsedCart
	 */
	public void setUsedCart(boolean aUsedCart) {
		usedCart = aUsedCart;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Map<String,String> getSandSaveAttemptsMap() {
		return sandSaveAttemptMap;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Map<String,String> getSandSaveConvertsMap() {
		return sandSaveConvertMap;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Map<String,String> getUpDownAttemptsMap() {
		return upDownAttemptMap;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Map<String,String> getUpDownConvertsMap() {
		return upDownConvertMap;
	}
	
	/**
	 * Loads the hole objects
	 */
	public void loadHoles() {
		
		// create the tee color object...
		teeNameDAO = BeanFactory.newTeeNameDAO();
		teeNameDAO.setId(courseTeeNameId);
		
		// load the course and tee color objects...
		getDAOUtils().loadObjects(courseDAO, teeNameDAO);
		
		// get the holes...
		holes = getDAOUtils().getHoles(courseDAO, courseTeeNameId);
	}
	
	/**
	 * Returns the tee played
	 * @return
	 */
	public TeeDAO getTee() {
		return courseDAO.getTee(new TeeId((Integer)teeNameDAO.getId()));
	}
	
	/**
	 * Returns the array of holes for the selected course and tee color that
	 * were actually played
	 * @return
	 */
	public HoleDAO[] getHoleDAOsPlayed() {
		
		List<HoleDAO> actualHolesPlayed;
		HoleDAO allHoles[];
		int loop;
		
		actualHolesPlayed = new ArrayList<HoleDAO>();
		allHoles = holes.values().toArray(new HoleDAO[holes.size()]);
		for (loop = 0; loop < allHoles.length; loop++) {
			if (inHolesPlayedRange(allHoles[loop].getNumber())) {
				actualHolesPlayed.add(allHoles[loop]);
			}
		}
		return actualHolesPlayed.toArray(new HoleDAO[actualHolesPlayed.size()]);
	}
	
	/**
	 * Returns true if aHoleNum falls within the range of holes played
	 * @param aHoleNum
	 * @return
	 */
	private boolean inHolesPlayedRange(int aHoleNum) {
		
		Set<String> holesPlayedRanges;
		int range[];
		boolean inRange;
		
		inRange = false;
		holesPlayedRanges = holesPlayed.keySet();
		for (String holesPlayedRange : holesPlayedRanges) {
			range = NewUtil.getHolesPlayedRange(holesPlayedRange);
			if (aHoleNum >= range[0] && aHoleNum <= range[1]) {
				inRange = true;
				break;
			}
		}
		return inRange;
	}
	
	/**
	 * Loads the DAO objects so the descriptions are available
	 *
	 */
	public void loadDescriptions() {
		getDAOUtils().loadObjects(eyeWearDAO, headWearDAO, pantWearDAO, 
				weatherConditionDAO);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getHeadWearDescription() {
		return headWearDAO.getDescription();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getWeatherConditionDescription() {
		return weatherConditionDAO.getDescription();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public WeatherConditionDAO getWeatherConditionDAO() {
		return weatherConditionDAO;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public EyeWearDAO getEyeWearDAO() {
		return eyeWearDAO;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public HeadWearDAO getHeadWearDAO() {
		return headWearDAO;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public PantWearDAO getPantWearDAO() {
		return pantWearDAO;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getPantWearDescription() {
		return pantWearDAO.getDescription();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getEyeWearDescription() {
		return eyeWearDAO.getDescription();
	}
	
	/**
	 * Returns the total yardage for the holes selected
	 * @return
	 */
	public int getTotalYardage() {
		
		Integer startingHoles[];
		int loop, hole;
		int totalYardage;
		
		totalYardage = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				totalYardage += holes.get(Integer.toString(hole)).getYards();
			}
		}
		return totalYardage;
	}
	
	/**
	 * Returns the total par for the holes selected
	 * @return
	 */
	public int getTotalPar() {
		
		Integer startingHoles[];
		int loop, hole;
		int totalPar;
		
		totalPar = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				totalPar += holes.get(Integer.toString(hole)).getPar();
			}
		}
		return totalPar;
	}
	
	/**
	 * Returns the total number of putts for the holes selected
	 * @return
	 */
	public int getTotalPutts() {
		
		Integer startingHoles[];
		int loop, hole;
		int totalPutts;
		
		totalPutts = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				totalPutts += Integer.parseInt(putts.get(
						Integer.toString(hole)));
			}
		}
		return totalPutts;
	}
	
	/**
	 * Returns the average number of putts per hole as a string to display.
	 * @return
	 */
	public String getAvgPuttsPerHoleDisplay() {
		return NewUtil.formatNumber3Digits(getAvgPuttsPerHole(), getLocale());
	}
	
	/**
	 * Returns the average number of putts per hole.
	 * @return
	 */
	public float getAvgPuttsPerHole() {
		return (float)getTotalPutts() / getTotalNumHolesPlayed();
	}
	
	/**
	 * Returns the total score for the holes selected
	 * @return
	 */
	public int getTotalScore() {
		
		Integer startingHoles[];
		int loop, hole;
		int totalScore;
		
		totalScore = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				totalScore += Integer.parseInt(scores.get(Integer.toString(hole)));
			}
		}
		return totalScore;
	}
	
	/**
	 * Returns the total number of fairways available to hit (returns the 
	 * total number of holes with a par higher than 3).
	 * @return
	 */
	public int getNumFairways() {
		
		Integer startingHoles[];
		int loop, hole, par;
		int numFairways;
		
		numFairways = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				par = Integer.parseInt(getPar(hole));
				if (par > 3) {
					numFairways++;
				}
			}
		}
		return numFairways;
	}
	
	/**
	 * Returns the total number of holes played.
	 * @return
	 */
	public int getTotalNumHolesPlayed() {
		return holesPlayed.size() * 9;
	}
	
	/**
	 * Returns the count of values that match aMatchValue from aValues
	 * @param aValues
	 * @param aMatchValue
	 * @return
	 */
	private static final int getCount(Integer aStartingHoles[], 
			Map<String,String> aValues, int aMatchValue) {
		
		int loop, hole, value;
		int count;
		String id;
		
		count = 0;
		for (loop = 0; loop < aStartingHoles.length; loop++) {
			for (hole = aStartingHoles[loop]; hole < aStartingHoles[loop]+9; 
			hole++) {
				id = aValues.get(Integer.toString(hole));
				if (id != null) {
					value = Integer.parseInt(id);
					if (value == aMatchValue) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	/**
	 * Returns the total number of pin-high approach shots
	 * @return
	 */
	public int getNumPinHighApproachShot() {
		return getCount(getStartingHoles(), approachShotDistanceMap, 
				Constants.APPROACH_SHOT_DISTANCE_PIN_HIGH);
	}
	
	/**
	 * Returns the total number of center-line approach shots
	 * @return
	 */
	public int getNumCenterLineApproachShot() {
		return getCount(getStartingHoles(), approachShotLineMap, 
				Constants.APPROACH_SHOT_LINE_GREEN);
	}
	
	/**
	 * Returns the total number of GIRs hit.
	 * @return
	 */
	public int getNumGIRs() {
		
		Integer startingHoles[];
		int loop, hole, value;
		int numGIRs;
		
		numGIRs = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				value = Integer.parseInt(girMap.get(Integer.toString(hole)));
				if (value != Constants.GIR_NO_GIR) {
					numGIRs++;
				}
			}
		}
		return numGIRs;
	}
	
	/**
	 * Returns the total number of fairways hit.
	 * @return
	 */
	public int getNumFairwaysHit() {
		return getCount(getStartingHoles(), teeShotAccuracyMap, 
				Constants.FAIRWAY_HIT_ID);
	}
	
	/**
	 * Returns the total number of putts when the GIR is hit
	 * @return
	 */
	public Integer getTotalPuttsGIR() {
		
		Integer startingHoles[];
		int loop, hole, value;
		int numPutts, totalPutts, numGIRsHit;
		
		numGIRsHit = 0;
		totalPutts = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				value = Integer.parseInt(girMap.get(Integer.toString(hole)));
				numPutts = Integer.parseInt(putts.get(Integer.toString(hole)));
				if (value != Constants.GIR_NO_GIR) {
					totalPutts += numPutts;
					numGIRsHit++;
				}
			}
		}
		return numGIRsHit == 0 ? null : totalPutts;
	}
	
	/**
	 * Returns the total putts GIR as a string to display
	 * @return
	 */
	public String getTotalPuttsGIRDisplay() {
		
		Integer totalPuttsGIR;
		
		totalPuttsGIR = getTotalPuttsGIR();
		return totalPuttsGIR != null ? totalPuttsGIR.toString() : 
			LabelStringFactory.getInstance(getLocale()).getString(
				LabelStringFactory.GENERAL_LABEL_NOTAPPLICABLE);
	}
	
	/**
	 * Returns the average number of putts per hole when the GIR is hit as a
	 * string to display
	 * @return
	 */
	public String getAvgPuttsPerHoleGIRDisplay() {
		return NewUtil.formatNumber3Digits(getAvgPuttsPerHoleGIR(), getLocale());
	}
	
	/**
	 * Returns the average number of putts per hole when the GIR is hit
	 * @return
	 */
	public Float getAvgPuttsPerHoleGIR() {
		return NewUtil.divide(getTotalPuttsGIR(), getNumGIRs());
	}
	
	/**
	 * Returns the GIRs-hit percentage value as a string to display.
	 * @return
	 */
	public String getGIRsPercentageDisplay() {
		return NewUtil.formatPercent1Digit(getGIRsPercentage(), getLocale());
	}
	
	/**
	 * Returns the GIRs-hit percentage value.
	 * @return
	 */
	public float getGIRsPercentage() {
		return (float)getNumGIRs() / getTotalNumHolesPlayed();
	}
	
	/**
	 * Returns the percentage of center-hit approach shot line values.
	 * @return
	 */
	public Float getCenterLineApproachShotPercentage() {
		return NewUtil.divide(getNumCenterLineApproachShot(), getNumFairways());
	}
	
	/**
	 * Returns the percentage of pin-high approach shot distance values.
	 * @return
	 */
	public Float getPinHighApproachShotPercentage() {
		return NewUtil.divide(getNumPinHighApproachShot(), getNumFairways());
	}
	
	/**
	 * Returns the number of attempted up and downs.
	 * @return
	 */
	public int getNumUpAndDownAttempts() {
		return upDownAttemptMap.size();
	}
	
	/**
	 * Returns the number of up and down conversions
	 * @return
	 */
	public int getNumUpAndDownConversions() {
		return upDownConvertMap.size();
	}

	/**
	 * Returns the up-and-down conversion percentage as a string to display
	 * @return
	 */
	public String getUpAndDownConversionPercentageDisplay() {
		return NewUtil.formatPercent1Digit(getUpAndDownConversionPercentage(), 
				getLocale());
	}
	
	/**
	 * Returns the up-and-down conversion percentage
	 * @return
	 */
	public Float getUpAndDownConversionPercentage() {
		return NewUtil.divide(getNumUpAndDownConversions(),
				getNumUpAndDownAttempts());
	}
	
	/**
	 * Returns the number of sand save attempts
	 * @return
	 */
	public int getNumSandSaveAttempts() {
		return sandSaveAttemptMap.size();
	}
	
	/**
	 * Returns the number of sand save conversions
	 * @return
	 */
	public int getNumSandSaveConversions() {
		return sandSaveConvertMap.size();
	}
	
	/**
	 * Returns the sand save conversion percentage as a formatted string
	 * @return
	 */
	public String getSandSaveConversionPercentageDisplay() {
		return NewUtil.formatPercent1Digit(getSandSaveConversionPercentage(),
				getLocale());
	}
	
	/**
	 * Returns the sand save conversion percentage
	 * @return
	 */
	public Float getSandSaveConversionPercentage() {
		return NewUtil.divide(getNumSandSaveConversions(), 
				getNumSandSaveAttempts());
	}
	
	/**
	 * Returns the fairways-hit percentage value as a formatted string.
	 * @return
	 */
	public String getFairwaysHitPercentageDisplay() {
		return NewUtil.formatPercent1Digit(getFairwaysHitPercentage(), getLocale());
	}
	
	/**
	 * Returns the fairways-hit percentage value
	 * @return
	 */
	public Float getFairwaysHitPercentage() {
		return NewUtil.divide(getNumFairwaysHit(), getNumFairways());
	}
	
	/**
	 * Returns the number of pars scored.
	 * @return
	 */
	public int getNumPars() {
		return getNumHits(0);
	}
	
	/**
	 * Returns the number of birdies scored.
	 * @return
	 */
	public int getNumBirdies() {
		return getNumHits(-1);
	}
	
	/**
	 * Returns the number of bogeys scored.
	 * @return
	 */
	public int getNumBogeys() {
		return getNumHits(1);
	}
	
	/**
	 * Returns the number of double bogeys scored.
	 * @return
	 */
	public int getNumDblBogeys() {
		return getNumHits(2);
	}
	
	/**
	 * Returns the number of triple bogeys or worse scored.
	 * @return
	 */
	public int getNumTplBogeys() {
		return getNumHitsOrWorse(3);
	}
	
	/**
	 * Returns the number of eagles or better scored.
	 * @return
	 */
	public int getNumEagles() {
		return getNumHitsOrBetter(-2);
	}

	/**
	 * Returns the number of 3 putts made
	 * @return
	 */
	public int getNumThreePutts() {
		return getPuttCount(3, false);
	}	
	
	/**
	 * Returns the number of 2 putts made
	 * @return
	 */
	public int getNumTwoPutts() {
		return getPuttCount(2, false);
	}	
	
	/**
	 * Returns the number of 1 putts made
	 * @return
	 */
	public int getNumOnePutts() {
		return getPuttCount(1, false);
	}	
	
	/**
	 * Returns the number of 0 putts made
	 * @return
	 */
	public int getNumZeroPutts() {
		return getPuttCount(0, false);
	}		
	
	/**
	 * Returns the number of 4 putts made or worse
	 * @return
	 */
	public int getNumFourPutts() {
		return getPuttCount(4, true);
	}		

	/**
	 * Returns the number of putts made
	 * @return
	 */
	private int getPuttCount(int aNumPutts, boolean aGreatherThanEqualTo) {
		
		Integer startingHoles[];
		int loop, hole, puttCount;
		int counter;
		
		counter = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				puttCount = Integer.parseInt(getNumPutts(hole));
				if (aGreatherThanEqualTo) {
					if (puttCount >= aNumPutts) {
						counter++;
					}
				} else {
					if (puttCount == aNumPutts) {
						counter++;
					}
				}
			}
		}
		return counter;
	}	
	
	/**
	 * Returns the number of scores that are equal to par + aAdditive.
	 * @return
	 */
	private int getNumHits(int aAdditive) {
		
		Integer startingHoles[];
		int loop, hole, score, par;
		int numHits;
		
		numHits = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				score = Integer.parseInt(getScore(hole));
				par = Integer.parseInt(getPar(hole));
				if (score == (par + aAdditive)) {
					numHits++;
				}
			}
		}
		return numHits;
	}
	
	/**
	 * Returns the length of the average tee shot for non-par-3 holes as a
	 * string to display
	 * @return
	 */
	public String getAvgTeeShotDistanceDisplay() {
		
		Float avgTeeShotDistance;
		
		avgTeeShotDistance = getAvgTeeShotDistance();
		if (avgTeeShotDistance == null) {
			avgTeeShotDistance = 0.0F;
		}
		return NewUtil.formatNumber1Digit(avgTeeShotDistance, getLocale());
	}
	
	/**
	 * Returns the length of the average tee shot for non-par-3 holes.
	 * @return
	 */
	public Float getAvgTeeShotDistance() {
		
		Integer startingHoles[];
		int loop, hole, driveDistance, par, totalDriveDistance, holeCount;
		Float avgTeeShotDistance; 
		
		holeCount = 0;
		totalDriveDistance = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				driveDistance = NumberUtils.toInt(getTeeShotDistance(
						Integer.toString(hole)));
				par = Integer.parseInt(getPar(hole));
				if (par > 3) {
					// only count those positive-value distances...
					if (driveDistance != BaseForm.NA_VALUE && 
						driveDistance > 0) { 
						totalDriveDistance += driveDistance;
						holeCount++;
					}
				}
			}
		}
		avgTeeShotDistance = null;
		if (holeCount > 0) {
			avgTeeShotDistance = (float)totalDriveDistance / holeCount;
		}
		return avgTeeShotDistance;
	}
	
	/**
	 * Returns the length of the longest drive of the round
	 * @return
	 */
	public int getLongestDrive() {
		
		Integer startingHoles[];
		int loop, hole, driveDistance, longestDrive;
		
		longestDrive = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				driveDistance = NumberUtils.toInt(getTeeShotDistance(
						Integer.toString(hole)));
				if (driveDistance > longestDrive) {
					longestDrive = driveDistance;
				}
			}
		}
		return longestDrive;
	}
	
	/**
	 * Returns the avg score given the par of the hole as indicated by aHolePar
	 * @param aHolePar
	 * @return
	 */
	private Float getAvg(int aHolePar) {
		
		Integer startingHoles[];
		int loop, hole, par, score, holeCount;
		float totalScore;
		Float avgValue;
		
		avgValue = null;
		holeCount = 0;
		totalScore = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				score = Integer.parseInt(getScore(hole));
				par = Integer.parseInt(getPar(hole));
				if (par == aHolePar) {
					holeCount++;
					totalScore += score;
				}
			}
		}
		if (holeCount > 0) {
			avgValue = totalScore / holeCount;
		}
		return avgValue;
	}
	
	/**
	 * Returns the avg score given the par of the hole as indicated by aHolePar
	 * but as a formatted string to 2 decimal places
	 * @param aHolePar
	 * @return
	 */
	public String getAvgDisplay(String aHolePar) {
		return NewUtil.formatNumber2Digits(getAvg(Integer.parseInt(aHolePar)), 
				getLocale());
	}
	
	/**
	 * Returns the par-3 average for this round
	 * @return
	 */
	public Float getPar3Avg() {
		return getAvg(3);
	}
	
	/**
	 * Returns the par-4 average for this round
	 * @return
	 */
	public Float getPar4Avg() {
		return getAvg(4);
	}
	
	/**
	 * Returns the par-5 average for this round
	 * @return
	 */
	public Float getPar5Avg() {
		return getAvg(5);
	}
	
	/**
	 * Returns the par-6 average for this round
	 * @return
	 */
	public Float getPar6Avg() {
		return getAvg(6);
	}
	
	/**
	 * Returns the number of scores that are less than or equal to 
	 * par + aAdditive.
	 * @return
	 */
	public int getNumHitsOrWorse(int aAdditive) {
		
		Integer startingHoles[];
		int loop, hole, score, par;
		int numHits;
		
		numHits = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				score = Integer.parseInt(getScore(hole));
				par = Integer.parseInt(getPar(hole));
				if (score >= (par + aAdditive)) {
					numHits++;
				}
			}
		}
		return numHits;
	}
	
	/**
	 * Returns the number of scores that are less than or equal to 
	 * par + aAdditive.
	 * @return
	 */
	private int getNumHitsOrBetter(int aAdditive) {
		
		Integer startingHoles[];
		int loop, hole, score, par;
		int numHits;
		
		numHits = 0;
		startingHoles = getStartingHoles();
		for (loop = 0; loop < startingHoles.length; loop++) {
			for (hole = startingHoles[loop]; hole < startingHoles[loop]+9; 
			hole++) {
				score = Integer.parseInt(getScore(hole));
				par = Integer.parseInt(getPar(hole));
				if (score <= (par + aAdditive)) {
					numHits++;
				}
			}
		}
		return numHits;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getTeeName() {
		return teeNameDAO.getName();
	}
	
	/**
	 * Returns the par of hole aHoleNum
	 * @param aHoleNum
	 * @return
	 */
	public String getPar(String aHoleNum) {
		return getPar(Integer.parseInt(aHoleNum));
	}
	
	/**
	 * Returns the par of hole aHoleNum.
	 * @param aHoleNum
	 * @return
	 */
	private String getPar(int aHoleNum) {
		return Integer.toString(holes.get(Integer.toString(aHoleNum)).getPar());
	}
	
	/**
	 * Returns the total nine-hole par from aStartingHole.  If aStartingHole
	 * is the value 10, then the total par for holes 10-18 will return returned.
	 * @param aStartingHole
	 * @return
	 */
	public int getNineHolePar(String aStartingHole) {
		
		int totalPar;
		int hole;
		int startingHole;
		
		startingHole = Integer.parseInt(aStartingHole);
		totalPar = 0;
		for (hole = startingHole; hole < (startingHole + 9); hole++) {
			totalPar += Integer.parseInt(getPar(hole));
		}
		return totalPar;
	}
	
	/**
	 * Returns the total nine-hole score from aStartingHole.  If aStartingHole
	 * is the value 10, then the total score for holes 10-18 will return returned.
	 * @param aStartingHole
	 * @return
	 */
	public int getNineHoleScore(String aStartingHole) {
		
		int totalScore;
		int hole;
		int startingHole;
		
		startingHole = Integer.parseInt(aStartingHole);
		totalScore = 0;
		for (hole = startingHole; hole < (startingHole + 9); hole++) {
			totalScore += Integer.parseInt(getScore(hole));
		}
		return totalScore;
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aScore
	 */
	public void setScore(String aHoleNum, String aScore) {
		scores.put(aHoleNum, aScore);
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getScore(String aHoleNum) {
		return NewUtil.getString(scores, aHoleNum, getPar(aHoleNum));
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	private String getScore(int aHoleNum) {
		return getScore(Integer.toString(aHoleNum));
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	private String getNumPutts(int aHoleNum) {
		return getNumPutts(Integer.toString(aHoleNum));
	}

	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getUpDownConvert(String aHoleNum) {
		return upDownConvertMap.get(aHoleNum);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setUpDownConvert(String aHoleNum, String aValue) {
		upDownConvertMap.put(aHoleNum, aValue);
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getUpDownAttempt(String aHoleNum) {
		return upDownAttemptMap.get(aHoleNum);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setUpDownAttempt(String aHoleNum, String aValue) {
		upDownAttemptMap.put(aHoleNum, aValue);
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getSandSaveConvert(String aHoleNum) {
		return sandSaveConvertMap.get(aHoleNum);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setSandSaveConvert(String aHoleNum, String aValue) {
		sandSaveConvertMap.put(aHoleNum, aValue);
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getSandSaveAttempt(String aHoleNum) {
		return sandSaveAttemptMap.get(aHoleNum);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setSandSaveAttempt(String aHoleNum, String aValue) {
		sandSaveAttemptMap.put(aHoleNum, aValue);
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getApproachShotDistance(String aHoleNum) {
		return NewUtil.getString(approachShotDistanceMap, aHoleNum, 
				Constants.APPROACH_SHOT_DISTANCE_PIN_HIGH);
	}
	
	/**
	 * Returns an ApproachShotDistanceDAO object for hole aHoleNum initialized
	 * with the ID returned from getApproacheShotDistance(String).
	 * @param aHoleNum
	 * @return
	 */
	public ApproachShotDistanceDAO getApproacheShotDistanceDAO(int aHoleNum) {
		
		int approachShotDistanceId;
		ApproachShotDistanceDAO approachShotDistanceDAO;
		
		approachShotDistanceDAO = null;
		approachShotDistanceId = Integer.parseInt(
				getApproachShotDistance(Integer.toString(aHoleNum)));
		if (approachShotDistanceId != BaseForm.NA_VALUE) {
			approachShotDistanceDAO = BeanFactory.newApproachShotDistanceDAO(
					approachShotDistanceId);
		}
		return approachShotDistanceDAO;
	}

	/**
	 * Returns an ApproachShotLineDAO object for hole aHoleNum initialized
	 * with the ID returned from getApproacheShotLine(String).
	 * @param aHoleNum
	 * @return
	 */
	public ApproachShotLineDAO getApproacheShotLineDAO(int aHoleNum) {
		
		int approachShotLineId;
		ApproachShotLineDAO approachShotLineDAO;
		
		approachShotLineDAO = null;
		approachShotLineId = Integer.parseInt(
				getApproachShotLine(Integer.toString(aHoleNum)));
		if (approachShotLineId != BaseForm.NA_VALUE) {
			approachShotLineDAO = BeanFactory.newApproachShotLineDAO(
					approachShotLineId);
		}
		return approachShotLineDAO;
	}

	/**
	 * Returns a GolfClubDAO object for hole aHoleNum initialized
	 * with the ID returned from getTeeShotGolfClub(String).
	 * @param aHoleNum
	 * @return
	 */
	public GolfClubDAO getGolfClubDAO(int aHoleNum) {
		return BeanFactory.newGolfClubDAO(Integer.parseInt(
				getTeeShotGolfClub(Integer.toString(aHoleNum))));
	}
	
	/**
	 * Returns an GreenInRegulationDAO object for hole aHoleNum initialized
	 * with the ID returned from getGreenInRegulation(String).
	 * @param aHoleNum
	 * @return
	 */
	public GreenInRegulationDAO getGreenInRegulationDAO(int aHoleNum) {
		return BeanFactory.newGreenInRegulationDAO(Integer.parseInt(
				getGreenInRegulation(Integer.toString(aHoleNum))));
	}	

	/**
	 * Returns an TeeShotAccuracyDAO object for hole aHoleNum initialized
	 * with the ID returned from getTeeShotAccuracy(String).  Returns null
	 * if the ID is equal to BaseForm.NA_VALUE
	 * @param aHoleNum
	 * @return
	 */
	public TeeShotAccuracyDAO getTeeShotAccuracyDAO(int aHoleNum) {
		
		int teeShotAccuracyId;
		TeeShotAccuracyDAO teeShotAccuracyDAO;
		
		teeShotAccuracyDAO = null;
		teeShotAccuracyId = Integer.parseInt(
				getTeeShotAccuracy(Integer.toString(aHoleNum)));
		if (teeShotAccuracyId != BaseForm.NA_VALUE) {
			teeShotAccuracyDAO = BeanFactory.newTeeShotAccuracyDAO(
					teeShotAccuracyId);
		}
		return teeShotAccuracyDAO;
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setApproachShotDistance(String aHoleNum, String aValue) {
		approachShotDistanceMap.put(aHoleNum, aValue);
	}	
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getApproachShotLine(String aHoleNum) {
		return NewUtil.getString(approachShotLineMap, aHoleNum, 
				Constants.APPROACH_SHOT_LINE_GREEN);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setApproachShotLine(String aHoleNum, String aValue) {
		approachShotLineMap.put(aHoleNum, aValue);
	}	
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setTeeShotGolfClub(String aHoleNum, String aValue) {
		teeShotGolfClubMap.put(aHoleNum, aValue);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setTeeShotDistance(String aHoleNum, String aValue) {
		teeShotDistanceMap.put(aHoleNum, aValue);
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getTeeShotDistance(String aHoleNum) {
		return teeShotDistanceMap.get(aHoleNum);
	}
	
	/**
	 * Getter - returns the display name of the tee shot accuracy
	 * @param aHoleNum
	 * @return
	 */
	public String getTeeShotAccuracyDisplay(String aHoleNum, Locale aLocale) {
		
		String teeShotAccuracyId;
		
		teeShotAccuracyId = getTeeShotAccuracy(aHoleNum);	
		return TEE_SHOT_ACCURACY_DISPLAY.get(
				buildLookupKey(teeShotAccuracyId, aLocale));
	}
	
	/**
	 * Getter - returns the display name of the tee club used
	 * @param aHoleNum
	 * @return
	 */
	public String getTeeShotGolfClubDisplay(String aHoleNum, Locale aLocale) {
		
		String teeClubId;
		
		teeClubId = getTeeShotGolfClub(aHoleNum);	
		return GOLF_CLUB_DISPLAY.get(buildLookupKey(teeClubId, aLocale));
	}
	
	/**
	 * Populates the display lookup maps
	 *
	 */
	public synchronized static void initDisplayLookupMaps(DAOUtils aUtils, 
			Locale aLocale) {
		
		if (!DISPLAY_MAPS_INITTED) {
			initDisplayLookupMap(aUtils.getGolfClubTypes(aLocale), aLocale, 
					"shortDescription", GOLF_CLUB_DISPLAY);
			DISPLAY_MAPS_INITTED = true;
		}		
	}
	
	/**
	 * Adds elements to the 
	 * @param aDAOs
	 * @param aLocale
	 * @param aMethod
	 */
	private static void initDisplayLookupMap(DAO aDAOs[], Locale aLocale,
			String aMethod, Map aDisplayMap) {
		try {
			for (DAO dao : aDAOs) {
				aDisplayMap.put(buildLookupKey(dao.getId().toString(),
						aLocale), (String)PropertyUtils.getProperty(dao, aMethod));
			}
		} catch (Exception any) {
			throw new RuntimeException(any);
		}
	}
	
	/**
	 * Builds the key for getting a value from one of the 'DISPLAY' maps
	 * @param aId
	 * @param aLocale
	 * @return
	 */
	private static String buildLookupKey(String aId, Locale aLocale) {
		return aId + "-" + aLocale.getISO3Language();
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getTeeShotGolfClub(String aHoleNum) {
		
		int par;
		String defaultClubId;
		
		// set default club for par 4s and higher to driver; 5-iron for 
		// par-3s...
		par = Integer.parseInt(getPar(aHoleNum));
		defaultClubId = Constants.FIVE_IRON_ID;
		if (par >= 4) {
			defaultClubId = Constants.DRIVER_ID;
		}
		return NewUtil.getString(teeShotGolfClubMap, aHoleNum, defaultClubId);
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getGreenInRegulation(String aHoleNum) {
		return NewUtil.getString(girMap, aHoleNum, 
				Constants.GIR_GREATER_THAN_15_FEET);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setGreenInRegulation(String aHoleNum, String aValue) {
		girMap.put(aHoleNum, aValue);
	}	
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getTeeShotAccuracy(String aHoleNum) {
		return NewUtil.getString(teeShotAccuracyMap, aHoleNum, 
				Constants.FAIRWAY_HIT_ID);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aValue
	 */
	public void setTeeShotAccuracy(String aHoleNum, String aValue) {
		teeShotAccuracyMap.put(aHoleNum, aValue);
	}
	
	/**
	 * Setter
	 * @param aHoleNum
	 * @param aScore
	 */
	public void setNumPutts(String aHoleNum, String aNumPutts) {
		putts.put(aHoleNum, aNumPutts);
	}
	
	/**
	 * Setter
	 * @param aKey
	 * @param aValue
	 */
	public void setHolesPlayed(String aKey, String aValue) {
		holesPlayed.put(aKey, aValue);
	}
	
	/**
	 * Getter
	 * @param aHoleNum
	 * @return
	 */
	public String getNumPutts(String aHoleNum) {
		return NewUtil.getString(putts, aHoleNum, defaultPuttValue);
	}
	
	/**
	 * Returns the array of starting holes.
	 * @return
	 */
	public Integer[] getStartingHoles() {
		
		String holesPlayedValues[];
		List<Integer> startingHoles;
		Integer startingHolesArray[];
		int loop;
		
		holesPlayedValues = (String[])holesPlayed.keySet().toArray(
				new String[holesPlayed.size()]);
		startingHoles = new ArrayList<Integer>();
		for (loop = 0; loop < holesPlayedValues.length; loop++) {
			startingHoles.add(NewUtil.getStartingHole(holesPlayedValues[loop]));
		}
		startingHolesArray = (Integer[])startingHoles.toArray(
				new Integer[startingHoles.size()]);
		Arrays.sort(startingHolesArray);
		return startingHolesArray;
	}
	
	/**
	 * Getter
	 * @param aKey
	 * @return
	 */
	public String getHolesPlayed(String aKey) {
		return holesPlayed.get(aKey);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Map<String,String> getAllHolesPlayed() {
		return holesPlayed;
	}
	
	/**
	 * Initializes the form bean from an existing scorecard object
	 * @param aScorecard
	 */
	public void initialize(NewUtil aUtil, ScorecardDAO aScorecard, 
			ScorecardSummaryDAO aScorecardSummary) {
		
		Set<ScoreDAO> scores;
		HoleDAO hole;
		CourseDAO course;
		TeeDAO teesPlayed;
		StateProvinceId stateProvinceId;
		int numStartingHoles, loop;
		String holeNum;
		Integer driveDistance;
		List<HoleDAO> startingHoles;
		
		teesPlayed = aScorecard.getTee();
		course = teesPlayed.getCourse();
		stateProvinceId = (StateProvinceId)
		course.getStateProvince().getId();
		scores = aScorecard.getScores();
		scorecard = aScorecard;
		
		isEditMode = true;
		if (aScorecardSummary != null) {
			setOverallScore(Float.toString((aScorecardSummary.getNumHolesPlayed() == 9 ? 
					aScorecardSummary.getScore() / 2 : 
						aScorecardSummary.getScore())));
		}
		setCountryId(stateProvinceId.getCountryId());
		setStateProvinceId(stateProvinceId.getId());
		setCourseId((Integer)course.getId());
		setCourseTeeNameId((Integer)teesPlayed.getTeeName().getId());
		setDate(aUtil.format(aScorecard.getDate()));
		setWeatherConditionTypeId((Integer)
				aScorecard.getWeatherCondition().getId());
		setHeadWearTypeId((Integer)aScorecard.getHeadWear().getId());
		setEyeWearTypeId((Integer)aScorecard.getEyeWear().getId());
		setPantWearTypeId((Integer)aScorecard.getPantWear().getId());
		setUsedCart(aScorecard.getUsedCart());
		setUsedCaddie(aScorecard.getUsedCaddie());
		setScorer(aScorecard.getScorer());
		setAttestedBy(aScorecard.getAttestedBy());
		setJournalNotes(aScorecard.getJournalNotes());
		setIsTournamentScore(aScorecard.getIsTournament());
		setCollectApproachShotDistance(BooleanUtils.toBoolean(
				aScorecard.isCollectApproachShotDistance()) ?
						Constants.TRUE : Constants.FALSE);
		setCollectApproachShotLine(BooleanUtils.toBoolean(
				aScorecard.isCollectApproachShotLine()) ?
						Constants.TRUE : Constants.FALSE);
		setCollectClubUsedOffTee(BooleanUtils.toBoolean(
				aScorecard.isCollectClubUsedOffTee()) ?
						Constants.TRUE : Constants.FALSE);
		setCollectTeeShotDistance(BooleanUtils.toBoolean(
				aScorecard.isCollectTeeShotDistance()) ?
						Constants.TRUE : Constants.FALSE);
		setCollectGir(BooleanUtils.toBoolean(
				aScorecard.isCollectGir()) ? 
						Constants.TRUE : Constants.FALSE);
		setCollectFairwayHit(BooleanUtils.toBoolean(
				aScorecard.isCollectFairwayHit()) ?
						Constants.TRUE : Constants.FALSE);
		setCollectNumPutts(BooleanUtils.toBoolean(
				aScorecard.isCollectNumPutts()) ?
						Constants.TRUE : Constants.FALSE);
		setCollectSandSave(BooleanUtils.toBoolean(
				aScorecard.isCollectSandSave()) ?
						Constants.TRUE : Constants.FALSE);
		setCollectUpDown(BooleanUtils.toBoolean(
				aScorecard.isCollectUpDown()) ? 
						Constants.TRUE : Constants.FALSE);
		setCollectPerHoleStats(scorecard.getScores().size() > 0 ? 
				Constants.TRUE : Constants.FALSE);
		
		// initialize the holes...
		startingHoles = new ArrayList<HoleDAO>();
		for (ScoreDAO score : scores) {
			hole = score.getHole();
			if (hole.getNumber() == 1) {
				startingHoles.add(hole);
			}
			if ((hole.getNumber() - 1) % 9 == 0) {
				startingHoles.add(hole);
			}
			holeNum = Integer.toString(hole.getNumber());
			holes.put(holeNum, hole);			
			setScore(holeNum, Integer.toString(score.getScore()));
			if (score.getGolfClub() != null) {
				setTeeShotGolfClub(holeNum, 
						score.getGolfClub().getId().toString());
			}
			driveDistance = score.getDriveDistance();
			if (driveDistance != null) {
				setTeeShotDistance(holeNum, driveDistance.toString());
			}
			if (score.getNumPutts() != null) {
				setNumPutts(holeNum, score.getNumPutts().toString());
			}
			if (score.getTeeShotAccuracy() != null) {
				setTeeShotAccuracy(holeNum, 
						score.getTeeShotAccuracy().getId().toString());
			}
			if (score.getGreenInRegulation() != null) {
				setGreenInRegulation(holeNum, 
						score.getGreenInRegulation().getId().toString());
			}
			if (score.getApproachShotLine() != null) {
				setApproachShotLine(holeNum,
					score.getApproachShotLine().getId().toString());
			}
			if (score.getApproachShotDistance() != null) {
				setApproachShotDistance(holeNum,
					score.getApproachShotDistance().getId().toString());
			}
			if (BooleanUtils.toBoolean(score.isSandSaveAttempted())) { 
				setSandSaveAttempt(holeNum, Constants.TRUE);
			}
			if (BooleanUtils.toBoolean(score.getIfSandSaveConversion())) {
				setSandSaveConvert(holeNum, Constants.TRUE);
			}
			if (BooleanUtils.toBoolean(score.isUpAndDownAttempted())) {
				setUpDownAttempt(holeNum, Constants.TRUE);
			}
			if (BooleanUtils.toBoolean(score.getIfUpAndDownConversion())) {
				setUpDownConvert(holeNum, Constants.TRUE);
			}
		}
		
		// init the starting holes map...
		if (!BooleanUtils.toBoolean(getCollectPerHoleStats())) {
			if (aScorecardSummary != null && 
					aScorecardSummary.getNumHolesPlayed() == 18) {
				setHolesPlayed(course.getFrontNineStartingHole() + 
						WebConstants.DELIMETER + 
						(course.getFrontNineStartingHole() + 8), 
						Constants.TRUE);
				setHolesPlayed(course.getBackNineStartingHole() + 
						WebConstants.DELIMETER + 
						(course.getBackNineStartingHole() + 8), 
						Constants.TRUE);
			} else {
				setHolesPlayed(scorecard.getStartingHole().getNumber() + 
						WebConstants.DELIMETER + 
						(scorecard.getStartingHole().getNumber() + 8), 
						Constants.TRUE);
			}
		} else {
			numStartingHoles = startingHoles.size();
			for (loop = 0; loop < numStartingHoles; loop++) {
				setHolesPlayed(startingHoles.get(loop).getNumber() + 
						WebConstants.DELIMETER + 
						(startingHoles.get(loop).getNumber() + 8), Constants.TRUE);
			}
		}
	}
	
	/**
	 * Initializes the bean.
	 * @param aUtil
	 * @param aRequest
	 */
	public void init(NewUtil aUtil, HttpServletRequest aRequest) {
		
		StateProvinceId stateProvinceId;
		PlayerDAO player;
		CourseDAO course;
		CourseDAO courses[];
		String courseId;
		
		util = aUtil;
		
		player = NewUtil.getPlayer(aRequest);
		if (!isInitialized) {
			
			// first check to see if course-id request parameter exists...
			courseId = aRequest.getParameter(
					AttributeKeyConstants.COURSE_ID_PARAM);
			if (StringUtils.isNotBlank(courseId)) {
				logger.info("course id initialized from request parameter");
				setCourseId(Integer.parseInt(courseId));
			} else if (player.getCourse() != null) {
				// init the course if only if the player has a home course set...
				logger.info("course id initialized from player's home course");
				setCourseId((Integer)player.getCourse().getId());
			} else {
				courses = getDAOUtils().getCourses(getCountryId(), 
						getStateProvinceId());
				// courses should always have at least one entry...the country
				// and state-province IDs came from the first course from the 
				// list of courses for the default country/state-province IDs
				// set in web.xml...
				logger.info("course id initialized from listofcourses[0] " + 
						"given the default country/state-province IDs set in " +
						"web.xml");
				setCourseId((Integer)courses[0].getId());
			}
			course = BeanFactory.newCourseDAO();
			course.setId(getCourseId());
			course.loadAddress();
			stateProvinceId = (StateProvinceId)
			course.getStateProvince().getId();
			setCountryId(stateProvinceId.getCountryId());
			setStateProvinceId(stateProvinceId.getId());
			setFront9StartingHole(course.getFrontNineStartingHole());
			setBack9StartingHole(course.getBackNineStartingHole());
			setCollectPerHoleStats(Constants.TRUE);
			
			// init other info from player's profile...
			setEyeWearTypeId((Integer)player.getEyeWear().getId());
			setHeadWearTypeId((Integer)player.getHeadWear().getId());
			setPantWearTypeId((Integer)player.getPantWear().getId());
			setVestWorn(player.isWearsVest());
			setLongSleevesWorn(player.isWearsLongSleeves());
			setUsedCaddie(player.getUsesCaddie());
			setUsedCart(player.getUsesCart());
			
			// init stats-to-collect info from player's profile...
			setCollectApproachShotDistance(
					player.isCollectApproachShotDistance() ? 
					Constants.TRUE : Constants.FALSE);
			setCollectApproachShotLine(player.isCollectApproachShotLine() ? 
					Constants.TRUE : Constants.FALSE);
			setCollectClubUsedOffTee(player.isCollectClubUsedOffTee() ? 
					Constants.TRUE : Constants.FALSE);
			setCollectNumPutts(player.isCollectNumPutts() ? 
					Constants.TRUE : Constants.FALSE);
			setCollectFairwayHit(player.isCollectFairwayHit() ? 
					Constants.TRUE : Constants.FALSE);
			setCollectGir(player.isCollectGir() ?  
					Constants.TRUE : Constants.FALSE);
			setCollectSandSave(player.isCollectSandSave() ? 
					Constants.TRUE : Constants.FALSE);
			setCollectUpDown(player.isCollectUpDown() ? 
					Constants.TRUE : Constants.FALSE);
			setCollectTeeShotDistance(player.isCollectTeeShotDistance() ? 
					Constants.TRUE : Constants.FALSE);
			
			// init the tees and holes played from player's profile...
			initHolesPlayed(player, course);
			setCourseTeeNameId((Integer)player.getTeeName().getId());
			
			// init date to today's date...
			setDate(util.getTodaysDateInput());
			
			isInitialized = true;
		}
	}
	
	/**
	 * Initializes the holesPlayed map
	 * @param aPlayer
	 */
	public void initHolesPlayed(PlayerDAO aPlayer, CourseDAO aCourse) {
		
		holesPlayed.clear();
		setHolesPlayed(aCourse.getFrontNineStartingHole() + 
				WebConstants.DELIMETER + 
				(aCourse.getFrontNineStartingHole() + 8), Constants.TRUE);
		if (aPlayer.getNumHolesPlayed() > 9) {
			setHolesPlayed(aCourse.getBackNineStartingHole() + 
					WebConstants.DELIMETER + 
					(aCourse.getBackNineStartingHole() + 8), Constants.TRUE);
		}
	}
	
	/**
	 * Returns the collection of key-strings used to initialize the holesPlayed
	 * map.  The keys will take the form: "1-9", "10-18", "19-27", etc
	 * @param aNumHolesPlayed
	 * @return
	 */
	/*private static String[] getHolesPlayedKeys(int aNumHolesPlayed) {
		
		Set<String> keys;
		String key;
		int loop, loopCompare;
		
		keys = new HashSet<String>();
		loopCompare = aNumHolesPlayed / 9;
		for (loop = 1; loop <= loopCompare; loop++) {
			key = (1 + ((loop - 1) * 9)) + WebConstants.DELIMETER + 
			(((loop - 1) * 9) + 9);
			keys.add(key);
		}
		return (String[])keys.toArray(new String[keys.size()]);
	}*/
	
	/**
	 * Getter
	 * @return
	 */
	public final int getCountryId() {
		return (Integer)stateProvince.getCountry().getId();
	}
	
	/**
	 * Setter
	 * @param countryId
	 */
	public final void setCountryId(int countryId) {
		
		StateProvinceId stateProvinceId;
		CountryDAO country;
		
		country = stateProvince.getCountry();
		country.setId(countryId);
		stateProvince.setCountry(country);
		stateProvinceId = (StateProvinceId)stateProvince.getId();
		stateProvinceId.setCountryId(countryId);
		stateProvince.setId(stateProvinceId);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getStateProvinceId() {
		return (Integer)((StateProvinceId)stateProvince.getId()).getId();
	}
	
	/**
	 * Setter
	 * @param aStateProvinceId
	 */
	public final void setStateProvinceId(int aStateProvinceId) {
		
		StateProvinceId stateProvinceId;
		
		stateProvinceId = (StateProvinceId)stateProvince.getId();
		stateProvinceId.setId((Integer)aStateProvinceId);
		stateProvince.setId(stateProvinceId);
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getCourseId() {
		return (Integer)courseDAO.getId();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final CourseDAO getCourseDAO() {
		return courseDAO;
	}

	/**
	 * Setter
	 * @param aCourseId
	 */
	public final void setCourseId(int aCourseId) {
		courseDAO.setId(aCourseId);
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getEyeWearTypeId() {
		return (Integer)eyeWearDAO.getId();
	}

	/**
	 * Setter
	 * @param aEyeWearTypeId
	 */
	public final void setEyeWearTypeId(int aEyeWearTypeId) {
		eyeWearDAO.setId(aEyeWearTypeId);
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getHeadWearTypeId() {
		return (Integer)headWearDAO.getId();
	}

	/**
	 * Setter
	 * @param aHeadWearTypeId
	 */
	public final void setHeadWearTypeId(int aHeadWearTypeId) {
		headWearDAO.setId(aHeadWearTypeId);
	}

	/**
	 * Getter
	 * @return
	 */
	public final boolean getLongSleevesWorn() {
		return longSleevesWorn;
	}

	/**
	 * Setter
	 * @param longSleevesWorn
	 */
	public final void setLongSleevesWorn(boolean sleevesWorn) {
		this.longSleevesWorn = sleevesWorn;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getPantWearTypeId() {
		return (Integer)pantWearDAO.getId();
	}

	/**
	 * Setter
	 * @param aPantWearTypeId
	 */
	public final void setPantWearTypeId(int aPantWearTypeId) {
		pantWearDAO.setId(aPantWearTypeId);
	}

	/**
	 * Getter
	 * @return
	 */
	public final boolean getVestWorn() {
		return vestWorn;
	}

	/**
	 * Setter
	 * @param vestWorn
	 */
	public final void setVestWorn(boolean vestWorn) {
		this.vestWorn = vestWorn;
	}

	/**
	 * Getter
	 * @return
	 */
	public final boolean getJacketWorn() {
		return jacketWorn;
	}

	/**
	 * Setter
	 * @param jacketWorn
	 */
	public final void setJacketWorn(boolean jacketWorn) {
		this.jacketWorn = jacketWorn;
	}
	
	/**
	 * Setter
	 * @param aIsTournamentScore
	 */
	public final void setIsTournamentScore(boolean aIsTournamentScore) {
		this.isTournamentScore = aIsTournamentScore;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final boolean getIsTournamentScore() {
		return isTournamentScore;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getNumAvailableHoles() {
		return numAvailableHoles;
	}

	/**
	 * Setter
	 * @param numAvailableHoles
	 */
	public final void setNumAvailableHoles(int numHolesPlayed) {
		this.numAvailableHoles = numHolesPlayed;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getCourseTeeNameId() {
		return courseTeeNameId;
	}

	/**
	 * Setter
	 * @param courseTeeNameId
	 */
	public final void setCourseTeeNameId(int courseTeeId) {
		this.courseTeeNameId = courseTeeId;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getWeatherConditionTypeId() {
		return (Integer)weatherConditionDAO.getId();
	}

	/**
	 * Setter
	 * @param aWeatherConditionTypeId
	 */
	public final void setWeatherConditionTypeId(int aWeatherConditionTypeId) {
		weatherConditionDAO.setId(aWeatherConditionTypeId);
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * Setter
	 * @param aIsInitialized
	 */
	public void setInitialized(boolean aIsInitialized) {
		this.isInitialized = aIsInitialized;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getDate() {
		return date;
	}

	/**
	 * Setter
	 * @param aDate
	 */
	public final void setDate(String aDate) {
		this.date = aDate;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getCourseDescription() {
		return courseDAO.getDescription();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getCountryDescription() {
		return courseDAO.getStateProvince().getCountry().getName();
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getStateProvinceDescription() {
		return courseDAO.getStateProvince().getName();
	}
	
	/**
	 * Set checkbox-mapped properties to their default state.
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest) {
		holesPlayed.clear();
		upDownAttemptMap.clear();
		upDownConvertMap.clear();
		sandSaveAttemptMap.clear();
		sandSaveConvertMap.clear();
		collectClubUsedOffTee = Constants.FALSE;
		collectTeeShotDistance = Constants.FALSE;
		collectNumPutts = Constants.FALSE;
		collectFairwayHit = Constants.FALSE;
		collectGir = Constants.FALSE;
		collectApproachShotLine = Constants.FALSE;
		collectApproachShotDistance = Constants.FALSE;
		collectSandSave = Constants.FALSE;
		collectUpDown = Constants.FALSE;
	}

	/**
	 * Setter
	 * @param aDefaultPuttValue
	 */
	public void setDefaultPuttValue(int aDefaultPuttValue) {
		this.defaultPuttValue = aDefaultPuttValue;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getAttestedBy() {
		return attestedBy;
	}

	/**
	 * Setter
	 * @param aAttestedBy
	 */
	public void setAttestedBy(String aAttestedBy) {
		this.attestedBy = aAttestedBy;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getScorer() {
		return scorer;
	}

	/**
	 * Setter
	 * @param aScorer
	 */
	public void setScorer(String aScorer) {
		this.scorer = aScorer;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getJournalNotes() {
		return journalNotes;
	}

	/**
	 * Setter
	 * @param aJournalNotes
	 */
	public void setJournalNotes(String aJournalNotes) {
		this.journalNotes = aJournalNotes;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Setter
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Getter
	 * @return
	 */
	public boolean isEditMode() {
		return isEditMode;
	}

	/**
	 * Getter
	 * @return
	 */
	public ScorecardDAO getScorecard() {
		return scorecard;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getBack9StartingHole() {
		return back9StartingHole;
	}

	/**
	 * Setter
	 * @param back9StartingHole
	 */
	public final void setBack9StartingHole(int back9StartingHole) {
		this.back9StartingHole = back9StartingHole;
	}

	/**
	 * Getter
	 * @return
	 */
	public final int getFront9StartingHole() {
		return front9StartingHole;
	}

	/**
	 * Setter
	 * @param front9StartingHole
	 */
	public final void setFront9StartingHole(int front9StartingHole) {
		this.front9StartingHole = front9StartingHole;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getCollectApproachShotDistance() {
		return collectApproachShotDistance;
	}

	/**
	 * Setter
	 * @param collectApproachShotDistance
	 */
	public final void setCollectApproachShotDistance(
			String collectApproachShotDistance) {
		this.collectApproachShotDistance = collectApproachShotDistance;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectApproachShotLine() {
		return collectApproachShotLine;
	}

	/**
	 * Setter
	 * @param collectApproachShotLine
	 */
	public final void setCollectApproachShotLine(String collectApproachShotLine) {
		this.collectApproachShotLine = collectApproachShotLine;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectClubUsedOffTee() {
		return collectClubUsedOffTee;
	}

	/**
	 * Setter
	 * @param collectClubUsedOffTee
	 */
	public final void setCollectClubUsedOffTee(String collectClubUsedOffTee) {
		this.collectClubUsedOffTee = collectClubUsedOffTee;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectFairwayHit() {
		return collectFairwayHit;
	}

	/**
	 * Setter
	 * @param collectFairwayHit
	 */
	public final void setCollectFairwayHit(String collectFairwayHit) {
		this.collectFairwayHit = collectFairwayHit;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectGir() {
		return collectGir;
	}

	/**
	 * Setter
	 * @param collectGir
	 */
	public final void setCollectGir(String collectGir) {
		this.collectGir = collectGir;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectNumPutts() {
		return collectNumPutts;
	}

	/**
	 * Setter
	 * @param collectNumPutts
	 */
	public final void setCollectNumPutts(String collectNumPutts) {
		this.collectNumPutts = collectNumPutts;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectSandSave() {
		return collectSandSave;
	}

	/**
	 * Setter
	 * @param collectSandSave
	 */
	public final void setCollectSandSave(String collectSandSave) {
		this.collectSandSave = collectSandSave;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectTeeShotDistance() {
		return collectTeeShotDistance;
	}

	/**
	 * Setter
	 * @param collectTeeShotDistance
	 */
	public final void setCollectTeeShotDistance(String collectTeeShotDistance) {
		this.collectTeeShotDistance = collectTeeShotDistance;
	}

	/**
	 * Getter
	 * @return
	 */
	public final String getCollectUpDown() {
		return collectUpDown;
	}

	/**
	 * Setter
	 * @param collectUpDown
	 */
	public final void setCollectUpDown(String collectUpDown) {
		this.collectUpDown = collectUpDown;
	}

	/**
	 * @return the collectPerHoleStats
	 */
	public final String getCollectPerHoleStats() {
		return collectPerHoleStats;
	}

	/**
	 * @param aCollectPerHolesStats the collectPerHoleStats to set
	 */
	public final void setCollectPerHoleStats(String aCollectPerHolesStats) {
		this.collectPerHoleStats = aCollectPerHolesStats;
	}

	/**
	 * @return the overallScore
	 */
	public final String getOverallScore() {
		return StringUtils.substringBefore(overallScore, ".");
	}

	/**
	 * @param aOverallScore the overallScore to set
	 */
	public final void setOverallScore(String aOverallScore) {
		this.overallScore = aOverallScore;
	}
}
