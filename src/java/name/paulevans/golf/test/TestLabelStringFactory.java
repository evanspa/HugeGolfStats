package name.paulevans.golf.test;

import java.util.Locale;

import junit.framework.TestCase;
import name.paulevans.golf.LabelStringFactory;

/**
 * Test fixture for LabelStringFactory
 * @author pevans
 *
 */
public class TestLabelStringFactory extends TestCase {
	
	private LabelStringFactory stringFactory;

	/**
	 * setup
	 */
	protected void setUp() throws Exception {
		super.setUp();
		stringFactory = LabelStringFactory.getInstance(Locale.getDefault());
	}

	/**
	 * Test case for getString()
	 *
	 */
	public final void testGetString() {
		
		String heading;
		
		heading = stringFactory.getString("header.heading");
		assertNotNull(heading);
		assertEquals("Golf Analysis Application", heading);
	}

	/**
	 * Test case for getInstance()
	 *
	 */
	public final void testGetInstance() {
		assertNotNull(stringFactory);
	}
}
