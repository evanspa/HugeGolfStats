package name.paulevans.golf.dao;



/**
 * Models a hole.
 * @author Paul
 *
 */
public interface HoleDAO extends DAO {

	/**
	 * Get the tee
	 * @return
	 */
	TeeDAO getTee();

	/**
	 * Set the tee
	 * @param aTee
	 */
	void setTee(TeeDAO aTee);

	/**
	 * Get the hole-number
	 * @return
	 */
	int getNumber();

	/**
	 * Set the hole-number
	 * @param aNumber
	 */
	void setNumber(int aNumber);

	/**
	 * Get the yardage
	 * @return
	 */
	int getYards();

	/**
	 * Set the yardage
	 * @param aYards
	 */
	void setYards(int aYards);

	/**
	 * Get the par
	 * @return
	 */
	int getPar();

	/**
	 * Set the par
	 * @param aPar
	 */
	void setPar(int aPar);

	/**
	 * Get the handicap
	 * @return
	 */
	int getHandicap();

	/**
	 * Set the handicap
	 * @param aHandicap
	 */
	void setHandicap(int aHandicap);
}
