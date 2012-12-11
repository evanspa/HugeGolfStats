package name.paulevans.golf.dao;



/**
 * Models a state-province.
 * @author Paul
 *
 */
public interface StateProvinceDAO extends DAO {

	/**
	 * Get the country
	 * @return
	 */
	CountryDAO getCountry();
	
	/**
	 * Sets the country
	 * @param aCountry
	 */
	void setCountry(CountryDAO aCountry);

	/**
	 * Get the name
	 * @return
	 */
	String getName();
	
	/**
	 * Sets the name
	 * @param aName
	 */
	void setName(String aName);
}
