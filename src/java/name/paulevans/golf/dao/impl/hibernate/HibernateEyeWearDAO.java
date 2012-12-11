package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.EyeWear;

import java.io.Serializable;

import name.paulevans.golf.dao.EyeWearDAO;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate eye-wear dao
 * @author Paul
 *
 */
public class HibernateEyeWearDAO extends HibernateDAOAdapter 
implements EyeWearDAO {

	// delegate object - generated Hibernate type...
	private EyeWear eyeWear;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateEyeWearDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aEyeWearDelegate
	 */
	public HibernateEyeWearDAO(EyeWear aEyeWearDelegate) {
		eyeWear = aEyeWearDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aEyeWearDelegate) {
		eyeWear = (EyeWear)aEyeWearDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return eyeWear;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return eyeWear.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		eyeWear.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return eyeWear.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		eyeWear.setDescription(aDescription);
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());		
		eyeWear = (EyeWear)ht.load(EyeWear.class, getId());
		ht.initialize(eyeWear);	
	}
}
