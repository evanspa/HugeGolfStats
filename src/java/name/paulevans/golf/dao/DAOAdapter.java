package name.paulevans.golf.dao;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implements each method so that subclasses only need to override what they 
 * need.
 * @author Paul
 *
 */
public abstract class DAOAdapter extends HibernateDaoSupport implements DAO, 
Serializable {

	/**
	 * Default implementation
	 */
	public void load() throws DataAccessException{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Default implementation
	 */
	public void load(Locale aLocale) throws DataAccessException{
		throw new UnsupportedOperationException();
	}

	/**
	 * Default implementation
	 */
	public void save() throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Default implementation
	 */
	public void delete() throws DataAccessException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Default implementation
	 */
	public void setDelegate(Object aDelegate) {
		// does nothing...
	}
	
	/**
	 * Default implementation
	 */
	public Object getDelegate() {
		return null;
	}
}
