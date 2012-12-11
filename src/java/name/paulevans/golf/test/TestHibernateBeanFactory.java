package name.paulevans.golf.test;

import junit.framework.TestCase;
import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.dao.impl.hibernate.HibernateApproachShotDistanceDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateApproachShotLineDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateCourseDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateDAOUtils;
import name.paulevans.golf.dao.impl.hibernate.HibernateEyeWearDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateGolfClubDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateGreenInRegulationDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateHeadWearDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateHoleDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernatePantWearDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernatePlayerDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateScoreDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateScorecardDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateScorecardSummaryDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateStateProvinceDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateTeeDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateTeeNameDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateTeeShotAccuracyDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateUserRoleDAO;
import name.paulevans.golf.dao.impl.hibernate.HibernateWeatherConditionDAO;
import name.paulevans.golf.util.NewUtil;

/**
 * Test fixture for BeanFactory
 * @author pevans
 *
 */
public class TestHibernateBeanFactory extends TestCase {

	/**
	 * Test case for BeanFactory.newGolfClubDAO(int)
	 *
	 */
	public final void testNewGolfClubDAOInt() {
		
		HibernateGolfClubDAO golfClub;
		Integer paramVal;
		
		paramVal = 7;
		golfClub = (HibernateGolfClubDAO)BeanFactory.newGolfClubDAO(paramVal);
		assertNotNull(golfClub);
		assertNotNull(golfClub.getDelegate());
		assertNotNull(golfClub.getSessionFactory());
		assertEquals(paramVal, golfClub.getId());
	}

	/**
	 * Test case for BeanFactory.newGolfClubDAO()
	 *
	 */
	public final void testNewGolfClubDAO() {
		
		HibernateGolfClubDAO golfClub;
		
		golfClub = (HibernateGolfClubDAO)BeanFactory.newGolfClubDAO();
		assertNotNull(golfClub);
		assertNotNull(golfClub.getDelegate());
		assertNotNull(golfClub.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newTeeShotAccuracyDAO(int)
	 *
	 */
	public final void testNewTeeShotAccuracyDAOInt() {
		
		HibernateTeeShotAccuracyDAO teeShotAccuracy;
		Integer paramVal;
		
		paramVal = 7;
		teeShotAccuracy = (HibernateTeeShotAccuracyDAO)
		BeanFactory.newTeeShotAccuracyDAO(paramVal);
		assertNotNull(teeShotAccuracy);
		assertNotNull(teeShotAccuracy.getDelegate());
		assertNotNull(teeShotAccuracy.getSessionFactory());
		assertEquals(paramVal, teeShotAccuracy.getId());
	}

	/**
	 * Test case for BeanFactory.newTeeShotAccuracyDAO()
	 *
	 */
	public final void testNewTeeShotAccuracyDAO() {
		
		HibernateTeeShotAccuracyDAO teeShotAccuracy;
		
		teeShotAccuracy = (HibernateTeeShotAccuracyDAO)
		BeanFactory.newTeeShotAccuracyDAO();
		assertNotNull(teeShotAccuracy);
		assertNotNull(teeShotAccuracy.getDelegate());
		assertNotNull(teeShotAccuracy.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newGreenInRegulationDAO(int)
	 *
	 */
	public final void testNewGreenInRegulationDAOInt() {
		
		HibernateGreenInRegulationDAO gir;
		Integer paramValue = 19;
		
		gir = (HibernateGreenInRegulationDAO)
		BeanFactory.newGreenInRegulationDAO(paramValue);
		assertNotNull(gir);
		assertNotNull(gir.getDelegate());
		assertNotNull(gir.getSessionFactory());
		assertEquals(paramValue, gir.getId());
	}

	
	/**
	 * Test case for BeanFactory.newGreenInRegulationDAO()
	 *
	 */
	public final void testNewGreenInRegulationDAO() {
		
		HibernateGreenInRegulationDAO gir;
		
		gir = (HibernateGreenInRegulationDAO)
		BeanFactory.newGreenInRegulationDAO();
		assertNotNull(gir);
		assertNotNull(gir.getDelegate());
		assertNotNull(gir.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newApproachShotLineDAO(int)
	 *
	 */
	public final void testNewApproachShotLineDAOInt() {
		
		HibernateApproachShotLineDAO approahShotLine;
		Integer paramValue = 19;
		
		approahShotLine = (HibernateApproachShotLineDAO)
		BeanFactory.newApproachShotLineDAO(paramValue);
		assertNotNull(approahShotLine);
		assertNotNull(approahShotLine.getDelegate());
		assertNotNull(approahShotLine.getSessionFactory());
		assertEquals(paramValue, approahShotLine.getId());
	}

	/**
	 * Test case for BeanFactory.newApproachShotLineDAO()
	 *
	 */
	public final void testNewApproachShotLineDAO() {
		
		HibernateApproachShotLineDAO approahShotLine;
		
		approahShotLine = (HibernateApproachShotLineDAO)
		BeanFactory.newApproachShotLineDAO();
		assertNotNull(approahShotLine);
		assertNotNull(approahShotLine.getDelegate());
		assertNotNull(approahShotLine.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newApproachShotDistanceDAO(int)
	 *
	 */
	public final void testNewApproachShotDistanceDAOInt() {
		
		HibernateApproachShotDistanceDAO approahShotDistance;
		Integer paramValue = 23;
		
		approahShotDistance = (HibernateApproachShotDistanceDAO)
		BeanFactory.newApproachShotDistanceDAO(paramValue);
		assertNotNull(approahShotDistance);
		assertNotNull(approahShotDistance.getDelegate());
		assertNotNull(approahShotDistance.getSessionFactory());
		assertEquals(paramValue, approahShotDistance.getId());
	}

	/**
	 * Test case for BeanFactory.newApproachShotDistanceDAO()
	 *
	 */
	public final void testNewApproachShotDistanceDAO() {
		
		HibernateApproachShotDistanceDAO approahShotDistance;
		
		approahShotDistance = (HibernateApproachShotDistanceDAO)
		BeanFactory.newApproachShotDistanceDAO();
		assertNotNull(approahShotDistance);
		assertNotNull(approahShotDistance.getDelegate());
		assertNotNull(approahShotDistance.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newScoreDAO()
	 *
	 */
	public final void testNewScoreDAO() {
		
		HibernateScoreDAO score;
		
		score = (HibernateScoreDAO)BeanFactory.newScoreDAO();
		assertNotNull(score);
		assertNotNull(score.getDelegate());
		assertNotNull(score.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newScorecardDAO()
	 *
	 */
	public final void testNewScorecardDAO() {
		
		HibernateScorecardDAO scorecard;
		
		scorecard = (HibernateScorecardDAO)BeanFactory.newScorecardDAO();
		assertNotNull(scorecard);
		assertNotNull(scorecard.getDelegate());
		assertNotNull(scorecard.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newScorecardSummaryDAO()
	 *
	 */
	public final void testNewScorecardSummaryDAO() {
		
		HibernateScorecardSummaryDAO scorecardSummary;
		
		scorecardSummary = (HibernateScorecardSummaryDAO)
		BeanFactory.newScorecardSummaryDAO();
		assertNotNull(scorecardSummary);
		assertNotNull(scorecardSummary.getDelegate());
		assertNotNull(scorecardSummary.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newStateProvinceDAO()
	 *
	 */
	public final void testNewStateProvinceDAO() {
		
		HibernateStateProvinceDAO stateProvince;
		
		stateProvince = (HibernateStateProvinceDAO)
		BeanFactory.newStateProvinceDAO();
		assertNotNull(stateProvince);
		assertNotNull(stateProvince.getDelegate());
		assertNotNull(stateProvince.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newCourseDAO()
	 *
	 */
	public final void testNewCourseDAO() {
		
		HibernateCourseDAO course;
		
		course = (HibernateCourseDAO)BeanFactory.newCourseDAO();
		assertNotNull(course);
		assertNotNull(course.getDelegate());
		assertNotNull(course.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newPantWearDAO()
	 *
	 */
	public final void testNewPantWearDAO() {
		
		HibernatePantWearDAO pantWear;
		
		pantWear = (HibernatePantWearDAO)BeanFactory.newPantWearDAO();
		assertNotNull(pantWear);
		assertNotNull(pantWear.getDelegate());
		assertNotNull(pantWear.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newWeatherConditionDAO()
	 *
	 */
	public final void testNewWeatherConditionDAO() {
		
		HibernateWeatherConditionDAO weatherCondition;
		
		weatherCondition = (HibernateWeatherConditionDAO)
		BeanFactory.newWeatherConditionDAO();
		assertNotNull(weatherCondition);
		assertNotNull(weatherCondition.getDelegate());
		assertNotNull(weatherCondition.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newHeadWearDAO()
	 *
	 */
	public final void testNewHeadWearDAO() {
		
		HibernateHeadWearDAO headWear;
		
		headWear = (HibernateHeadWearDAO)BeanFactory.newHeadWearDAO();
		assertNotNull(headWear);
		assertNotNull(headWear.getDelegate());
		assertNotNull(headWear.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newEyeWearDAO()
	 *
	 */
	public final void testNewEyeWearDAO() {
		
		HibernateEyeWearDAO eyeWear;
		
		eyeWear = (HibernateEyeWearDAO)BeanFactory.newEyeWearDAO();
		assertNotNull(eyeWear);
		assertNotNull(eyeWear.getDelegate());
		assertNotNull(eyeWear.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newTeeColorDAO()
	 *
	 */
	public final void testNewTeeColorDAO() {
		
		HibernateTeeNameDAO teeColor;
		
		teeColor = (HibernateTeeNameDAO)BeanFactory.newTeeNameDAO();
		assertNotNull(teeColor);
		assertNotNull(teeColor.getDelegate());
		assertNotNull(teeColor.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newTeeDAO()
	 *
	 */
	public final void testNewTeeDAO() {
		
		HibernateTeeDAO tee;
		
		tee = (HibernateTeeDAO)BeanFactory.newTeeDAO();
		assertNotNull(tee);
		assertNotNull(tee.getDelegate());
		assertNotNull(tee.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newHoleDAO()
	 *
	 */
	public final void testNewHoleDAO() {
		
		HibernateHoleDAO hole;
		
		hole = (HibernateHoleDAO)BeanFactory.newHoleDAO();
		assertNotNull(hole);
		assertNotNull(hole.getDelegate());
		assertNotNull(hole.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newPlayerDAO()
	 *
	 */
	public final void testNewPlayerDAO() {
		
		HibernatePlayerDAO player;
		
		player = (HibernatePlayerDAO)BeanFactory.newPlayerDAO();
		assertNotNull(player);
		assertNotNull(player.getDelegate());
		assertNotNull(player.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.newUserRoleDAO()
	 *
	 */
	public final void testNewUserRoleDAO() {
		
		HibernateUserRoleDAO userRole;
		
		userRole = (HibernateUserRoleDAO)BeanFactory.newUserRoleDAO();
		assertNotNull(userRole);
		assertNotNull(userRole.getDelegate());
		assertNotNull(userRole.getSessionFactory());
	}

	/**
	 * Test case for BeanFactory.getUtilObject()
	 *
	 */
	public final void testGetUtilObject() {
		
		NewUtil util;
		
		util = (NewUtil)BeanFactory.getUtilObject();
		assertNotNull(util);
	}

	/**
	 * Test case for BeanFactory.getDAOUtils()
	 *
	 */
	public final void testGetDAOUtils() {
		
		HibernateDAOUtils daoUtils;
		
		daoUtils = (HibernateDAOUtils)BeanFactory.getDAOUtils();
		assertNotNull(daoUtils);
		assertNotNull(daoUtils.getSessionFactory());
		assertNotNull(daoUtils.getTransactionManager());
	}

}
