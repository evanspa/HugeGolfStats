package name.paulevans.golf.dao;

import java.util.Date;
import java.util.Set;



/**
 * Models a scorecard
 * @author Paul
 *
 */
public interface ScorecardDAO extends DAO {
	
	/**
	 * Returns the starting hole (only useful if this is a nine-hole round)
	 * @return
	 */
	HoleDAO getStartingHole();
	
	/**
	 * Sets the starting hole (only useful if this is a nine-hole round)
	 * @param aHole
	 */
	void setStartingHole(HoleDAO aHole);

	/**
	 * Get the player
	 * @return
	 */
	PlayerDAO getPlayer();

	/**
	 * Set the player
	 * @param aPlayer
	 */
	void setPlayer(PlayerDAO aPlayer);

	/**
	 * Get the weather condition
	 * @return
	 */
	WeatherConditionDAO getWeatherCondition();

	/**
	 * Set the weather condition
	 * @param aWeatherCondition
	 */
	void setWeatherCondition(WeatherConditionDAO aWeatherCondition);

	/**
	 * Get the tee played
	 * @return
	 */
	TeeDAO getTee();

	/**
	 * Set the tee played
	 * @param aTee
	 */
	void setTee(TeeDAO aTee);

	/**
	 * Get the eye-wear used
	 * @return
	 */
	EyeWearDAO getEyeWear();

	/**
	 * Set the eye-wear used
	 * @param aEyeWear
	 */
	void setEyeWear(EyeWearDAO aEyeWear);

	/**
	 * Get the pant-wear used
	 * @return
	 */
	PantWearDAO getPantWear();

	/**
	 * Set the pant-wear used
	 * @param aPantWear
	 */
	void setPantWear(PantWearDAO aPantWear);

	/**
	 * Get the head-wear used
	 * @return
	 */
	HeadWearDAO getHeadWear();

	/**
	 * Set the head-wear used
	 * @param aHeadWear
	 */
	void setHeadWear(HeadWearDAO aHeadWear);

	/**
	 * Get the date played
	 * @return
	 */
	Date getDate();

	/**
	 * Set the date played
	 * @param aDate
	 */
	void setDate(Date aDate);

	/**
	 * Get if long-sleeves were worn
	 * @return
	 */
	boolean getWoreLongSleeves();

	/**
	 * Set if long-sleeves were worn
	 * @param aWoreLongSleeves
	 */
	void setWoreLongSleeves(boolean aWoreLongSleeves);

	/**
	 * Get is a vest was worn
	 * @return
	 */
	boolean getWoreVest();

	/**
	 * Set if a vest was worn
	 * @param aWoreVest
	 */
	void setWoreVest(boolean aWoreVest);

	/**
	 * Get the journal notes
	 * @return
	 */
	String getJournalNotes();

	/**
	 * Set the journal notes
	 * @param aJournalNotes
	 */
	void setJournalNotes(String aJournalNotes);

	/**
	 * Get the attestor
	 * @return
	 */
	String getAttestedBy();

	/**
	 * Set the attestor; null if there is no attestor
	 * @param aAttestedBy
	 */
	void setAttestedBy(String aAttestedBy);

	/**
	 * Get the scorer; null if scored by the player
	 * @return
	 */
	String getScorer();

	/**
	 * Set the scorer
	 * @param aScorer
	 */
	void setScorer(String aScorer);
	
	/**
	 * Returns if a caddie was used for this round
	 * @return
	 */
	boolean getUsedCaddie();
	
	/**
	 * Sets if a caddie was used for this round
	 * @param aUsedCaddie
	 */
	void setUsedCaddie(boolean aUsedCaddie);
	
	/**
	 * Returns if the player used a cart for this round
	 * @return
	 */
	boolean getUsedCart();
	
	/**
	 * Sets if the player used a cart for this round
	 * @param aUsedCart
	 */
	void setUsedCart(boolean aUsedCart);

	/**
	 * Get the hole-scores
	 * @return
	 */
	Set<ScoreDAO> getScores();

	/**
	 * Set the hole-scores
	 * @param aScores
	 */
	void setScores(Set<ScoreDAO> aScores);
	
	/**
	 * Returns if this score was made at the player's home course or not
	 * @return
	 */
	boolean getIsHome();
	
	/**
	 * Sets if this score was made at the player's home course or not
	 * @param aIsHome
	 */
	void setIsHome(boolean aIsHome);
	
	/**
	 * Returns if this score was played during a tournament or not
	 * @return
	 */
	boolean getIsTournament();
	
	/**
	 * Sets if this score was played during a tournament or not
	 * @param aIsTournament
	 */
	void setIsTournament(boolean aIsTournament);
	
	/**
	 * Returns if the player collected the 'club used off tee' stat
	 * @return
	 */
    boolean isCollectClubUsedOffTee();
    
    /**
     * Sets if the player collected the 'club used off tee' stat
     * @param aCollectClubUsedOffTee
     */
    void setCollectClubUsedOffTee(boolean aCollectClubUsedOffTee);
    
    /**
     * Returns if the player collected the 'tee shot distance' stat
     * @return
     */
    boolean isCollectTeeShotDistance();
    
    /**
     * Sets if the player collected the 'tee shot distance' stat
     * @param aCollectTeeShotDistance
     */
    void setCollectTeeShotDistance(boolean aCollectTeeShotDistance);
    
    /**
     * Returns if the player collected the 'num putts' stat
     * @return
     */
    boolean isCollectNumPutts();
    
    /**
     * Sets if the player collected the 'num putts' stat
     * @param aCollectNumPutts
     */
    void setCollectNumPutts(boolean aCollectNumPutts);
    
    /**
     * Returns if the player collected the 'fairway hit' stat
     * @return
     */
    boolean isCollectFairwayHit();
    
    /**
     * Sets if the player collected the 'fairway hit' stat
     * @param aCollectFairwayHit
     */
    void setCollectFairwayHit(boolean aCollectFairwayHit);
    
    /**
     * Returns if the player collected the 'GIR' stat
     * @return
     */
    boolean isCollectGir();
    
    /**
     * Sets if the player collected the 'GIR' stat
     * @param aCollectGir
     */
    void setCollectGir(boolean aCollectGir);
    
    /**
     * Returns if the player collected the 'approach shot line' stat
     * @return
     */
    boolean isCollectApproachShotLine();
    
    /**
     * Sets if the player collected the 'approach shot line' stat
     * @param aCollectApproachShotLine
     */
    void setCollectApproachShotLine(boolean aCollectApproachShotLine);
    
    /**
     * Returns if the player collected the 'approach shot distance' 
     * stat
     * @return
     */
    boolean isCollectApproachShotDistance();
    
    /**
     * Sets if the player collected the 'approach shot distance' stat
     * @param aCollectApproachShotDistance
     */
    void setCollectApproachShotDistance(boolean aCollectApproachShotDistance);
    
    /**
     * Returns if the player collected the 'sand save' stat
     * @return
     */
    boolean isCollectSandSave();
    
    /**
     * Sets if the player collected the 'sand save' stat
     * @param aCollectSandSave
     */
    void setCollectSandSave(boolean aCollectSandSave);
    
    /**
     * Returns if the player collected the 'up and down' stat
     * @return
     */
    boolean isCollectUpDown();
    
    /**
     * Sets if the player collected the 'up and down' stat
     * @param aCollectUpDown
     */
    void setCollectUpDown(boolean aCollectUpDown);
    
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
