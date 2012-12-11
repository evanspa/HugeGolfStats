package name.paulevans.golf.dao;




/**
 * Models a calendar
 * @author Paul
 *
 */
public interface CalendarMonthDAO extends DAO {

	/**
	 * Get the calendar name
	 * @return
	 */
	String getName();
	
	/**
	 * Returns the month 'sequence' number
	 * @return
	 */
	int getMonthNumber();
	
	/**
	 * Sets the month number
	 * @param aMonthNumber
	 */
	void setMonthNumber(int aMonthNumber);
}
