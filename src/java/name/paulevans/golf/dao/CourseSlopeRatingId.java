package name.paulevans.golf.dao;

/**
 * Models a composite-key used by the CourseSlopeRatingDAO
 * @author pevans
 *
 */
public class CourseSlopeRatingId {

	private int courseId;
	private int teeNameId;
	private Integer nineType;

	/**
	 * Default no-arg constructor
	 *
	 */
	public CourseSlopeRatingId() {
		// does nothing...
	}

	/**
	 * Public constructor
	 * @param aCourseId
	 * @param aTeeNameId
	 * @param aNineType
	 */
	public CourseSlopeRatingId(int aCourseId, int aTeeNameId, 
			Integer aNineType) {
		courseId = aCourseId;
		teeNameId = aTeeNameId;
		nineType = aNineType;
	}

	/**
	 * Getter
	 * @return
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * Setter
	 * @param aCourseId
	 */
	public void setCourseId(int aCourseId) {
		courseId = aCourseId;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public int getTeeNameId() {
		return teeNameId;
	}

	/**
	 * Setter
	 * @param aTeeNameId
	 */
	public void setTeeNameId(int aTeeNameId) {
		teeNameId = aTeeNameId;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Integer getNineType() {
		return nineType;
	}

	/**
	 * Setter
	 * @param aStartingHole1
	 */
	public void setNineType(Integer aNineType) {
		nineType = aNineType;
	}
}


