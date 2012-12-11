package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.TeeName;

import java.io.Serializable;

import name.paulevans.golf.dao.TeeNameDAO;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate tee dao
 * @author Paul
 *
 */
public class HibernateTeeNameDAO extends HibernateDAOAdapter 
implements TeeNameDAO {

	// delegate object - generated Hibernate type...
	private TeeName teeName;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateTeeNameDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aTee
	 */
	public HibernateTeeNameDAO(TeeName aTeeColorDelegate) {
		teeName = aTeeColorDelegate;
	}
	
	/**
	 * Sets the tee delegate
	 */
	public void setDelegate(Object aTeeColorDelegate) {
		teeName = (TeeName)aTeeColorDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return teeName;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return teeName != null ? teeName.getId() : null;
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		teeName.setId((Integer)aId);
	}
	
	/**
	 * Get the color
	 * @return
	 */
	public String getName() {
		return teeName.getName();
	}

	/**
	 * Set the color
	 * @param aColor
	 */
	public void setName(String aColor) {
		teeName.setName(aColor);
	}
	
	/**
	 * Returns if this tee name is named using a standard color
	 * @return
	 */
	public boolean isStandardColor() {
		return teeName.isIsStandardColor();
	}
	
	/**
	 * Sets if this tee name is named using a standard color
	 * @param aIsStandardColor
	 */
	public void setIsStandardColor(boolean aIsStandardColor) {
		teeName.setIsStandardColor(aIsStandardColor);
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		ht = new HibernateTemplate(getSessionFactory());		
		teeName = (TeeName)ht.load(TeeName.class, getId());
		ht.initialize(teeName);
	}
}
