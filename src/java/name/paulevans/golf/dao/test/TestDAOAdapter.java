package name.paulevans.golf.dao.test;

import junit.framework.TestCase;
import name.paulevans.golf.dao.DAOAdapter;

/**
 * Test fixture for DAOAdapter
 * @author pevans
 *
 */
public class TestDAOAdapter extends TestCase {
	
	private DAOAdapter daoAdapter;

	protected void setUp() throws Exception {
		super.setUp();
		
		daoAdapter = new MockDAOAdapter();
	}

	/**
	 * Test fixture for load()
	 *
	 */
	public final void testLoad() {
		try {
			daoAdapter.load();
			fail("should not have reached this point");
		} catch (Exception any) {
			// do nothing...
		}
	}

	/**
	 * Test fixture for save()
	 *
	 */
	public final void testSoad() {
		try {
			daoAdapter.save();
			fail("should not have reached this point");
		} catch (Exception any) {
			// do nothing...
		}
	}

	/**
	 * Test fixture for delete()
	 *
	 */
	public final void testDelete() {
		try {
			daoAdapter.delete();
			fail("should not have reached this point");
		} catch (Exception any) {
			// do nothing...
		}
	}

	/**
	 * Test fixture for setDelegate(Object)
	 *
	 */
	public final void testSetDelegate() {


		Object delegate;
		
		delegate = new Integer(5);
		daoAdapter.setDelegate(delegate);
	}

	/**
	 * Test fixture for getDelegate()
	 *
	 */
	public final void testGetDelegate() {
		assertNull(daoAdapter.getDelegate());
		daoAdapter.setDelegate((Integer)5);
		assertNull(daoAdapter.getDelegate());
	}
}
