package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.ApproachShotDistance;

import java.io.Serializable;

import name.paulevans.golf.dao.ApproachShotDistanceDAO;

/**
 * Hibernate approach-shot line dao
 * @author Paul
 *
 */
public class HibernateApproachShotDistanceDAO extends HibernateDAOAdapter 
implements ApproachShotDistanceDAO {

	// delegate object - generated Hibernate type...
	private ApproachShotDistance approachShotDistance;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateApproachShotDistanceDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aApproachShotLineDelegate
	 */
	public HibernateApproachShotDistanceDAO(
			ApproachShotDistance aApproachShotLineDelegate) {
		approachShotDistance = aApproachShotLineDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aApproachShotLineDelegate) {
		approachShotDistance = (ApproachShotDistance)aApproachShotLineDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return approachShotDistance;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return approachShotDistance.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		approachShotDistance.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return approachShotDistance.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		approachShotDistance.setDescription(aDescription);
	}
	
	/**
	 * Get the short description
	 * @return
	 */
	public String getShortDescription() {
		return approachShotDistance.getShortDescription();
	}

	/**
	 * Set the short description
	 * @param aShortDescription
	 */
	public void setShortDescription(String aShortDescription) {
		approachShotDistance.setShortDescription(aShortDescription);
	}
}
