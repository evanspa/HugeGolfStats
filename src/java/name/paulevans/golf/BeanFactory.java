package name.paulevans.golf;

import name.paulevans.golf.bean.chart.ChartCategory;
import name.paulevans.golf.dao.ApproachShotDistanceDAO;
import name.paulevans.golf.dao.ApproachShotLineDAO;
import name.paulevans.golf.dao.CalendarMonthDAO;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.CourseSlopeRatingDAO;
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
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeNameDAO;
import name.paulevans.golf.dao.TeeShotAccuracyDAO;
import name.paulevans.golf.dao.UsageSummaryDAO;
import name.paulevans.golf.dao.UserRoleDAO;
import name.paulevans.golf.dao.WeatherConditionDAO;
import name.paulevans.golf.mail.NotificationManager;
import name.paulevans.golf.tools.DataLoadTool;
import name.paulevans.golf.util.EncryptionUtils;
import name.paulevans.golf.util.NewUtil;

/**
 * Factory object used to retrieve beans
 * @author Paul
 *
 */
public class BeanFactory {
	
	// Spring bean factory object...
	private static org.springframework.beans.factory.BeanFactory beanFactory;
	
	// initialize the Spring beanFactory...
	static {
		beanFactory = new org.springframework.context.support.ClassPathXmlApplicationContext( new String[] {
				"spring-hgs-dao-hibernate.xml",  "spring-hgs-util.xml", 
				"spring-hgs-mail.xml", 			 "spring-hgs-charts-putting.xml",
				"spring-hgs-charts-scoring.xml", "spring-hgs-charts-teeshot.xml",
				"spring-hgs-charts-gir.xml",     "spring-hgs-charts-approachshot.xml",
				"spring-hgs-charts-aroundgreen.xml"});
	}
	
	// ID of the Course DAO bean...
	private static final String COURSE_DAO = "courseDAO";
	
	// ID of the Hole DAO bean...
	private static final String HOLE_DAO = "holeDAO";
	
	// ID of the State-Province DAO bean...
	private static final String STATE_PROVINCE_DAO = "stateProvinceDAO";
	
	// ID of the calendar-month DAO bean...
	private static final String CALENDAR_MONTH_DAO = "calendarMonthDAO";
	
	// ID of the utility object...
	private static final String UTILITY_OBJECT = "utilityObject";
	
	// ID of DAO utils object...
	private static final String DAO_UTILS_OBJECT = "daoUtils";
	
	// ID of the encryption utils object...
	private static final String ENCRYPTION_UTILS_OBJECT = "encryptionUtils";
	
	// ID of pant-wear DAO bean...
	private static final String PANT_WEAR_DAO = "pantWearDAO";
	
	// ID of weather-condition DAO bean...
	private static final String WEATHER_CONDITION_DAO = "weatherConditionDAO";
	
	// ID of head-wear DAO bean...
	private static final String HEAD_WEAR_DAO = "headWearDAO";
	
	// ID of eye-wear DAO bean...
	private static final String EYE_WEAR_DAO = "eyeWearDAO";
	
	// ID of tee name DAO bean...
	private static final String TEE_NAME_DAO = "teeNameDAO";
	
	// ID of tee DAO bean...
	private static final String TEE_DAO = "teeDAO";
	
	// ID of player DAO bean...
	private static final String PLAYER_DAO = "playerDAO";
	
	// ID of user role DAO bean...
	private static final String USER_ROLE_DAO = "userRoleDAO";
	
	// ID of score DAO bean...
	private static final String SCORE_DAO = "scoreDAO";
	
	// ID of scorecard DAO bean...
	private static final String SCORECARD_DAO = "scorecardDAO";

	// ID of scorecard summary DAO bean...
	private static final String SCORECARD_SUMMARY_DAO = "scorecardSummaryDAO";
	
	// ID of approach shot distance DAO bean...
	private static final String APPROACH_SHOT_DISTANCE_DAO = 
		"approachShotDistanceDAO";
	
	// ID of approach shot line DAO bean...
	private static final String APPROACH_SHOT_LINE_DAO = "approachShotLineDAO";
	
	// ID of green-in-regulation DAO bean...
	private static final String GREEN_IN_REGULATION_DAO = 
		"greenInRegulationDAO";
	
	// ID of tee shot accuracy DAO bean...
	private static final String TEE_SHOT_ACCURACY_DAO = "teeShotAccuracyDAO";
	
	// ID of golf club DAO bean...
	private static final String GOLF_CLUB_DAO = "golfClubDAO";
	
	// ID of usage summary DAO bean...
	private static final String USAGE_SUMMARY_DAO = "usageSummaryDAO";
	
	// ID of course slope-rating DAO bean...
	private static final String COURSE_SLOPE_RATING_DAO = 
		"courseSlopeRatingDAO";
	
	// ID of the DataLoadTool instance...
	private static final String DATA_LOAD_TOOL_ID = "DataLoadTool";
	
	// ID of the email notification manager object..
	private static final String NOTIFICATION_MANAGER = "notificationMgr";
	
	/**
	 * Returns a notification manager object
	 * @return
	 */
	public static final NotificationManager getNotificationManager() {
		return (NotificationManager)beanFactory.getBean(NOTIFICATION_MANAGER);
	}
	
	/**
	 * Returns a new DataLoadTool instance
	 * @return
	 */
	public static final DataLoadTool newDataLoadTool() {
		return (DataLoadTool)beanFactory.getBean(DATA_LOAD_TOOL_ID);
	}
	
	/**
	 * Returns a new CourseSlopeRatingDAO bean
	 * @return
	 */
	public static final CourseSlopeRatingDAO newCourseSlopeRatingDAO() {
		return (CourseSlopeRatingDAO)beanFactory.getBean(
				COURSE_SLOPE_RATING_DAO);
	}

	/**
	 * Returns a new GolfClubDAO bean with its ID initialized
	 * to aInitID
	 * @return
	 */
	public static final GolfClubDAO newGolfClubDAO(
			int aInitID) {
		
		GolfClubDAO golfClubDAO;
		
		golfClubDAO = newGolfClubDAO();
		golfClubDAO.setId(aInitID);
		return golfClubDAO;
	}
	
	/**
	 * Returns the UsageSummaryDAO bean
	 * @return
	 */
	public static final UsageSummaryDAO getUsageSummaryDAO() {
		return (UsageSummaryDAO)beanFactory.getBean(USAGE_SUMMARY_DAO);
	}
	
	/**
	 * Returns a new GolfClubDAO bean
	 * @return
	 */
	public static final GolfClubDAO newGolfClubDAO() {
		return (GolfClubDAO)beanFactory.getBean(GOLF_CLUB_DAO);
	}	
	
	/**
	 * Returns a new TeeShotAccuracyDAO bean with its ID initialized
	 * to aInitID
	 * @return
	 */
	public static final TeeShotAccuracyDAO newTeeShotAccuracyDAO(
			int aInitID) {
		
		TeeShotAccuracyDAO teeShotAccuracy;
		
		teeShotAccuracy = newTeeShotAccuracyDAO();
		teeShotAccuracy.setId(aInitID);
		return teeShotAccuracy;
	}
	
	/**
	 * Returns a new TeeShotAccuracyDAO bean
	 * @return
	 */
	public static final TeeShotAccuracyDAO newTeeShotAccuracyDAO() {
		return (TeeShotAccuracyDAO)beanFactory.getBean(TEE_SHOT_ACCURACY_DAO);
	}
	
	/**
	 * Returns a new GreenInRegulationDAO bean with its ID initialized
	 * to aInitID
	 * @return
	 */
	public static final GreenInRegulationDAO newGreenInRegulationDAO(
			int aInitID) {
		
		GreenInRegulationDAO greenInRegulation;
		
		greenInRegulation = newGreenInRegulationDAO();
		greenInRegulation.setId(aInitID);
		return greenInRegulation;
	}
	
	/**
	 * Returns a new GreenInRegulationDAO bean
	 * @return
	 */
	public static final GreenInRegulationDAO newGreenInRegulationDAO() {
		return (GreenInRegulationDAO)beanFactory.getBean(
				GREEN_IN_REGULATION_DAO);
	}	
	
	/**
	 * Returns a new ApproachShotLineDAO bean with its ID initialized
	 * to aInitID
	 * @return
	 */
	public static final ApproachShotLineDAO newApproachShotLineDAO(
			int aInitID) {
		
		ApproachShotLineDAO approachShotLine;
		
		approachShotLine = newApproachShotLineDAO();
		approachShotLine.setId(aInitID);
		return approachShotLine;
	}
	
	/**
	 * Returns a new ApproachShotLineDAO bean
	 * @return
	 */
	public static final ApproachShotLineDAO newApproachShotLineDAO() {
		return (ApproachShotLineDAO)beanFactory.getBean(
				APPROACH_SHOT_LINE_DAO);
	}

	/**
	 * Returns a new ApproachShotDistanceDAO bean with its ID initialized
	 * to aInitID
	 * @return
	 */
	public static final ApproachShotDistanceDAO newApproachShotDistanceDAO(
			int aInitID) {
		
		ApproachShotDistanceDAO approachShotDistance;
		
		approachShotDistance = newApproachShotDistanceDAO();
		approachShotDistance.setId(aInitID);
		return approachShotDistance;
	}
	
	/**
	 * Returns a new ApproachShotDistanceDAO bean
	 * @return
	 */
	public static final ApproachShotDistanceDAO newApproachShotDistanceDAO() {
		return (ApproachShotDistanceDAO)beanFactory.getBean(
				APPROACH_SHOT_DISTANCE_DAO);
	}
	
	/**
	 * Returns a new ScoreDAO bean
	 * @return
	 */
	public static final ScoreDAO newScoreDAO() {
		return (ScoreDAO)beanFactory.getBean(SCORE_DAO);
	}
	
	/**
	 * Returns a new ScorecardDAO bean
	 * @return
	 */
	public static final ScorecardDAO newScorecardDAO() {
		return (ScorecardDAO)beanFactory.getBean(SCORECARD_DAO);
	}
	
	/**
	 * Returns a new ScorecardSummaryDAO bean
	 * @return
	 */
	public static final ScorecardSummaryDAO newScorecardSummaryDAO() {
		return (ScorecardSummaryDAO)beanFactory.getBean(SCORECARD_SUMMARY_DAO);
	}
	
	/**
	 * Returns a new StateProvinceDAO bean
	 * @return
	 */
	public static final StateProvinceDAO newStateProvinceDAO() {
		return (StateProvinceDAO)beanFactory.getBean(STATE_PROVINCE_DAO);
	}
	
	/**
	 * Returns a new CalendarMonthDAO bean
	 * @return
	 */
	public static final CalendarMonthDAO newCalendarMonthDAO() {
		return (CalendarMonthDAO)beanFactory.getBean(CALENDAR_MONTH_DAO);
	}
	
	/**
	 * Returns a new CourseDAO bean
	 * @return
	 */
	public static final CourseDAO newCourseDAO() {
		return (CourseDAO)beanFactory.getBean(COURSE_DAO);
	}
	
	/**
	 * Returns a new PantWearDAO bean
	 * @return
	 */
	public static final PantWearDAO newPantWearDAO() {
		return (PantWearDAO)beanFactory.getBean(PANT_WEAR_DAO);
	}
	
	public static final WeatherConditionDAO newWeatherConditionDAO() {
		return (WeatherConditionDAO)beanFactory.getBean(WEATHER_CONDITION_DAO);
	}
	
	/**
	 * Returns a new HeadWearDAO bean
	 * @return
	 */
	public static final HeadWearDAO newHeadWearDAO() {
		return (HeadWearDAO)beanFactory.getBean(HEAD_WEAR_DAO);
	}
	
	/**
	 * Returns a chart category object...
	 * @param aCategory
	 * @return
	 */
	public static final ChartCategory getChartCategory(String aCategory) {
		return (ChartCategory)beanFactory.getBean(aCategory);
	}
	
	/**
	 * Returns a new EyeWearDAO bean
	 * @return
	 */
	public static final EyeWearDAO newEyeWearDAO() {
		return (EyeWearDAO)beanFactory.getBean(EYE_WEAR_DAO);
	}
	
	/**
	 * Returns a new TeeNameDAO bean
	 * @return
	 */
	public static final TeeNameDAO newTeeNameDAO() {
		return (TeeNameDAO)beanFactory.getBean(TEE_NAME_DAO);
	}
	
	/**
	 * Returns a new TeeDAO bean
	 * @return
	 */
	public static final TeeDAO newTeeDAO() {
		return (TeeDAO)beanFactory.getBean(TEE_DAO);
	}
	
	/**
	 * Returns a new HoleDAO bean
	 * @return
	 */
	public static final HoleDAO newHoleDAO() {
		return (HoleDAO)beanFactory.getBean(HOLE_DAO);
	}
	
	/**
	 * Returns a new PlayerDAO bean
	 * @return
	 */
	public static final PlayerDAO newPlayerDAO() {
		return (PlayerDAO)beanFactory.getBean(PLAYER_DAO);
	}
	
	/**
	 * Returns a new UserRoleDAO bean
	 * @return
	 */
	public static final UserRoleDAO newUserRoleDAO() {
		return (UserRoleDAO)beanFactory.getBean(USER_ROLE_DAO);
	}
	
	/**
	 * Returns the utility object
	 * @return
	 */
	public static final NewUtil getUtilObject() {
		return (NewUtil)beanFactory.getBean(UTILITY_OBJECT);
	}
	
	/**
	 * Returns the DAO utils object
	 * @return
	 */
	public static final DAOUtils getDAOUtils() {
		return (DAOUtils)beanFactory.getBean(DAO_UTILS_OBJECT);
	}
	
	/**
	 * Returns the encryption utils object
	 * @return
	 */
	public static final EncryptionUtils getEncryptionUtils() {
		return (EncryptionUtils)beanFactory.getBean(ENCRYPTION_UTILS_OBJECT);
	}

}
