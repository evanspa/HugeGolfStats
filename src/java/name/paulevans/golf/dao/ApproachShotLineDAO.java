package name.paulevans.golf.dao;



/**
 * Models the approach-shot type
 * @author Paul
 *
 */
public interface ApproachShotLineDAO extends DAO {

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
	
	/**
	 * Get the short description
	 * @return
	 */
	String getShortDescription();
	
	/**
	 * Set the short description
	 * @param aDescription
	 */
	void setShortDescription(String aDescription);
}
