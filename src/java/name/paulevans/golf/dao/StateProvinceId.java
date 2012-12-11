package name.paulevans.golf.dao;

/**
 * Models a composite-key used by the StateProvinceDAO
 * @author Paul
 *
 */
public class StateProvinceId implements Identifiable {
	
	private int id;
	private int countryId;
	
	/**
	 * Get the country ID
	 * @return
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * Set the country ID
	 * @param aCountryId
	 */
	public void setCountryId(int aCountryId) {
		countryId = aCountryId;
	}
	
	/**
	 * Set the ID
	 * @param aId
	 */
	public void setId(Object aId) {
		id = (Integer)aId;
	}
	
	/**
	 * Get the ID
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Needed to satisfy Comparable interface
	 */
	public int compareTo(Object aCompareTo) {
		throw new UnsupportedOperationException();
	}
}
