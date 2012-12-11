package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.ApproachShotDistance;
import gen.hibernate.name.paulevans.golf.ApproachShotLine;
import gen.hibernate.name.paulevans.golf.CalendarMonth;
import gen.hibernate.name.paulevans.golf.Country;
import gen.hibernate.name.paulevans.golf.Course;
import gen.hibernate.name.paulevans.golf.EyeWear;
import gen.hibernate.name.paulevans.golf.GolfClub;
import gen.hibernate.name.paulevans.golf.GreenInRegulation;
import gen.hibernate.name.paulevans.golf.HeadWear;
import gen.hibernate.name.paulevans.golf.PantWear;
import gen.hibernate.name.paulevans.golf.Player;
import gen.hibernate.name.paulevans.golf.StateProvince;
import gen.hibernate.name.paulevans.golf.Tee;
import gen.hibernate.name.paulevans.golf.TeeName;
import gen.hibernate.name.paulevans.golf.TeeShotAccuracy;
import gen.hibernate.name.paulevans.golf.WeatherCondition;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.dao.ApproachShotDistanceDAO;
import name.paulevans.golf.dao.ApproachShotLineDAO;
import name.paulevans.golf.dao.CalendarMonthDAO;
import name.paulevans.golf.dao.CountryDAO;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
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

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Hibernate DAO Utils implementation
 * 
 * @author pevans
 * 
 */
public class HibernateDAOUtils extends DAOUtils {

	// logger objects...
	private static Logger logger = Logger.getLogger(HibernateDAOUtils.class);
	private static Logger hqllogger = Logger.getLogger("HQL");

	// map of locale ids...
	private static final Map<String, Integer> LOCALE_ID_CACHE = 
		new HashMap<String, Integer>();

	// Hibernate session factory...
	private SessionFactory sessionFactory;

	/**
	 * Sets the session factory
	 * 
	 * @param aSessionFactory
	 */
	public void setSessionFactory(SessionFactory aSessionFactory) {
		sessionFactory = aSessionFactory;
	}

	/**
	 * Gets the session factory
	 * 
	 * @return
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Returns the number of holes associated with the course with id aCourseId
	 * 
	 * @param aCourseId
	 * @return
	 */
	public int getNumHoles(final int aCourseId) throws DataAccessException {

		int numHoles;
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		numHoles = (Integer) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				int numHoles;
				Object result;

				numHoles = 0;
				result = aSession.createSQLQuery(
						"select max(number) as maxHole from hole "
								+ "where course_id = ?").addScalar("maxHole",
						Hibernate.INTEGER).setInteger(0, aCourseId)
						.uniqueResult();
				if (result != null) {
					numHoles = (Integer) result;
				}
				logger.info("Number of holes for course Id: [" + aCourseId
						+ "] is: [" + numHoles + "]");
				return numHoles;
			}
		});
		return numHoles;
	}

	/**
	 * Returns a count-value associated around-the-green values including
	 * up-and-down and sand-save values
	 * 
	 * @param aQueryText
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	private int getAroundGreenCount(final String aQueryText,
			final Date aFromDate, final Date aToDate, 
			final CircumstancesBean aCircumstances, final PlayerDAO aPlayer) {

		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		return (Integer) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				String queryText;
				Query query;
				Player player;

				player = (Player) aPlayer.getDelegate();
				queryText = aQueryText;
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				if (aFromDate != null)
					query.setDate("fromDate", aFromDate);
				if (aToDate != null)
					query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				return query.uniqueResult();
			}
		});
	}

	/**
	 * Returns the count of sand-save attempts for the inputted player and date
	 * range
	 * 
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	public int getSandSaveAttemptCount(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer) {
		return getAroundGreenCount("SELECT count(score.id.holeId) "
				+ "FROM Score score, Scorecard sc "
				+ "WHERE score.id.scorecardId = sc.id AND "
				+ "score.sandSaveAttempt = 1 AND sc.player.id = :playerId ",
				aFromDate, aToDate, aCircumstances, aPlayer);
	}

	/**
	 * Returns the count of up-and-down converts for this inputted player and
	 * date range
	 * 
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	public int getSandSaveConvertCount(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances,
			final PlayerDAO aPlayer) {
		return getAroundGreenCount("SELECT count(score.id.holeId) "
				+ "FROM Score score, Scorecard sc "
				+ "WHERE score.id.scorecardId = sc.id AND "
				+ "score.sandSaveConversion = 1 AND sc.player.id = :playerId ",
				aFromDate, aToDate, aCircumstances, aPlayer);
	}

	/**
	 * Returns the count of up-and-down attempts for the inputted player and
	 * date range
	 * 
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	public int getUpDownAttemptCount(Date aFromDate, Date aToDate,
			CircumstancesBean aCircumstances, PlayerDAO aPlayer) {
		return getAroundGreenCount("SELECT count(score.id.holeId) "
				+ "FROM Score score, Scorecard sc "
				+ "WHERE score.id.scorecardId = sc.id AND "
				+ "score.upDownAttempt = 1 AND sc.player.id = :playerId ",
				aFromDate, aToDate, aCircumstances, aPlayer);
	}

	/**
	 * Returns the count of up-and-down converts for this inputted player and
	 * date range
	 * 
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @return
	 */
	public int getUpDownConvertCount(Date aFromDate, Date aToDate,
			CircumstancesBean aCircumstances, PlayerDAO aPlayer) {
		return getAroundGreenCount("SELECT count(score.id.holeId) "
				+ "FROM Score score, Scorecard sc "
				+ "WHERE score.id.scorecardId = sc.id AND "
				+ "score.upDownConversion = 1 AND sc.player.id = :playerId ",
				aFromDate, aToDate, aCircumstances, aPlayer);
	}

	/**
	 * Returns the collection of courses given the inputted country and
	 * state/province ID.
	 * 
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public CourseDAO[] getCourses(final int aCountryId,
			final int aStateProvinceId) throws DataAccessException {

		List<Course> courseList;
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		courseList = (List<Course>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				SQLQuery query;
				List<Course> courseList;
				query = aSession
						.createSQLQuery("SELECT {course.*} FROM "
								+ "course course WHERE course.country_id = :countryId AND "
								+ "course.state_province_id = :stateProvinceId "
								+ "ORDER BY course.description");
				query.addEntity("course", Course.class);
				query.setInteger("countryId", aCountryId);
				query.setInteger("stateProvinceId", aStateProvinceId);
				courseList = (List<Course>) query.list();
				return courseList;
			}
		});
		return convert(courseList);
	}

	/**
	 * Returns the collection of course tees for the given aCourseId.
	 * 
	 * @param aCourseId
	 * @return
	 */
	// @SuppressWarnings({"unchecked"})
	/*
	 * public TeeDAO[] getCourseTees(final int aCourseId) throws
	 * DataAccessException {
	 * 
	 * Tee tees[]; HibernateTemplate ht;
	 * 
	 * ht = new HibernateTemplate(getSessionFactory()); tees =
	 * (Tee[])ht.execute(new HibernateCallback() { public Object
	 * doInHibernate(Session aSession) throws HibernateException {
	 * 
	 * Course course; int loop; Tee tees[];
	 *  // load the course... course = (Course)aSession.load(Course.class,
	 * aCourseId); Hibernate.initialize(course); // initialize each tee... tees =
	 * (Tee[])course.getTees().toArray( new Tee[course.getTees().size()]); for
	 * (loop = 0; loop < tees.length; loop++) {
	 * Hibernate.initialize(tees[loop]);
	 * Hibernate.initialize(tees[loop].getTeeColor()); } return tees; } });
	 * return convert(tees); }
	 */

	/**
	 * Returns the set of weather condition types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public WeatherConditionDAO[] getWeatherConditionTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<WeatherCondition>) getLookupValues(
				"from WeatherCondition wc where wc.locale.id = ?", aLocale));
	}

	/**
	 * Returns the set of tee shot accuracy types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public TeeShotAccuracyDAO[] getTeeShotAccuracyTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<TeeShotAccuracy>) getLookupValues(
				"from TeeShotAccuracy tsa where tsa.locale.id = ?", aLocale));
	}

	/**
	 * Returns the set of approach shot line types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public ApproachShotLineDAO[] getApproachShotLineTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<ApproachShotLine>) getLookupValues(
				"from ApproachShotLine asl where asl.locale.id = ?", aLocale));
	}

	/**
	 * Returns the set of golf club types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public GolfClubDAO[] getGolfClubTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<GolfClub>) getLookupValues(
				"from GolfClub gc where gc.locale.id = ?", aLocale));
	}

	/**
	 * Returns the set of approach shot distance types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public ApproachShotDistanceDAO[] getApproachShotDistanceTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<ApproachShotDistance>) getLookupValues(
				"from ApproachShotDistance asd where asd.locale.id = ?",
				aLocale));
	}

	/**
	 * Returns the set of green-in-regulation types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public GreenInRegulationDAO[] getGreenInRegulationTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<GreenInRegulation>) getLookupValues(
				"from GreenInRegulation gir where gir.locale.id = ?", aLocale));
	}

	/**
	 * Returns the set of head wear types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public HeadWearDAO[] getHeadWearTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<HeadWear>) getLookupValues(
				"from HeadWear hw where hw.locale.id = ?", aLocale));
	}

	/**
	 * Returns the set of pant wear types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public PantWearDAO[] getPantWearTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<PantWear>) getLookupValues(
				"from PantWear pw where pw.locale.id = ?", aLocale));
	}

	/**
	 * Returns the set of eye wear types.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public EyeWearDAO[] getEyeWearTypes(Locale aLocale)
			throws DataAccessException {
		return convert((List<EyeWear>) getLookupValues(
				"from EyeWear ew where ew.locale.id = ?", aLocale));
	}

	/**
	 * Returns the set of countries.
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public CountryDAO[] getCountries() throws DataAccessException {

		List<Country> countries;
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		countries = ht.loadAll(Country.class);
		return convert(countries);
	}

	/**
	 * Returns the locale id
	 */
	public int getLocaleId(Locale aLocale) throws DataAccessException {

		Integer localeId;
		String name;

		name = aLocale.getISO3Language();
		localeId = LOCALE_ID_CACHE.get(name);
		if (localeId == null) {
			localeId = retrieveLocaleId(aLocale);
			LOCALE_ID_CACHE.put(name, localeId);
		}
		return localeId;
	}

	/**
	 * Returns the locale id.
	 * 
	 * @param aLocale
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	private int retrieveLocaleId(final Locale aLocale)
			throws DataAccessException {

		gen.hibernate.name.paulevans.golf.Locale locale;
		HibernateTemplate ht;
		int localeId;

		localeId = Constants.ENGLISH_LOCALE_ID; // init to default value...
		ht = new HibernateTemplate(getSessionFactory());
		locale = (gen.hibernate.name.paulevans.golf.Locale) ht
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session aSession)
							throws HibernateException {
						return aSession.createQuery(
								"from Locale as locale where locale.name = ?")
								.setString(0, aLocale.getISO3Language())
								.uniqueResult();
					}
				});
		if (locale != null) {
			localeId = locale.getId();
		}
		return localeId;
	}

	/**
	 * Returns the set of months.
	 * 
	 * @param aLocale
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public CalendarMonthDAO[] getMonths(final Locale aLocale)
			throws DataAccessException {
		return convert((List<CalendarMonth>) getLookupValues(
				"from CalendarMonth as cm where cm.locale.id = ?", aLocale));
	}

	/**
	 * Returns the list of values based on the query and locale
	 * 
	 * @param aLocale
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	private List getLookupValues(final String aQuery, final Locale aLocale) {

		List lookupData;
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		lookupData = (List) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {
				return aSession.createQuery(aQuery).setCacheable(true)
						.setInteger(0, getLocaleId(aLocale)).list();
			}
		});
		return lookupData;
	}

	/**
	 * Returns the collection of state provinces for the given aCountryId.
	 * 
	 * @param aCountryId
	 * @return StateProvinceDAO[]
	 */
	@SuppressWarnings( { "unchecked" })
	public StateProvinceDAO[] getStateProvinces(final int aCountryId)
			throws DataAccessException {

		List<StateProvince> stateProvinces;
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		stateProvinces = (List<StateProvince>) ht
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session aSession)
							throws HibernateException {

						Query query;
						List<StateProvince> stateProvinces;

						// load the countries...
						query = aSession
								.createQuery("from StateProvince where "
										+ "country_id = ?");
						query.setInteger(0, aCountryId);
						stateProvinces = query.list();
						return stateProvinces;
					}
				});
		return convert(stateProvinces);
	}
	
	/**
	 * Returns the collection of courses for the given aPlayerId.
	 * 
	 * @param aPlayerId
	 * @return CourseDAO[]
	 */
	@SuppressWarnings( { "unchecked" })
	public CourseDAO[] getCoursesPlayed(final int aPlayerId)
			throws DataAccessException {

		List<CourseDAO> courses;
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		courses = (List<CourseDAO>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) throws HibernateException {

				Query query;
				List results;
				List<CourseDAO> courses;
				int numRecords, loop;
				Object rowData[];
				CourseDAO course;

				// load the countries...
				query = aSession.createQuery("SELECT DISTINCT c.id, " +
					"c.description FROM Course c, Player p, " +
					"Scorecard s WHERE p.id = s.player.id AND " +
					"s.tee.course.id = c.id AND p.id = ? Order By c.description");
				query.setInteger(0, aPlayerId);
				results = query.list();
				numRecords = results.size();
				courses = new ArrayList<CourseDAO>();
				for (loop = 0; loop < numRecords; loop++) {
					rowData = (Object[])results.get(loop);
					course = BeanFactory.newCourseDAO();
					course.setId((Integer)rowData[0]);
					course.setDescription((String)rowData[1]);
					courses.add(course);
				}						
				return courses;
			}
		});
		return courses.toArray(new CourseDAO[courses.size()]);
	}	

	/**
	 * Returns the collection of tee colors
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public TeeNameDAO[] getTeeNames() throws DataAccessException {

		List<TeeName> teeNames;
		HibernateTemplate ht;

		ht = new HibernateTemplate(sessionFactory);
		teeNames = (List) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {
				return aSession
						.createQuery(
								"from TeeName tee where "
										+ "tee.isStandardColor = true order by tee.name")
						.list();
			}
		});
		return convert(teeNames);
	}

	/**
	 * Returns true if a player exists within the system with the email address
	 * aEmailAddress
	 * 
	 * @param aEmailAddress
	 * @return
	 */
	public boolean doesEmailAddressExist(final String aEmailAddress)
			throws DataAccessException {

		HibernateTemplate ht;
		int numPlayers;

		ht = new HibernateTemplate(sessionFactory);
		numPlayers = (Integer) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {
				return aSession.createSQLQuery(
						"SELECT count(id) AS count "
								+ "FROM player WHERE email_address = ?")
						.addScalar("count", Hibernate.INTEGER).setString(0,
								aEmailAddress).uniqueResult();
			}
		});
		return numPlayers > 0;
	}

	/**
	 * Returns true if there are no players in the database with the inputted
	 * user id; otherwise returns false.
	 * 
	 * @param aUserId
	 * @return
	 */
	public boolean doesUserIdExist(final String aUserId) {

		HibernateTemplate ht;
		int numPlayers;

		ht = new HibernateTemplate(sessionFactory);
		numPlayers = (Integer) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {
				return aSession.createSQLQuery(
						"SELECT count(id) AS count "
								+ "FROM player WHERE user_id = ?").addScalar(
						"count", Hibernate.INTEGER).setString(0, aUserId)
						.uniqueResult();
			}
		});
		return numPlayers > 0;
	}

	/**
	 * Finds and returns player from the database
	 * 
	 * @param aEmailAddress
	 * @return
	 */
	public PlayerDAO getPlayerByEmailAddress(final String aEmailAddress)
			throws DataAccessException {
		return getPlayer("from Player as p where p.emailAddress = ?",
				aEmailAddress);
	}

	/**
	 * Finds and returns player from the database
	 * 
	 * @param aUserId
	 * @return
	 */
	public PlayerDAO getPlayerByUserId(final String aUserId)
			throws DataAccessException {
		return getPlayer("from Player as p where p.userId = ?", aUserId);
	}

	/**
	 * Finds and returns player from the database
	 * 
	 * @param aUserId
	 * @return
	 */
	private PlayerDAO getPlayer(final String aQuery, final String aQueryParam)
			throws DataAccessException {

		Player player;
		HibernateTemplate ht;
		SessionFactory sessionFactory;
		HibernatePlayerDAO playerDAO;

		playerDAO = null;
		sessionFactory = getSessionFactory();
		ht = new HibernateTemplate(sessionFactory);
		player = (Player) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				Player player;
				Query query;
				Course homeCourse;

				// load the player...
				query = aSession.createQuery(aQuery);
				query.setString(0, aQueryParam);
				player = (Player) query.uniqueResult();

				if (player != null) {
					// initialize the references w/in the player object...
					Hibernate.initialize(player);
					Hibernate.initialize(player.getTeeName());
					Hibernate.initialize(homeCourse = player.getCourse());
					if (homeCourse != null) {
						Hibernate.initialize(homeCourse.getStateProvince());
						Hibernate.initialize(homeCourse.getStateProvince()
								.getCountry());
						Hibernate.initialize(homeCourse.getTees());
					}
					Hibernate.initialize(player.getEyeWear());
					Hibernate.initialize(player.getHeadWear());
					Hibernate.initialize(player.getPantWear());
				}
				return player;
			}
		});
		if (player != null) {
			playerDAO = new HibernatePlayerDAO(player);
			playerDAO.setSessionFactory(sessionFactory);
		}
		return playerDAO;
	}

	/**
	 * Returns the number instances of an approach-shot-distance value grouped
	 * by date. Return[0] will contain a date and return[1] will contain the
	 * number of occurances
	 * 
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aAPSDistanceTypeId
	 * @return
	 */
	public List<Object[]> getAPSDistanceValues(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer, final int aAPSDistanceTypeId) {

		HibernateTemplate ht;
		List<Object[]> apsDistanceTypes;

		ht = new HibernateTemplate(getSessionFactory());
		apsDistanceTypes = (List<Object[]>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				String queryText;
				Query query;
				Player player;

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT sc.date, "
						+ "count(score.approachShotDistance.id) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId AND score.approachShotDistance.id = "
						+ ":apsDistanceTypeId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY sc.date";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				query.setInteger("apsDistanceTypeId", aAPSDistanceTypeId);
				if (aFromDate != null)
					query.setDate("fromDate", aFromDate);
				if (aToDate != null)
					query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				return query.list();
			}
		});
		return apsDistanceTypes;
	}

	/**
	 * Returns the number instances of an approach-shot-line value grouped by
	 * date. Return[0] will contain a date and return[1] will contain the number
	 * of occurances
	 * 
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aAPSLineTypeId
	 * @return
	 */
	public List<Object[]> getAPSLineValues(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer, final int aAPSLineTypeId) {

		HibernateTemplate ht;
		List<Object[]> apsLineTypes;

		ht = new HibernateTemplate(getSessionFactory());
		apsLineTypes = (List<Object[]>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				String queryText;
				Query query;
				Player player;

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT sc.date, count(score.approachShotLine.id) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId AND score.approachShotLine.id = "
						+ ":apsLineTypeId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY sc.date";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				query.setInteger("apsLineTypeId", aAPSLineTypeId);
				if (aFromDate != null)
					query.setDate("fromDate", aFromDate);
				if (aToDate != null)
					query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				return query.list();
			}
		});
		return apsLineTypes;
	}

	/**
	 * Returns the number instances of an GIR types grouped by date. return[0]
	 * will contain a date and return[1] will contain the number of GIR types
	 * 
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aGIRTypeId
	 * @return
	 */
	public List<Object[]> getGIRValues(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer, final int aGIRTypeId) {

		HibernateTemplate ht;
		List<Object[]> girTypes;

		ht = new HibernateTemplate(getSessionFactory());
		girTypes = (List<Object[]>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				String queryText;
				Query query;
				Player player;

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT sc.date, count(score.greenInRegulation.id) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId AND score.greenInRegulation.id = "
						+ ":girTypeId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY sc.date";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				query.setInteger("girTypeId", aGIRTypeId);
				if (aFromDate != null)
					query.setDate("fromDate", aFromDate);
				if (aToDate != null)
					query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				return query.list();
			}
		});
		return girTypes;
	}

	/**
	 * Returns the number instances of an "accuracy type" grouped by date.
	 * return[0] will contain a date and return[1] will contain the number of
	 * accuracy types
	 * 
	 * @param aFromDate
	 * @param aToDate
	 * @param aPlayer
	 * @param aAccuracyTypeId
	 * @return
	 */
	public List<Object[]> getTeeShotAccuracyTypes(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer, final int aAccuracyTypeId) {

		HibernateTemplate ht;
		List<Object[]> accuracyTypes;

		ht = new HibernateTemplate(getSessionFactory());
		accuracyTypes = (List<Object[]>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				String queryText;
				Query query;
				Player player;

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT sc.date, count(score.teeShotAccuracy.id) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId AND score.teeShotAccuracy.id = "
						+ ":accuracyTypeId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY sc.date";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				query.setInteger("accuracyTypeId", aAccuracyTypeId);
				if (aFromDate != null)
					query.setDate("fromDate", aFromDate);
				if (aToDate != null)
					query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				return query.list();
			}
		});
		return accuracyTypes;
	}

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
	public List<Object[]> getPutts(final Date aFromDate, final Date aToDate,
			final CircumstancesBean aCircumstances, final PlayerDAO aPlayer, 
			final boolean aIsGIR, final int aNumPutts, 
			final boolean aGreaterThanEqualTo) {

		HibernateTemplate ht;
		List<Object[]> numPutts;

		ht = new HibernateTemplate(getSessionFactory());
		numPutts = (List<Object[]>) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				String queryText;
				Query query;
				Player player;

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT sc.date, count(score.numPutts) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId AND score.numPutts ";
				if (aGreaterThanEqualTo) {
					queryText += ">=";
				} else {
					queryText += "=";
				}
				queryText += " :numPutts ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				if (aIsGIR) {
					queryText += "AND (score.greenInRegulation.id = " +
							":GIRLess15 OR score.greenInRegulation.id = " +
							":GIRGreater15) ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY sc.date";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				query.setInteger("numPutts", aNumPutts);
				if (aFromDate != null) {
					query.setDate("fromDate", aFromDate);
				}
				if (aToDate != null) {
					query.setDate("toDate", aToDate);
				}
				if (aIsGIR) {
					query.setInteger("GIRLess15", 
							Constants.GIR_LESS_THAN_15_FEET);
					query.setInteger("GIRGreater15", 
							Constants.GIR_GREATER_THAN_15_FEET);
				}
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				return query.list();
			}
		});
		return numPutts;
	}

	/**
	 * Returns a portion of the query text used in queries returning scoring-
	 * data that can be filtered by circumstances.
	 * @param aCircumstances
	 * @return
	 */
	public static final String getCircumstanceWhereClauseAddition(CircumstancesBean aCircumstances) {
	
		String queryText;
		
		queryText = "";
		if (aCircumstances != null) {
			if ((aCircumstances.getTournamentTypeId()) !=
				Constants.ALL_OPTION_VAL) {
				queryText += "AND sc.isTournament = :isTourneyRound ";
			}
			if ((aCircumstances.getPantWearId()) !=
				Constants.ALL_OPTION_VAL) {
				queryText += "AND sc.pantWear.id = :pantWearId ";
			}
			if ((aCircumstances.getHeadWearId()) !=
				Constants.ALL_OPTION_VAL) {
				queryText += "AND sc.headWear.id = :headWearId ";
			}
			if ((aCircumstances.getEyeWearId()) !=
				Constants.ALL_OPTION_VAL) {
				queryText += "AND sc.eyeWear.id = :eyeWearId ";
			}
			if ((aCircumstances.getWeatherConditionId()) !=
				Constants.ALL_OPTION_VAL) {
				queryText += "AND sc.weatherCondition.id = :weatherTypeId ";
			}
			if ((aCircumstances.getLocomotionTypeId()) !=
				Constants.ALL_OPTION_VAL) {
				queryText += "AND sc.usedCart = :usedCart ";
			}
			if ((aCircumstances.getBagCarryingMechanismTypeId()) !=
				Constants.ALL_OPTION_VAL) {
				queryText += "AND sc.usedCaddie = :usedCaddie ";
			}
			if ((aCircumstances.getCoursePlayedId()) != 
				Constants.ALL_OPTION_VAL) {
				queryText += "AND sc.tee.course.id = :coursePlayedId ";
			}
		}
		return queryText;
	}
	
	/**
	 * Updates the aQuery object with the circumstance parameter values.
	 * @param aCircumstances
	 * @param aQuery
	 */
	public static final void updateQueryObjectWithCircumstanceParamValues(
			CircumstancesBean aCircumstances, Query aQuery) {
		
		int tourneyId, pantWearTypeId, eyeWearTypeId, headWearTypeId,
		weatherTypeId, locomotionTypeId, bagCarryingTypeId, coursePlayedId;
		
		if (aCircumstances != null) {	
			tourneyId = aCircumstances.getTournamentTypeId();
			pantWearTypeId = aCircumstances.getPantWearId();
			eyeWearTypeId = aCircumstances.getEyeWearId();
			headWearTypeId = aCircumstances.getHeadWearId();
			weatherTypeId = aCircumstances.getWeatherConditionId();
			locomotionTypeId = aCircumstances.getLocomotionTypeId();
			bagCarryingTypeId = aCircumstances.getBagCarryingMechanismTypeId();
			coursePlayedId = aCircumstances.getCoursePlayedId();
			if (tourneyId != Constants.ALL_OPTION_VAL) 
				aQuery.setBoolean("isTourneyRound", BooleanUtils.toBoolean(
						tourneyId, 1, 0));
			if (pantWearTypeId != Constants.ALL_OPTION_VAL) 
				aQuery.setInteger("pantWearId", pantWearTypeId);
			if (headWearTypeId != Constants.ALL_OPTION_VAL) 
				aQuery.setInteger("headWearId", headWearTypeId);
			if (eyeWearTypeId != Constants.ALL_OPTION_VAL) 
				aQuery.setInteger("eyeWearId", eyeWearTypeId);
			if (weatherTypeId != Constants.ALL_OPTION_VAL) 
				aQuery.setInteger("weatherTypeId", weatherTypeId);
			if (locomotionTypeId != Constants.ALL_OPTION_VAL) 
				aQuery.setBoolean("usedCart", BooleanUtils.toBoolean(
						locomotionTypeId, 1, 0));
			if (bagCarryingTypeId != Constants.ALL_OPTION_VAL) 
				aQuery.setBoolean("usedCaddie", BooleanUtils.toBoolean(
						bagCarryingTypeId, 1, 0));
			if (coursePlayedId != Constants.ALL_OPTION_VAL) 
				aQuery.setInteger("coursePlayedId", coursePlayedId);
		}
	}

	/**
	 * Returns an array of size 2; subscript [0] is the total number of pin-high
	 * approach shots; [1] is the total number of approach shots not pin-high
	 * 
	 * @param aPlayer
	 * @return
	 */
	public int[] getAPSDistanceBreakdownValues(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer) {

		int apsDistanceValues[];
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		apsDistanceValues = (int[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				List results;
				String queryText;
				Query query;
				Player player;
				int apsLineId, numRecords, loop;
				Object rowData[];
				int returnData[];

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT score.approachShotDistance.id, "
						+ "count(score.approachShotDistance.id) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY score.approachShotDistance.id ";
				queryText += "HAVING score.approachShotDistance.id is not null";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				if (aFromDate != null)
					query.setDate("fromDate", aFromDate);
				if (aToDate != null)
					query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				results = query.list();
				numRecords = results.size();
				returnData = new int[3];
				for (loop = 0; loop < numRecords; loop++) {
					rowData = (Object[]) results.get(loop);
					apsLineId = (Integer) rowData[0];
					switch (apsLineId) {
					case Constants.APPROACH_SHOT_DISTANCE_SHORT:
						returnData[0] = (Integer) rowData[1];
						break;
					case Constants.APPROACH_SHOT_DISTANCE_PIN_HIGH:
						returnData[1] = (Integer) rowData[1];
						break;
					case Constants.APPROACH_SHOT_DISTANCE_LONG:
						returnData[2] = (Integer) rowData[1];
						break;
					}
				}
				return returnData;
			}
		});
		return apsDistanceValues;
	}

	/**
	 * Returns an array of size 2; subscript [0] is the total number of on-line
	 * approach shots; [1] is the total number of approach shots not on-line
	 * 
	 * @param aPlayer
	 * @return
	 */
	public int[] getAPSLineBreakdownValues(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer) {

		int apsLineValues[];
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		apsLineValues = (int[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				List results;
				String queryText;
				Query query;
				Player player;
				int apsLineId, numRecords, loop;
				Object rowData[];
				int returnData[];

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT score.approachShotLine.id, "
						+ "count(score.approachShotLine.id) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY score.approachShotLine.id ";
				queryText += "HAVING score.approachShotLine.id is not null";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				if (aFromDate != null)
					query.setDate("fromDate", aFromDate);
				if (aToDate != null)
					query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				results = query.list();
				numRecords = results.size();
				returnData = new int[3];
				for (loop = 0; loop < numRecords; loop++) {
					rowData = (Object[]) results.get(loop);
					apsLineId = (Integer) rowData[0];
					switch (apsLineId) {
					case Constants.APPROACH_SHOT_LINE_LEFT:
						returnData[0] = (Integer) rowData[1];
						break;
					case Constants.APPROACH_SHOT_LINE_GREEN:
						returnData[1] = (Integer) rowData[1];
						break;
					case Constants.APPROACH_SHOT_LINE_RIGHT:
						returnData[2] = (Integer) rowData[1];
						break;
					}
				}
				return returnData;
			}
		});
		return apsLineValues;
	}

	/**
	 * Returns an array of size 3; subscript [0] is the total number of close (<15')
	 * GIRs; [1] is the total number of (>15') GIRs; [2] is the total number of
	 * non-GIR values
	 * 
	 * @param aPlayer
	 * @return
	 */
	public int[] getGIRBreakdownValues(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer) {

		int girValues[];
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		girValues = (int[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				List results;
				String queryText;
				Query query;
				Player player;
				int girId, numRecords, loop;
				Object rowData[];
				int returnData[];

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT score.greenInRegulation.id, "
						+ "count(score.greenInRegulation.id) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY score.greenInRegulation.id ";
				queryText += "HAVING score.greenInRegulation.id is not null";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				if (aFromDate != null) query.setDate("fromDate", aFromDate);
				if (aToDate != null) query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				results = query.list();
				numRecords = results.size();
				returnData = new int[3];
				for (loop = 0; loop < numRecords; loop++) {
					rowData = (Object[]) results.get(loop);
					girId = (Integer) rowData[0];
					switch (girId) {
					case Constants.GIR_LESS_THAN_15_FEET:
						returnData[0] = (Integer) rowData[1];
						break;
					case Constants.GIR_GREATER_THAN_15_FEET:
						returnData[1] = (Integer) rowData[1];
						break;
					case Constants.GIR_NO_GIR:
						returnData[2] = (Integer) rowData[1];
						break;
					}
				}
				return returnData;
			}
		});
		return girValues;
	}

	/**
	 * Returns an array of size 3; subscript [0] is the total number of
	 * left-rough tee shots; [1] is the total number of fairway-hit tee shots;
	 * [2] is the total number of right-rough tee shots
	 * 
	 * @param aPlayer
	 * @return
	 */
	public int[] getTeeShotAccuracyValues(final Date aFromDate,
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final PlayerDAO aPlayer) {

		int accuracyValues[];
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		accuracyValues = (int[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				List results;
				String queryText;
				Query query;
				Player player;
				int accuracyId, numRecords, loop;
				Object rowData[];
				int returnData[];

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT score.teeShotAccuracy.id, "
						+ "count(score.teeShotAccuracy.id) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY score.teeShotAccuracy.id ";
				queryText += "HAVING score.teeShotAccuracy.id is not null";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				if (aFromDate != null)
					query.setDate("fromDate", aFromDate);
				if (aToDate != null)
					query.setDate("toDate", aToDate);
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				results = query.list();
				numRecords = results.size();
				returnData = new int[3];
				for (loop = 0; loop < numRecords; loop++) {
					rowData = (Object[]) results.get(loop);
					accuracyId = (Integer) rowData[0];
					switch (accuracyId) {
					case Constants.LEFT_ROUGH_ID:
						returnData[0] = (Integer) rowData[1];
						break;
					case Constants.FAIRWAY_HIT_ID:
						returnData[1] = (Integer) rowData[1];
						break;
					case Constants.RIGHT_ROUGH_ID:
						returnData[2] = (Integer) rowData[1];
						break;
					}
				}
				return returnData;
			}
		});
		return accuracyValues;
	}

	/**
	 * Returns an array of size 4; subscript [0] is the total number of 1-putts
	 * made; [1] is the total number of 2-putts; [2] is the total number of
	 * 3-putts; [3] is the total number of 4-putts and worse
	 * 
	 * @param aPlayer
	 * @return
	 */
	public int[] getNumPutts(final Date aFromDate, final Date aToDate,
			final CircumstancesBean aCircumstances, final PlayerDAO aPlayer, 
			final boolean aIsGIR) {

		int numPutts[];
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		numPutts = (int[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				List results;
				String queryText;
				Query query;
				Player player;
				int numPutts, numRecords, loop;
				Object rowData[];
				int returnData[];

				player = (Player) aPlayer.getDelegate();
				queryText = "SELECT score.numPutts, count(score.numPutts) "
						+ "FROM Score score, Scorecard sc "
						+ "WHERE score.id.scorecardId = sc.id AND "
						+ "sc.player.id = :playerId ";
				if (aFromDate != null) {
					queryText += "AND sc.date >= :fromDate ";
				}
				if (aToDate != null) {
					queryText += "AND sc.date <= :toDate ";
				}
				if (aIsGIR) {
					queryText += "AND (score.greenInRegulation.id = " +
							":GIRLess15 OR score.greenInRegulation.id = " +
							":GIRGreater15) ";
				}
				queryText += getCircumstanceWhereClauseAddition(aCircumstances);
				queryText += "GROUP BY score.numPutts "
						+ "HAVING (score.numPutts = 1 or score.numPutts = 2 or "
						+ "score.numPutts = 3 or score.numPutts >= 4)";
				hqllogger.info(queryText);
				query = aSession.createQuery(queryText);
				query.setInteger("playerId", player.getId());
				if (aFromDate != null) {
					query.setDate("fromDate", aFromDate);
				}
				if (aToDate != null) {
					query.setDate("toDate", aToDate);
				}
				if (aIsGIR) {
					query.setInteger("GIRLess15", 
							Constants.GIR_LESS_THAN_15_FEET);
					query.setInteger("GIRGreater15", 
							Constants.GIR_GREATER_THAN_15_FEET);
				}
				updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				results = query.list();
				numRecords = results.size();
				returnData = new int[4];
				for (loop = 0; loop < numRecords; loop++) {
					rowData = (Object[]) results.get(loop);
					numPutts = (Integer) rowData[0];
					if (numPutts == 1) {
						returnData[0] = (Integer) rowData[1];
					} else if (numPutts == 2) {
						returnData[1] = (Integer) rowData[1];
					} else if (numPutts == 3) {
						returnData[2] = (Integer) rowData[1];
					} else if (numPutts >= 4) {
						returnData[3] += (Integer) rowData[1];
					}
				}
				return returnData;
			}
		});
		return numPutts;
	}

	/**
	 * Returns the tournament reduction value based on the inputs
	 * 
	 * @param aNumTournamentScores
	 * @param aAvgTScoreDifferential
	 * @return
	 */
	public BigDecimal getTournamentScoreTableValue(
			final int aNumTournamentScores,
			final BigDecimal aAvgTScoreDifferential) {

		HibernateTemplate ht;
		BigDecimal value;

		ht = new HibernateTemplate(getSessionFactory());
		value = (BigDecimal) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				BigDecimal value;
				Connection connection;
				PreparedStatement stmt;
				ResultSet rs;
				String query;
				int numScores;
				BigDecimal avgDiff;

				rs = null;
				stmt = null;
				connection = null;
				value = null;
				try {
					connection = aSession.connection();
					numScores = aNumTournamentScores > 40 ? 40
							: aNumTournamentScores;
					avgDiff = aAvgTScoreDifferential.floatValue() > 14 ? BigDecimal
							.valueOf(14)
							: aAvgTScoreDifferential;
					query = "SELECT value FROM tournament_reduction_values "
							+ "WHERE ? >= from_num_tournament_scores AND "
							+ "? <= to_num_tournament_scores AND "
							+ "? >= from_differential_average AND "
							+ "? <= to_differential_average";
					stmt = connection.prepareStatement(query);
					stmt.setInt(1, numScores);
					stmt.setInt(2, numScores);
					stmt.setBigDecimal(3, avgDiff);
					stmt.setBigDecimal(4, avgDiff);
					rs = stmt.executeQuery();
					while (rs.next()) {
						value = rs.getBigDecimal(1);
					}
					if (value != null) {
						value = value.setScale(1);
					}
					return value;
				} catch (SQLException e) {
					throw new JDBCException(e.getMessage(), e);
				} finally {
					DbUtils.closeQuietly(connection, stmt, rs);
				}
			}
		});
		return value;
	}

	/**
	 * Returns an array of unique email addresses stored in the player table
	 * @return
	 */
	public String[] getAllUniqueEmailAddresses() {
		
		HibernateTemplate ht;
		SessionFactory sessionFactory;

		sessionFactory = getSessionFactory();
		ht = new HibernateTemplate(sessionFactory);
		return (String[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				SQLQuery query;
				List results;

				query = aSession.createSQLQuery("select distinct p.email_address from player p");
				results = query.list();
				return results.toArray(new String[results.size()]);
			}
		});
	}
	
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
	@SuppressWarnings( { "unchecked" })
	public PlayerDAO[] searchPlayers(final String aLastName,
			final String aFirstName, final String aPostalCode,
			final int aFirstResultNum, final int aMaxResultsNum) {

		HibernateTemplate ht;
		SessionFactory sessionFactory;
		PlayerDAO results[];

		sessionFactory = getSessionFactory();
		ht = new HibernateTemplate(sessionFactory);
		results = (PlayerDAO[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				List<PlayerDAO> players;
				SQLQuery query;
				List searchResults;
				int loop, numResults;
				Object rowData[];
				StringBuffer sb;

				sb = new StringBuffer("select p.id, p.lastname, p.firstname, ");
				sb.append("count(s.id) as numRounds, p.date_of_last_login, ");
				sb.append("p.date_created, p.last_update_date, p.postal_code, ");
				sb.append("p.user_id ");
				sb.append(getSearchPlayersFromAndWhereClause(aLastName, 
						aFirstName, aPostalCode));
				query = aSession.createSQLQuery(sb.toString());
				query.setFirstResult(aFirstResultNum);
				query.setMaxResults(aMaxResultsNum);
				//query.addScalar("numRounds", new IntegerType());
				searchResults = query.list();
				numResults = searchResults.size();
				players = new ArrayList<PlayerDAO>();
				for (loop = 0; loop < numResults; loop++) {
					rowData = (Object[]) searchResults.get(loop);
					players.add(new HibernatePlayerDAO((Integer) rowData[0],
							(String) rowData[1], (String) rowData[2],
							((BigInteger) rowData[3]).intValue(), (Date) rowData[4],
							(Date) rowData[5], (Date) rowData[6],
							(String)rowData[7], (String)rowData[8]));
				}
				return players.toArray(new PlayerDAO[players.size()]);
			}
		});
		return results;
	}

	/**
	 * Returns the 'from' and 'where' clause for the search-players query
	 * 
	 * @param aLastName
	 * @param aFirstName
	 * @param aPostalCode
	 * @return
	 */
	private static final String getSearchPlayersFromAndWhereClause(
			String aLastName, String aFirstName, String aPostalCode) {

		StringBuffer sb;

		sb = new StringBuffer("FROM player p ");
		sb.append("LEFT JOIN scorecard s ");
		sb.append("ON p.id = s.player_id ");
		sb.append("GROUP BY p.id ");
		sb.append("HAVING ");
		sb.append(getSearchPlayersWhereClause(aLastName, aFirstName, 
				aPostalCode));
		sb.append("ORDER BY p.lastname");
		return sb.toString();
	}
	
	/**
	 * Returns the 'where' clause for the search-players query
	 * 
	 * @param aLastName
	 * @param aFirstName
	 * @param aPostalCode
	 * @return
	 */
	private static final String getSearchPlayersWhereClause(
			String aLastName, String aFirstName, String aPostalCode) {

		StringBuffer sb;

		sb = new StringBuffer();
		sb.append("p.lastname LIKE '%").append(aLastName).append("%' ");
		sb.append("AND p.firstname LIKE '%").append(aFirstName).append("%' ");
		sb.append("AND p.postal_code LIKE '%").append(aPostalCode).append("%' ");
		return sb.toString();
	}


	/**
	 * Returns the total result count of the search-players query
	 * @param aLastName
	 * @param aFirstName
	 * @param aPostalCode
	 * @return
	 */
	public int searchPlayersCount(final String aLastName,
			final String aFirstName, final String aPostalCode) {
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		return (Integer) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				StringBuffer sb;
				SQLQuery query;

				sb = new StringBuffer();
				sb.append("SELECT count(p.id) as value FROM player p WHERE ");
				sb.append(getSearchPlayersWhereClause(aLastName, 
						aFirstName, aPostalCode));
				query = aSession.createSQLQuery(sb.toString());
				query.addScalar("value", new IntegerType());
				return query.uniqueResult();
			}
		});
	}	
	
	/**
	 * Searches the data source for the set of courses that match the search
	 * criteria
	 * 
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public CourseDAO[] searchCourses(final String aCity, final int aCountryId,
			final int aStateProvinceId, final String aCourseName,
			final int aFirstResultNum, final int aMaxResultsNum) {

		HibernateTemplate ht;
		SessionFactory sessionFactory;
		CourseDAO results[];

		sessionFactory = getSessionFactory();
		ht = new HibernateTemplate(sessionFactory);
		results = (CourseDAO[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				List<CourseDAO> courses;
				Query query;
				List searchResults;
				int loop, numResults;
				Object rowData[];
				StringBuffer sb;

				sb = new StringBuffer("SELECT DISTINCT ");
				sb.append("course.id, course.description, country.name, ");
				sb.append("stateProv.name, course.city, course.description, ");
				sb.append("course.associationName ");
				sb.append(getSearchCoursesFromAndWhereClause(aCity, 
						aCourseName));
				query = aSession.createQuery(sb.toString());
				query.setInteger("stateProvId", aStateProvinceId);
				query.setInteger("countryId", aCountryId);
				query.setFirstResult(aFirstResultNum);
				query.setMaxResults(aMaxResultsNum);
				searchResults = query.list();
				numResults = searchResults.size();
				courses = new ArrayList<CourseDAO>();
				for (loop = 0; loop < numResults; loop++) {
					rowData = (Object[]) searchResults.get(loop);
					courses.add(new HibernateCourseDAO((Integer) rowData[0],
							(String) rowData[1], (String) rowData[2],
							(String) rowData[3], (String) rowData[4],
							(String) rowData[5], (String) rowData[6]));
				}
				return courses.toArray(new CourseDAO[courses.size()]);
			}
		});
		return results;
	}

	/**
	 * Returns the 'from' and 'where' clause for the search-courses query
	 * 
	 * @param aCity
	 * @param aCourseName
	 * @return
	 */
	private static final String getSearchCoursesFromAndWhereClause(
			String aCity, String aCourseName) {

		StringBuffer sb;

		sb = new StringBuffer("FROM Course course, Country country, ");
		sb.append("StateProvince stateProv ");
		sb.append("WHERE course.stateProvince.id.id = :stateProvId AND ");
		sb.append("upper(course.city) like '%").append(aCity.toUpperCase())
				.append("%' ");
		sb.append("AND course.stateProvince.id.countryId = country.id ");
		sb.append("AND course.stateProvince.country.id = country.id ");
		sb.append("AND course.stateProvince.id.id = stateProv.id.id ");
		sb.append("AND course.stateProvince.country.id = stateProv.country.id ");
		sb.append("AND course.stateProvince.country.id = :countryId ");
		sb.append("AND upper(course.description) LIKE '%").append(
				aCourseName.toUpperCase());
		sb.append("%' ORDER BY course.description");
		return sb.toString();
	}

	/**
	 * Returns the total result count of the search-courses query
	 * 
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	public int searchCoursesCount(final String aCity, final int aCountryId,
			final int aStateProvinceId, final String aCourseName) {
		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		return (Integer) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				StringBuffer sb;
				Query query;

				sb = new StringBuffer();
				sb.append("SELECT count(course.id) ");
				sb.append(getSearchCoursesFromAndWhereClause(aCity, 
						aCourseName));
				query = aSession.createQuery(sb.toString());
				query.setInteger("stateProvId", aStateProvinceId);
				query.setInteger("countryId", aCountryId);
				return query.uniqueResult();
			}
		});
	}

	/**
	 * Searches the data source for the set of rounds that match the search
	 * criteria
	 * 
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	public ScorecardSummaryDAO[] searchRounds(final String aCity,
			final int aCountryId, final int aStateProvinceId,
			final String aCourseName, final String aDateFilter,
			final int aPlayerId, final int aFirstResultNum,
			final int aMaxResultsNum) {

		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		return (ScorecardSummaryDAO[]) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				List<ScorecardSummaryDAO> rounds;
				List searchResults;
				Object rowData[];
				StringBuffer sb;
				Query query;
				int numResults, loop;

				sb = new StringBuffer();
				sb.append("SELECT DISTINCT ");
				sb.append("sc.id, course.id, course.description, ");
				sb.append("country.name, stateProv.name, course.city, ");
				sb.append("sc.date, sum.score, sum.numHolesPlayed, ");
				sb.append("sum.scoreType ");
				sb.append(getSearchRoundsFromAndWhereClause(aCity, aCountryId,
						aStateProvinceId, aCourseName, aDateFilter, aPlayerId));
				query = aSession.createQuery(sb.toString());
				query.setInteger("playerId", aPlayerId);
				if (aCountryId != Constants.ANY_OPTION_VAL) {
					query.setInteger("countryId", aCountryId);
				}
				if (aStateProvinceId != Constants.ANY_OPTION_VAL) {
					query.setInteger("stateProvId", aStateProvinceId);
				}
				if (StringUtils.isNotBlank(aDateFilter)) {
					query.setString("datePlayed", aDateFilter);
				}
				query.setFirstResult(aFirstResultNum);
				query.setMaxResults(aMaxResultsNum);
				searchResults = query.list();
				numResults = searchResults.size();
				rounds = new ArrayList<ScorecardSummaryDAO>();
				for (loop = 0; loop < numResults; loop++) {
					rowData = (Object[]) searchResults.get(loop);
					rounds.add(new HibernateScorecardSummaryDAO(
							(Integer) rowData[0], (Integer) rowData[1],
							(String) rowData[2], (String) rowData[3],
							(String) rowData[4], (String) rowData[5],
							(Date) rowData[6], (Integer) rowData[7],
							(Integer) rowData[8], (String) rowData[9], null,
							null, null, null));
				}
				return rounds.toArray(new ScorecardSummaryDAO[rounds.size()]);
			}
		});
	}

	/**
	 * Returns the search-rounds 'from' and 'where' clauses
	 * 
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	private static final String getSearchRoundsFromAndWhereClause(String aCity,
			int aCountryId, final int aStateProvinceId, String aCourseName,
			String aDateFilter, int aPlayerId) {

		StringBuffer sb;

		sb = new StringBuffer();
		sb.append("FROM Course course, ");
		sb.append("Country country, StateProvince stateProv, ");
		sb.append("Scorecard sc, ScorecardSummary sum WHERE ");
		sb.append("course.id = sc.tee.course.id AND sc.id = ");
		sb.append("sum.scorecard.id AND ");
		sb.append("stateProv.id.id = course.stateProvince.id.id AND ");
		sb.append("stateProv.country.id = country.id AND ");
		sb.append("country.id = course.stateProvince.country.id AND ");
		sb.append("sc.player.id = :playerId ");
		if (aCountryId != Constants.ANY_OPTION_VAL) {
			sb.append("AND country.id = :countryId ");
		}
		if (aStateProvinceId != Constants.ANY_OPTION_VAL) {
			sb.append("AND stateProv.id.id = :stateProvId ");
		}
		if (StringUtils.isNotBlank(aCourseName)) {
			sb.append("AND upper(course.description) LIKE '%");
			sb.append(aCourseName.toUpperCase()).append("%' ");
		}
		if (StringUtils.isNotBlank(aCity)) {
			sb.append("AND upper(course.city) LIKE '%").append(
					aCity.toUpperCase());
			sb.append("%' ");
		}
		if (StringUtils.isNotBlank(aDateFilter)) {
			sb.append("AND sc.date >= STR_TO_DATE(:datePlayed, ");
			sb.append("'%m/%d/%Y') ");
		}
		sb.append("ORDER BY sc.date desc, course.description asc, ");
		sb.append("sum.numHolesPlayed desc");
		return sb.toString();
	}

	/**
	 * Returns the row-count of the search rounds where-clause
	 * 
	 * @param aCity
	 * @param aCountryId
	 * @param aStateProvinceId
	 * @param aCourseName
	 * @return
	 */
	public int searchRoundsCount(final String aCity, final int aCountryId,
			final int aStateProvinceId, final String aCourseName,
			final String aDateFilter, final int aPlayerId) {

		HibernateTemplate ht;

		ht = new HibernateTemplate(getSessionFactory());
		return (Integer) ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {

				StringBuffer sb;
				Query query;

				sb = new StringBuffer();
				sb.append("SELECT count(sc.id) ");
				sb.append(getSearchRoundsFromAndWhereClause(aCity, aCountryId,
						aStateProvinceId, aCourseName, aDateFilter, aPlayerId));
				query = aSession.createQuery(sb.toString());
				query.setInteger("playerId", aPlayerId);
				if (aCountryId != Constants.ANY_OPTION_VAL) {
					query.setInteger("countryId", aCountryId);
				}
				if (aStateProvinceId != Constants.ANY_OPTION_VAL) {
					query.setInteger("stateProvId", aStateProvinceId);
				}
				if (StringUtils.isNotBlank(aDateFilter)) {
					query.setString("datePlayed", aDateFilter);
				}
				return (Integer) query.uniqueResult();
			}
		});
	}
	
	/**
	 * Deletes aOldScorecard and saves aNewScorecard
	 */
	public void deleteAndSave(final ScorecardDAO aOldScorecard,
			final ScorecardDAO aNewScorecard,
			final ScorecardSummaryDAO aNewSummary, final PlayerDAO aPlayer,
			final Integer aStartingHoles[]) {
		deleteAndSave(aOldScorecard, aNewScorecard, null, aNewSummary,
				aPlayer, aStartingHoles);
	}

	/**
	 * Deletes aOldScorecard and saves aNewScorecard
	 */
	public void deleteAndSave(final ScorecardDAO aOldScorecard,
			final ScorecardDAO aNewScorecard, final ScoreDAO aNewScores[],
			final ScorecardSummaryDAO aNewSummary, final PlayerDAO aPlayer,
			final Integer aStartingHoles[]) {

		TransactionTemplate txTemplate;

		txTemplate = new TransactionTemplate(getTransactionManager());
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus aStatus) {

				ScorecardSummaryDAO tmpSummary;
				Set<ScoreDAO> tmpScores;
				Date createDate;

				// delete the old scores, scorecard and summary...
				tmpSummary = BeanFactory.newScorecardSummaryDAO();
				tmpSummary.setScorecardId((Integer) aOldScorecard.getId());
				// Load first so then we can delete it. In order to delete
				// successfully (w/out getting an error), all the non-null
				// fields of the entity must be set; so this is why we load it
				// before deleting it...
				tmpSummary.load();
				tmpSummary.delete(); // delete the summary...
				tmpScores = aOldScorecard.getScores();
				for (ScoreDAO score : tmpScores) {
					score.delete(); // delete the scores...
				}
				createDate = aOldScorecard.getDateCreated();
				aOldScorecard.delete(); // delete the scorecard...

				// save the new scorecard and scores...
				saveScorecard(aNewScorecard, aNewScores, aNewSummary, aPlayer,
						aStartingHoles, createDate);
			}
		});
	}

	/**
	 * Converts a list of Hibernate 'Country' objects to an array of CountryDAO
	 * objects.
	 * 
	 * @param aCountries
	 * @return
	 */
	private final static CountryDAO[] convert(List<Country> aCountries) {

		List<CountryDAO> countryDAOs;

		countryDAOs = new ArrayList<CountryDAO>();
		for (Country country : aCountries) {
			countryDAOs.add(new HibernateCountryDAO(country));
		}
		return (CountryDAO[]) countryDAOs.toArray(new CountryDAO[countryDAOs
				.size()]);
	}

	/**
	 * Converts a list of Hibernate 'CalendarMonth' objects to an array of
	 * CalendarMonthDAO objects.
	 * 
	 * @param aMonths
	 * @return
	 */
	private final static CalendarMonthDAO[] convert(List<CalendarMonth> aMonths) {

		List<CalendarMonthDAO> monthDAOs;

		monthDAOs = new ArrayList<CalendarMonthDAO>();
		for (CalendarMonth month : aMonths) {
			monthDAOs.add(new HibernateCalendarMonthDAO(month));
		}
		return (CalendarMonthDAO[]) monthDAOs
				.toArray(new CalendarMonthDAO[monthDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'EyeWear' objects to an array of EyeWearDAO
	 * objects.
	 * 
	 * @param aPantWearTypes
	 * @return
	 */
	private final static EyeWearDAO[] convert(List<EyeWear> aEyeWearTypes) {

		List<EyeWearDAO> eyeWearDAOs;

		eyeWearDAOs = new ArrayList<EyeWearDAO>();
		for (EyeWear eyeWear : aEyeWearTypes) {
			eyeWearDAOs.add(new HibernateEyeWearDAO(eyeWear));
		}
		return (EyeWearDAO[]) eyeWearDAOs.toArray(new EyeWearDAO[eyeWearDAOs
				.size()]);
	}

	/**
	 * Converts a list of Hibernate 'PantWear' objects to an array of
	 * PantWearDAO objects.
	 * 
	 * @param aPantWearTypes
	 * @return
	 */
	private final static PantWearDAO[] convert(List<PantWear> aPantWearTypes) {

		List<PantWearDAO> pantWearDAOs;

		pantWearDAOs = new ArrayList<PantWearDAO>();
		for (PantWear pantWear : aPantWearTypes) {
			pantWearDAOs.add(new HibernatePantWearDAO(pantWear));
		}
		return (PantWearDAO[]) pantWearDAOs
				.toArray(new PantWearDAO[pantWearDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'HeadWear' objects to an array of
	 * HeadWearDAO objects.
	 * 
	 * @param aHeadWearTypes
	 * @return
	 */
	private final static HeadWearDAO[] convert(List<HeadWear> aHeadWearTypes) {

		List<HeadWearDAO> headWearDAOs;

		headWearDAOs = new ArrayList<HeadWearDAO>();
		for (HeadWear headWear : aHeadWearTypes) {
			headWearDAOs.add(new HibernateHeadWearDAO(headWear));
		}
		return (HeadWearDAO[]) headWearDAOs
				.toArray(new HeadWearDAO[headWearDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'WeatherCondition' objects to an array of
	 * WeatherConditionDAO objects.
	 * 
	 * @param aWeatherConditionTypes
	 * @return
	 */
	private final static WeatherConditionDAO[] convert(
			List<WeatherCondition> aWeatherConditionTypes) {

		List<WeatherConditionDAO> weatherConditionDAOs;

		weatherConditionDAOs = new ArrayList<WeatherConditionDAO>();
		for (WeatherCondition weatherCondition : aWeatherConditionTypes) {
			weatherConditionDAOs.add(new HibernateWeatherConditionDAO(
					weatherCondition));
		}
		return (WeatherConditionDAO[]) weatherConditionDAOs
				.toArray(new WeatherConditionDAO[weatherConditionDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'ApproachShotLine' objects to an array of
	 * ApproachShotLineDAO objects.
	 * 
	 * @param aApproachShotLineTypes
	 * @return
	 */
	private final static ApproachShotLineDAO[] convert(
			List<ApproachShotLine> aApproachShotLineTypes) {

		List<ApproachShotLineDAO> approachShotLineDAOs;

		approachShotLineDAOs = new ArrayList<ApproachShotLineDAO>();
		for (ApproachShotLine approachShotLine : aApproachShotLineTypes) {
			approachShotLineDAOs.add(new HibernateApproachShotLineDAO(
					approachShotLine));
		}
		return (ApproachShotLineDAO[]) approachShotLineDAOs
				.toArray(new ApproachShotLineDAO[approachShotLineDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'ApproachShotDistance' objects to an array
	 * of ApproachShotDistanceDAO objects.
	 * 
	 * @param aApproachShotDistanceTypes
	 * @return
	 */
	private final static ApproachShotDistanceDAO[] convert(
			List<ApproachShotDistance> aApproachShotDistanceTypes) {

		List<ApproachShotDistanceDAO> approachShotDistanceDAOs;

		approachShotDistanceDAOs = new ArrayList<ApproachShotDistanceDAO>();
		for (ApproachShotDistance approachShotDistance : aApproachShotDistanceTypes) {
			approachShotDistanceDAOs.add(new HibernateApproachShotDistanceDAO(
					approachShotDistance));
		}
		return (ApproachShotDistanceDAO[]) approachShotDistanceDAOs
				.toArray(new ApproachShotDistanceDAO[approachShotDistanceDAOs
						.size()]);
	}

	/**
	 * Converts a list of Hibernate 'TeeShotAccuracy' objects to an array of
	 * TeeShotAccuracyDAO objects.
	 * 
	 * @param aTeeShotAccuracyTypes
	 * @return
	 */
	private final static TeeShotAccuracyDAO[] convert(
			List<TeeShotAccuracy> aTeeShotAccuracyTypes) {

		List<TeeShotAccuracyDAO> teeShotAccuracyDAOs;

		teeShotAccuracyDAOs = new ArrayList<TeeShotAccuracyDAO>();
		for (TeeShotAccuracy teeShotAccuracy : aTeeShotAccuracyTypes) {
			teeShotAccuracyDAOs.add(new HibernateTeeShotAccuracyDAO(
					teeShotAccuracy));
		}
		return (TeeShotAccuracyDAO[]) teeShotAccuracyDAOs
				.toArray(new TeeShotAccuracyDAO[teeShotAccuracyDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'GolfClub' objects to an array of
	 * GolfClubDAO objects.
	 * 
	 * @param aGolfClubTypes
	 * @return
	 */
	private final static GolfClubDAO[] convert(List<GolfClub> aGolfClubTypes) {

		List<GolfClubDAO> golfClubDAOs;

		golfClubDAOs = new ArrayList<GolfClubDAO>();
		for (GolfClub golfClub : aGolfClubTypes) {
			golfClubDAOs.add(new HibernateGolfClubDAO(golfClub));
		}
		return (GolfClubDAO[]) golfClubDAOs
				.toArray(new GolfClubDAO[golfClubDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'GreenInRegulation' objects to an array of
	 * GreenInRegulationDAO objects.
	 * 
	 * @param aGreenInRegulationTypes
	 * @return
	 */
	private final static GreenInRegulationDAO[] convert(
			List<GreenInRegulation> aGreenInRegulationTypes) {

		List<GreenInRegulationDAO> greenInRegulationDAOs;

		greenInRegulationDAOs = new ArrayList<GreenInRegulationDAO>();
		for (GreenInRegulation gir : aGreenInRegulationTypes) {
			greenInRegulationDAOs.add(new HibernateGreenInRegulationDAO(gir));
		}
		return (GreenInRegulationDAO[]) greenInRegulationDAOs
				.toArray(new GreenInRegulationDAO[greenInRegulationDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'Tee' objects to an array of TeeDAO objects.
	 * 
	 * @param aTees
	 * @return
	 */
	private final static TeeDAO[] convert(Tee aTees[]) {

		List<TeeDAO> teeDAOs;

		teeDAOs = new ArrayList<TeeDAO>();
		for (Tee tee : aTees) {
			teeDAOs.add(new HibernateTeeDAO(tee));
		}
		return (TeeDAO[]) teeDAOs.toArray(new TeeDAO[teeDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'Course' objects to an array of CourseDAO
	 * objects.
	 * 
	 * @param aCourses
	 * @return
	 */
	private final static CourseDAO[] convert(List<Course> aCourses) {

		List<CourseDAO> courseDAOs;

		courseDAOs = new ArrayList<CourseDAO>();
		for (Course course : aCourses) {
			courseDAOs.add(new HibernateCourseDAO(course));
		}
		return (CourseDAO[]) courseDAOs
				.toArray(new CourseDAO[courseDAOs.size()]);
	}

	/**
	 * Converts a list of Hibernate 'TeeName' objects to an array of TeeNameDAO
	 * objects.
	 * 
	 * @param aTeeNames
	 * @return
	 */
	private final static TeeNameDAO[] convert(List<TeeName> aTeeNames) {

		List<TeeNameDAO> teeNameDAOs;

		teeNameDAOs = new ArrayList<TeeNameDAO>();
		for (TeeName teeName : aTeeNames) {
			teeNameDAOs.add(new HibernateTeeNameDAO(teeName));
		}
		return (TeeNameDAO[]) teeNameDAOs.toArray(new TeeNameDAO[teeNameDAOs
				.size()]);
	}

	/**
	 * Converts a list of Hibernate 'StateProvince' objects to an array of
	 * StateProvinceDAO objects.
	 * 
	 * @param aStateProvinces
	 * @return
	 */
	private final static StateProvinceDAO[] convert(
			List<StateProvince> aStateProvinces) {

		List<StateProvinceDAO> stateProvinceDAOs;

		stateProvinceDAOs = new ArrayList<StateProvinceDAO>();
		for (StateProvince stateProvince : aStateProvinces) {
			stateProvinceDAOs.add(new HibernateStateProvinceDAO(stateProvince));
		}
		return (StateProvinceDAO[]) stateProvinceDAOs
				.toArray(new StateProvinceDAO[stateProvinceDAOs.size()]);
	}
}
