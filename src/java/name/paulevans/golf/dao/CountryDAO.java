package name.paulevans.golf.dao;

import java.util.Set;



/**
 * Models a country
 * @author Paul
 *
 */
public interface CountryDAO extends DAO {

	/**
	 * Get the name
	 * @return
	 */
	String getName();

	/**
	 * Get the set of state-province DAOs
	 * @return
	 */
	Set<StateProvinceDAO> getStateProvinces();
}
