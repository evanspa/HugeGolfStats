package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.Country;
import gen.hibernate.name.paulevans.golf.StateProvince;

import java.util.HashSet;
import java.util.Set;

import name.paulevans.golf.dao.CountryDAO;
import name.paulevans.golf.dao.StateProvinceDAO;

/**
 * Hibernate country dao
 * @author Paul
 *
 */
public class HibernateCountryDAO extends HibernateDAOAdapter 
implements CountryDAO {

	// delegate object - generated Hibernate type...
	private Country country;
	
	/**
	 * Public constructor
	 * @param aCountryDelegate
	 */
	public HibernateCountryDAO(Country aCountryDelegate) {
		country = aCountryDelegate;
		if (country == null) {
			country = new Country();
		}
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aCountryDelegate) {
		country = (Country)aCountryDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Object getDelegate() {
		return country;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return country.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		country.setId((Integer)aId);
	}
	
	/**
	 * Get the name
	 * @return
	 */
	public String getName() {
		return country.getName();
	}

	/**
	 * Get the set of state-province DAOs
	 * @return
	 */
	public Set<StateProvinceDAO> getStateProvinces() {
		
		Set<StateProvinceDAO> stateProvinceDAOs;
		Set<StateProvince> stateProvinces;
		
		stateProvinceDAOs = new HashSet<StateProvinceDAO>();
		stateProvinces = country.getStateProvinces();
		for (StateProvince stateProvince : stateProvinces) {
			stateProvinceDAOs.add(new HibernateStateProvinceDAO(stateProvince));
		}
		return stateProvinceDAOs;
	}
}
