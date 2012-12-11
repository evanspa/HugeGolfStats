package name.paulevans.golf.dao;



/**
 * Models a weather-condition
 * @author Paul
 *
 */
public interface WeatherConditionDAO extends DAO {

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
