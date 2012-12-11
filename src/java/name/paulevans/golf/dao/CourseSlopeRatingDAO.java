package name.paulevans.golf.dao;

/**
 * Models the slope/rating values for a course/tee combination
 * @author pevans
 *
 */
public interface CourseSlopeRatingDAO extends DAO {

	/**
	 * Returns the slope value
	 * @return
	 */
	Integer getSlope();

	/**
	 * Sets the slope value
	 * @param aSlope
	 */
	void setSlope(Integer aSlope);

	/**
	 * Returns the rating value
	 * @return
	 */
	Float getRating();

	/**
	 * Sets the rating value
	 * @param aRating
	 */
	void setRating(Float aRating);
	
	/**
	 * Sets the starting hole
	 * @param aStartingHole
	 */
	void setStartingHole(int aStartingHole);
	
	/**
	 * Gets the starting hole
	 * @param aStartingHole
	 * @return
	 */
	int getStartingHole();
}