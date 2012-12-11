package name.paulevans.golf.dao;

import java.util.Date;



/**
 * Models an immutable object to obtain the usage summary numbers
 * @author Paul
 *
 */
public interface UsageSummaryDAO extends DAO {

	/**
	 * Get the description
	 * @return
	 */
	int getTotalUserCount();
	
	/**
	 * Returns the total number of rounds entered into the system
	 * @return
	 */
	int getTotalNumRounds();
	
	/**
	 * Returns the total number of courses that exist within the system
	 * @return
	 */
	int getTotalNumCourses();
	
	/**
	 * Returns the average 18-hole score entered into the system
	 * @return
	 */
	float getAverageEighteenHoleScore();
	
	/**
	 * Returns the highest 18-hole score entered into the system
	 * @return
	 */
	int getHighestEighteenHoleScore();
	
	/**
	 * Returns the lowest 18-hole score entered into the system
	 * @return
	 */
	int getLowestEighteenHoleScore();
	
	/**
	 * Returns the date of the last score entered
	 * @return
	 */
	Date getDateOfLastScoreEntered();
	
	/**
	 * Returns the date and time of the last user-login
	 * @return
	 */
	Date getDateOfLastLogin();
}
