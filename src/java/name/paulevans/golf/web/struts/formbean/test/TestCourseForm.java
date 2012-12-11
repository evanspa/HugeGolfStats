package name.paulevans.golf.web.struts.formbean.test;

import junit.framework.TestCase;
import name.paulevans.golf.dao.CourseSlopeRatingDAO;
import name.paulevans.golf.dao.CourseSlopeRatingId;
import name.paulevans.golf.web.struts.formbean.CourseForm;

/**
 * Test fixture for CourseForm class
 * @author pevans
 *
 */
public class TestCourseForm extends TestCase {
	
	private CourseForm form;

	/**
	 * Setup method
	 */
	protected void setUp() throws Exception {
		super.setUp();
		form = new CourseForm();
	}

	/**
	 * Test case for getCourseSlopeRatingValues()
	 *
	 */
	public final void testGetCourseSlopeRatingValues() {
		
		CourseSlopeRatingDAO values[];
		CourseSlopeRatingId id;
		
		form.setSlopeValue("1-9,10-18|2", "121");
		form.setRatingValue("1-9,10-18|2", "72");
		
		values = form.createCourseSlopeRatingValues();
		assertEquals(1, values.length);
		id = (CourseSlopeRatingId)values[0].getId();
		//assertEquals(1, id.getStartingHole1());
		//assertEquals(10, id.getStartingHole2());
		assertEquals(2, id.getTeeNameId());
		assertEquals(121.0F, values[0].getSlope());
		assertEquals(72.0F, values[0].getRating());
	}
}
