package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.HeadWear;

import java.io.Serializable;

import name.paulevans.golf.dao.HeadWearDAO;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate head-wear dao
 * @author Paul
 *
 */
public class HibernateHeadWearDAO extends HibernateDAOAdapter 
implements HeadWearDAO {

	// delegate object - generated Hibernate type...
	private HeadWear headWear;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateHeadWearDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aPantWearDelegate
	 */
	public HibernateHeadWearDAO(HeadWear aPantWearDelegate) {
		headWear = aPantWearDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aPantWearDelegate) {
		headWear = (HeadWear)aPantWearDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return headWear;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return headWear.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		headWear.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return headWear.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		headWear.setDescription(aDescription);
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		headWear = (HeadWear)ht.load(HeadWear.class, getId());
		ht.initialize(headWear);
	}
}
