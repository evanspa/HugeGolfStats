package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.CourseSlopeRating;

import java.io.Serializable;

import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.CourseSlopeRatingDAO;
import name.paulevans.golf.dao.CourseSlopeRatingId;

/**
 * Hibernate CourseSlopeRating DAO
 * @author pevans
 *
 */
public class HibernateCourseSlopeRatingDAO extends HibernateDAOAdapter 
implements CourseSlopeRatingDAO {
	
	// delegate object - generated Hibernate type...
	private CourseSlopeRating courseSlopeRating;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateCourseSlopeRatingDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aCourseSlopeRatingDelegate
	 */
	public HibernateCourseSlopeRatingDAO(CourseSlopeRating 
			aCourseSlopeRatingDelegate) {
		courseSlopeRating = aCourseSlopeRatingDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aCourseSlopeRatingDelegate) {
		courseSlopeRating = (CourseSlopeRating)aCourseSlopeRatingDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return courseSlopeRating;
	}
	
	/**
	 * Returns the slope value
	 * @return
	 */
	public Integer getSlope() {
		return courseSlopeRating.getSlope();
	}

	/**
	 * Sets the slope value
	 * @param aSlope
	 */
	public void setSlope(Integer aSlope) {
		courseSlopeRating.setSlope(aSlope);
	}

	/**
	 * Returns the rating value
	 * @return
	 */
	public Float getRating() {
		return courseSlopeRating.getRating();
	}

	/**
	 * Sets the rating value
	 * @param aRating
	 */
	public void setRating(Float aRating) {
		courseSlopeRating.setRating(aRating);
	}
	
	/**
	 * Sets the starting hole
	 * @param aStartingHole
	 */
	public void setStartingHole(int aStartingHole) {
		courseSlopeRating.setStartingHole(aStartingHole);
	}
	
	/**
	 * Gets the starting hole
	 * @return
	 */
	public int getStartingHole() {
		return courseSlopeRating.getStartingHole();
	}
	
	/**
	 * Get the ID
	 */
	public CourseSlopeRatingId getId() {
		return convert(courseSlopeRating.getId());
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		courseSlopeRating.setId(convert(
				(name.paulevans.golf.dao.CourseSlopeRatingId)aId));
	}
	
	/**
	 * Returns a string representation of this DAO of the form:
	 * AA|Z where AA is the starting-hole and Z is the tee color id.
	 */
	public String toString() {
		return (courseSlopeRating.getId().getNineType() == 
			Constants.FRONT_NINE_TYPE ? "front" : "back") + "|" + 
		courseSlopeRating.getId().getTeeNameId();
	}
	
	/**
	 * Converts the hibernate CourseSlopeRatingId type to dao 
	 * CourseSlopeRatingId type
	 * @param aId
	 * @return
	 */
	private static final name.paulevans.golf.dao.CourseSlopeRatingId 
	convert(gen.hibernate.name.paulevans.golf.CourseSlopeRatingId aId) {
		
		name.paulevans.golf.dao.CourseSlopeRatingId id;
		
		id = new name.paulevans.golf.dao.CourseSlopeRatingId();
		id.setCourseId(aId.getCourseId());
		id.setTeeNameId(aId.getTeeNameId());
		id.setNineType(null);
		if (aId.getNineType() != Constants.NULL_ID_VAL) {
			id.setNineType(aId.getNineType());
		}
		return id;
	}
	
	/**
	 * Converts the dao CourseSlopeRatingId type to the hibernate 
	 * CourseSlopeRatingId type
	 * @param aId
	 * @return
	 */
	private static final gen.hibernate.name.paulevans.golf.CourseSlopeRatingId 
	convert(name.paulevans.golf.dao.CourseSlopeRatingId aId) {
		
		gen.hibernate.name.paulevans.golf.CourseSlopeRatingId id;
		
		id = new gen.hibernate.name.paulevans.golf.CourseSlopeRatingId();
		id.setCourseId(aId.getCourseId());
		id.setTeeNameId(aId.getTeeNameId());
		id.setNineType(Constants.NULL_ID_VAL);
		if (aId.getNineType() != null) {
			id.setNineType(aId.getNineType());
		}
		return id;
	}
}
