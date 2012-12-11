package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.Course;
import gen.hibernate.name.paulevans.golf.EyeWear;
import gen.hibernate.name.paulevans.golf.HeadWear;
import gen.hibernate.name.paulevans.golf.PantWear;
import gen.hibernate.name.paulevans.golf.Player;
import gen.hibernate.name.paulevans.golf.Scorecard;
import gen.hibernate.name.paulevans.golf.TeeName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.Constants;
import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.MasterSummary;
import name.paulevans.golf.dao.CourseDAO;
import name.paulevans.golf.dao.DAOUtils;
import name.paulevans.golf.dao.EyeWearDAO;
import name.paulevans.golf.dao.HeadWearDAO;
import name.paulevans.golf.dao.PantWearDAO;
import name.paulevans.golf.dao.PlayerDAO;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.dao.TeeNameDAO;
import name.paulevans.golf.util.NewUtil;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate player dao
 * @author Paul
 *
 */
public class HibernatePlayerDAO extends HibernateDAOAdapter 
implements PlayerDAO {
	
	// logger objects...
	private static final Logger logger = Logger.getLogger(
			HibernatePlayerDAO.class);
	private static Logger hqllogger = Logger.getLogger("HQL");

	private Player player; // delegate object - generated Hibernate type...
	private MasterSummary masterSummary; // summary object...
	private BigDecimal handicapIndex, reductionValue;
	private String reducedHandicapIndex;
	private ScorecardSummaryDAO rounds[], allRounds[];
	private ScorecardSummaryDAO recentMergedRounds[];
	private ScorecardSummaryDAO eligibleTournamentRounds[];
	private int numRounds;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernatePlayerDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aPlayerDelegate
	 */
	public HibernatePlayerDAO(Player aPlayerDelegate) {
		player = aPlayerDelegate;
	}
	
	/**
	 * Public constructor
	 * @param aId
	 * @param aLastName
	 * @param aFirstName
	 * @param aNumRounds
	 * @param aLastLogin
	 * @param aDateCreated
	 * @param aDateUpdated
	 */
	public HibernatePlayerDAO(Integer aId, String aLastName, String aFirstName,
			Integer aNumRounds, Date aLastLogin, Date aDateCreated,
			Date aDateUpdated, String aPostalCode, String aUserId) {
		player = new Player();
		player.setId(aId);
		player.setLastname(aLastName);
		player.setFirstname(aFirstName);
		numRounds = aNumRounds;
		player.setDateOfLastLogin(aLastLogin);
		player.setDateCreated(aDateCreated);
		player.setLastUpdateDate(aDateUpdated);
		player.setPostalCode(aPostalCode);
		player.setUserId(aUserId);
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aPlayerDelegate) {
		player = (Player)aPlayerDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return player;
	}

	/**
	 * Get the ID
	 */
	public Integer getId() {
		return player.getId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		player.setId((Integer)aId);
	}

	/**
	 * Returns the player's birthdate
	 * @return
	 */
	public Date getBirthdate() {
		return player.getBirthdate();
	}
	
	/**
	 * Sets the player's birthdate
	 * @param aBirthdate
	 *
	 */
	public void setBirthdate(Date aBirthdate) {
		player.setBirthdate(aBirthdate);
	}
	
	/**
	 * Returns the player's user id
	 * @return
	 */
	public String getUserId() {
		return player.getUserId();
	}
	
	/**
	 * Sets the player's user id
	 * @param aUserId
	 */
	public void setUserId(String aUserId) {
		player.setUserId(aUserId);
	}
	
	/**
	 * Returns the postal code of where the player lives
	 * @return
	 */
	public String getPostalCode() {
		return player.getPostalCode();
	}
	
	/**
	 * Sets the postal code of where the player lives
	 * @param aPostalCode
	 */
	public void setPostalCode(String aPostalCode) {
		player.setPostalCode(aPostalCode);
	}
	
	/**
	 * Get tee color typically played
	 * @return
	 */
	public TeeNameDAO getTeeName() {
		return new HibernateTeeNameDAO(player.getTeeName());
	}

	/**
	 * Set tee color typically played
	 * @param aTeeName
	 */
	public void setTeeName(TeeNameDAO aTeeName) {
		player.setTeeName((TeeName)aTeeName.getDelegate());
	}

	/**
	 * Get the home course
	 * @return
	 */
	public CourseDAO getCourse() {
		if (player.getCourse() != null) {
			return new HibernateCourseDAO(player.getCourse());
		} else {
			return null;
		}
	}

	/**
	 * Set the home course
	 * @param aCourse
	 */
	public void setCourse(CourseDAO aCourse) {
		player.setCourse(
				aCourse != null ? (Course)aCourse.getDelegate() : null);
	}

	/**
	 * Get the eye-wear typically used
	 * @return
	 */
	public EyeWearDAO getEyeWear() {
		return new HibernateEyeWearDAO(player.getEyeWear());
	}

	/**
	 * Set the eye-wear typically used
	 * @param aEyeWear
	 */
	public void setEyeWear(EyeWearDAO aEyeWear) {
		player.setEyeWear((EyeWear)aEyeWear.getDelegate());
	}

	/**
	 * Get the pant-wear typically used
	 * @return
	 */
	public PantWearDAO getPantWear() {
		return new HibernatePantWearDAO(player.getPantWear());
	}

	/**
	 * Set the pant-wear typically used
	 * @param aPantWear
	 */
	public void setPantWear(PantWearDAO aPantWear) {
		player.setPantWear((PantWear)aPantWear.getDelegate());
	}

	/**
	 * Get the head-wear typically used
	 * @return
	 */
	public HeadWearDAO getHeadWear() {
		return new HibernateHeadWearDAO(player.getHeadWear());
	}

	/**
	 * Set the head-wear typically used
	 * @param aHeadWear
	 */
	public void setHeadWear(HeadWearDAO aHeadWear) {
		player.setHeadWear((HeadWear)aHeadWear.getDelegate());
	}

	/**
	 * Get the email address
	 * @return
	 */
	public String getEmailAddress() {
		return player.getEmailAddress();
	}

	/**
	 * Set teh email address
	 * @param aEmailAddress
	 */
	public void setEmailAddress(String aEmailAddress) {
		player.setEmailAddress(aEmailAddress);
	}

	/**
	 * Get the first name
	 * @return
	 */
	public String getFirstName() {
		return player.getFirstname();
	}

	/**
	 * Set the first name
	 * @param aFirstName
	 */
	public void setFirstName(String aFirstName) {
		player.setFirstname(aFirstName);
	}

	/**
	 * Get the last name
	 * @return
	 */
	public String getLastName() {
		return player.getLastname();
	}

	/**
	 * Set the last name
	 * @param aLastName
	 */
	public void setLastName(String aLastName) {
		player.setLastname(aLastName);
	}

	/**
	 * Get the password
	 * @return
	 */
	public String getPasswd() {
		return player.getPasswd();
	}

	/**
	 * SEt the password
	 * @param aPassword
	 */
	public void setPasswd(String aPassword) {
		player.setPasswd(aPassword);
	}

	/**
	 * Get the GHIN number
	 * @return
	 */
	public String getGHINNumber() {
		return player.getGhinnumber();
	}

	/**
	 * Set the GHIN number
	 * @param aGHINNumber
	 */
	public void setGHINNumber(String aGHINNumber) {
		player.setGhinnumber(aGHINNumber);
	}

	/**
	 * Get if the player swings right-handed
	 * @return
	 */
	public boolean isSwingsRightHanded() {
		return BooleanUtils.isTrue(player.getSwingsRightHanded());
	}

	/**
	 * Set if the player swings right-handed
	 * @param aSwingsRightHanded
	 */
	public void setSwingsRightHanded(boolean aSwingsRightHanded) {
		player.setSwingsRightHanded(aSwingsRightHanded);
	}

	/**
	 * Get if the player putts right-handed
	 * @return
	 */
	public boolean isPuttsRightHanded() {
		return BooleanUtils.isTrue(player.getPuttsRightHanded());
	}

	/**
	 * Set if the player putts right-handed
	 * @param aPuttsRightHanded
	 */
	public void setPuttsRightHanded(boolean aPuttsRightHanded) {
		player.setPuttsRightHanded(aPuttsRightHanded);
	}

	/**
	 * Get the number of holes typically played
	 * @return
	 */
	public int getNumHolesPlayed() {
		return player.getNumHolesPlayed();
	}

	/**
	 * Set the number of holes typically played
	 * @param aNumHolesTypicallyPlayed
	 */
	public void setNumHolesPlayed(int aNumHolesTypicallyPlayed) {
		player.setNumHolesPlayed(aNumHolesTypicallyPlayed);
	}

	/**
	 * Set if the player typically wears a vest 
	 * @return
	 */
	public boolean isWearsVest() {
		return BooleanUtils.isTrue(player.getWearsVest());
	}

	/**
	 * Get if the player typically wears a vest
	 * @param aWearsVest
	 */
	public void setWearsVest(boolean aWearsVest) {
		player.setWearsVest(aWearsVest);
	}
	
	/**
	 * Returns if the player typically uses a cart
	 * @return
	 */
	public boolean getUsesCart() {
		return BooleanUtils.isTrue(player.getUsesCart());
	}
	
	/**
	 * Sets if the player typically uses a cart
	 * @param aUsesCart
	 */
	public void setUsesCart(boolean aUsesCart) {
		player.setUsesCart(aUsesCart);
	}
	
	/**
	 * Returns if the player typically uses a caddie
	 * @return
	 */
	public boolean getUsesCaddie() {		
		return BooleanUtils.isTrue(player.getUsesCaddie());
	}
	
	/**
	 * Sets if the player typically uses a caddie
	 * @param aUsesCaddie
	 */
	public void setUsesCaddie(boolean aUsesCaddie) {
		player.setUsesCaddie(aUsesCaddie);
	}

	/**
	 * Get if the player typically wears long-sleeves
	 * @return
	 */
	public boolean isWearsLongSleeves() {
		return BooleanUtils.isTrue(player.getWearsLongSleeves());
	}

	/**
	 * Set if the player typically wears long-sleeves
	 * @param aWearsLongSleeves
	 */
	public void setWearsLongSleeves(boolean aWearsLongSleeves) {
		player.setWearsLongSleeves(aWearsLongSleeves);
	}
	
	/**
	 * Returns if the player typically collects the 'club used off tee' stat
	 * @return
	 */
    public boolean isCollectClubUsedOffTee() {
    	return player.isCollectClubUsedOffTee();
    }
    
    /**
     * Sets if the player typically collects the 'club used off tee' stat
     * @param aCollectClubUsedOffTee
     */
    public void setCollectClubUsedOffTee(boolean aCollectClubUsedOffTee) {
    	player.setCollectClubUsedOffTee(aCollectClubUsedOffTee);
    }
    
    /**
     * Returns if the player typically collects the 'tee shot distance' stat
     * @return
     */
    public boolean isCollectTeeShotDistance() {
    	return player.isCollectTeeShotDistance();
    }
    
    /**
     * Sets if the player typically collects the 'tee shot distance' stat
     * @param aCollectTeeShotDistance
     */
    public void setCollectTeeShotDistance(boolean aCollectTeeShotDistance) {
    	player.setCollectTeeShotDistance(aCollectTeeShotDistance);
    }
    
    /**
     * Returns if the player typically collects the 'num putts' stat
     * @return
     */
    public boolean isCollectNumPutts() {
    	return player.isCollectNumPutts();
    }
    
    /**
     * Sets if the player typically collects the 'num putts' stat
     * @param aCollectNumPutts
     */
    public void setCollectNumPutts(boolean aCollectNumPutts) {
    	player.setCollectNumPutts(aCollectNumPutts);
    }
    
    /**
     * Returns if the player typically collects the 'fairway hit' stat
     * @return
     */
    public boolean isCollectFairwayHit() {
    	return player.isCollectFairwayHit();
    }
    
    /**
     * Sets if the player typically collects the 'fairway hit' stat
     * @param aCollectFairwayHit
     */
    public void setCollectFairwayHit(boolean aCollectFairwayHit) {
    	player.setCollectFairwayHit(aCollectFairwayHit);
    }
    
    /**
     * Returns if the player typically collects the 'GIR' stat
     * @return
     */
    public boolean isCollectGir() {
    	return player.isCollectGir();
    }
    
    /**
     * Sets if the player typically collects the 'GIR' stat
     * @param aCollectGir
     */
    public void setCollectGir(boolean aCollectGir) {
    	player.setCollectGir(aCollectGir);
    }
    
    /**
     * Returns if the player typically collects the 'approach shot line' stat
     * @return
     */
    public boolean isCollectApproachShotLine() {
    	return player.isCollectApproachShotLine();
    }
    
    /**
     * Sets if the player typically collects the 'approach shot line' stat
     * @param aCollectApproachShotLine
     */
    public void setCollectApproachShotLine(boolean aCollectApproachShotLine) {
    	player.setCollectApproachShotLine(aCollectApproachShotLine);
    }
    
    /**
     * Returns if the player typically collects the 'approach shot distance' 
     * stat
     * @return
     */
    public boolean isCollectApproachShotDistance() {
    	return player.isCollectApproachShotDistance();
    }
    
    /**
     * Sets if the player typically collects the 'approach shot distance' stat
     * @param aCollectApproachShotDistance
     */
    public void setCollectApproachShotDistance(
    		boolean aCollectApproachShotDistance) {
    	player.setCollectApproachShotDistance(aCollectApproachShotDistance);
    }
    
    /**
     * Returns if the player typically collects the 'sand save' stat
     * @return
     */
    public boolean isCollectSandSave() {
    	return player.isCollectSandSave();
    }
    
    /**
     * Sets if the player typically collects the 'sand save' stat
     * @param aCollectSandSave
     */
    public void setCollectSandSave(boolean aCollectSandSave) {
    	player.setCollectSandSave(aCollectSandSave);
    }
    
    /**
     * Returns if the player typically collects the 'up and down' stat
     * @return
     */
    public boolean isCollectUpDown() {
    	return player.isCollectUpDown();
    }
    
    /**
     * Sets if the player typically collects the 'up and down' stat
     * @param aCollectUpDown
     */
    public void setCollectUpDown(boolean aCollectUpDown) {
    	player.setCollectUpDown(aCollectUpDown);
    }

	/**
	 * Get the scorecards
	 * @return
	 */
	public Set<ScorecardDAO> getScorecards() {
		
		Set<Scorecard> scorecards;
		Set<ScorecardDAO> scorecardDAOs;
		
		scorecards = player.getScorecards();
		scorecardDAOs = new HashSet<ScorecardDAO>();
		for (Scorecard scorecard : scorecards) {
			scorecardDAOs.add(new HibernateScorecardDAO(scorecard));
		}
		return scorecardDAOs;
	}
	
	/**
	 * Returns the total number of rounds posted to-date
	 * @return
	 */
	public int getTotalNumberOfRoundsPosted() {
		return masterSummary.getScorecardCount();
	}
	
	/**
	 * Refreshes the scorecard summary for this player from the database
	 *
	 */
	public void refreshSummary(final DAOUtils aDAOUtils, final NewUtil aUtil,
			final Locale aLocale) {
		
		// refresh the master summary...
		masterSummary = refreshMasterSummary(aLocale); 
		
		// refresh the player's rounds...
		refreshRounds(); 
		
		// refresh the player's rounds...
		refreshAllRounds();
		
		// merge any 9-hole rounds into 18-hole rounds...
		recentMergedRounds = NewUtil.mergeNineHoleRounds(rounds);
		
		// refresh the lowest-2 tournament rounds...
		refreshEligibleTournamentRounds(aUtil);
		
		// recalculate the player's handicap index...
		handicapIndex = NewUtil.calculateHandicapIndex(masterSummary, 
				recentMergedRounds, aDAOUtils);	
		if (handicapIndex != null) {
			reductionValue = NewUtil.calculateHandicapIndexReductionValue(
					handicapIndex, eligibleTournamentRounds, aDAOUtils);
			reducedHandicapIndex = NewUtil.calculateHandicapIndexReduced(
					handicapIndex, reductionValue);
		}
	}
	
	/**
	 * Helper method that creates the basic query for getting master summary 
	 * data that filters by the course played
	 * @param aCourseIDFilter
	 * @return
	 */
	private static String getMasterSummaryBasicQuery(CircumstancesBean aCircumstances) {
		
		String query;
		
		query = "select " +
		"avg(sum.score), " + 				// index 0
		"avg(sum.numEagles), " +			// index 1
		"avg(sum.numBirdies), " +			// index 2
		"avg(sum.numPars), " + 				// index 3
		"avg(sum.numBogeys), " + 			// index 4
		"avg(sum.numDblBogeys), " + 		// index 5
		"avg(sum.numTplBogeys), " + 		// index 6
		"avg(sum.par3Avg), " + 				// index 7
		"avg(sum.par4Avg), " + 				// index 8
		"avg(sum.par5Avg), " + 				// index 9
		"avg(sum.totalPutts), " + 			// index 10
		"avg(sum.totalPuttsGir), " + 		// index 11
		"avg(sum.avgPutts), " + 			// index 12
		"avg(sum.avgPuttsGir), " + 			// index 13
		"avg(sum.girAvg), " + 				// index 14
		"avg(sum.teeShotAccuracy), " +		// index 15
		"avg(sum.drivingDistanceAvg), " + 	// index 16
		"avg(sum.numSandSaveOpportunities), " + // index 17
		"avg(sum.sandSaveConvert), " + 			// index 18
		"avg(sum.numUpDownOpportunities), " + 	// index 19
		"avg(sum.upDownConvert), " +			// index 20
		"count(sum.scorecardId) " +				// index 21
			"from ScorecardSummary sum " +
		"where sum.scorecardId in (select sc.id from " +
		"Scorecard sc where sc.player.id = :playerId ";
		query += HibernateDAOUtils.getCircumstanceWhereClauseAddition(aCircumstances);
		query += ")";
		return query;
	}
	
	/**
	 * Helper-method that creates a MasterSummary object given the Hibernate
	 * result object array
	 * @param result
	 * @param aLocale
	 * @return
	 */
	private static MasterSummary createMasterSummaryFromResult(Object result[],
			Locale aLocale) {
		
		MasterSummary summary;
		
		if (result != null && result.length > 0 && result[0] != null) {
			summary = new MasterSummary(
			(Float)result[0], (Float)result[1], (Float)result[2],
			(Float)result[3], (Float)result[4], (Float)result[5],
			(Float)result[6], (Float)result[7], (Float)result[8],
			(Float)result[9], (Float)result[10], (Float)result[11],
			(Float)result[12], (Float)result[13], (Float)result[14],
			(Float)result[15], (Float)result[16], (Float)result[17],
			(Float)result[18], (Float)result[19], (Float)result[20],
			(Integer)result[21], aLocale);
		} else {
			summary = new MasterSummary(aLocale);
		}
		return summary;
	}
	
	/**
	 * Refreshes the masterSummary member variable
	 *
	 */
	private MasterSummary refreshMasterSummary(final Locale aLocale) {
		
		HibernateTemplate ht;
		MasterSummary calculatedSummary;
		
		ht = new HibernateTemplate(getSessionFactory());
		calculatedSummary = (MasterSummary)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Query query;
				
				query = aSession.createQuery(getMasterSummaryBasicQuery(null));
				query.setInteger("playerId", player.getId());
				return createMasterSummaryFromResult(
						(Object[])query.uniqueResult(), aLocale);
			}
		});
		return calculatedSummary;
	}
	
	/**
	 * Returns a new master summary object for this player
	 * @param aFromDate
	 * @param aToDate
	 * @return
	 */
	public MasterSummary calculateNewSummary(final Date aFromDate, 
			final Date aToDate, final CircumstancesBean aCircumstances, 
			final Locale aLocale) {
		
		HibernateTemplate ht;
		MasterSummary calculatedSummary;
		
		ht = new HibernateTemplate(getSessionFactory());
		calculatedSummary = (MasterSummary)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Query query;
				String strQuery;
				
				strQuery = getMasterSummaryBasicQuery(aCircumstances);
				if (aFromDate != null) {
					strQuery += " and sum.datePlayed >= :fromDate";
				}
				if (aToDate != null) {
					strQuery += " and sum.datePlayed <= :toDate";
				}
				hqllogger.info(strQuery);
				query = aSession.createQuery(strQuery);
				query.setInteger("playerId", player.getId());
				if (aFromDate != null) query.setDate("fromDate", aFromDate);
				if (aToDate != null) query.setDate("toDate", aToDate);
				HibernateDAOUtils.updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				return createMasterSummaryFromResult(
						(Object[])query.uniqueResult(), aLocale);
			}
		});
		return calculatedSummary;
	}
	
	/**
	 * Retrieves and returns the collection (maxsize of 2) of scorecard summary 
	 * objects from the data source.  Only the most-recent rounds are returned.
	 * @return
	 */
	private void refreshEligibleTournamentRounds(NewUtil aUtil) {
		
		String query;
		String compareToDate; 
		Calendar calendar;
		
		calendar = Calendar.getInstance(); // get todays date...
		calendar.add(Calendar.MONTH, 12); // add 12 months to todays date...
		compareToDate = aUtil.format(calendar.getTime()); // convert to string
		
		query = "SELECT sum.scorecardId, sum.courseId, course.description, " +  
		"country.name, stateProv.name, course.city, sum.datePlayed, " +  
		"sum.score, sum.numHolesPlayed, sum.scoreType, sum.slope, " +
		"sum.rating, sum.avgPutts, sum.avgPuttsGir " +
		"FROM ScorecardSummary sum, Country country, StateProvince stateProv, " +
		"Course course WHERE sum.courseId = course.id AND sum.scorecardId IN " +
		"(SELECT sc.id FROM Scorecard sc WHERE sc.player.id = :playerId) AND " +
		"((sum.slope IS NOT NULL) AND (sum.slope > 0)) AND " +
		"((sum.rating IS NOT NULL) AND (sum.rating > 0)) AND " +
		"sum.scoreType IN ('" + Constants.TOURNAMENT_INTERNET_SCORE_TYPE + 
		"', '" + Constants.TOURNAMENT_SCORE_TYPE + "') AND " +
		"sum.numHolesPlayed = 18 and sum.datePlayed < STR_TO_DATE('" + 
		compareToDate + "', '%m/%d/%Y') AND " +
		"country.id = course.stateProvince.country.id AND stateProv.id.id = " + 
		"course.stateProvince.id.id AND country.id = stateProv.country.id " +
		"ORDER BY sum.score ASC, sum.datePlayed DESC";
		eligibleTournamentRounds =  rounds(query, 
				Constants.NUM_ELIGIBLE_TOURNAMENT_ROUNDS, player.getId());
	}
	
	/**
	 * Retrieves and returns the collection of scorecard summary objects from
	 * the data source.  Only the most-recent rounds are returned.
	 * @return
	 */
	private void refreshRounds() {
		
		String query;
		
		// we sort by numHolesPlayed first because according to the USGA
		// handicap system manual, 18-hole rounds take precendence over
		// merged 9-holes even if 
		query = "SELECT sum.scorecardId, sum.courseId, course.description, " +  
		"country.name, stateProv.name, course.city, sum.datePlayed, " +  
		"sum.score, sum.numHolesPlayed, sum.scoreType, sum.slope, " +
		"sum.rating, sum.avgPutts, sum.avgPuttsGir " +
		"FROM ScorecardSummary sum, Country country, StateProvince stateProv, " +
		"Course course WHERE sum.courseId = course.id AND sum.scorecardId IN " +
		"(SELECT sc.id FROM Scorecard sc WHERE sc.player.id = :playerId) AND " +
		"country.id = course.stateProvince.country.id AND stateProv.id.id = " + 
		"course.stateProvince.id.id AND country.id = stateProv.country.id " +
		"ORDER BY sum.datePlayed DESC";
		rounds = rounds(query, Constants.MAX_ROUNDS, player.getId());
	}
	
	/**
	 * Retrieves and returns the collection of scorecard summary objects from
	 * the data source.  Only the most-recent rounds are returned.
	 * @return
	 */
	private void refreshAllRounds() {
		
		//String query;
		
		// we sort by numHolesPlayed first because according to the USGA
		// handicap system manual, 18-hole rounds take precendence over
		// merged 9-holes even if 
		//query = getAllRoundsSelectClause() + 
		//"FROM ScorecardSummary sum " +
		//"WHERE sum.scorecardId IN " +
		//"(SELECT sc.id FROM Scorecard sc WHERE sc.player.id = :playerId) " + 
		//"GROUP BY sum.datePlayed " + 
		//"ORDER BY sum.datePlayed ASC";
		allRounds = getAllRounds(null); //query, player.getId());
	}
	
	public ScorecardSummaryDAO[] getAllRounds(final CircumstancesBean aCircumstances) {
		return getAllRounds(Constants.EIGHTEEN, aCircumstances);
	}
	
	public ScorecardSummaryDAO[] getAllRounds(final int aNumHolesPlayed, 
			final CircumstancesBean aCircumstances) {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		return (ScorecardSummaryDAO[])ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Query query;
				List<ScorecardSummaryDAO> summaryDAOs;
				Object results[];
				List scorecardSummaries;
				ScorecardSummaryDAO summary;
				int numRecords, loop;
				String strQuery;
				
				// we sort by numHolesPlayed first because according to the USGA
				// handicap system manual, 18-hole rounds take precendence over
				// merged 9-holes even if 
				strQuery = "SELECT avg(sum.score), sum.datePlayed, avg(sum.avgPutts), " +
				"avg(sum.avgPuttsGir), avg(sum.totalPutts), " +
				"avg(sum.totalPuttsGir), avg(sum.teeShotAccuracy)," +
				"avg(sum.drivingDistanceAvg), avg(sum.longestDrive), " + 
				"avg(sum.girAvg), avg(sum.approachShotLine), " +
				"avg(sum.approachShotDistance), avg(sum.upDownConvert), " +
				"avg(sum.sandSaveConvert) FROM ScorecardSummary sum " +
				"WHERE sum.numHolesPlayed = :numHolesPlayed AND sum.scorecardId IN " +
				"(SELECT sc.id FROM Scorecard sc WHERE sc.player.id = :playerId ";
				strQuery += HibernateDAOUtils.getCircumstanceWhereClauseAddition(aCircumstances);
				strQuery += ") GROUP BY sum.datePlayed ORDER BY sum.datePlayed ASC";
				summaryDAOs = new ArrayList<ScorecardSummaryDAO>();
				hqllogger.info(strQuery);
				query = aSession.createQuery(strQuery);
				query.setInteger("playerId", player.getId());
				query.setInteger("numHolesPlayed", aNumHolesPlayed);
				HibernateDAOUtils.updateQueryObjectWithCircumstanceParamValues(aCircumstances, query);
				scorecardSummaries = query.list();
				numRecords = scorecardSummaries.size();
				for (loop = 0; loop < numRecords; loop++) {
					summary = BeanFactory.newScorecardSummaryDAO();
					results = (Object[])scorecardSummaries.get(loop);
					summary.setAvgScore((Float)results[0]);
					summary.setScore((int)summary.getAvgScore());
					summary.setDatePlayed((Date)results[1]);
					summary.setAvgPutts((Float)results[2]);
					summary.setAvgPuttsGir((Float)results[3]);
					summary.setTotalPutts((Float)results[4]);
					summary.setTotalPuttsGir((Float)results[5]);
					summary.setTeeShotAccuracy((Float)results[6]);
					summary.setDrivingDistanceAvg((Float)results[7]);
					summary.setLongestDrive((Float)results[8]);
					summary.setGirAvg((Float)results[9]);
					summary.setApproachShotLine((Float)results[10]);
					summary.setApproachShotDistance((Float)results[11]);
					summary.setUpDownConvert((Float)results[12]);
					summary.setSandSaveConvert((Float)results[13]);
					summaryDAOs.add(summary);
				}
				return summaryDAOs.toArray(
						new ScorecardSummaryDAO[summaryDAOs.size()]);
			}
		});
		
		
		//return allRounds(query, player.getId());
	}
	
	/**
	 * Returns this player's rounds (all of them)
	 */
	public ScorecardSummaryDAO[] getAllRounds() {
		return allRounds;
	} 
	
	/**
	 * Returns this player's most recent 40 rounds
	 */
	public ScorecardSummaryDAO[] getRounds() {
		return rounds;
	}
	
	/**
	 * Retrieves and returns the collection of scorecard summary objects from
	 * the data source given the inputted query
	 * @return
	 */
	/*private ScorecardSummaryDAO[] allRounds(final String aQuery, 
			final int aPlayerId) {
		 
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		return (ScorecardSummaryDAO[])ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Query query;
				List<ScorecardSummaryDAO> summaryDAOs;
				Object results[];
				List scorecardSummaries;
				ScorecardSummaryDAO summary;
				int numRecords, loop;
				
				summaryDAOs = new ArrayList<ScorecardSummaryDAO>();
				query = aSession.createQuery(aQuery);
				query.setInteger("playerId", aPlayerId);
				scorecardSummaries = query.list();
				numRecords = scorecardSummaries.size();
				for (loop = 0; loop < numRecords; loop++) {
					summary = BeanFactory.newScorecardSummaryDAO();
					results = (Object[])scorecardSummaries.get(loop);
					summary.setAvgScore((Float)results[0]);
					summary.setScore((int)summary.getAvgScore());
					summary.setDatePlayed((Date)results[1]);
					summary.setAvgPutts((Float)results[2]);
					summary.setAvgPuttsGir((Float)results[3]);
					summary.setTotalPutts((Float)results[4]);
					summary.setTotalPuttsGir((Float)results[5]);
					summary.setTeeShotAccuracy((Float)results[6]);
					summary.setDrivingDistanceAvg((Float)results[7]);
					summary.setLongestDrive((Float)results[8]);
					summary.setGirAvg((Float)results[9]);
					summary.setApproachShotLine((Float)results[10]);
					summary.setApproachShotDistance((Float)results[11]);
					summary.setUpDownConvert((Float)results[12]);
					summary.setSandSaveConvert((Float)results[13]);
					summaryDAOs.add(summary);
				}
				return summaryDAOs.toArray(
						new ScorecardSummaryDAO[summaryDAOs.size()]);
			}
		});
	}*/
	
	/**
	 * Retrieves and returns the collection of scorecard summary objects from
	 * the data source given the inputted query
	 * @return
	 */
	private ScorecardSummaryDAO[] rounds(final String aQuery, 
			final int aMaxResults, final int aPlayerId) {
		 
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		return (ScorecardSummaryDAO[])ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				Query query;
				List<ScorecardSummaryDAO> summaryDAOs;
				Object results[];
				List scorecardSummaries;
				ScorecardSummaryDAO summary;
				int numRecords, loop, numHolesPlayed, score;
				
				summaryDAOs = new ArrayList<ScorecardSummaryDAO>();
				query = aSession.createQuery(aQuery);
				if (aMaxResults != -1) {
					query = query.setMaxResults(aMaxResults);
				}           
				query.setInteger("playerId", aPlayerId);
				scorecardSummaries = query.list();
				numRecords = scorecardSummaries.size();
				for (loop = 0; loop < numRecords; loop++) {
					results = (Object[])scorecardSummaries.get(loop);
					score = (Integer)results[7];
					numHolesPlayed = (Integer)results[8];
					// divide 9-hole score in 2 since it is stored in the 
					// summary table as an 18-hole score...
					if (numHolesPlayed == 9) {
						score /= 2; 
					}
					summary = new HibernateScorecardSummaryDAO(
							(Integer)results[0], (Integer)results[1], 
							(String)results[2], (String)results[3], 
							(String)results[4], (String)results[5], 
							(Date)results[6], score, 
							(Integer)results[8], (String)results[9],
							(Integer)results[10], (Float)results[11],
							(Float)results[12], (Float)results[12]);
					summaryDAOs.add(summary);
				}
				return summaryDAOs.toArray(
						new ScorecardSummaryDAO[summaryDAOs.size()]);
			}
		});
	}
	
	/**
	 * Returns the master summary object for this player
	 */
	public MasterSummary getSummary() {
		return masterSummary;
	}
	
	/**
	 * Returns the date of the user's last login
	 * @return
	 */
	public Date getDateOfLastLogin() {
		return player.getDateOfLastLogin();
	}
	
	/**
	 * Sets the date of the user's last login
	 * @param aLastLogin
	 */
	public void setDateOfLastLogin(Date aLastLogin) {
		player.setDateOfLastLogin(aLastLogin);
	}

	/**
	 * Returns this player's handicap index
	 */
	public BigDecimal getHandicapIndex() {
		return handicapIndex;
	}
	
	/**
	 * Returns the player's reduced handicap index
	 * @return
	 */
	public String getReducedHandicapIndex() {
		return reducedHandicapIndex;
	}

	/**
	 * Returns the player's most recent rounds; 9-hole rounds have been 
	 * merged.
	 * @return
	 */
	public final ScorecardSummaryDAO[] getRecentMergedRounds() {
		return recentMergedRounds;
	}

	/**
	 * Returns the player's lowest-2 eligible tournament rounds
	 * @return
	 */
	public final ScorecardSummaryDAO[] getEligibleTournamentRounds() {
		return eligibleTournamentRounds;
	}

	/**
	 * Returns the handicap index reduction value
	 * @return
	 */
	public final BigDecimal getReductionValue() {
		return reductionValue;
	}
	
    /**
     * Returns the date created
     * @return
     */
    public Date getDateCreated() {
    	return player.getDateCreated();
    }
    
    /**
     * Sets the date created
     * @param aDateCreated
     */
    public void setDateCreated(Date aDateCreated) {
    	player.setDateCreated(aDateCreated);
    }
    
    /**
     * Returns the last update date
     * @return
     */
    public Date getLastUpdateDate() {
    	return player.getLastUpdateDate();
    }
    
    /**
     * Sets the last update date
     * @param aLastUpdateDate
     */
    public void setLastUpdateDate(Date aLastUpdateDate) {
    	player.setLastUpdateDate(aLastUpdateDate);
    }

	/**
	 * @return the numRounds
	 */
	public final int getNumRounds() {
		return numRounds;
	}
}
