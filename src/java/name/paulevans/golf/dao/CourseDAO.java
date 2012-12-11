package name.paulevans.golf.dao;

import java.util.Set;

/**
 * Models a golf course.
 * @author Paul
 *
 */
public interface CourseDAO extends DAO {
	
	/**
	 * Returns the state-province for this course
	 * @return
	 */
	StateProvinceDAO getStateProvince();
	
	/**
	 * Sets the state-province for this course
	 * @param aStateProvinceDAO
	 */
	void setStateProvince(StateProvinceDAO aStateProvinceDAO);
	
	/**
	 * Returns the city this course is located in
	 * @return
	 */
	String getCity();
	
	/**
	 * Sets the city this course is located int
	 * @param aCity
	 */
	void setCity(String aCity);

	/**
	 * Get the description
	 * @return
	 */
	String getDescription();

	/**
	 * Set the description
	 * @param aDescription
	 */
	void setDescription(String aDescription);
	
	/**
	 * Returns the associated club name
	 * @return
	 */
	String getClubName();
	
	/**
	 * Sets the associated club name
	 * @param aClubName
	 */
	void setClubName(String aClubName);
	
	/**
	 * Returns the associated association name
	 * @return
	 */
	String getAssociationName();
	
	/**
	 * Sets the associated association name
	 * @param aAssociationName
	 */
	void setAssociationName(String aAssociationName);

	/**
	 * Get the tees
	 * @return
	 */
	Set<TeeDAO> getTees();

	/**
	 * Set the tees
	 * @param aTees
	 */
	void setTees(Set<TeeDAO> aTees);
	
	/**
	 * Returns the total number of holes this course has
	 * @return
	 */
	int getNumTotalHoles();
	
	/**
	 * Returns the HoleDAO given the inputted tee color id and hole number.
	 * @param aTeeColorId
	 * @param aHoleNumber
	 * @return
	 */
	HoleDAO getHole(int aTeeColorId, int aHoleNumber);
	
	/**
	 * Loads the address object associated with this course
	 *
	 */
	void loadAddress();
	
	/**
	 * Loads the tee objects associated with this course
	 *
	 */
	void loadTees();
	
	/**
	 * Sets the starting hole number for the front-nine
	 * @param aStartingHoleNum
	 */
	void setFrontNineStartingHole(int aStartingHoleNum);
	
	/**
	 * Returns the starting hole number for the front-nine
	 * @return
	 */
	int getFrontNineStartingHole();
	
	/**
	 * Sets the starting hole number for the back-nine
	 * @param aStartingHoleNum
	 */
	void setBackNineStartingHole(int aStartingHoleNum);
	
	/**
	 * Returns the starting hole number for the front-nine
	 * @return
	 */
	int getBackNineStartingHole();
	
	/**
	 * Returns the slope-rating DAO given the inputted parameters
	 * @param aTeeColorId
	 * @param aStartingHole
	 * @return
	 */
	CourseSlopeRatingDAO getSlopeRating(int aTeeColorId, int aStartingHole);
	
	/**
	 * Returns the tee given the inputted tee id
	 * @param aTeeId
	 * @return
	 */
	TeeDAO getTee(TeeId aTeeId);
}
