package name.paulevans.golf.dao;

/**
 * Models a composite-key used by the TeeDAO
 * @author Paul
 *
 */
public class TeeId  {
	
	private int courseId;
	private int teeNameId;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public TeeId() {
		// does nothing...
	}
	
	/**
	 * Constructor
	 * @param aTeeNameId
	 */
	public TeeId(int aTeeNameId) {
		teeNameId = aTeeNameId;
	}
	
	/**
	 * Get the course ID
	 * @return
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * Set the course ID
	 * @param aCourseId
	 */
	public void setCourseId(int aCourseId) {
		courseId = aCourseId;
	}
	
	/**
	 * Get the tee name ID
	 * @return
	 */
	public int getTeeNameId() {
		return teeNameId;
	}

	/**
	 * Set the tee name ID
	 * @param aTeeNameId
	 */
	public void setTeeNameId(int aTeeNameId) {
		teeNameId = aTeeNameId;
	}
}
