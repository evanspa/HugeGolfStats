package name.paulevans.golf.dao;

import java.io.Serializable;

/**
 * Models a composite-key used by the CalendarMonthDAO
 * @author Paul
 *
 */
public class CalendarMonthId implements Serializable {
	
	private int id;
	private int localeId;
	
	/**
	 * Constructor
	 * @param aId
	 * @param aLocalId
	 */
	public CalendarMonthId(int aId, int aLocalId) {
		id = aId;
		localeId = aLocalId;
	}
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public CalendarMonthId() {
		// does nothing...
	}
	
	/**
	 * Get the month ID
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the month ID
	 * @param aMonthId
	 */
	public void setId(int aMonthId) {
		id = aMonthId;
	}
	
	/**
	 * Get the locale ID
	 * @return
	 */
	public int getLocaleId() {
		return localeId;
	}

	/**
	 * Set the locale ID
	 * @param aLocaleId
	 */
	public void setLocaleId(int aLocaleId) {
		localeId = aLocaleId;
	}
}
