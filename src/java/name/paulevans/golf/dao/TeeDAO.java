package name.paulevans.golf.dao;

import java.util.Set;


/**
 * Models a tee.
 * @author Paul
 *
 */
public interface TeeDAO extends DAO {

	/**
	 * Get the course
	 * @return
	 */
	CourseDAO getCourse();

	/**
	 * Set the course
	 * @param aCourse
	 */
	void setCourse(CourseDAO aCourse);

	/**
	 * Get the tee name
	 * @return
	 */
	TeeNameDAO getTeeName();

	/**
	 * Set the tee name
	 * @param aTeeName
	 */
	void setTeeName(TeeNameDAO aTeeName);

	/**
	 * Get the slope
	 * @return
	 */
	Integer getOverallSlope();

	/**
	 * Set the slope
	 * @param aSlope
	 */
	void setOverallSlope(Integer aSlope);

	/**
	 * Get the rating
	 * @return
	 */
	Float getOverallRating();

	/**
	 * Set the rating
	 * @param aRating
	 */
	void setOverallRating(Float aRating);

	/**
	 * Get the holes
	 * @return
	 */
	Set<HoleDAO> getHoles();

	/**
	 * Set the holes
	 * @param aHoles
	 */
	void setHoles(Set<HoleDAO> aHoles);
	
	/**
	 * Returns the set of slope-rating values
	 * @return
	 */
	Set<CourseSlopeRatingDAO> getSlopeRatingValues();
	
	/**
	 * Sets the set of slope-rating values
	 * @param aSlopeRatingDAOs
	 */
	void setSlopeRatingValues(Set<CourseSlopeRatingDAO> aSlopeRatingDAOs);
}
