package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.Course;
import gen.hibernate.name.paulevans.golf.CourseSlopeRating;
import gen.hibernate.name.paulevans.golf.Hole;
import gen.hibernate.name.paulevans.golf.Tee;
import gen.hibernate.name.paulevans.golf.TeeName;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.CourseSlopeRatingDAO;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeId;
import name.paulevans.golf.dao.TeeNameDAO;

/**
 * Hibernate tee dao
 * @author Paul
 *
 */
public class HibernateTeeDAO extends HibernateDAOAdapter implements TeeDAO {

	// delegate object - generated Hibernate type...
	private Tee tee;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateTeeDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aTee
	 */
	public HibernateTeeDAO(Tee aTee) {
		tee = aTee;
	}
	
	/**
	 * Sets the tee delegate
	 */
	public void setDelegate(Object aTeeDelegate) {
		tee = (Tee)aTeeDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return tee;
	}

	/**
	 * Get the ID
	 */
	public TeeId getId() {
		return convert(tee.getId());
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		tee.setId(convert((TeeId)aId));
	}
	
	/**
	 * Get the course
	 * @return
	 */
	public CourseDAO getCourse() {
		
		CourseDAO course;
		
		course = BeanFactory.newCourseDAO();
		course.setDelegate(tee.getCourse());
		return course;
	}

	/**
	 * Set the course
	 * @param aCourse
	 */
	public void setCourse(CourseDAO aCourse) {
		tee.setCourse((Course)aCourse.getDelegate());
	}

	/**
	 * Get the tee color
	 * @return
	 */
	public TeeNameDAO getTeeName() {
		
		TeeNameDAO teeColor;
		
		teeColor = BeanFactory.newTeeNameDAO();
		teeColor.setDelegate(tee.getTeeName());
		return teeColor;
	}

	/**
	 * Set the tee color
	 * @param aTeeName
	 */
	public void setTeeName(TeeNameDAO aTeeName) {
		tee.setTeeName((TeeName)aTeeName.getDelegate());
	}

	/**
	 * Get the slope
	 * @return
	 */
	public Integer getOverallSlope() {
		return tee.getOverallSlope();
	}

	/**
	 * Set the slope
	 * @param aSlope
	 */
	public void setOverallSlope(Integer aSlope) {
		tee.setOverallSlope(aSlope);
	}

	/**
	 * Get the rating
	 * @return
	 */
	public Float getOverallRating() {
		return tee.getOverallRating();
	}

	/**
	 * Set the rating
	 * @param aRating
	 */
	public void setOverallRating(Float aRating) {
		tee.setOverallRating(aRating);
	}

	/**
	 * Get the holes
	 * @return
	 */
	public Set<HoleDAO> getHoles() {
		
		Set<Hole> holes;
		Set<HoleDAO> holeDAOs;
		
		holes = tee.getHoles();
		holeDAOs = new HashSet<HoleDAO>();
		for (Hole hole : holes) {
			holeDAOs.add(new HibernateHoleDAO(hole));
		}	
		return holeDAOs;
	}

	/**
	 * Set the holes
	 * @param aHoles
	 */
	public void setHoles(Set<HoleDAO> aHoles) {
		
		Set<Hole> holes;
		
		holes = new HashSet<Hole>();
		for (HoleDAO hole : aHoles) {
			holes.add((Hole)hole.getDelegate());
		}
		tee.setHoles(holes);
	}
	
	/**
	 * Returns the set of slope-rating values
	 * @return
	 */
	public Set<CourseSlopeRatingDAO> getSlopeRatingValues() {
		
		Set<CourseSlopeRating> slopeRatingValues;
		Set<CourseSlopeRatingDAO> slopeRatingDAOs;
		
		slopeRatingValues = tee.getCourseSlopeRatings();
		slopeRatingDAOs = new HashSet<CourseSlopeRatingDAO>();
		for (CourseSlopeRating slopeRatingValue : slopeRatingValues) {
			slopeRatingDAOs.add(new HibernateCourseSlopeRatingDAO(
					slopeRatingValue));
		}	
		return slopeRatingDAOs;
	}
	
	/**
	 * Sets the set of slope-rating values
	 * @param aSlopeRatingDAOs
	 */
	public void setSlopeRatingValues(
			Set<CourseSlopeRatingDAO> aSlopeRatingDAOs) {
		
		Set<CourseSlopeRating> slopeRatingValues;
		
		slopeRatingValues = new HashSet<CourseSlopeRating>();
		for (CourseSlopeRatingDAO slopeRatingDAO : aSlopeRatingDAOs) {
			slopeRatingValues.add(
					(CourseSlopeRating)slopeRatingDAO.getDelegate());
		}
		tee.setHoles(slopeRatingValues);
	}
	
	/**
	 * Converts the hibernate TeeId type to dao TeeId type
	 * @param aId
	 * @return
	 */
	private static final TeeId convert(
			gen.hibernate.name.paulevans.golf.TeeId aId) {
		
		TeeId id;
		
		id = new TeeId();
		id.setCourseId(aId.getCourseId());
		id.setTeeNameId(aId.getTeeNameId());
		return id;
	}
	
	/**
	 * Converts the dao TeeId type to hibernate TeeId type
	 * @param aId
	 * @return
	 */
	private static final gen.hibernate.name.paulevans.golf.TeeId 
	convert(TeeId aId) {
		
		gen.hibernate.name.paulevans.golf.TeeId  id;
		
		id = new gen.hibernate.name.paulevans.golf.TeeId();
		id.setCourseId(aId.getCourseId());
		id.setTeeNameId(aId.getTeeNameId());
		return id;
	}
}
