package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.GreenInRegulation;

import java.io.Serializable;

import name.paulevans.golf.dao.GreenInRegulationDAO;

/**
 * Hibernate green-in-regulation type dao
 * @author Paul
 *
 */
public class HibernateGreenInRegulationDAO extends HibernateDAOAdapter 
implements GreenInRegulationDAO {

	// delegate object - generated Hibernate type...
	private GreenInRegulation greenInRegulation;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateGreenInRegulationDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aGreenInRegulationDelegate
	 */
	public HibernateGreenInRegulationDAO(
			GreenInRegulation aGreenInRegulationDelegate) {
		greenInRegulation = aGreenInRegulationDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aGreenInRegulationDelegate) {
		greenInRegulation = (GreenInRegulation)aGreenInRegulationDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return greenInRegulation;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return greenInRegulation.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		greenInRegulation.setId((Integer)aId);
	}
	
	/**
	 * Get the description
	 * @return
	 */
	public String getDescription() {
		return greenInRegulation.getDescription();
	}

	/**
	 * Set the description
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		greenInRegulation.setDescription(aDescription);
	}
	
	/**
	 * Get the short description
	 * @return
	 */
	public String getShortDescription() {
		return greenInRegulation.getShortDescription();
	}

	/**
	 * Set the short description
	 * @param aShortDescription
	 */
	public void setShortDescription(String aShortDescription) {
		greenInRegulation.setShortDescription(aShortDescription);
	}
}
