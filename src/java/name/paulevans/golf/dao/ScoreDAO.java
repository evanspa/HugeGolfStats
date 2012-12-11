package name.paulevans.golf.dao;




/**
 * Models a score for a hole
 * @author Paul
 *
 */
public interface ScoreDAO extends DAO {

	/**
	 * Get the golf club used off the tee
	 * @return
	 */
	GolfClubDAO getGolfClub();

	/**
	 * Set the golf club used off the tee
	 * @param aGolfClub
	 */
	void setGolfClub(GolfClubDAO aGolfClub);

	/**
	 * Get the approach-shot-distance dao
	 * @return
	 */
	ApproachShotDistanceDAO getApproachShotDistance();

	/**
	 * Set the apporach-shot-distance dao
	 * @param aApproachShotDistance
	 */
	void setApproachShotDistance(ApproachShotDistanceDAO aApproachShotDistance);

	/**
	 * Get the green-in-regulation dao
	 * @return
	 */
	GreenInRegulationDAO getGreenInRegulation();

	/**
	 * Set the green-in-regulation dao
	 * @param aGreenInRegulation
	 */
	void setGreenInRegulation(GreenInRegulationDAO aGreenInRegulation);

	/**
	 * Get the scorecard
	 * @return
	 */
	ScorecardDAO getScorecard();

	/**
	 * Set the scorecard
	 * @param aScorecard
	 */
	void setScorecard(ScorecardDAO aScorecard);

	/**
	 * Get the associated hole
	 * @return
	 */
	HoleDAO getHole();

	/**
	 * Set the associated hole
	 * @param aHole
	 */
	void setHole(HoleDAO aHole);

	/**
	 * Get the approach-shot-line dao
	 * @return
	 */
	ApproachShotLineDAO getApproachShotLine();

	/**
	 * Set the approach-shot-line dao
	 * @param aApproachShotLine
	 */
	void setApproachShotLine(ApproachShotLineDAO aApproachShotLine);

	/**
	 * Get the golf ball used
	 * @return
	 */
	GolfBallDAO getGolfBall();

	/**
	 * Set the golf ball used
	 * @param aGolfBall
	 */
	void setGolfBall(GolfBallDAO aGolfBall);

	/**
	 * Get the tee-shot-accuracy dao
	 * @return
	 */
	TeeShotAccuracyDAO getTeeShotAccuracy();

	/**
	 * Set the tee-shot-accuracy dao
	 * @param aTeeShotAccuracy
	 */
	void setTeeShotAccuracy(TeeShotAccuracyDAO aTeeShotAccuracy);

	/**
	 * Get the score
	 * @return
	 */
	int getScore();

	/**
	 * Set the score
	 * @param aScore
	 */
	void setScore(int aScore);

	/**
	 * Get the number of putts
	 * @return
	 */
	Integer getNumPutts();

	/**
	 * Set the number of putts
	 * @param aNumPutts
	 */
	void setNumPutts(Integer aNumPutts);

	/**
	 * Get if a sand-save was attempted
	 * @return
	 */
	Boolean isSandSaveAttempted();

	/**
	 * Set if a sand-save was attempted
	 * @param aSandSaveAttempt
	 */
	void setSandSaveAttempted(Boolean aSandSaveAttempt);

	/**
	 * Get if an up-and-down was attempted
	 * @return
	 */
	Boolean isUpAndDownAttempted();

	/**
	 * Set if an up-and-down was attempted
	 * @param aUpDownAttempt
	 */
	void setUpAndDownAttempted(Boolean aUpDownAttempt);

	/**
	 * Get the tee-shot distance
	 * @return
	 */
	Integer getDriveDistance();

	/**
	 * Set the tee-shot distance
	 * @param aDriveDistance
	 */
	void setDriveDistance(Integer aDriveDistance);

	/**
	 * Get if sand-save conversion 
	 * @return
	 */
	Boolean getIfSandSaveConversion();

	/**
	 * Set if sand-save conversion
	 * @param aSandSaveConversion
	 */
	void setIfSandSaveConversion(Boolean aSandSaveConversion);

	/**
	 * Get if up-and-down conversion
	 * @return
	 */
	Boolean getIfUpAndDownConversion();

	/**
	 * Set if up-and-down conversion
	 * @param aUpDownConversion
	 */
	void setIfUpAndDownConversion(Boolean aUpDownConversion);
}
