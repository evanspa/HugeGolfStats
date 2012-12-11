package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.ApproachShotDistance;
import gen.hibernate.name.paulevans.golf.ApproachShotLine;
import gen.hibernate.name.paulevans.golf.GolfBall;
import gen.hibernate.name.paulevans.golf.GolfClub;
import gen.hibernate.name.paulevans.golf.GreenInRegulation;
import gen.hibernate.name.paulevans.golf.Hole;
import gen.hibernate.name.paulevans.golf.Score;
import gen.hibernate.name.paulevans.golf.Scorecard;
import gen.hibernate.name.paulevans.golf.TeeShotAccuracy;

import java.io.Serializable;

import name.paulevans.golf.dao.ApproachShotDistanceDAO;
import name.paulevans.golf.dao.ApproachShotLineDAO;
import name.paulevans.golf.dao.GolfBallDAO;
import name.paulevans.golf.dao.GolfClubDAO;
import name.paulevans.golf.dao.GreenInRegulationDAO;
import name.paulevans.golf.dao.HoleDAO;
import name.paulevans.golf.dao.ScoreDAO;
import name.paulevans.golf.dao.ScoreId;
import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.TeeShotAccuracyDAO;

/**
 * Hibernate score dao
 * @author Paul
 *
 */
public class HibernateScoreDAO extends HibernateDAOAdapter implements ScoreDAO {

	// delegate object - generated Hibernate type...
	private Score score;
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateScoreDAO() {
		// does nothing...
	}
	
	/**
	 * Public constructor
	 * @param aScoreDelegate
	 */
	public HibernateScoreDAO(Score aScoreDelegate) {
		score = aScoreDelegate;
	}
	
	/**
	 * Sets the delegate
	 */
	public void setDelegate(Object aScoreDelegate) {
		score = (Score)aScoreDelegate;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return score;
	}

	/**
	 * Get the ID
	 */
	public ScoreId getId() {
		return convert(score.getId());
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		score.setId(convert((name.paulevans.golf.dao.ScoreId)aId));
	}
	
	/**
	 * Get the golf club used off the tee
	 * @return
	 */
	public GolfClubDAO getGolfClub() {
		return score.getGolfClub() != null ? new HibernateGolfClubDAO(
				score.getGolfClub()) : null;
	}

	/**
	 * Set the golf club used off the tee
	 * @param aGolfClub
	 */
	public void setGolfClub(GolfClubDAO aGolfClub) {
		score.setGolfClub((GolfClub)aGolfClub.getDelegate());
	}

	/**
	 * Get the approach-shot-distance dao
	 * @return
	 */
	public ApproachShotDistanceDAO getApproachShotDistance() {
		return score.getApproachShotDistance() != null ?
		new HibernateApproachShotDistanceDAO(score.getApproachShotDistance()) :
			null;
	}

	/**
	 * Set the apporach-shot-distance dao
	 * @param aApproachShotDistance
	 */
	public void setApproachShotDistance(
			ApproachShotDistanceDAO aApproachShotDistance) {
		score.setApproachShotDistance(aApproachShotDistance != null ? 
				(ApproachShotDistance)aApproachShotDistance.getDelegate() : 
					(ApproachShotDistance)null);
	}

	/**
	 * Get the green-in-regulation dao
	 * @return
	 */
	public GreenInRegulationDAO getGreenInRegulation() {
		return score.getGreenInRegulation() != null ? 
				new HibernateGreenInRegulationDAO(
						score.getGreenInRegulation()) : null;
	}

	/**
	 * Set the green-in-regulation dao
	 * @param aGreenInRegulation
	 */
	public void setGreenInRegulation(GreenInRegulationDAO aGreenInRegulation) {
		score.setGreenInRegulation((GreenInRegulation)
				aGreenInRegulation.getDelegate());
	}

	/**
	 * Get the scorecard
	 * @return
	 */
	public ScorecardDAO getScorecard() {
		return new HibernateScorecardDAO(score.getScorecard());
	}

	/**
	 * Set the scorecard
	 * @param aScorecard
	 */
	public void setScorecard(ScorecardDAO aScorecard) {
		score.setScorecard((Scorecard)aScorecard.getDelegate());
	}

	/**
	 * Get the associated hole
	 * @return
	 */
	public HoleDAO getHole() {
		return new HibernateHoleDAO(score.getHole());
	}

	/**
	 * Set the associated hole
	 * @param aHole
	 */
	public void setHole(HoleDAO aHole) {
		score.setHole((Hole)aHole.getDelegate());
	}

	/**
	 * Get the approach-shot-line dao
	 * @return
	 */
	public ApproachShotLineDAO getApproachShotLine() {
		return score.getApproachShotLine() != null ? 
				new HibernateApproachShotLineDAO(score.getApproachShotLine()) :
					null;
	}

	/**
	 * Set the approach-shot-line dao
	 * @param aApproachShotLine
	 */
	public void setApproachShotLine(ApproachShotLineDAO aApproachShotLine) {
		score.setApproachShotLine(aApproachShotLine != null ? 
				(ApproachShotLine)aApproachShotLine.getDelegate() : 
					(ApproachShotLine)null);
	}

	/**
	 * Get the golf ball used
	 * @return
	 */
	public GolfBallDAO getGolfBall() {
		return score.getGolfBall() != null ? 
				new HibernateGolfBallDAO(score.getGolfBall()) : null;
	}

	/**
	 * Set the golf ball used
	 * @param aGolfBall
	 */
	public void setGolfBall(GolfBallDAO aGolfBall) {
		score.setGolfBall((GolfBall)aGolfBall.getDelegate());
	}

	/**
	 * Get the tee-shot-accuracy dao
	 * @return
	 */
	public TeeShotAccuracyDAO getTeeShotAccuracy() {
		return score.getTeeShotAccuracy() != null ? 
				new HibernateTeeShotAccuracyDAO(score.getTeeShotAccuracy()) : 
					null;
	}

	/**
	 * Set the tee-shot-accuracy dao
	 * @param aTeeShotAccuracy
	 */
	public void setTeeShotAccuracy(TeeShotAccuracyDAO aTeeShotAccuracy) {
		score.setTeeShotAccuracy(aTeeShotAccuracy != null ? 
				(TeeShotAccuracy)aTeeShotAccuracy.getDelegate() : 
					(TeeShotAccuracy)null);
	}

	/**
	 * Get the score
	 * @return
	 */
	public int getScore() {
		return score.getScore();
	}

	/**
	 * Set the score
	 * @param aScore
	 */
	public void setScore(int aScore) {
		score.setScore(aScore);
	}

	/**
	 * Get the number of putts
	 * @return
	 */
	public Integer getNumPutts() {
		return score.getNumPutts();
	}

	/**
	 * Set the number of putts
	 * @param aNumPutts
	 */
	public void setNumPutts(Integer aNumPutts) {
		score.setNumPutts(aNumPutts);
	}

	/**
	 * Get if a sand-save was attempted
	 * @return
	 */
	public Boolean isSandSaveAttempted() {
		return score.getSandSaveAttempt();
	}

	/**
	 * Set if a sand-save was attempted
	 * @param aSandSaveAttempt
	 */
	public void setSandSaveAttempted(Boolean aSandSaveAttempt) {
		score.setSandSaveAttempt(aSandSaveAttempt);
	}

	/**
	 * Get if an up-and-down was attempted
	 * @return
	 */
	public Boolean isUpAndDownAttempted() {
		return score.getUpDownAttempt();
	}

	/**
	 * Set if an up-and-down was attempted
	 * @param aUpDownAttempt
	 */
	public void setUpAndDownAttempted(Boolean aUpDownAttempt) {
		score.setUpDownAttempt(aUpDownAttempt);
	}

	/**
	 * Get the tee-shot distance
	 * @return
	 */
	public Integer getDriveDistance() {
		return score.getDriveDistance();
	}

	/**
	 * Set the tee-shot distance
	 * @param aDriveDistance
	 */
	public void setDriveDistance(Integer aDriveDistance) {
		score.setDriveDistance(aDriveDistance);
	}

	/**
	 * Get if sand-save conversion 
	 * @return
	 */
	public Boolean getIfSandSaveConversion() {
		return score.getSandSaveConversion();
	}

	/**
	 * Set if sand-save conversion
	 * @param aSandSaveConversion
	 */
	public void setIfSandSaveConversion(Boolean aSandSaveConversion) {
		score.setSandSaveConversion(aSandSaveConversion);
	}

	/**
	 * Get if up-and-down conversion
	 * @return
	 */
	public Boolean getIfUpAndDownConversion() {
		return score.getUpDownConversion();
	}

	/**
	 * Set if up-and-down conversion
	 * @param aUpDownConversion
	 */
	public void setIfUpAndDownConversion(Boolean aUpDownConversion) {
		score.setUpDownConversion(aUpDownConversion);
	}
	
	/**
	 * Converts the hibernate ScoreId type to dao ScoreId type
	 * @param aId
	 * @return
	 */
	private static final name.paulevans.golf.dao.ScoreId convert(
			gen.hibernate.name.paulevans.golf.ScoreId aId) {
		
		name.paulevans.golf.dao.ScoreId id;
		
		id = new name.paulevans.golf.dao.ScoreId();
		id.setHoleId(aId.getHoleId());
		id.setScorecardId(aId.getScorecardId());
		return id;
	}
	
	/**
	 * Converts the dao ScoreId type to the hibernate ScoreId type
	 * @param aId
	 * @return
	 */
	private static final gen.hibernate.name.paulevans.golf.ScoreId convert(
			name.paulevans.golf.dao.ScoreId aId) {
		
		gen.hibernate.name.paulevans.golf.ScoreId id;
		
		id = new gen.hibernate.name.paulevans.golf.ScoreId();
		id.setHoleId(aId.getHoleId());
		id.setScorecardId(aId.getScorecardId());
		return id;
	}
}
