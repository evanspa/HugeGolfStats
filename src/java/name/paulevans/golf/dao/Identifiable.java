package name.paulevans.golf.dao;

/**
 * Contains getter/setting for an ID
 * @author Paul
 *
 */
public interface Identifiable extends Comparable {
	
	/**
	 * Get the ID
	 * @return
	 */
	Object getId();

	/**
	 * Set the ID
	 * @param aId
	 */
	void setId(Object aId);
}
