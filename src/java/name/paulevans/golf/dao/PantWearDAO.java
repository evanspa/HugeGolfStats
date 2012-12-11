package name.paulevans.golf.dao;



/**
 * Models pant-wear
 * @author Paul
 *
 */
public interface PantWearDAO extends DAO {

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
