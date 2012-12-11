package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.CalendarMonth;

import java.util.Locale;

import name.paulevans.golf.dao.CalendarMonthDAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate calendarMonth dao
 * @author Paul
 *
 */
public class HibernateCalendarMonthDAO extends HibernateDAOAdapter 
implements CalendarMonthDAO {

	// delegate object - generated Hibernate type...
	private CalendarMonth calendarMonth;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateCalendarMonthDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aCountryDelegate
	 */
	public HibernateCalendarMonthDAO(CalendarMonth aCountryDelegate) {
		calendarMonth = aCountryDelegate;
		if (calendarMonth == null) {
			calendarMonth = new CalendarMonth();
		}
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aCountryDelegate) {
		calendarMonth = (CalendarMonth)aCountryDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Object getDelegate() {
		return calendarMonth;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return calendarMonth.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		calendarMonth.setId((Integer)aId);
	}
	
	/**
	 * Get the name
	 * @return
	 */
	public String getName() {
		return calendarMonth.getName();
	}
	
	/**
	 * Returns the month 'sequence' number
	 * @return
	 */
	public int getMonthNumber() {
		return calendarMonth.getMonthNumber();
	}
	
	/**
	 * Sets the month number
	 * @param aMonthNumber
	 */
	public void setMonthNumber(int aMonthNumber) {
		calendarMonth.setMonthNumber(aMonthNumber);
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load(final Locale aLocale) throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());	
		calendarMonth = (CalendarMonth)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
					
				Query query;
				
				query = aSession.createQuery(
					"from CalendarMonth as cm where cm.locale.id in (select " +
					"loc.id from Locale as loc where loc.name = ?) and " +
					"cm.monthNumber = ?");
				query.setString(0, aLocale.getISO3Language());
				query.setInteger(1, calendarMonth.getMonthNumber());
				return query.uniqueResult();
			}
		});
		ht.initialize(calendarMonth);	
	}
}
