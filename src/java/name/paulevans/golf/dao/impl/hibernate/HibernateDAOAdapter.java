package name.paulevans.golf.dao.impl.hibernate;

import name.paulevans.golf.dao.DAOAdapter;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Implements save() method using HibernateTemplate helper-method
 * @author Paul Evans
 *
 */
public abstract class HibernateDAOAdapter extends DAOAdapter {
	
	/**
	 * Saves the Hibernate-delegate object associated with this DAO
	 */
	public void save() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		ht.saveOrUpdate(getDelegate());
	}
	
	/**
	 * Deletes the Hibernate-delegate object associated with this DAO
	 */
	public void delete() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		ht.delete(getDelegate());
		//ht.flush();
	}
	
	/**
	 * Needed to satisfy Comparable interface
	 */
	public int compareTo(Object aCompareTo) {
		throw new UnsupportedOperationException();
	}
}
