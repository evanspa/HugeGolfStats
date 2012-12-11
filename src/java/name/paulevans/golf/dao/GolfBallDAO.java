package name.paulevans.golf.dao;



/**
 * Models the golf-ball type
 * @author Paul
 *
 */
public interface GolfBallDAO extends DAO {

	/**
	 * Get the description
	 * @return
	 */
	String getDescription();
	
	/**
	 * Set the description
	 * @param aDescription
	 */
	void setDescription(String aDescription);
}
