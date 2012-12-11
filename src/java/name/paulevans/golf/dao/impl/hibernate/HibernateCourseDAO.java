package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.Course;
import gen.hibernate.name.paulevans.golf.CourseSlopeRating;
import gen.hibernate.name.paulevans.golf.Hole;
import gen.hibernate.name.paulevans.golf.StateProvince;
import gen.hibernate.name.paulevans.golf.Tee;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.CourseSlopeRatingDAO;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.StateProvinceDAO;
import name.paulevans.golf.dao.TeeDAO;
import name.paulevans.golf.dao.TeeId;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate course dao
 * @author Paul
 *
 */
public class HibernateCourseDAO extends HibernateDAOAdapter 
implements CourseDAO {

	// delegate object - generated Hibernate type...
	private Course course;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateCourseDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aCourseDelegate
	 */
	public HibernateCourseDAO(Course aCourseDelegate) {
		course = aCourseDelegate;	
	}
	
	/**
	 * Public constructor
	 * @param aCourseId
	 * @param aCourseName
	 * @param aStateProvinceName
	 * @param aCountryName
	 */
	public HibernateCourseDAO(int aCourseId, String aCourseName, 
			String aCountryName, String aStateProvinceName, String aCity,
			String aClubName, String aAssociationName) {
		
		course = new Course();
		course.setId(aCourseId);
		course.setDescription(aCourseName);
		course.setClubName(aClubName);
		course.setAssociationName(aAssociationName);
		course.setCity(aCity);
		course.setStateProvince((StateProvince)new HibernateStateProvinceDAO(
				aCountryName, aStateProvinceName).getDelegate());
	}
	
	/**
	 * Sets the course delegate
	 */
	public void setDelegate(Object aCourseDelegate) {
		course = (Course)aCourseDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return course;
	}
	
	/**
	 * Returns the state-province for this course
	 * @return
	 */
	public StateProvinceDAO getStateProvince() {
		
		StateProvinceDAO stateProvinceDAO;
		
		stateProvinceDAO = BeanFactory.newStateProvinceDAO();
		stateProvinceDAO.setDelegate(course.getStateProvince());
		return stateProvinceDAO;
	}
	
	/**
	 * Sets the state-province for this course
	 * @param aStateProvinceDAO
	 */
	public void setStateProvince(StateProvinceDAO aStateProvinceDAO) {
		course.setStateProvince((StateProvince)aStateProvinceDAO.getDelegate());
	}
	
	/**
	 * Returns the city this course is located in
	 * @return
	 */
	public String getCity() {
		return course.getCity();
	}
	
	/**
	 * Sets the city this course is located int
	 * @param aCity
	 */
	public void setCity(String aCity) {
		course.setCity(aCity);
	}

	/**
	 * Gets the description
	 */
	public String getDescription() {
		return course.getDescription();
	}
	
	/**
	 * Returns the associated club name
	 * @return
	 */
	public String getClubName() {
		return course.getClubName();
	}
	
	/**
	 * Sets the associated club name
	 * @param aClubName
	 */
	public void setClubName(String aClubName) {
		course.setClubName(aClubName);
	}
	
	/**
	 * Returns the associated association name
	 * @return
	 */
	public String getAssociationName() {
		return course.getAssociationName();
	}
	
	/**
	 * Sets the associated association name
	 * @param aAssociationName
	 */
	public void setAssociationName(String aAssociationName) {
		course.setAssociationName(aAssociationName);
	}

	/**
	 * Gets the tees
	 */
	public Set<TeeDAO> getTees() {
		
		Set<Tee> tees;
		Set<TeeDAO> teeDAOs;
		
		tees = course.getTees();
		teeDAOs = new HashSet<TeeDAO>();
		for (Tee tee : tees) {
			teeDAOs.add(new HibernateTeeDAO(tee));
		}
		return teeDAOs;
	}

	/**
	 * Sets the description
	 */
	public void setDescription(String aDescription) {
		course.setDescription(aDescription);
	}
	
	/**
	 * Sets the starting hole number for the front-nine
	 * @param aStartingHoleNum
	 */
	public void setFrontNineStartingHole(int aStartingHoleNum) {
		course.setFrontNineStartingHole(aStartingHoleNum);
	}
	
	/**
	 * Returns the starting hole number for the front-nine
	 * @return
	 */
	public int getFrontNineStartingHole() {
		return course.getFrontNineStartingHole();
	}
	
	/**
	 * Sets the starting hole number for the back-nine
	 * @param aStartingHoleNum
	 */
	public void setBackNineStartingHole(int aStartingHoleNum) {
		course.setBackNineStartingHole(aStartingHoleNum);
	}
	
	/**
	 * Returns the starting hole number for the front-nine
	 * @return
	 */
	public int getBackNineStartingHole() {
		return course.getBackNineStartingHole();
	}

	/**
	 * Set the tees
	 */
	public void setTees(Set<TeeDAO> aTees) {
		course.setTees(aTees);
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return course.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		course.setId((Integer)aId);
	}
	
	/**
	 * Returns the total number of holes this course has
	 * @return
	 */
	public int getNumTotalHoles() {
		
		Set<TeeDAO> tees;
		TeeDAO tee;
		int numTotalHoles;
		
		numTotalHoles = 0;
		tees = getTees();
		if (tees.size() > 0) {
			tee = tees.toArray(new TeeDAO[tees.size()])[0];
			numTotalHoles = tee.getHoles().size();
		}
		return numTotalHoles;
	}
	
	/**
	 * Returns the HoleDAO given the inputted tee color id and hole number.
	 * @param aTeeColorId
	 * @param aHoleNumber
	 * @return
	 */
	public HoleDAO getHole(int aTeeColorId, int aHoleNumber) {
		
		Set<TeeDAO> tees;
		Set<HoleDAO> holes;
		HoleDAO hole;
		
		hole = null;
		tees = getTees();
		for (TeeDAO tee : tees) {
			if ((Integer)tee.getTeeName().getId() == aTeeColorId) {
				holes = tee.getHoles();
				for (HoleDAO tmpHole : holes) {
					if (tmpHole.getNumber() == aHoleNumber) {
						hole = tmpHole;
						break;
					}
				}
			}
		}
		return hole;
	}
	
	/**
	 * Returns the slope-rating DAO given the inputted parameters
	 * @param aTeeColorId
	 * @param aStartingHole
	 * @return
	 */
	public CourseSlopeRatingDAO getSlopeRating(int aTeeColorId, 
			int aStartingHole) {
		
		Set<TeeDAO> tees;
		Set<CourseSlopeRatingDAO> slopeRatingValues;
		//CourseSlopeRatingId slopeRatingId;
		TeeId teeId;
		
		tees = this.getTees();
		for (TeeDAO tee : tees) {
			teeId = (TeeId)tee.getId();
			if (teeId.getTeeNameId() == aTeeColorId) {
				slopeRatingValues = tee.getSlopeRatingValues();
				for (CourseSlopeRatingDAO slopeRating : slopeRatingValues) {
					//slopeRatingId = (CourseSlopeRatingId)slopeRating.getId();
					//if (slopeRatingId.getNineType() == aStartingHole) {
					if (slopeRating.getStartingHole() == aStartingHole) {
						return slopeRating;
					}
				}	
			}
		}
		return null;
	}
	
	/**
	 * Returns the tee given the inputted tee id
	 * @param aTeeId
	 * @return
	 */
	public TeeDAO getTee(TeeId aTeeId) {
		
		Set<TeeDAO> tees;
		TeeId teeId;
		
		tees = this.getTees();
		for (TeeDAO tee : tees) {
			teeId = (TeeId)tee.getId();
			if (teeId.getTeeNameId() == aTeeId.getTeeNameId()) {
				return tee;
			}
		}
		return null;
	}
	
	/**
	 * Loads the address object associated with this course
	 *
	 */
	public void loadAddress() {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		course = (Course)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Course course;
				
				course = (Course)aSession.load(Course.class, getId());
				Hibernate.initialize(course);
				Hibernate.initialize(course.getStateProvince());
				Hibernate.initialize(
						course.getStateProvince().getCountry());
				return course;
			}
		});
	}
	
	/**
	 * Loads the address object associated with this course
	 *
	 */
	public void loadTees() {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		course = (Course)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Course course;
				Set<Tee> tees;
				
				course = (Course)aSession.load(Course.class, getId());
				Hibernate.initialize(course);
				Hibernate.initialize(tees = course.getTees());
				for (Tee tee : tees) {
					Hibernate.initialize(tee);
					Hibernate.initialize(tee.getTeeName());
				}
				return course;
			}
		});
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		course = (Course)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Course course;
				Set<Tee> tees;
				Set<Hole> holes;
				Set<CourseSlopeRating> slopeRatingValues;
				
				course = (Course)aSession.load(Course.class, getId());
				Hibernate.initialize(course);
				Hibernate.initialize(course.getStateProvince());
				Hibernate.initialize(
						course.getStateProvince().getCountry());
				Hibernate.initialize(tees = course.getTees());
				for (Tee tee : tees) {
					Hibernate.initialize(tee);
					Hibernate.initialize(tee.getTeeName());
					holes = tee.getHoles();
					for (Hole hole : holes) {
						Hibernate.initialize(hole);
						Hibernate.initialize(hole.getTee());
						Hibernate.initialize(hole.getTee().getTeeName());
					}
					slopeRatingValues = tee.getCourseSlopeRatings();
					for (CourseSlopeRating slopeRating : slopeRatingValues) {
						Hibernate.initialize(slopeRating);
					}
				}
				return course;
			}
		});
	}
}
