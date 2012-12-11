package name.paulevans.golf.dao;



/**
 * Models eye-wear
 * @author Paul
 *
 */
public interface EyeWearDAO extends DAO {

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
