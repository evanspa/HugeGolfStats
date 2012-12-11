package name.paulevans.golf.dao;



/**
 * Models a green-in-regulation type
 * @author Paul
 *
 */
public interface GreenInRegulationDAO extends DAO {

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
