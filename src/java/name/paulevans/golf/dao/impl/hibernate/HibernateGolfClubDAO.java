package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.GolfClub;

import java.io.Serializable;

import name.paulevans.golf.dao.GolfClubDAO;

/**
 * Hibernate golf club dao
 * @author Paul
 *
 */
public class HibernateGolfClubDAO extends HibernateDAOAdapter 
implements GolfClubDAO {

	// delegate object - generated Hibernate type...
	private GolfClub golfClub;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateGolfClubDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aGolfClubDelegate
	 */
	public HibernateGolfClubDAO(GolfClub aGolfClubDelegate) {
		golfClub = aGolfClubDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aGolfClubDelegate) {
		golfClub = (GolfClub)aGolfClubDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return golfClub;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return golfClub.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		golfClub.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return golfClub.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		golfClub.setDescription(aDescription);
	}
	
	/**
	 * Get the short description
	 * @return
	 */
	public String getShortDescription() {
		return golfClub.getShortDescription();
	}

	/**
	 * Set the short description
	 * @param aShortDescription
	 */
	public void setShortDescription(String aShortDescription) {
		golfClub.setShortDescription(aShortDescription);
	}
}
