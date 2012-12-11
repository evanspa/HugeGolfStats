package name.paulevans.golf.web.struts.formbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.CourseSlopeRatingDAO;
import name.paulevans.golf.dao.CourseSlopeRatingId;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.util.NewUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

/**
 * Models the add/edit course form
 * @author Paul
 *
 */
public class CourseForm extends BaseForm {
	
	// logger object...
	private static final Logger logger = Logger.getLogger(CourseForm.class);
	
	// constants...
	private static final int DEFAULT_FRONT_9_STARTING_HOLE = 1;
	private static final int DEFAULT_BACK_9_STARTING_HOLE = 10;
	
	// course info...
	private String courseName;
	private String courseId;
	
	// number of available holes...
	private int numHoles;
	
	// starting holes...
	private int front9StartingHole;
	private int back9StartingHole;
	
	// make-home indicator
	private String makeHome;
	
	// course address info...
	private String city;
	private int stateProvinceId;
	private int countryId;
	
	// stores whether yardages and handicaps should be collected...
	private String promptYardages;
	private String promptHandicaps;
	
	// default values...
	private int defaultParValue;
	
	// available tees...
	private Map<String,String> courseTees;
	private Map<String,String> readOnlyTees;
	
	// stores par values...
	private Map<String,String> parValues;
	
	// stores yardage values...
	private Map<String,String> yardageValues;
	
	// stores handicap values...
	private Map<String,String> handicapValues;
	
	// stores slope values...
	private Map<String,String> slopeValues;
	
	// stores rating values...
	private Map<String,String> ratingValues;
	
	// boolean to store if we're in edit-mode or not...
	private boolean isEdit;
	
	// used to control if the starting-hole drop-downs should be disabled or
	// not on the edit-course page...
	private boolean holesExist;
	
	/**
	 * Constructor setting initial values.
	 */
	public CourseForm() {
		initMaps();
		front9StartingHole = DEFAULT_FRONT_9_STARTING_HOLE;
		back9StartingHole = DEFAULT_BACK_9_STARTING_HOLE;
	}
	
	/**
	 * Clears this form
	 *
	 */
	public void clear() {
		initMaps();
		courseId = null;
		courseName = null;
		countryId = -1;
		stateProvinceId = -1;
		numHoles = -1;
		makeHome = null;
		city = null;
		promptYardages = null;
		promptHandicaps = null;
		defaultParValue = -1;
		isEdit = false;
		holesExist = false;
	}
	
	/**
	 * Creates the maps
	 *
	 */
	private void initMaps() {
		// using tree map for ordering purposes...
		courseTees = new TreeMap<String,String>(); 
		readOnlyTees = new HashMap<String,String>();
		parValues = new HashMap<String,String>();
		yardageValues = new HashMap<String,String>();
		handicapValues = new HashMap<String,String>();
		slopeValues = new HashMap<String,String>();
		ratingValues = new HashMap<String,String>();
	}
	
	/**
	 * Initialize the form bean state from the aCourse DAO
	 * @param aCourse
	 */
	public void initialize(NewUtil aUtil, CourseDAO aCourse) {
		
		StateProvinceDAO stateProvince;
		Set<TeeDAO> tees;
		Set<HoleDAO> holes;
		Set<CourseSlopeRatingDAO> slopeRatingValues;
		String teeColorId;
		
		stateProvince = aCourse.getStateProvince();
		countryId = (Integer) stateProvince.getCountry().getId();
		stateProvinceId = ((StateProvinceId)stateProvince.getId()).getId();
		city = aCourse.getCity();
		courseName = aCourse.getDescription();
		courseId = aCourse.getId().toString();
		front9StartingHole = aCourse.getFrontNineStartingHole();
		back9StartingHole = aCourse.getBackNineStartingHole();
		tees = aCourse.getTees();
		if (tees.size() > 0) {
			for (TeeDAO tee : tees) {
				teeColorId = tee.getTeeName().getId().toString();
				numHoles = tee.getHoles().size();
				courseTees.put(tee.getTeeName().getName(), teeColorId);
				readOnlyTees.put(tee.getTeeName().getName(), teeColorId);
				if (tee.getHoles().size() > 0) {
					holesExist = true;
				}
				
				// get the holes...
				holes = tee.getHoles();
				// init the par/yardage/handicap values for each hole...
				for (HoleDAO hole : holes) {
					parValues.put(teeColorId + "-" + hole.getNumber(),
							Integer.toString(hole.getPar()));
				}
				
				// get the slope-rating values...
				slopeRatingValues = tee.getSlopeRatingValues();
				// init the overall slope and rating values...
				if (tee.getOverallSlope() != null) {
					setSlopeValue("overall|" + teeColorId, 
						Integer.toString(tee.getOverallSlope()));
				}
				if (tee.getOverallRating() != null) {
					setRatingValue("overall|" + teeColorId,
						Float.toString(tee.getOverallRating()));		
				}
				// init the 9-hole slope and rating values...
				for (CourseSlopeRatingDAO slopeRating : slopeRatingValues) {
					if (slopeRating.getSlope() != null) {
						setSlopeValue(slopeRating.toString(), 
							Integer.toString(slopeRating.getSlope()));
					}
					if (slopeRating.getRating() != null) {
						setRatingValue(slopeRating.toString(), 
							Float.toString(slopeRating.getRating()));
					}
				}
			}
		}
	}
	
	/**
	 * Setter
	 * @param aDefaultParValue
	 */
	public void setDefaultParValue(int aDefaultParValue) {
		defaultParValue = aDefaultParValue;
	}

	/**
	 * Getter
	 * @param aKey
	 * @return
	 */
	public final String getParValue(String aKey) {
		return NewUtil.getString(parValues, aKey, defaultParValue);
	}	
	
	/**
	 * Getter
	 * @param aKey
	 * @return
	 */
	public final String getYardageValue(String aKey) {
		return yardageValues.get(aKey);
	}
	
	/**
	 * Getter
	 * @param aKey
	 * @return
	 */
	public final String getHandicapValue(String aKey) {
		return (String)handicapValues.get(aKey);
	}
	
	/**
	 * Setter
	 * @param aKey
	 * @param aValue
	 */
	public final void setParValue(String aKey, String aValue) {
		parValues.put(aKey, aValue);
	}	
	
	/**
	 * Setter
	 * @param aKey
	 * @param aValue
	 */
	public final void setYardageValue(String aKey, String aValue) {
		yardageValues.put(aKey, aValue);
	}
	
	/**
	 * Setter
	 * @param aKey
	 * @param aValue
	 */
	public final void setHandicapValue(String aKey, String aValue) {
		handicapValues.put(aKey, aValue);
	}
	
	/**
	 * Setter
	 * @param aKey
	 * @param aValue
	 */
	public final void setRatingValue(String aKey, String aValue) {
		ratingValues.put(aKey, aValue);
	}
	
	/**
	 * Getter
	 * @param aKey
	 * @return
	 */
	public final String getRatingValue(String aKey) {
		return ratingValues.get(aKey);
	}
	
	/**
	 * Setter
	 * @param aKey
	 * @param aValue
	 */
	public final void setSlopeValue(String aKey, String aValue) {
		slopeValues.put(aKey, aValue);
	}
	
	/**
	 * Getter
	 * @param aKey
	 * @return
	 */
	public final String getSlopeValue(String aKey) {
		return slopeValues.get(aKey);
	}
	
	/**
	 * Setter
	 * @param aKey
	 * @param aValue
	 */
	public final void setCourseTee(String aKey, String aValue) {
		courseTees.put(aKey, aValue);
	}
	
	/**
	 * Getter
	 * @param aKey
	 * @return
	 */
	public final String getCourseTee(String aKey) {
		return courseTees.get(aKey);
	}
	
	/**
	 * Getter
	 * @param aKey
	 * @return
	 */
	public final String getReadOnlyTee(String aKey) {
		return readOnlyTees.get(aKey);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final Map<String,String> getReadOnlyTees() {
		return readOnlyTees;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final String getCity() {
		return city;
	}
	
	/**
	 * Setter
	 * @param city
	 */
	public final void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getCountryId() {
		return countryId;
	}
	
	/**
	 * Setter
	 * @param country
	 */
	public final void setCountryId(int country) {
		this.countryId = country;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final int getStateProvinceId() {
		return stateProvinceId;
	}
	
	/**
	 * Setter
	 * @param stateProvince
	 */
	public final void setStateProvinceId(int stateProvince) {
		this.stateProvinceId = stateProvince;
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
	public int getNumHoles() {
		return numHoles;
	}
	
	/**
	 * Setter
	 * @param numHoles
	 */
	public void setNumHoles(int numHoles) {
		this.numHoles = numHoles;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public final Map<String, String> getCourseTees() {
		return courseTees;
	}
	
	/**
	 * Set checkbox-mapped properties to their default state.
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest) {
		courseTees.clear();
		makeHome = "";
		promptYardages = "";
		promptHandicaps = "";
	}

	/**
	 * Getter
	 */
	public String getMakeHome() {
		return makeHome;
	}

	/**
	 * Setter
	 * @param makeHome
	 */
	public void setMakeHome(String makeHome) {
		this.makeHome = makeHome;
	}

	/**
	 * Getter
	 * @return
	 */
	public final Map<String, String> getParValues() {
		return parValues;
	}

	/**
	 * Getter
	 * @return
	 */
	public final Map<String, String> getHandicapValues() {
		return handicapValues;
	}

	/**
	 * Getter
	 * @return
	 */
	public final Map<String, String> getRatingValues() {
		return ratingValues;
	}

	/**
	 * Getter
	 * @return
	 */
	public final Map<String, String> getSlopeValues() {
		return slopeValues;
	}

	/**
	 * Getter
	 * @return
	 */
	public final Map<String, String> getYardageValues() {
		return yardageValues;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getPromptHandicaps() {
		return promptHandicaps;
	}

	/**
	 * Setter
	 * @param aPromptHandicaps
	 */
	public void setPromptHandicaps(String aPromptHandicaps) {
		this.promptHandicaps = aPromptHandicaps;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getPromptYardages() {
		return promptYardages;
	}

	/**
	 * Setter
	 * @param aPromptYardages
	 */
	public void setPromptYardages(String aPromptYardages) {
		this.promptYardages = aPromptYardages;
	}
	
	/**
	 * Returns the nine-hole par for the inputted key.  aTeeColorIdStartingHole
	 * takes the form of XX-YY where 'XX' is the tee color id and 'YY' is the
	 * starting hole number.
	 * @param aTeeColorIdStartingHole
	 * @return
	 */
	public int getNineHolePar(String aTeeColorIdStartingHole) {
		
		String teeColorId;
		int startingHole;
		int hyphenIndex, loop;
		int totalPar;
		
		hyphenIndex = aTeeColorIdStartingHole.indexOf('-');
		teeColorId = aTeeColorIdStartingHole.substring(0, hyphenIndex);
		startingHole = Integer.parseInt(aTeeColorIdStartingHole.substring(
				hyphenIndex + 1, aTeeColorIdStartingHole.length()));
		totalPar = 0;
		for (loop = startingHole; loop < startingHole + 9; loop++) {
			totalPar += Integer.parseInt(getParValue(teeColorId + "-" + loop));
		}
		return totalPar;
	}

	/**
	 * Getter
	 * @return
	 */
	public String getCourseId() {
		return courseId;
	}
	
	/**
	 * Returns the array of course slope-rating dao beans based on the contents
	 * of this form bean
	 * @param aCourseToEdit May be null
	 * @return
	 */
	public CourseSlopeRatingDAO[] createCourseSlopeRatingValues() {
		
		Set<String> slopeKeys;
		String holesPlayedTeeNameId[];
		int teeNameId;
		String slopeValue, ratingValue;
		List<CourseSlopeRatingDAO> courseSlopeRatingsList;
		CourseSlopeRatingDAO courseSlopeRatingDAO;
		CourseSlopeRatingId courseSlopeRatingId;
		
		courseSlopeRatingsList = new ArrayList<CourseSlopeRatingDAO>();
		slopeKeys = slopeValues.keySet();
		for (String slopeRatingKey : slopeKeys) {
			holesPlayedTeeNameId = getStartingHoleTeeNameId(slopeRatingKey);
			teeNameId = Integer.parseInt(holesPlayedTeeNameId[1]);
			courseSlopeRatingDAO = BeanFactory.newCourseSlopeRatingDAO();
			courseSlopeRatingId = new CourseSlopeRatingId();
			if (!holesPlayedTeeNameId[0].equals("overall")) {
				courseSlopeRatingId.setNineType(
						holesPlayedTeeNameId[0].equals("front") ? 
								Constants.FRONT_NINE_TYPE : 
									Constants.BACK_NINE_TYPE);
			}
			courseSlopeRatingId.setTeeNameId(teeNameId);
			courseSlopeRatingDAO.setId(courseSlopeRatingId);
			courseSlopeRatingDAO.setStartingHole(
					holesPlayedTeeNameId[0].equals("front") ? 
							getFront9StartingHole() : getBack9StartingHole());
			slopeValue = getSlopeValue(slopeRatingKey);
			if (StringUtils.isNotBlank(slopeValue)) {
				courseSlopeRatingDAO.setSlope(Integer.parseInt(slopeValue));
			}
			ratingValue = getRatingValue(slopeRatingKey);
			if (StringUtils.isNotBlank(ratingValue)) {
				courseSlopeRatingDAO.setRating(
						Float.parseFloat(ratingValue));
			}
			courseSlopeRatingsList.add(courseSlopeRatingDAO);
		}
		return courseSlopeRatingsList.toArray(
				new CourseSlopeRatingDAO[courseSlopeRatingsList.size()]);
	}	
	
	/**
	 * Returns a string array of size 2.  aValue takes the form of: front|17
	 * and returns an array such that string[0]=front and string[1]=17
	 * @param aValue
	 * @return
	 */
	private static final String[] getStartingHoleTeeNameId(String aValue) {
		
		String holesPlayedTeeNameId[];
		StringTokenizer tokenizer;
		
		holesPlayedTeeNameId = new String[2];
		tokenizer = new StringTokenizer(aValue, "|");
		holesPlayedTeeNameId[0] = tokenizer.nextToken();
		holesPlayedTeeNameId[1] = tokenizer.nextToken();
		return holesPlayedTeeNameId;
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
	 * Returns true if this form is in 'edit' mode
	 * @return
	 */
	public final boolean getIsEditMode() {
		return isEdit;
	}

	/**
	 * Sets the form into edit mode or not
	 * @param aIsEditMode
	 */
	public final void setEditMode(boolean aIsEditMode) {
		this.isEdit = aIsEditMode;
	}

	/**
	 * Getter
	 * @return
	 */
	public final boolean getHolesExist() {
		return holesExist;
	}
}
