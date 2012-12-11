package name.paulevans.golf.dao;

import java.math.BigDecimal;
import java.util.Date;



/**
 * Models a scorecard summary.
 * @author Paul
 *
 */
public interface ScorecardSummaryDAO extends DAO {
	
	/**
	 * Returns the scorecard ID
	 * @return
	 */
	int getScorecardId();

	/**
	 * Sets the scorecard ID
	 * @param scorecardId
	 */
	void setScorecardId(int scorecardId);

	/**
	 * Returns the associated scorecard object
	 * @return
	 */
	ScorecardDAO getScorecard();
	
	/**
	 * Returns the differential value for this summary
	 * @return
	 */
	BigDecimal getDifferential();

	/**
	 * Sets the associated scorecard object
	 * @param scorecard
	 */
	void setScorecard(ScorecardDAO scorecard);

	/**
	 * Returns the number of pars for this round
	 * @return
	 */
	float getNumPars();

	/**
	 * Sets the number of pars for this round
	 * @param numPars
	 */
	void setNumPars(float numPars);

	/**
	 * Returns the number of bogeys for this round
	 * @return
	 */
	float getNumBogeys();

	/**
	 * Sets the number of bogeys for this round
	 * @param numBogeys
	 */
	void setNumBogeys(float numBogeys);

	/**
	 * Returns the number of eagles for this round
	 * @return
	 */
	float getNumEagles();

	/**
	 * Sets the number of eagles or better for this round
	 * @param numBirdies
	 */
	void setNumEagles(float numEagles);	
	
	/**
	 * Returns the number of birdies for this round
	 * @return
	 */
	float getNumBirdies();

	/**
	 * Sets the number of birdies for this round
	 * @param numBirdies
	 */
	void setNumBirdies(float numBirdies);

	/**
	 * Returns the number of double bogeys for this round
	 * @return
	 */
	float getNumDblBogeys();

	/**
	 * Sets the number of double bogeys for this round
	 * @param numDblBogeys
	 */
	void setNumDblBogeys(float numDblBogeys);

	/**
	 * Returns the number of triple bogeys or worse for this round
	 * @return
	 */
	float getNumTplBogeys();

	/**
	 * Sets the number of triple bogeys or worse for this round
	 * @param numDblBogeys
	 */
	void setNumTplBogeys(float numTplBogeys);

	/**
	 * Returns the par-3 average for this round
	 * @return
	 */
	Float getPar3Avg();

	/**
	 * Sets the par-3 average for this round
	 * @param par3Avg
	 */
	void setPar3Avg(Float par3Avg);

	/**
	 * Returns the par-4 average for this round
	 * @return
	 */
	Float getPar4Avg();

	/**
	 * Sets the par-4 average for this round
	 * @param par4Avg
	 */
	void setPar4Avg(Float par4Avg);

	/**
	 * Returns the par-5 average for this round
	 * @return
	 */
	Float getPar5Avg();

	/**
	 * Sets the par-5 average for this round
	 * @param par5Avg
	 */
	void setPar5Avg(Float par5Avg);

	/**
	 * Returns the tee-shot-accuracy percentage for this round
	 * @return
	 */
	Float getTeeShotAccuracy();

	/**
	 * Sets the tee-shot-accuracy percentage for this round
	 * @param teeShotAccuracy
	 */
	void setTeeShotAccuracy(Float teeShotAccuracy);
	
	/**
	 * Returns the approach-shot-line accuracy percentage for this round
	 * @return
	 */
    Float getApproachShotLine();
    
    /**
     * Sets the approach-shot-line accuracy percentage for this round
     * @param approachShotLine
     */
    void setApproachShotLine(Float approachShotLine);
    
	/**
	 * Returns the approach-shot-distance accuracy percentage for this round
	 * @return
	 */
    Float getApproachShotDistance();
    
    /**
     * Sets the approach-shot-distance accuracy percentage for this round
     * @param approachShotDistance
     */
    void setApproachShotDistance(Float approachShotDistance);

	/**
	 * Returns the average putts per hole for this round
	 * @return
	 */
	Float getAvgPutts();

	/**
	 * Sets the average putts per hole for this round
	 * @param avgPutts
	 */
	void setAvgPutts(Float avgPutts);

	/**
	 * Returns the average putts per hole when GIR is hit for this round
	 * @return
	 */
	Float getAvgPuttsGir();

	/**
	 * Sets the average putts per hole when GIR is hit for this round
	 * @param avgPuttsGir
	 */
	void setAvgPuttsGir(Float avgPuttsGir);

	/**
	 * Returns the total number of putts for this round
	 * @return
	 */
	Float getTotalPutts();

	/**
	 * Sets the total number of putts for this round
	 * @param totalPutts
	 */
	void setTotalPutts(Float totalPutts);

	/**
	 * Returns the total number of putts for all holes where GIR is hit for 
	 * this round
	 * @return
	 */
	Float getTotalPuttsGir();

	/**
	 * Sets the total number of putts for all holes where GIR is hit for this
	 * round
	 * @param totalPuttsGir
	 */
	void setTotalPuttsGir(Float totalPuttsGir);

	/**
	 * Returns the percentage of GIRs for this round
	 * @return
	 */
	Float getGirAvg();

	/**
	 * Sets the percentage of GIRs for this round
	 * @param girAvg
	 */
	void setGirAvg(Float girAvg);

	/**
	 * Returns the average driving distance for this round
	 * @return
	 */
	Float getDrivingDistanceAvg();

	/**
	 * Sets the average driving distance for this round
	 * @param drivingDistanceAvg
	 */
	void setDrivingDistanceAvg(Float drivingDistanceAvg);

	/**
	 * Returns the longest drive distance for this round
	 * @return
	 */
	Float getLongestDrive();

	/**
	 * Sets the longest driver distance for this round
	 * @param longestDrive
	 */
	void setLongestDrive(Float longestDrive);

	/**
	 * Returns the sand save conversion percentage for this round
	 * @return
	 */
	Float getSandSaveConvert();

	/**
	 * Sets the sand save conversion percentage for this round
	 * @param sandSaveConvert
	 */
	void setSandSaveConvert(Float sandSaveConvert);

	/**
	 * Returns the number of sand save opportunities for this round
	 * @return
	 */
	Float getNumSandSaveOpportunities();

	/**
	 * Sets the number of sand save opportunities for this round
	 * @param numSandSaveOpportunities
	 */
	void setNumSandSaveOpportunities(Float numSandSaveOpportunities);

	/**
	 * Returns the up-and-down conversion percentage for this round
	 * @return
	 */
	Float getUpDownConvert();

	/**
	 * Sets the up-and-down conversion percentage for this round
	 * @param upDownConvert
	 */
	void setUpDownConvert(Float upDownConvert);

	/**
	 * Returns the number of up-and-down opportunities for this round
	 * @return
	 */
	Float getNumUpDownOpportunities();

	/**
	 * Sets the number of up-and-down opportunities for this round
	 * @param numUpDownOpportunities
	 */
	void setNumUpDownOpportunities(Float numUpDownOpportunities);
	
	/**
	 * Returns the score
	 * @return
	 */
	int getScore();
	
	/**
	 * Sets the score
	 * @param aScore
	 */
	void setScore(int aScore);
	
	/**
	 * Returns the average score
	 * @return
	 */
	float getAvgScore();
	
	/**
	 * Sets the average score
	 * @param aScore
	 */
	void setAvgScore(float aScore);
	
	/**
	 * Returns the number of holes played (either 9 or 18)
	 * @return
	 */
	int getNumHolesPlayed();
	
	/**
	 * Sets the number of holes played (either 9 or 18)
	 * @param aNumHolesPlayed
	 */
	void setNumHolesPlayed(int aNumHolesPlayed);
	
	/**
	 * Returns the ID of the associated course
	 * @return
	 */
	int getCourseId();
	
	/**
	 * Returns the name of the associated course
	 * @return
	 */
	String getCourseName();
	
	/**
	 * Sets the course name
	 * @param aCourseName
	 */
	void setCourseName(String aCourseName);
	
	/**
	 * Returns the rating value of the associated course
	 * @return
	 */
	Float getRating();
	
	/**
	 * Returns the slope value of the associated course
	 * @return
	 */
	Integer getSlope();
	
	/**
	 * Sets the ID of the associated course
	 * @return
	 */
	void setCourseId(int aCourseId);
	
	/**
	 * Sets the rating value of the associated course
	 * @return
	 */
	void setRating(Float aRating);
	
	/**
	 * Sets the slope value of the associated course
	 * @return
	 */
	void setSlope(Integer aSlope);
	
	/**
	 * Sets the date played
	 * @param aDatePlayed
	 */
	void setDatePlayed(Date aDatePlayed);
	
	/**
	 * Returns the date played
	 * @return
	 */
	Date getDatePlayed();
	
	/**
	 * Returns true if this round is a 9-hole round
	 * @return
	 */
	boolean isNineHoleRound();
	
	/**
	 * Returns the score type
	 * @return
	 */
	String getScoreType();
	
	/**
	 * Sets the score type
	 * @param aScoreType
	 */
	void setScoreType(String aScoreType);
	
	/**
	 * Returns the handicap of the player at the time this score was posted
	 * @return
	 */
	BigDecimal getHandicap();
	
	/**
	 * Sets the handicap of the player at the time this score was posted
	 * @param aHandicap
	 */
	void setHandicap(BigDecimal aHandicap);
	
	/**
	 * Returns true if used in the player's handicap calculation
	 * @return
	 */
	boolean getUsedInHandicapCalculation();
	
	/**
	 * Sets if used in the player's handicap calculation
	 * @param aUsedInHandicapCalculation
	 */
	void setUsedInHandicapCalculation(boolean aUsedInHandicapCalculation);
}
