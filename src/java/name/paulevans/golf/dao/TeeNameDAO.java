package name.paulevans.golf.dao;

/**
 * Models a tee color.
 * @author Paul
 *
 */
public interface TeeNameDAO extends DAO {
	
	/**
	 * Get the color
	 * @return
	 */
	String getName();

	/**
	 * Set the color
	 * @param aName
	 */
	void setName(String aName);
	
	/**
	 * Returns if this tee name is named using a standard color
	 * @return
	 */
	boolean isStandardColor();
	
	/**
	 * Sets if this tee name is named using a standard color
	 * @param aIsStandardColor
	 */
	void setIsStandardColor(boolean aIsStandardColor);
}
