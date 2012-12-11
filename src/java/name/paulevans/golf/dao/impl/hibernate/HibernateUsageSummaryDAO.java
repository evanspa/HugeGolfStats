package name.paulevans.golf.dao.impl.hibernate;

import java.util.Date;

import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.UsageSummaryDAO;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.DbTimestampType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate usage summary dao
 * @author Paul
 *
 */
public class HibernateUsageSummaryDAO extends HibernateDAOAdapter 
implements UsageSummaryDAO {
	
	private int totalUserCount;
	private int totalRoundCount;
	private int totalCoursesCount;
	private int lowest18HoleScore;
	private int highest18HoleScore;
	private int average18HoleScore;
	private Date lastLoginDate;
	private Date lastScoreEnteredDate;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateUsageSummaryDAO() {
		// does nothing...
	}
	
	/**
	 * Get the ID
	 */
	public Integer getId() {
		return null;
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		// does nothing...
	}

	/**
	 * Get the description
	 * @return
	 */
	public int getTotalUserCount() {
		return totalUserCount;
	}
	
	/**
	 * Returns the total number of rounds entered into the system
	 * @return
	 */
	public int getTotalNumRounds() {
		return totalRoundCount;
	}
	
	/**
	 * Returns the total number of courses that exist within the system
	 * @return
	 */
	public int getTotalNumCourses() {
		return totalCoursesCount;
	}
	
	/**
	 * Returns the average 18-hole score entered into the system
	 * @return
	 */
	public float getAverageEighteenHoleScore() {
		return average18HoleScore;
	}
	
	/**
	 * Returns the highest 18-hole score entered into the system
	 * @return
	 */
	public int getHighestEighteenHoleScore() {
		return highest18HoleScore;
	}
	
	/**
	 * Returns the lowest 18-hole score entered into the system
	 * @return
	 */
	public int getLowestEighteenHoleScore() {
		return lowest18HoleScore;
	}
	
	/**
	 * Returns the date of the last score entered
	 * @return
	 */
	public Date getDateOfLastScoreEntered() {
		return lastScoreEnteredDate;
	}
	
	/**
	 * Returns the date and time of the last user-login
	 * @return
	 */
	public Date getDateOfLastLogin() {
		return lastLoginDate;
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		totalUserCount = getIntegerValue("select count(id) as value from " +
				"player");
		totalRoundCount = getIntegerValue(
				"select count(id) as value from scorecard");
		totalCoursesCount = getIntegerValue(
				"select count(id) as value from course");
		highest18HoleScore = getIntegerValue(
				"select max(score) as value from scorecard_summary where " +
				"num_holes_played = 18");
		average18HoleScore = getIntegerValue(
				"select avg(score) as value from scorecard_summary where " +
				"num_holes_played = 18");
		lowest18HoleScore = getIntegerValue(
				"select min(score) as value from scorecard_summary where " +
				"num_holes_played = 18");
		lastLoginDate = getDateValue(
				"select max(date_of_last_login) as value from player " +
				"where user_id != '" + Constants.EVANSPA_USERID + "'");
		lastScoreEnteredDate = getDateValue(
				"select max(date_created) as value from scorecard");
	}
	
	/**
	 * Convience method for returning a single value from the database
	 * @throws DataAccessException
	 */
	private Object getValue(final String aQuery, final Type aType) 
	throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		return ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession)
					throws HibernateException {
				
				SQLQuery query;
				
				query = aSession.createSQLQuery(aQuery);
				query.addScalar("value", aType);
				return query.uniqueResult();
			}
		});	
	}
	
	/**
	 * Convienence method for obtaining a date value from the database
	 * @param aQuery
	 * @return
	 * @throws DataAccessException
	 */
	private Date getDateValue(final String aQuery) 
	throws DataAccessException {
		
		Object value;
		
		value = getValue(aQuery, new DbTimestampType());
		if (value != null) {
			return (Date)value;
		}
		return null;
	}
	
	/**
	 * Convienence method for obtaining an integer value from the database
	 * @param aQuery
	 * @return
	 * @throws DataAccessException
	 */
	private int getIntegerValue(final String aQuery) 
	throws DataAccessException {
		
		Object value;
		
		value = getValue(aQuery, new IntegerType());
		if (value != null) {
			return (Integer)value;
		}
		return 0;
	}
}
