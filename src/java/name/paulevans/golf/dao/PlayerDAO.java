package name.paulevans.golf.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import name.paulevans.golf.bean.CircumstancesBean;
import name.paulevans.golf.bean.MasterSummary;
import name.paulevans.golf.util.NewUtil;



/**
 * Models a player.
 * @author Paul
 *
 */
public interface PlayerDAO extends DAO {
	
	/**
	 * Returns the number of rounds posted by this player
	 * @return
	 */
	int getNumRounds();

	/**
	 * Get tee color typically played
	 * @return
	 */
	TeeNameDAO getTeeName();

	/**
	 * Set tee color typically played
	 * @param aTeeName
	 */
	void setTeeName(TeeNameDAO aTeeName);

	/**
	 * Get the home course
	 * @return
	 */
	CourseDAO getCourse();

	/**
	 * Set the home course
	 * @param aCourse
	 */
	void setCourse(CourseDAO aCourse);

	/**
	 * Get the eye-wear typically used
	 * @return
	 */
	EyeWearDAO getEyeWear();
	
	/**
	 * Returns the player's birthdate
	 * @return
	 */
	Date getBirthdate();
	
	/**
	 * Sets the player's birthdate
	 * @param aBirthdate
	 *
	 */
	void setBirthdate(Date aBirthdate);
	
	/**
	 * Returns the postal code of where the player lives
	 * @return
	 */
	String getPostalCode();
	
	/**
	 * Sets the postal code of where the player lives
	 * @param aPostalCode
	 */
	void setPostalCode(String aPostalCode);
	
	/**
	 * Returns the player's user id
	 * @return
	 */
	String getUserId();
	
	/**
	 * Sets the player's user id
	 * @param aUserId
	 */
	void setUserId(String aUserId);

	/**
	 * Set the eye-wear typically used
	 * @param aEyeWear
	 */
	void setEyeWear(EyeWearDAO aEyeWear);

	/**
	 * Get the pant-wear typically used
	 * @return
	 */
	PantWearDAO getPantWear();

	/**
	 * Set the pant-wear typically used
	 * @param aPantWear
	 */
	void setPantWear(PantWearDAO aPantWear);

	/**
	 * Get the head-wear typically used
	 * @return
	 */
	HeadWearDAO getHeadWear();

	/**
	 * Set the head-wear typically used
	 * @param aHeadWear
	 */
	void setHeadWear(HeadWearDAO aHeadWear);

	/**
	 * Get the email address
	 * @return
	 */
	String getEmailAddress();

	/**
	 * Set teh email address
	 * @param aEmailAddress
	 */
	void setEmailAddress(String aEmailAddress);

	/**
	 * Get the first name
	 * @return
	 */
	String getFirstName();

	/**
	 * Set the first name
	 * @param aFirstName
	 */
	void setFirstName(String aFirstName);

	/**
	 * Get the last name
	 * @return
	 */
	String getLastName();

	/**
	 * Set the last name
	 * @param aLastName
	 */
	void setLastName(String aLastName);

	/**
	 * Get the password
	 * @return
	 */
	String getPasswd();

	/**
	 * SEt the password
	 * @param aPassword
	 */
	void setPasswd(String aPassword);

	/**
	 * Get the GHIN number
	 * @return
	 */
	String getGHINNumber();

	/**
	 * Set the GHIN number
	 * @param aGHINNumber
	 */
	void setGHINNumber(String aGHINNumber);

	/**
	 * Get if the player swings right-handed
	 * @return
	 */
	boolean isSwingsRightHanded();

	/**
	 * Set if the player swings right-handed
	 * @param aSwingsRightHanded
	 */
	void setSwingsRightHanded(boolean aSwingsRightHanded);

	/**
	 * Get if the player putts right-handed
	 * @return
	 */
	boolean isPuttsRightHanded();
	
	/**
	 * Returns if the player typically uses a cart
	 * @return
	 */
	boolean getUsesCart();
	
	/**
	 * Sets if the player typically uses a cart
	 * @param aUsesCart
	 */
	void setUsesCart(boolean aUsesCart);
	
	/**
	 * Returns if the player typically uses a caddie
	 * @return
	 */
	boolean getUsesCaddie();
	
	/**
	 * Sets if the player typically uses a caddie
	 * @param aUsesCaddie
	 */
	void setUsesCaddie(boolean aUsesCaddie);

	/**
	 * Set if the player putts right-handed
	 * @param aPuttsRightHanded
	 */
	void setPuttsRightHanded(boolean aPuttsRightHanded);

	/**
	 * Get the number of holes typically played
	 * @return
	 */
	int getNumHolesPlayed();

	/**
	 * Set the number of holes typically played
	 * @param aNumHolesTypicallyPlayed
	 */
	void setNumHolesPlayed(int aNumHolesTypicallyPlayed);

	/**
	 * Set if the player typically wears a vest 
	 * @return
	 */
	boolean isWearsVest();

	/**
	 * Get if the player typically wears a vest
	 * @param aWearsVest
	 */
	void setWearsVest(boolean aWearsVest);

	/**
	 * Get if the player typically wears long-sleeves
	 * @return
	 */
	boolean isWearsLongSleeves();

	/**
	 * Set if the player typically wears long-sleeves
	 * @param aWearsLongSleeves
	 */
	void setWearsLongSleeves(boolean aWearsLongSleeves);
	
	/**
	 * Returns the player's handicap index or null if the player has less than
	 * 5 rounds posted
	 * @return
	 */
	BigDecimal getHandicapIndex();

	/**
	 * Returns the player's reduced handicap index
	 * @return
	 */
	String getReducedHandicapIndex();
	
	/**
	 * Returns if the player typically collects the 'club used off tee' stat
	 * @return
	 */
    boolean isCollectClubUsedOffTee();
    
    /**
     * Sets if the player typically collects the 'club used off tee' stat
     * @param aCollectClubUsedOffTee
     */
    void setCollectClubUsedOffTee(boolean aCollectClubUsedOffTee);
    
    /**
     * Returns if the player typically collects the 'tee shot distance' stat
     * @return
     */
    boolean isCollectTeeShotDistance();
    
    /**
     * Sets if the player typically collects the 'tee shot distance' stat
     * @param aCollectTeeShotDistance
     */
    void setCollectTeeShotDistance(boolean aCollectTeeShotDistance);
    
    /**
     * Returns if the player typically collects the 'num putts' stat
     * @return
     */
    boolean isCollectNumPutts();
    
    /**
     * Sets if the player typically collects the 'num putts' stat
     * @param aCollectNumPutts
     */
    void setCollectNumPutts(boolean aCollectNumPutts);
    
    /**
     * Returns if the player typically collects the 'fairway hit' stat
     * @return
     */
    boolean isCollectFairwayHit();
    
    /**
     * Sets if the player typically collects the 'fairway hit' stat
     * @param aCollectFairwayHit
     */
    void setCollectFairwayHit(boolean aCollectFairwayHit);
    
    /**
     * Returns if the player typically collects the 'GIR' stat
     * @return
     */
    boolean isCollectGir();
    
    /**
     * Sets if the player typically collects the 'GIR' stat
     * @param aCollectGir
     */
    void setCollectGir(boolean aCollectGir);
    
    /**
     * Returns if the player typically collects the 'approach shot line' stat
     * @return
     */
    boolean isCollectApproachShotLine();
    
    /**
     * Sets if the player typically collects the 'approach shot line' stat
     * @param aCollectApproachShotLine
     */
    void setCollectApproachShotLine(boolean aCollectApproachShotLine);
    
    /**
     * Returns if the player typically collects the 'approach shot distance' 
     * stat
     * @return
     */
    boolean isCollectApproachShotDistance();
    
    /**
     * Sets if the player typically collects the 'approach shot distance' stat
     * @param aCollectApproachShotDistance
     */
    void setCollectApproachShotDistance(boolean aCollectApproachShotDistance);
    
    /**
     * Returns if the player typically collects the 'sand save' stat
     * @return
     */
    boolean isCollectSandSave();
    
    /**
     * Sets if the player typically collects the 'sand save' stat
     * @param aCollectSandSave
     */
    void setCollectSandSave(boolean aCollectSandSave);
    
    /**
     * Returns if the player typically collects the 'up and down' stat
     * @return
     */
    boolean isCollectUpDown();
    
    /**
     * Sets if the player typically collects the 'up and down' stat
     * @param aCollectUpDown
     */
    void setCollectUpDown(boolean aCollectUpDown);
	
	/**
	 * Get the scorecards
	 * @return
	 */
	Set<ScorecardDAO> getScorecards();
	
	/**
	 * Refreshes the scorecard summary for this player from the database
	 * @param aDAOUtils
	 * @param aUtil
	 *
	 */
	void refreshSummary(DAOUtils aDAOUtils, NewUtil aUtil, Locale aLocale);
	
	/**
	 * Returns the master summary object for this player
	 */
	MasterSummary getSummary();
	
	/**
	 * Calculates and returns a new master summary object for this player
	 * @param aFromDate
	 * @param aToDate
	 * @return
	 */
	MasterSummary calculateNewSummary(Date aFromDate, Date aToDate, 
			CircumstancesBean aCircumstances, Locale aLocale);
	
	/**
	 * Returns the date of the user's last login
	 * @return
	 */
	Date getDateOfLastLogin();
	
	/**
	 * Sets the date of the user's last login
	 * @param aLastLogin
	 */
	void setDateOfLastLogin(Date aLastLogin);
	
	/**
	 * Returns the total number of rounds posted to-date
	 * @return
	 */
	int getTotalNumberOfRoundsPosted();
	
	/**
	 * Retrieves and returns the collection of scorecard summary objects from
	 * the data source.  Only the most-recent rounds are returned.
	 * @return
	 */
	ScorecardSummaryDAO[] getRounds();
	
	/**
	 * Retrieves and returns the collection of all of the player's rounds (only
	 * the score and date played is retrieved)
	 * @return
	 */
	ScorecardSummaryDAO[] getAllRounds(CircumstancesBean aCircumstances);
	
	/**
	 * Retrieves and returns the collection of all of the player's rounds (only
	 * the score and date played is retrieved)
	 * @param aNumHolesPlayed 18 or 9
	 * @param aCircumstances
	 * @return
	 */
	ScorecardSummaryDAO[] getAllRounds(int aNumHolesPlayed, 
			CircumstancesBean aCircumstances);
	
	/**
	 * Returns the player's most recent rounds; 9-hole rounds have been 
	 * merged.
	 * @return
	 */	
	ScorecardSummaryDAO[] getRecentMergedRounds();
	
	/**
	 * Returns the player's lowest-2 eligible tournament rounds
	 * @return
	 */
	ScorecardSummaryDAO[] getEligibleTournamentRounds();
	
	/**
	 * Returns the handicap index reduction value
	 * @return
	 */
	BigDecimal getReductionValue();
	
    /**
     * Returns the date created
     * @return
     */
    Date getDateCreated();
    
    /**
     * Sets the date created
     * @param aDateCreated
     */
    void setDateCreated(Date aDateCreated);
    
    /**
     * Returns the last update date
     * @return
     */
    Date getLastUpdateDate();
    
    /**
     * Sets the last update date
     * @param aLastUpdateDate
     */
    void setLastUpdateDate(Date aLastUpdateDate);
}
