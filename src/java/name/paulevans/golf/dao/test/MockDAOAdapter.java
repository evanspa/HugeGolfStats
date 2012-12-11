package name.paulevans.golf.dao.test;

import name.paulevans.golf.dao.DAOAdapter;

/**
 * Mock object
 * @author pevans
 *
 */
public class MockDAOAdapter extends DAOAdapter {
	
	/**
	 * Needed to satisfy contract
	 */
	public Object getId() {
		return null;
	}
	
	/**
	 * Needed to satisfy contact
	 */
	public void setId(Object aId) {
		// do nothing...
	}
	
	/**
	 * Needed to satisfy Comparable interface
	 */
	public int compareTo(Object aCompareTo) {
		throw new UnsupportedOperationException();
	}
}
