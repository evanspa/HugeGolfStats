package name.paulevans.golf.web;

/**
 * Stores attribute and parameter keys for session and request objects
 * @author Paul
 *
 */
public final class AttributeKeyConstants {
	
	/**
	 * Private constructor to prevent instantiation.
	 *
	 */
	private AttributeKeyConstants() {
		// do nothing...
	}
	
	/**
	 * Locale object attribute-name
	 */
	public static final String LOCALE = "LocaleObject";
	
	/**
	 * Request parameter-name used to configure if the JSP should display 
	 * the left-menu
	 */
	public static final String NO_MENU = "nomenu";
	
	/**
	 * Country-changed parameter-name
	 */
	public static final String COUNTRY_CHANGED = "countrychanged";
	
	/**
	 * State-province-changed parameter-name
	 */
	public static final String STATE_PROVINCE_CHANGED = "stateprovincechanged";
	
	/**
	 * Course-changed parameter name
	 */
	public static final String COURSE_CHANGED = "courseChanged";
	
	/**
	 * Key to the collection of countries...
	 */
	public static final String COUNTRIES = "CountriesCollection";
	
	/**
	 * Key to the collection of courses-played...
	 */
	public static final String COURSES_PLAYED = "CoursesPlayedCollection";
	
	/**
	 * Key to the collection of calendar months...
	 */
	public static final String CALENDAR_MONTHS = "CalendarMonthsCollection";
	
	/**
	 * Key to the collection of state provinces...
	 */
	public static final String STATE_PROVINCES = "StateProvincesCollection";
	
	/**
	 * Key to the collection of head wear types...
	 */
	public static final String HEAD_WEAR_TYPES = "HeadWearTypesCollection";
	
	/**
	 * Key to the collection of weather condition types...
	 */
	public static final String WEATHER_CONDITION_TYPES = 
		"WeatherConditionTypesCollection";
	
	/**
	 * Key to the collection of tee shot accuracy types...
	 */
	public static final String TEE_SHOT_ACCURACY_TYPES = 
		"TeeShotAccuracyTypesCollection";
	
	/**
	 * Key to the collection of approach shot line types...
	 */
	public static final String APPROACH_SHOT_LINE_TYPES = 
		"ApproachShotLineTypes";
	
	/**
	 * Key to the collection of approach shot distance types...
	 */
	public static final String APPROACH_SHOT_DISTANCE_TYPES = 
		"ApproachShotDistanceTypes";
	
	/**
	 * Key to the collection of golf club types...
	 */
	public static final String GOLF_CLUB_TYPES = "golfClubTypes";
	
	/**
	 * Key to the collection of green-in-regulation types...
	 */
	public static final String GREEN_IN_REGULATION_TYPES = 
		"GreenInRegulationTypesCollection";
	
	/**
	 * Key to the collection of eye wear types...
	 */
	public static final String EYE_WEAR_TYPES = "EyeWearTypesCollection";
	
	/**
	 * Key to the collection of pant wear types...
	 */
	public static final String PANT_WEAR_TYPES = "PantWearTypesCollection";
	
	/**
	 * Key to the collection of courses...
	 */
	public static final String COURSES = "CoursesCollection";
	
	/**
	 * Key to the collection of players...
	 */
	public static final String PLAYERS = "PlayersCollection";
	
	/**
	 * Key to the collection of rounds...
	 */
	public static final String ROUNDS = "RoundsCollection";
	
	/**
	 * Key to the total number of search results...
	 */
	public static final String TOTAL_RESULTS_COUNT = "TotalResultsCount";
	
	/**
	 * Key to the collection course tees...
	 */
	public static final String COURSE_TEES = "CourseTeesCollection";
	
	/**
	 * Key to the collection of course hole numbers (9, 18, 27, 36, etc.)...
	 */
	public static final String NUM_COURSE_HOLES = "NumCourseHoles";
	
	/**
	 * Key to the collection of tee colors
	 */
	public static final String TEE_NAMES = "TeeNames";
	
	/**
	 * Key to the player object...
	 */
	public static final String PLAYER = "PlayerObject";
	
	/**
	 * Key to the summary stats object...
	 */
	public static final String SUMMARY_STATS = "SummaryStatsObject";
	
	/**
	 * Key to the course object...
	 */
	public static final String COURSE = "CourseObject";
	
	/**
	 * Key to the util object...
	 */
	public static final String UTIL = "UtilObject";
	
	/**
	 * Name of course ID request parameter
	 */
	public static final String COURSE_ID_PARAM = "courseid";
	
	/**
	 * Name of the scorecard ID request parameter
	 */
	public static final String SCORECARD_ID_PARAM = "scorecardid";
	
	/**
	 * Name of the dropDownRefresh request parameter
	 */
	public static final String DROP_DOWN_REFRESH_PARAM = "dropDownRefresh";
	
	
	/**
	 * Name of the selected-menu session attribute
	 */
	public static final String SELECTED_MENU = "selectedMenu";
	
	/**
	 * Sort-column request parameter name
	 */
	public static final String SORT_COLUMN_PARAM = "sortcolumn";
	
	/**
	 * Exception message parameter name
	 */
	public static final String EXCEPTION_MESSAGE_PARAM = "exceptionmessage";
	
	/**
	 * Chart category parmaeter name
	 */
	public static final String CHART_CATEGORY_PARAM = "chartcategory";
	
	/**
	 * Chart category attribute name
	 */
	public static final String CHART_CATEGORY = "chartCategoryObject";
	
	/**
	 * The admin usage summary session attribute
	 */
	public static final String USAGE_SUMMARY = "usageSummary";
	
	/**
	 * Image file parameter (used on take-tour jsp)
	 */
	public static final String IMAGEFILE_PARAM = "imagefile";
	
	/**
	 * Key to the screenshot name attribute
	 */
	public static final String SCREENSHOT_IMAGEFILE_ATTR = "ScreenshotImageFileAttr";
}
