package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.ApproachShotLine;

import java.io.Serializable;

import name.paulevans.golf.dao.ApproachShotLineDAO;

/**
 * Hibernate approach-shot line dao
 * @author Paul
 *
 */
public class HibernateApproachShotLineDAO extends HibernateDAOAdapter 
implements ApproachShotLineDAO {

	// delegate object - generated Hibernate type...
	private ApproachShotLine approachShotLine;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateApproachShotLineDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aApproachShotLineDelegate
	 */
	public HibernateApproachShotLineDAO(
			ApproachShotLine aApproachShotLineDelegate) {
		approachShotLine = aApproachShotLineDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aApproachShotLineDelegate) {
		approachShotLine = 
			(ApproachShotLine)aApproachShotLineDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return approachShotLine;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return approachShotLine.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		approachShotLine.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return approachShotLine.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		approachShotLine.setDescription(aDescription);
	}
	
	/**
	 * Get the short description
	 * @return
	 */
	public String getShortDescription() {
		return approachShotLine.getShortDescription();
	}

	/**
	 * Set the short description
	 * @param aShortDescription
	 */
	public void setShortDescription(String aShortDescription) {
		approachShotLine.setShortDescription(aShortDescription);
	}
}
