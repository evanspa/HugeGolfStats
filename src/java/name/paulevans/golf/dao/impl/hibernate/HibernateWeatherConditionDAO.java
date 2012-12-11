package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.WeatherCondition;

import java.io.Serializable;

import name.paulevans.golf.dao.WeatherConditionDAO;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate head-wear dao
 * @author Paul
 *
 */
public class HibernateWeatherConditionDAO extends HibernateDAOAdapter 
implements WeatherConditionDAO {

	// delegate object - generated Hibernate type...
	private WeatherCondition weatherCondition;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateWeatherConditionDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aWeatherConditionDelegate
	 */
	public HibernateWeatherConditionDAO(
			WeatherCondition aWeatherConditionDelegate) {
		weatherCondition = aWeatherConditionDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aWeatherConditionDelegate) {
		weatherCondition = (WeatherCondition)aWeatherConditionDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return weatherCondition;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return weatherCondition.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		weatherCondition.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return weatherCondition.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		weatherCondition.setDescription(aDescription);
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		weatherCondition = (WeatherCondition)ht.load(WeatherCondition.class, 
				getId());
		ht.initialize(weatherCondition);
	}
}
