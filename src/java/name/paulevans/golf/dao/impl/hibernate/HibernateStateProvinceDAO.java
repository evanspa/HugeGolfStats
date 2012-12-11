package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.Country;
import gen.hibernate.name.paulevans.golf.StateProvince;

import java.io.Serializable;

import name.paulevans.golf.dao.CountryDAO;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.StateProvinceId;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate state-province dao
 * @author Paul
 *
 */
public class HibernateStateProvinceDAO extends HibernateDAOAdapter 
implements StateProvinceDAO {

	// delegate object - generated Hibernate type...
	private StateProvince stateProvince;
	
	/**
	 * no-arg constructor
	 *
	 */
	public HibernateStateProvinceDAO() {
		stateProvince = new StateProvince();
	}
	
	/**
	 * Public constructor
	 * @param aStateProvinceDelegate
	 */
	public HibernateStateProvinceDAO(StateProvince aStateProvinceDelegate) {
		stateProvince = aStateProvinceDelegate;
	}
	
	/**
	 * Public constructor
	 * @param aStateProvinceName
	 */
	public HibernateStateProvinceDAO(String aCountryName, 
			String aStateProvinceName) {
		this();
		Country country;
		
		country = new Country();
		country.setName(aCountryName);
		stateProvince.setCountry(country);
		stateProvince.setName(aStateProvinceName);
	}
	
	/**
	 * Sets the stateProvince delegate
	 */
	public void setDelegate(Object aTeeDelegate) {
		stateProvince = (StateProvince)aTeeDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return stateProvince;
	}

	/**
	 * Get the ID
	 */
	public StateProvinceId getId() {
		return convert(stateProvince.getId());
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		stateProvince.setId(convert((StateProvinceId)aId));
	}
	
	/**
	 * Get the country
	 * @return
	 */
	public CountryDAO getCountry() {
		return new HibernateCountryDAO(stateProvince.getCountry());
	}
	
	/**
	 * Sets the country
	 * @param aCountry
	 */
	public void setCountry(CountryDAO aCountry) {
		stateProvince.setCountry((Country)aCountry.getDelegate());
	}

	/**
	 * Get the name
	 * @return
	 */
	public String getName() {
		return stateProvince.getName();
	}
	
	/**
	 * Sets the name
	 * @param aName
	 */
	public void setName(String aName) {
		stateProvince.setName(aName);
	}
	
	/**
	 * Converts the hibernate StateProvinceId type to dao StateProvinceId type
	 * @param aId
	 * @return
	 */
	private static final StateProvinceId convert(
			gen.hibernate.name.paulevans.golf.StateProvinceId aId) {
		
		StateProvinceId  id;
		
		id = new StateProvinceId();
		if (aId != null) {
			id.setCountryId(aId.getCountryId());
			id.setId(aId.getId());
		}
		return id;
	}
	
	/**
	 * Converts the dao StateProvinceId type to hibernate StateProvinceId type
	 * @param aId
	 * @return
	 */
	private static final gen.hibernate.name.paulevans.golf.StateProvinceId 
	convert(StateProvinceId aId) {
		
		gen.hibernate.name.paulevans.golf.StateProvinceId  id;
		
		id = new gen.hibernate.name.paulevans.golf.StateProvinceId();
		id.setCountryId(aId.getCountryId());
		id.setId(aId.getId());
		return id;
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		stateProvince = (StateProvince)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				StateProvince stateProvince;
				
				stateProvince = (StateProvince)aSession.load(
						StateProvince.class, convert(getId()));
				Hibernate.initialize(stateProvince);
				Hibernate.initialize(stateProvince.getCountry());
				return stateProvince;
			}
		});
	}
}
