package name.paulevans.golf.web;

/**
 * Stores various constants referenced in JSPs, etc.
 * @author Paul
 *
 */
public final class WebConstants {
	
	/**
	 * Private constructor to prevent instantiation.
	 *
	 */
	private WebConstants() {
		// do nothing...
	}
	
	/**
	 * Name of the global exception-caught forward
	 */
	public static final String EXCEPTION_CAUGHT_FORWARD = "exceptioncaught";
	
	/**
	 * Name of the context root path
	 */
	public static final String CONTEXT_ROOT = "/golf-statistics";
	
	// Return values used to determine which add/edit round jsp to render...
	public static final String COLLECT_STATS = "collectstats";
	public static final String NOT_COLLECT_STATS = "notcollectstats";
	
	/**
	 * "success" string used by struts-config.xml
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * "failure" string used by struts-config.xml
	 */
	public static final String FAILURE = "failure";
	
	/**
	 * Course ID request parameter
	 */
	public static final String COURSE_ID_REQ_PARAM = "courseid";
	
	/**
	 * Do course-slope validatio request parameter
	 */
	public static final String DO_VALIDATE_SLOPES_REQ_PARAM = 
		"validateSlopeValues";
	
	/**
	 * Do course-slope validatio request parameter
	 */
	public static final String DO_VALIDATE_RATINGS_REQ_PARAM = 
		"validateRatingValues";
	
	/**
	 * Delimeter character
	 */
	public static final String DELIMETER = "-";
	
	/**
	 * The name of the user id cookie
	 */
	public static final String USER_ID_COOKIE_NAME = "userId";
	
	/**
	 * The max cookie age of the user id cookie
	 */
	public static final int USER_ID_COOKIE_MAX_AGE = 1209600; // 2 weeks...
	
	/**
	 * Sort-column request parameter name
	 */
	public static final String SORT_COLUMN_PARAM = "sortcolumn";
	
	// round sort colum parameter names...
	public static final String ROUNDS_COURSE_COL = "courseName";
	public static final String ROUNDS_DATEPLAYED_COL = "datePlayed";
	public static final String ROUNDS_NUMHOLESPLAYED_COL = "numHolesPlayed";
	public static final String ROUNDS_SCORE_COL = "score";
	public static final String ROUNDS_ISTOURNEY_COL = "scoreType";
	public static final String ROUNDS_CITY_COL = "scorecard.tee.course.city";
	public static final String ROUNDS_STATEPROV_COL = 
		"scorecard.tee.course.stateProvince.name";
	public static final String ROUNDS_COUNTRY_COL = 
		"scorecard.tee.course.stateProvince.country.name";	
	
	// course sort column parameter names...
	public static final String COURSES_COURSE_COL = "description";
	public static final String COURSES_CITY_COL = "city";
	public static final String COURSES_STATEPROV_COL = "stateProvince.name";
	public static final String COURSES_COUNTRY_COL = "stateProvince.country.name";
	
	// player sort column parameter names...
	public static final String PLAYERS_LASTNAME_COL = "player.lastName";
	public static final String PLAYERS_FIRSTNAME_COL = "player.firstName";
	public static final String PLAYERS_POSTALCODE_COL = "player.postalCode";
	public static final String PLAYERS_NUMROUNDS_COL = "player.numRounds";
	public static final String PLAYERS_LASTLOGIN_COL = "player.dateOfLastLogin";
	public static final String PLAYERS_DATECREATED_COL = "player.dateCreated";
	public static final String PLAYERS_DATEUPDATED_COL = "player.lastUpdateDate";
	public static final String PLAYERS_ID_COL = "player.id";
	public static final String PLAYERS_USERID_COL = "player.userId";
	
	// mode parameter name...
	public static final String MODE = "qmode";
	
	// modes...
	public static final String INPUT_OVERALL_SCORE = "inputoverallscore";
}
