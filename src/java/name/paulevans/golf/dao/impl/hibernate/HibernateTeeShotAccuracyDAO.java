package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.TeeShotAccuracy;

import java.io.Serializable;

import name.paulevans.golf.dao.TeeShotAccuracyDAO;

/**
 * Hibernate tee-shot accuracy dao
 * @author Paul
 *
 */
public class HibernateTeeShotAccuracyDAO extends HibernateDAOAdapter 
implements TeeShotAccuracyDAO {

	// delegate object - generated Hibernate type...
	private TeeShotAccuracy teeShotAccuracy;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateTeeShotAccuracyDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aTeeshotAccuracyDelegate
	 */
	public HibernateTeeShotAccuracyDAO(TeeShotAccuracy aTeeShotAccuracyDelegate) {
		teeShotAccuracy = aTeeShotAccuracyDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aTeeShotAccuracyDelegate) {
		teeShotAccuracy = (TeeShotAccuracy)aTeeShotAccuracyDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return teeShotAccuracy;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return teeShotAccuracy != null ? teeShotAccuracy.getId() : null;
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		teeShotAccuracy.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return teeShotAccuracy.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		teeShotAccuracy.setDescription(aDescription);
	}
	
	/**
	 * Get the short description
	 * @return
	 */
	public String getShortDescription() {
		return teeShotAccuracy.getShortDescription();
	}

	/**
	 * Set the short description
	 * @param aShortDescription
	 */
	public void setShortDescription(String aShortDescription) {
		teeShotAccuracy.setShortDescription(aShortDescription);
	}
}
