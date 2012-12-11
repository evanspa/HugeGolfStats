package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.Hole;
import gen.hibernate.name.paulevans.golf.Tee;

import java.io.Serializable;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.TeeDAO;

/**
 * Hibernate hole dao
 * @author Paul
 *
 */
public class HibernateHoleDAO extends HibernateDAOAdapter implements HoleDAO {

	// delegate object - generated Hibernate type...
	private Hole hole;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateHoleDAO() {
		// does nothing...
	}
	
	/**
	 * Constructor
	 * @param aHoleDelegate
	 */
	public HibernateHoleDAO(Hole aHoleDelegate) {
		hole = aHoleDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aHoleDelegate) {
		hole = (Hole)aHoleDelegate;
	}
	
	/**
	 * Gets the delegate
	 */
	public Serializable getDelegate() {
		return hole;
	}
	
	/**
	 * Get the ID
	 */
	public Integer getId() {
		return hole.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		hole.setId((Integer)aId);
	}
	
	/**
	 * Get the tee
	 * @return
	 */
	public TeeDAO getTee() {
		
		TeeDAO teeDAO;
		
		teeDAO = BeanFactory.newTeeDAO();
		teeDAO.setDelegate(hole.getTee());
		return teeDAO;
	}

	/**
	 * Set the tee
	 * @param aTee
	 */
	public void setTee(TeeDAO aTee) {
		hole.setTee((Tee)aTee.getDelegate());
	}

	/**
	 * Get the hole-number
	 * @return
	 */
	public int getNumber() {
		return hole.getNumber();
	}

	/**
	 * Set the hole-number
	 * @param aNumber
	 */
	public void setNumber(int aNumber) {
		hole.setNumber(aNumber);
	}

	/**
	 * Get the yardage
	 * @return
	 */
	public int getYards() {
		return hole.getYards();
	}

	/**
	 * Set the yardage
	 * @param aYards
	 */
	public void setYards(int aYards) {
		hole.setYards(aYards);
	}

	/**
	 * Get the par
	 * @return
	 */
	public int getPar() {
		return hole.getPar();
	}

	/**
	 * Set the par
	 * @param aPar
	 */
	public void setPar(int aPar) {
		hole.setPar(aPar);
	}

	/**
	 * Get the handicap
	 * @return
	 */
	public int getHandicap() {
		return hole.getHandicap();
	}

	/**
	 * Set the handicap
	 * @param aHandicap
	 */
	public void setHandicap(int aHandicap) {
		hole.setHandicap(aHandicap);
	}
}
