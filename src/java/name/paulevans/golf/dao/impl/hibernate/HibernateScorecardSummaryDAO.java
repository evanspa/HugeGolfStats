/**
 * 
 */
package name.paulevans.golf.dao.impl.hibernate;

import gen.hibernate.name.paulevans.golf.Course;
import gen.hibernate.name.paulevans.golf.Scorecard;
import gen.hibernate.name.paulevans.golf.ScorecardSummary;
import gen.hibernate.name.paulevans.golf.StateProvince;
import gen.hibernate.name.paulevans.golf.Tee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import name.paulevans.golf.dao.ScorecardDAO;
import name.paulevans.golf.dao.ScorecardSummaryDAO;
import name.paulevans.golf.util.NewUtil;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Hibernate scorecard summary DAO
 * @author pevans
 *
 */
public class HibernateScorecardSummaryDAO extends HibernateDAOAdapter 
implements ScorecardSummaryDAO, Comparable {
	
	private ScorecardSummary scorecardSummary;
	private String courseName;
	private boolean usedInHandicapCalculation;
	private float avgScore; 
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public HibernateScorecardSummaryDAO() {
		scorecardSummary = new ScorecardSummary();
	}
	
	/**
	 * Public constructor
	 * @param aScorecardSummaryDelegate
	 */
	public HibernateScorecardSummaryDAO(ScorecardSummary 
			aScorecardSummaryDelegate) {
		scorecardSummary = aScorecardSummaryDelegate;	
	}
	
	/**
	 * Public constructor
	 * @param aCourseId
	 * @param aCourseName
	 * @param aStateProvinceName
	 * @param aCountryName
	 */
	public HibernateScorecardSummaryDAO(int aScorecardId, int aCourseId, 
			String aCourseName, String aCountryName, String aStateProvinceName, 
			String aCity, Date aDatePlayed, int aScore, int aNumHolesPlayed,
			String aScoreType, Integer aSlope, Float aRating, Float aAvgPutts, 
			Float aAvgPuttsGir) {
		
		Tee tee;
		Course course;
		Scorecard scorecard;
		
		course = new Course();
		course.setId(aCourseId);
		course.setDescription(aCourseName);
		course.setCity(aCity);
		course.setStateProvince((StateProvince) new HibernateStateProvinceDAO(
				aCountryName, aStateProvinceName).getDelegate());
		scorecard = new Scorecard();
		scorecard.setId(aScorecardId);
		tee = new Tee();
		tee.setCourse(course);
		scorecard.setTee(tee);
		scorecard.setDate(aDatePlayed);
		scorecardSummary = new ScorecardSummary();
		scorecardSummary.setScorecard(scorecard);
		scorecardSummary.setScoreType(aScoreType);
		scorecardSummary.setNumHolesPlayed(aNumHolesPlayed);
		scorecardSummary.setScore(aScore);
		scorecardSummary.setSlope(aSlope);
		scorecardSummary.setRating(aRating);
		scorecardSummary.setDatePlayed(aDatePlayed);
		scorecardSummary.setScorecardId(aScorecardId);
		scorecardSummary.setCourseId(aCourseId);
		scorecardSummary.setAvgPutts(aAvgPutts);
		scorecardSummary.setAvgPuttsGir(aAvgPuttsGir);
		courseName = aCourseName;
	}
	
	/**
	 * Compares this scorecard summary with the input summary, aCompareTo
	 */
	public int compareTo(Object aCompareTo) {
		
		ScorecardSummaryDAO compareTo;
		BigDecimal thisDifferential, compareToDifferential;
		
		compareTo = (ScorecardSummaryDAO)aCompareTo;
		thisDifferential = getDifferential();
		compareToDifferential = compareTo.getDifferential();
		if (thisDifferential.floatValue() < 
				compareToDifferential.floatValue()) {
			return -1;
		} else if (thisDifferential == compareToDifferential) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * Returns the differential value for this summary
	 * @return
	 */
	public BigDecimal getDifferential() {
		return NewUtil.calculateDifferential((int)getScore(), getRating(), 
				getSlope());
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getAvgPutts()
	 */
	public Float getAvgPutts() {
		return scorecardSummary.getAvgPutts();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getAvgPuttsGir()
	 */
	public Float getAvgPuttsGir() {
		return scorecardSummary.getAvgPuttsGir();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getDrivingDistanceAvg()
	 */
	public Float getDrivingDistanceAvg() {
		return scorecardSummary.getDrivingDistanceAvg();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getGirAvg()
	 */
	public Float getGirAvg() {
		return scorecardSummary.getGirAvg();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getLongestDrive()
	 */
	public Float getLongestDrive() {
		return scorecardSummary.getLongestDrive();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getNumBirdies()
	 */
	public float getNumBirdies() {
		return scorecardSummary.getNumBirdies();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getNumEagles()
	 */
	public float getNumEagles() {
		return scorecardSummary.getNumEagles();
	}	
	
	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getNumBogeys()
	 */
	public float getNumBogeys() {
		return scorecardSummary.getNumBogeys();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getNumDblBogeys()
	 */
	public float getNumDblBogeys() {
		return scorecardSummary.getNumDblBogeys();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getNumTplBogeys()
	 */
	public float getNumTplBogeys() {
		return scorecardSummary.getNumTplBogeys();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getNumPars()
	 */
	public float getNumPars() {
		return scorecardSummary.getNumPars();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#
	 * getNumSandSaveOpportunities()
	 */
	public Float getNumSandSaveOpportunities() {
		return scorecardSummary.getNumSandSaveOpportunities();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#
	 * getNumUpDownOpportunities()
	 */
	public Float getNumUpDownOpportunities() {
		return scorecardSummary.getNumUpDownOpportunities();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getPar3Avg()
	 */
	public Float getPar3Avg() {
		return scorecardSummary.getPar3Avg();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getPar4Avg()
	 */
	public Float getPar4Avg() {
		return scorecardSummary.getPar4Avg();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getPar5Avg()
	 */
	public Float getPar5Avg() {
		return scorecardSummary.getPar5Avg();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getSandSandConvert()
	 */
	public Float getSandSaveConvert() {
		return scorecardSummary.getSandSaveConvert();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getScorecard()
	 */
	public ScorecardDAO getScorecard() {
		return new HibernateScorecardDAO(scorecardSummary.getScorecard());
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getScorecardId()
	 */
	public int getScorecardId() {
		return scorecardSummary.getScorecardId();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getTeeShotAccuracy()
	 */
	public Float getTeeShotAccuracy() {
		return scorecardSummary.getTeeShotAccuracy();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getTotalPutts()
	 */
	public Float getTotalPutts() {
		return scorecardSummary.getTotalPutts();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getTotalPuttsGir()
	 */
	public Float getTotalPuttsGir() {
		return scorecardSummary.getTotalPuttsGir();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#getUpDownConvert()
	 */
	public Float getUpDownConvert() {
		return scorecardSummary.getUpDownConvert();
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setAvgPutts(float)
	 */
	public void setAvgPutts(Float avgPutts) {
		scorecardSummary.setAvgPutts(avgPutts);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setAvgPuttsGir(float)
	 */
	public void setAvgPuttsGir(Float avgPuttsGir) {
		scorecardSummary.setAvgPuttsGir(avgPuttsGir);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setDrivingDistanceAvg(
	 * Float)
	 */
	public void setDrivingDistanceAvg(Float drivingDistanceAvg) {
		scorecardSummary.setDrivingDistanceAvg(drivingDistanceAvg);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setGirAvg(float)
	 */
	public void setGirAvg(Float girAvg) {
		scorecardSummary.setGirAvg(girAvg);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setLongestDrive(int)
	 */
	public void setLongestDrive(Float longestDrive) {
		scorecardSummary.setLongestDrive(longestDrive);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setNumBirdies(int)
	 */
	public void setNumBirdies(float numBirdies) {
		scorecardSummary.setNumBirdies(numBirdies);
	}
	
	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setNumEagles(int)
	 */
	public void setNumEagles(float numEagles) {
		scorecardSummary.setNumEagles(numEagles);
	}	

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setNumBogeys(int)
	 */
	public void setNumBogeys(float numBogeys) {
		scorecardSummary.setNumBogeys(numBogeys);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setNumDblBogeys(int)
	 */
	public void setNumDblBogeys(float numDblBogeys) {
		scorecardSummary.setNumDblBogeys(numDblBogeys);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setNumTplBogeys(int)
	 */
	public void setNumTplBogeys(float numTplBogeys) {
		scorecardSummary.setNumTplBogeys(numTplBogeys);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setNumPars(int)
	 */
	public void setNumPars(float numPars) {
		scorecardSummary.setNumPars(numPars);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#
	 * setNumSandSaveOpportunities(int)
	 */
	public void setNumSandSaveOpportunities(Float numSandSaveOpportunities) {
		scorecardSummary.setNumSandSaveOpportunities(numSandSaveOpportunities);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#
	 * setNumUpDownOpportunities(int)
	 */
	public void setNumUpDownOpportunities(Float numUpDownOpportunities) {
		scorecardSummary.setNumUpDownOpportunities(numUpDownOpportunities);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setPar3Avg(float)
	 */
	public void setPar3Avg(Float par3Avg) {
		scorecardSummary.setPar3Avg(par3Avg);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setPar4Avg(float)
	 */
	public void setPar4Avg(Float par4Avg) {
		scorecardSummary.setPar4Avg(par4Avg);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setPar5Avg(float)
	 */
	public void setPar5Avg(Float par5Avg) {
		scorecardSummary.setPar5Avg(par5Avg);
	}
	
	/**
	 * Returns the approach-shot-line accuracy percentage for this round
	 * @return
	 */
    public Float getApproachShotLine() {
    	return scorecardSummary.getApproachShotLine();
    }
    
    /**
     * Sets the approach-shot-line accuracy percentage for this round
     * @param approachShotLine
     */
    public void setApproachShotLine(Float approachShotLine) {
    	scorecardSummary.setApproachShotLine(approachShotLine);
    }
    
	/**
	 * Returns the approach-shot-distance accuracy percentage for this round
	 * @return
	 */
    public Float getApproachShotDistance() {
    	return scorecardSummary.getApproachShotDistance();
    }
    
    /**
     * Sets the approach-shot-distance accuracy percentage for this round
     * @param approachShotDistance
     */
    public void setApproachShotDistance(Float approachShotDistance) {
    	scorecardSummary.setApproachShotDistance(approachShotDistance);
    }

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setSandSandConvert(
	 * float)
	 */
	public void setSandSaveConvert(Float sandSaveConvert) {
		scorecardSummary.setSandSaveConvert(sandSaveConvert);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setScorecard(
	 * gen.hibernate.name.paulevans.golf.Scorecard)
	 */
	public void setScorecard(ScorecardDAO scorecard) {
		scorecardSummary.setScorecard((Scorecard)scorecard.getDelegate());
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setScorecardId(int)
	 */
	public void setScorecardId(int scorecardId) {
		scorecardSummary.setScorecardId(scorecardId);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setTeeShotAccuracy(
	 * float)
	 */
	public void setTeeShotAccuracy(Float teeShotAccuracy) {
		scorecardSummary.setTeeShotAccuracy(teeShotAccuracy);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setTotalPutts(float)
	 */
	public void setTotalPutts(Float totalPutts) {
		scorecardSummary.setTotalPutts(totalPutts);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setTotalPuttsGir(float)
	 */
	public void setTotalPuttsGir(Float totalPuttsGir) {
		scorecardSummary.setTotalPuttsGir(totalPuttsGir);
	}

	/* (non-Javadoc)
	 * @see name.paulevans.golf.dao.ScorecardSummaryDAO#setUpDownConvert(float)
	 */
	public void setUpDownConvert(Float upDownConvert) {
		scorecardSummary.setUpDownConvert(upDownConvert);
	}
	
	/**
	 * Returns the score
	 * @return
	 */
	public int getScore() {
		return scorecardSummary.getScore();
	}
	
	/**
	 * Sets the score
	 * @param aScore
	 */
	public void setScore(int aScore) {
		scorecardSummary.setScore(aScore);
	}
	
	/**
	 * Returns the average score
	 * @return
	 */
	public float getAvgScore() {
		return avgScore;
	}
	
	/**
	 * Sets the average score
	 * @param aScore
	 */
	public void setAvgScore(float aScore) {
		avgScore = aScore;
	}
	
	/**
	 * Returns the number of holes played (either 9 or 18)
	 * @return
	 */
	public int getNumHolesPlayed() {
		return scorecardSummary.getNumHolesPlayed();
	}
	
	/**
	 * Returns true if this round is a 9-hole round
	 * @return
	 */
	public boolean isNineHoleRound() {
		return getNumHolesPlayed() == 9;
	}
	
	/**
	 * Sets the number of holes played (either 9 or 18)
	 * @param aNumHolesPlayed
	 */
	public void setNumHolesPlayed(int aNumHolesPlayed) {
		scorecardSummary.setNumHolesPlayed(aNumHolesPlayed);
	}
	
	/**
	 * Returns the ID of the associated course
	 * @return
	 */
	public int getCourseId() {
		return scorecardSummary.getCourseId();
	}
	
	/**
	 * Returns the rating value of the associated course
	 * @return
	 */
	public Float getRating() {
		return scorecardSummary.getRating();
	}
	
	/**
	 * Returns the slope value of the associated course
	 * @return
	 */
	public Integer getSlope() {
		return scorecardSummary.getSlope();
	}
	
	/**
	 * Sets the ID of the associated course
	 * @return
	 */
	public void setCourseId(int aCourseId) {
		scorecardSummary.setCourseId(aCourseId);
	}
	
	/**
	 * Sets the rating value of the associated course
	 * @return
	 */
	public void setRating(Float aCourseRating) {
		scorecardSummary.setRating(aCourseRating);
	}
	
	/**
	 * Sets the slope value of the associated course
	 * @return
	 */
	public void setSlope(Integer aCourseSlope) {
		scorecardSummary.setSlope(aCourseSlope);
	}
	
	/**
	 * Sets the date played
	 * @param aDatePlayed
	 */
	public void setDatePlayed(Date aDatePlayed) {
		scorecardSummary.setDatePlayed(aDatePlayed);
	}
	
	/**
	 * Returns the date played
	 * @return
	 */
	public Date getDatePlayed() {
		return scorecardSummary.getDatePlayed();
	}
	
	/**
	 * Satisfies the Dateable interface
	 */
	public Date getDate() {
		return getDatePlayed();
	}
	
	/**
	 * Returns the score type
	 * @return
	 */
	public String getScoreType() {
		return scorecardSummary.getScoreType();
	}
	
	/**
	 * Sets the score type
	 * @param aScoreType
	 */
	public void setScoreType(String aScoreType) {
		scorecardSummary.setScoreType(aScoreType);
	}
	
	/**
	 * Get the ID
	 */
	public Integer getId() {
		return getScorecardId();
	}
	
	/**
	 * Set the ID
	 */
	public void setId(Object aId) {
		setScorecardId((Integer)aId);
	}
	
	/**
	 * Sets the scorecard summary delegate
	 */
	public void setDelegate(Object aScorecardSummary) {
		scorecardSummary = (ScorecardSummary)aScorecardSummary;
	}
	
	/**
	 * Gets the delegate object
	 */
	public Serializable getDelegate() {
		return scorecardSummary;
	}
	
	/**
	 * Returns the name of the associated course
	 * @return
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Sets the course name
	 * @param aCourseName
	 */
	public void setCourseName(String aCourseName) {
		courseName = aCourseName;
	}
	
	/**
	 * Returns true if used in the player's handicap calculation
	 * @return
	 */
	public boolean getUsedInHandicapCalculation() {
		return usedInHandicapCalculation;
	}
	
	/**
	 * Sets if used in the player's handicap calculation
	 * @param aUsedInHandicapCalculation
	 */
	public void setUsedInHandicapCalculation(
			boolean aUsedInHandicapCalculation) {
		usedInHandicapCalculation = aUsedInHandicapCalculation;
	}
	
	/**
	 * Returns the handicap of the player at the time this score was posted
	 * @return
	 */
	public BigDecimal getHandicap() {
		return new BigDecimal(scorecardSummary.getCurrentHandicap());
	}
	
	/**
	 * Sets the handicap of the player at the time this score was posted
	 * @param aHandicap
	 */
	public void setHandicap(BigDecimal aHandicap) {
		if (aHandicap != null) {
			scorecardSummary.setCurrentHandicap(aHandicap.floatValue());
		}
	}
	
	/**
	 * Load this DAO from the data source
	 */
	public void load() throws DataAccessException {
		
		HibernateTemplate ht;
		
		ht = new HibernateTemplate(getSessionFactory());
		scorecardSummary = (ScorecardSummary)ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session aSession) 
			throws HibernateException {
				
				ScorecardSummary scorecardSummary;
				
				scorecardSummary = (ScorecardSummary)aSession.load(
						ScorecardSummary.class, getId());
				Hibernate.initialize(scorecardSummary);
				
				return scorecardSummary;
			}
		});
	}
}
