package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.PantWear;

import java.io.Serializable;

import name.paulevans.golf.dao.PantWearDAO;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate pant-wear dao
 * @author Paul
 *
 */
public class HibernatePantWearDAO extends HibernateDAOAdapter 
implements PantWearDAO {

	// delegate object - generated Hibernate type...
	private PantWear pantWear;
	
	/**
	 * Default no-arg constructor 
	 */
	public HibernatePantWearDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aPantWearDelegate
	 */
	public HibernatePantWearDAO(PantWear aPantWearDelegate) {
		pantWear = aPantWearDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aPantWearDelegate) {
		pantWear = (PantWear)aPantWearDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return pantWear;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return pantWear.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		pantWear.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return pantWear.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		pantWear.setDescription(aDescription);
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		pantWear = (PantWear)ht.load(PantWear.class, getId());
		ht.initialize(pantWear);		
	}
}
