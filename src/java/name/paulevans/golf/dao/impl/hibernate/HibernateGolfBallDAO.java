package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.GolfBall;

import java.io.Serializable;

import name.paulevans.golf.dao.GolfBallDAO;

/**
 * Hibernate golf ball-type dao
 * @author Paul
 *
 */
public class HibernateGolfBallDAO extends HibernateDAOAdapter 
implements GolfBallDAO {

	// delegate object - generated Hibernate type...
	private GolfBall golfBall;
	
	/**
	 * Public constructor
	 * @param aGolfBallDelegate
	 */
	public HibernateGolfBallDAO(GolfBall aGolfBallDelegate) {
		golfBall = aGolfBallDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aGolfBallDelegate) {
		golfBall = (GolfBall)aGolfBallDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return golfBall;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return golfBall.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		golfBall.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return golfBall.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		golfBall.setDescription(aDescription);
	}
}
