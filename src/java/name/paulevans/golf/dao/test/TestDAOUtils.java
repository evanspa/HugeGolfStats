package name.paulevans.golf.dao.test;

import java.util.Map;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.test.GolfDatabaseTestCase;
import name.paulevans.golf.test.TestUtils;

/**
 * Test fixture for DAOUtils
 * @author pevans
 *
 */
public class TestDAOUtils extends GolfDatabaseTestCase {
	
	private DAOUtils daoUtils;

	protected void setUp() throws Exception {
		super.setUp();
		
		//daoUtils = new MockDAOUtils();
	}

	/**
	 * Test case for getHoles(CourseDAO, int)
	 *
	 */
	public final void testGetHolesCourseDAOInt() {
		
		CourseDAO course;
		Map<String,HoleDAO> holes;
		
		// create a course object...
		course = BeanFactory.newCourseDAO();
		
		// set the id and load the course...
		course.setId((Integer)TestUtils.COURSE_STADIUM);
		course.load();
		
		// get the holes...
		holes = daoUtils.getHoles(course, Constants.WHITE_TEE_COLOR_ID);
		assertTrue(holes.size() == 18); 		
	}

	public final void testGetTransactionManager() {
		fail("Not yet implemented");
	}

	public final void testSetTransactionManager() {
		fail("Not yet implemented");
	}

	public final void testGetHolesSetOfHoleDAOInt() {
		fail("Not yet implemented");
	}

	public final void testSaveScorecard() {
		fail("Not yet implemented");
	}

	public final void testSaveAccount() {
		fail("Not yet implemented");
	}

	public final void testSaveCourse() {
		fail("Not yet implemented");
	}

	public final void testLoadObjectsEyeWearDAOHeadWearDAOPantWearDAOTeeColorDAO() {
		fail("Not yet implemented");
	}

	public final void testLoadObjectsCourseDAOTeeColorDAO() {
		fail("Not yet implemented");
	}

	public final void testLoadObjectsEyeWearDAOHeadWearDAOPantWearDAOWeatherConditionDAO() {
		fail("Not yet implemented");
	}

	public final void testInCollection() {
		fail("Not yet implemented");
	}

}
