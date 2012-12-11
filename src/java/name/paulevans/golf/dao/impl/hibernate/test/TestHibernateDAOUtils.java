package name.paulevans.golf.dao.impl.hibernate.test;

import java.math.BigDecimal;

import junit.framework.TestCase;
import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.DAOUtils;

/**
 * Test class for HibernateDAOUtils
 * @author pevans
 *
 */
public class TestHibernateDAOUtils extends TestCase {
	
	private DAOUtils utils; 

	/**
	 * Test fixture for getTournamentScoreTableValue(int, float)
	 *
	 */
	public final void testGetTournamentScoreTableValue() {
		
		// get the utils object...
		utils = BeanFactory.getDAOUtils();
		
		// assert valid values...
		assertEquals(1.9F, utils.getTournamentScoreTableValue(5, 
				BigDecimal.valueOf(6.1F)).floatValue());
		assertNull(utils.getTournamentScoreTableValue(1, 
				BigDecimal.valueOf(3.0F)).floatValue());
		assertEquals(4.8F, utils.getTournamentScoreTableValue(12, 
				BigDecimal.valueOf(8.1F)).floatValue());
		assertEquals(13.7F, utils.getTournamentScoreTableValue(2, 
				BigDecimal.valueOf(19.7F)).floatValue());
		assertEquals(12.6F, utils.getTournamentScoreTableValue(68, 
				BigDecimal.valueOf(14.0F)).floatValue());
	}
}
