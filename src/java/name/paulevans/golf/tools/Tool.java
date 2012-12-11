package name.paulevans.golf.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class Tool {
	
	private static final String JDBC_URL_PROPERTY = "jdbc.url";
	private static final String JDBC_USERNAME_PROPERTY = "jdbc.username";
	private static final String JDBC_PASSWORD_PROPERTY = "jdbc.password";
	private static final String JDBC_DRIVER_PROPERTY = "jdbc.driver";
	private static final String HBM_MAPPING_DIR_PROPERTY = "hbm.mapping.dir";
	
	// database constants...
	private static final String JDBC_URL = System.getProperty(
			JDBC_URL_PROPERTY);
	private static final String JDBC_USERNAME = System.getProperty(
			JDBC_USERNAME_PROPERTY);
	private static final String JDBC_PASSWORD = System.getProperty(
			JDBC_PASSWORD_PROPERTY);
	private static final String JDBC_DRIVER = System.getProperty(
			JDBC_DRIVER_PROPERTY);
	private static final String HBM_MAPPING_DIR = System.getProperty(
			HBM_MAPPING_DIR_PROPERTY);
	
	private static final int BATCH_SIZE = 20;
	
	private SessionFactory sessionFactory;
	
	/**
	 * Public constructor
	 *
	 */
	public Tool() {
		sessionFactory = getConfiguration().buildSessionFactory();
	}
	
	/**
	 * Flushes and clears the session
	 * @param aSaveCount
	 * @param aSession
	 */
	public static final void flushSession(int aSaveCount, Session aSession) {
		if (aSaveCount % BATCH_SIZE == 0) {
			aSession.flush();
			aSession.clear();
		}
	}
	
	/**
	 * Flushes and clears the session
	 * @param aSession
	 */
	public static final void flush(Session aSession) {
		aSession.flush();
		aSession.clear();
	}
	
	/**
	 * Returns a new session
	 * @return
	 */
	public final Session newSession() {
		return sessionFactory.openSession();
	}
	
	/**
	 * Creates and returns a connection to the database
	 * @return
	 */
	private final Configuration getConfiguration() {
		
		Configuration configuration; 
		
		configuration = new Configuration();
		configuration.setProperty("hibernate.connection.driver_class", JDBC_DRIVER);
		configuration.setProperty("hibernate.connection.url", JDBC_URL);
		configuration.setProperty("hibernate.connection.username", JDBC_USERNAME);
		configuration.setProperty("hibernate.connection.password", JDBC_PASSWORD);
		configuration.setProperty("hibernate.connection.pool_size", "1");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
		configuration.setProperty("hibernate.query.factory_class", "org.hibernate.hql.classic.ClassicQueryTranslatorFactory");
		configuration.setProperty("hibernate.current_session_context_class", "thread");
		configuration.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		configuration.setProperty("hibernate.jdbc.batch_size", Integer.toString(BATCH_SIZE));
		configuration.setProperty("hibernate.cache.use_second_level_cache", "false");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/Course.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/CourseSlopeRating.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/Tee.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/TeeName.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/Player.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/UserRoles.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/Scorecard.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/ScorecardSummary.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/Score.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/TeeShotAccuracy.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/WeatherCondition.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/PantWear.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/EyeWear.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/GolfClub.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/GreenInRegulation.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/Country.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/StateProvince.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/HeadWear.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/Hole.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/ApproachShotDistance.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/ApproachShotLine.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/GolfBall.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/Locale.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/CalendarMonth.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/SavedChart.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/RoundCircumstance.hbm.xml");
		configuration.addFile(HBM_MAPPING_DIR + "/gen/hibernate/name/paulevans/golf/SavedChartCircumstance.hbm.xml");
		return configuration;
	}
}
