package name.paulevans.golf.dao;

import java.util.Locale;

import org.springframework.dao.DataAccessException;

/**
 * Generic DAO-interface
 * @author Paul
 *
 */
public interface DAO extends Identifiable {
	
	/**
	 * Loads an object from the data source
	 *
	 */
	void load() throws DataAccessException;
	
	/**
	 * Loads an object from the data source 
	 *
	 */
	void load(Locale aLocale) throws DataAccessException;
	
	/**
	 * Persists an object to the data source
	 *
	 */
	void save() throws DataAccessException;
	
	/**
	 * Deletes an object from the data source
	 *
	 */
	void delete() throws DataAccessException;
	
	/**
	 * Sets the delegate object (used for dependency-injection)
	 * @param aDelegate
	 */
	void setDelegate(Object aDelegate);
	
	/**
	 * Gets the delegate object (may or may not be used by the 
	 * DAO-implementation)
	 * @return
	 */
	Object getDelegate();
}
