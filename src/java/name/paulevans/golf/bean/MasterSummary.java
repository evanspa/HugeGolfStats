package name.paulevans.golf.bean;

import java.util.Locale;

import name.paulevans.golf.util.NewUtil;

/**
 * Models the master summary for a player
 * @author Paul
 *
 */
public class MasterSummary {
	
	private Float avgScore;
	private Float avgNumEagles;
	private Float avgNumBirdies;
	private Float avgNumPars;
	private Float avgNumBogeys;
	private Float avgNumDblBogeys;
	private Float avgNumTplBogeys;
	private Float par3Avg;
	private Float par4Avg;
	private Float par5Avg;
	private Float avgTotalPutts;
	private Float avgTotalPuttsGIR;
	private Float avgPutts;
	private Float avgPuttsGIR;
	private Float avgGIRPercentage;
	private Float avgFairwaysHitPercentage;
	private Float avgDrivingDistance;
	private Float avgNumSandSaveOpportunities;
	private Float avgSandSaveConvertPercentage;
	private Float avgNumUpDownOpportunities;
	private Float avgUpDownConvertPercentage;
	private Integer scorecardCount;
	private Locale locale;
	
	/**
	 * Public constructor
	 * @param aAvgScore
	 * @param aAvgNumEagles
	 * @param aAvgNumBirdies
	 * @param aAvgNumPars
	 * @param aAvgNumBogeys
	 * @param aAvgNumDblBogeys
	 * @param aAvgNumTplBogeys
	 * @param aPar3Avg
	 * @param aPar4Avg
	 * @param aPar5Avg
	 * @param aAvgTotalPutts
	 * @param aAvgTotalPuttsGIR
	 * @param aAvgPutts
	 * @param aAvgPuttsGIR
	 * @param aAvgGIRPercentage
	 * @param aAvgFairwaysHitPercentage
	 * @param aAvgDrivingDistance
	 * @param aAvgNumSandSaveOpportunities
	 * @param aAvgSandSaveConvertPercentage
	 * @param aAvgNumUpDownOpportunities
	 * @param aAvgUpDownConvertPercentage
	 */
	public MasterSummary(Float aAvgScore, 
			Float aAvgNumEagles,
			Float aAvgNumBirdies,
			Float aAvgNumPars,
			Float aAvgNumBogeys,
			Float aAvgNumDblBogeys,
			Float aAvgNumTplBogeys,
			Float aPar3Avg,
			Float aPar4Avg,
			Float aPar5Avg,
			Float aAvgTotalPutts,
			Float aAvgTotalPuttsGIR,
			Float aAvgPutts,
			Float aAvgPuttsGIR,
			Float aAvgGIRPercentage,
			Float aAvgFairwaysHitPercentage,
			Float aAvgDrivingDistance,
			Float aAvgNumSandSaveOpportunities,
			Float aAvgSandSaveConvertPercentage,
			Float aAvgNumUpDownOpportunities,
			Float aAvgUpDownConvertPercentage, 
			Integer aScorecardCount,
			Locale aLocale) {
		avgScore = aAvgScore;
		avgNumEagles = aAvgNumEagles;
		avgNumBirdies = aAvgNumBirdies;
		avgNumPars = aAvgNumPars;
		avgNumBogeys = aAvgNumBogeys;
		avgNumDblBogeys = aAvgNumDblBogeys;
		avgNumTplBogeys = aAvgNumTplBogeys;
		par3Avg = aPar3Avg;
		par4Avg = aPar4Avg;
		par5Avg = aPar5Avg;
		avgTotalPutts = aAvgTotalPutts;
		avgTotalPuttsGIR = aAvgTotalPuttsGIR;
		avgPutts = aAvgPutts;
		avgPuttsGIR = aAvgPuttsGIR;
		avgGIRPercentage = aAvgGIRPercentage;
		avgFairwaysHitPercentage = aAvgFairwaysHitPercentage;
		avgDrivingDistance = aAvgDrivingDistance;
		avgNumSandSaveOpportunities = aAvgNumSandSaveOpportunities;
		avgSandSaveConvertPercentage = aAvgSandSaveConvertPercentage;
		avgNumUpDownOpportunities = aAvgNumUpDownOpportunities;
		avgUpDownConvertPercentage = aAvgUpDownConvertPercentage;
		scorecardCount = aScorecardCount;
		locale = aLocale;
	}
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public MasterSummary(Locale aLocale) {
		scorecardCount = 0;
		locale = aLocale;
	}

	/**
	 * Returns the number of pars for this round
	 * @return
	 */
	public String getAvgNumPars() {
		return NewUtil.formatNumber2Digits(avgNumPars, locale);
	}

	/**
	 * Returns the number of bogeys for this round
	 * @return
	 */
	public String getAvgNumBogeys() {
		return NewUtil.formatNumber2Digits(avgNumBogeys, locale);
	}

	/**
	 * Returns the number of eagles for this round
	 * @return
	 */
	public String getAvgNumEagles() {
		return NewUtil.formatNumber2Digits(avgNumEagles, locale);
	}
	
	/**
	 * Returns the number of birdies for this round
	 * @return
	 */
	public String getAvgNumBirdies() {
		return NewUtil.formatNumber2Digits(avgNumBirdies, locale);
	}

	/**
	 * Returns the number of double bogeys for this round
	 * @return
	 */
	public String getAvgNumDblBogeys() {
		return NewUtil.formatNumber2Digits(avgNumDblBogeys, locale);
	}

	/**
	 * Returns the number of triple bogeys or worse for this round
	 * @return
	 */
	public String getAvgNumTplBogeys() {
		return NewUtil.formatNumber2Digits(avgNumTplBogeys, locale);
	}

	/**
	 * Returns the par-3 average for this round
	 * @return
	 */
	public String getPar3Avg() {
		return NewUtil.formatNumber3Digits(par3Avg, locale);
	}

	/**
	 * Returns the par-4 average for this round
	 * @return
	 */
	public String getPar4Avg() {
		return NewUtil.formatNumber3Digits(par4Avg, locale);
	}

	/**
	 * Returns the par-5 average for this round
	 * @return
	 */
	public String getPar5Avg() {
		return NewUtil.formatNumber3Digits(par5Avg, locale);
	}

	/**
	 * Returns the tee-shot-accuracy percentage for this round
	 * @return
	 */
	public String getAvgFairwaysHitPercentage() {
		return NewUtil.formatPercent1Digit(avgFairwaysHitPercentage, locale);
	}

	/**
	 * Returns the average putts per hole for this round
	 * @return
	 */
	public String getAvgPutts() {
		return NewUtil.formatNumber3Digits(avgPutts, locale);
	}

	/**
	 * Returns the average putts per hole when GIR is hit for this round
	 * @return
	 */
	public String getAvgPuttsGir() {
		return NewUtil.formatNumber3Digits(avgPuttsGIR, locale);
	}

	/**
	 * Returns the total number of putts for this round
	 * @return
	 */
	public String getAvgTotalPutts() {
		return NewUtil.formatNumber1Digit(avgTotalPutts, locale);
	}

	/**
	 * Returns the total number of putts for all holes where GIR is hit for 
	 * this round
	 * @return
	 */
	public String getAvgTotalPuttsGir() {
		return NewUtil.formatNumber1Digit(avgTotalPuttsGIR, locale);
	}

	/**
	 * Returns the percentage of GIRs for this round
	 * @return
	 */
	public String getAvgGirPercentage() {
		return NewUtil.formatPercent1Digit(avgGIRPercentage, locale);
	}

	/**
	 * Returns the average driving distance for this round
	 * @return
	 */
	public String getAvgDrivingDistance() {
		return NewUtil.formatNumber1Digit(this.avgDrivingDistance, locale);
	}

	/**
	 * Returns the sand save conversion percentage for this round
	 * @return
	 */
	public String getAvgSandSaveConvertPercentage() {
		return NewUtil.formatPercent1Digit(avgSandSaveConvertPercentage, 
				locale);
	}

	/**
	 * Returns the number of sand save opportunities for this round
	 * @return
	 */
	public String getAvgNumSandSaveOpportunities() {
		return NewUtil.formatNumber1Digit(avgNumSandSaveOpportunities, locale);
	}

	/**
	 * Returns the up-and-down conversion percentage for this round
	 * @return
	 */
	public String getAvgUpDownConvertPercentage() {
		return NewUtil.formatPercent1Digit(avgUpDownConvertPercentage, locale);
	}

	/**
	 * Returns the number of up-and-down opportunities for this round
	 * @return
	 */
	public String getAvgNumUpDownOpportunities() {
		return NewUtil.formatNumber1Digit(avgNumUpDownOpportunities, locale);
	}
	
	/**
	 * Returns the score
	 * @return
	 */
	public String getAvgScore() {
		return NewUtil.formatNumber1Digit(avgScore, locale);
	}

	/**
	 * Getter
	 * @return
	 */
	public final Integer getScorecardCount() {
		return scorecardCount;
	}
}
