package name.paulevans.golf.dao;



/**
 * Models head-wear
 * @author Paul
 *
 */
public interface HeadWearDAO extends DAO {

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
