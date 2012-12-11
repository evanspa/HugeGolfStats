package name.paulevans.golf;

import java.util.HashMap;
import java.util.Map;

/**
 * Collection of general-purpose constants.
 * @author pevans
 *
 */
public class Constants {
	
   /**
	 * The current release version of the application
	 */
	public static final String RELEASE_VERSION = "1.1.0"; // Major#.Minor#.Patch#

	// the value: 18
	public static final int EIGHTEEN = 18;
	
	// the value: 9
	public static final int NINE = 9;
	
	/**
	 * User ID of admin-user: Paul Evans
	 */
	public static final String EVANSPA_USERID = "evanspa";
	
	/**
	 * The max result size to use when looking up lists, performing searches
	 */
	public static final String MAX_RESULT_COUNT = "25";
	
	/**
	 * The ID of the english-language locale
	 */
	public static final int ENGLISH_LOCALE_ID = 0;
	
	/**
	 * The number of characters used when creating a new password for a user
	 */
	public static final int NEW_PASSWORD_LENGTH = 8;
	
	/**
	 * The name of the menu XSD schema file
	 */
	public static final String MENU_XSD = "menu.xsd";
	
	/**
	 * The name of the logged-in menu xml file
	 */
	public static final String MENU_LOGGEDIN_XML = "menu-loggedin.xml";
	
	/**
	 * The name of the not-logged-in menu xml file
	 */
	public static final String MENU_NOTLOGGEDIN_XML = "menu-notloggedin.xml";
	
	/**
	 * Table of handicap differentials from USGA Handicapping System Manual,
	 * Section 10-2
	 */
	public static final Map<Integer,Integer> DIFFERENTIALS_TABLE = 
		new HashMap<Integer,Integer>(); 
	
	// populate differentials table...
	static {
		DIFFERENTIALS_TABLE.put(5, 1);
		DIFFERENTIALS_TABLE.put(6, 1);
		DIFFERENTIALS_TABLE.put(7, 2);
		DIFFERENTIALS_TABLE.put(8, 2);
		DIFFERENTIALS_TABLE.put(9, 3);
		DIFFERENTIALS_TABLE.put(10, 3);
		DIFFERENTIALS_TABLE.put(11, 4);
		DIFFERENTIALS_TABLE.put(12, 4);
		DIFFERENTIALS_TABLE.put(13, 5);
		DIFFERENTIALS_TABLE.put(14, 5);
		DIFFERENTIALS_TABLE.put(15, 6);
		DIFFERENTIALS_TABLE.put(16, 6);
		DIFFERENTIALS_TABLE.put(17, 7);
		DIFFERENTIALS_TABLE.put(18, 8);
		DIFFERENTIALS_TABLE.put(19, 9);
		DIFFERENTIALS_TABLE.put(20, 10);
	}
	
	// i18n resource file name prefix...
	public static final String I18N_RESOURCE_FILE_NAME_PREFIX = 
		"GolfAppResources";

	// tee color IDs...
	public static final int RED_TEE_COLOR_ID = 0;
	public static final int WHITE_TEE_COLOR_ID = 1;
	public static final int BLUE_TEE_COLOR_ID = 1;
	public static final int GOLD_TEE_COLOR_ID = 3;
	public static final int GREEN_TEE_COLOR_ID = 4;
	public static final int BLACK_TEE_COLOR_ID = 5;
	
	// weather condition IDs...
	public static final int BEATIFUL_WEATHER = 0;
	public static final int RAINY_WEATHER = 1;
	public static final int COLD_WEATHER = 2;
	public static final int COLD_RAINY_WEATHER = 3;
	public static final int HOT_HUMID_WEATHER = 4;	
	
	// head wear type IDs...
	public static final int NONE_HEAD_WEAR_TYPE = 0;
	public static final int VISOR_HEAD_WEAR_TYPE = 1;
	public static final int CAP_HEAD_WEAR_TYPE = 2;
	
	// pant wear type IDs...
	public static final int PANTS_PANT_WEAR_TYPE = 0;
	public static final int SHORTS_PANT_WEAR_TYPE = 1;
	public static final int KNICKERS_PANT_WEAR_TYPE = 2;
	
	// eye wear type IDs...
	public static final int NONE_EYE_WEAR_TYPE = 0;
	public static final int CONTACT_LENSES_EYE_WEAR_TYPE = 1;
	public static final int GLASSES_EYE_WEAR_TYPE = 2;
	public static final int SUN_GLASSES_EYE_WEAR_TYPE = 3;
	
	// nine types...
	public static final int FRONT_NINE_TYPE = 1;
	public static final int BACK_NINE_TYPE = 0;
	
	// club IDs...
	public static final String DRIVER_ID = "0";
	public static final String FIVE_IRON_ID = "17";
	
	// no home course ID...
	public static final int NO_HOME_COURSE_SET_ID = -1;
	
	// security roles...
	public static final String ADMIN_ROLE = "admin";
	public static final String USER_ROLE = "user";
	
	// true / false strings...
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final int TRUE_INTEGER = 1;
	public static final int FALSE_INTEGER = 0;
	
	// tee-shot accuracy value - hit fairway...
	public static final int LEFT_ROUGH_ID = 0;
	public static final int FAIRWAY_HIT_ID = 1;
	public static final int RIGHT_ROUGH_ID = 2;
	
	// gir value - no gir....
	public static final int NO_GIR_HIT_ID = 2;
	
	// gir value - greater than 15 feet...
	public static final int GIR_LESS_THAN_15_FEET = 0;
	public static final int GIR_GREATER_THAN_15_FEET = 1;
	public static final int GIR_NO_GIR = 2;
	
	// approach shot line values...
	public static final int APPROACH_SHOT_LINE_LEFT = 0;
	public static final int APPROACH_SHOT_LINE_GREEN = 1;
	public static final int APPROACH_SHOT_LINE_RIGHT = 2;
	
	// approach shot distance values...
	public static final int APPROACH_SHOT_DISTANCE_SHORT = 0;
	public static final int APPROACH_SHOT_DISTANCE_PIN_HIGH = 1;
	public static final int APPROACH_SHOT_DISTANCE_LONG = 2;
	
	// id number virtually representing 'null'...
	public static final int NULL_ID_VAL = -1;
	
	// max scores to bring back to use to calculate handicap index...
	public static final int MAX_ROUNDS = 20;
	
	// max number of eligible tournament rounds used for handicap 
	// reduction...
	public static final int NUM_ELIGIBLE_TOURNAMENT_ROUNDS = 2;
	
	// score types...
	public static final String COMBINED_SCORE_TYPE = "C";
	public static final String TOURNAMENT_SCORE_TYPE = "T";
	public static final String TOURNAMENT_INTERNET_SCORE_TYPE = "TI";
	public static final String AWAY_SCORE_TYPE = "A";
	public static final String AWAY_INTERNET_SCORE_TYPE = "AI";
	public static final String HOME_SCORE_TYPE = "H";
	
	// option values...
	public static final int ALL_OPTION_VAL = -99;
	public static final int ANY_OPTION_VAL = -99;
	public static final int ALL_COURSES_VAL = -99;
	
	// option value label keys...
	public static final String ALL_OPTION_LBL_KEY = "option.all";
	public static final String OPTION_TRUE_LBL_KEY = "option.true";
	public static final String OPTION_FALSE_LBL_KEY = "option.false";
	public static final String OPTION_CART_LBL_KEY = "option.cart";
	public static final String OPTION_WALK_LBL_KEY = "option.walk";
	
	// option values for contact-us message-type...
	public static final int MSGTYPE_ENHANCEMENTREQUEST 			= 0;
	public static final String MSGTYPE_ENHANCEMENTREQUEST_LABEL = "Enhancement Request";
	public static final int MSGTYPE_GENERALQUESTION 			= 1;
	public static final String MSGTYPE_GENERALQUESTION_LABEL 	= "General Question";
	public static final int MSGTYPE_OTHER 						= 2;
	public static final String MSGTYPE_OTHER_LABEL 				= "Other";
	public static final String MSGTYPE_UNKNOWN_LABEL			= "Unknown";
	
	/**
	 * private no-arg constructor
	 */
	private Constants() {
		// do nothing...
	}
}
