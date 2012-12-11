package name.paulevans.golf.dao;

/**
 * Models a composite-key used by the ScoreDAO
 * @author Paul
 *
 */
public class ScoreId  {
	
	private int holeId;
	private int scorecardId;
	
	/**
	 * Constructor
	 * @param aHoleId
	 * @param aScorecardId
	 */
	public ScoreId(int aHoleId, int aScorecardId) {
		holeId = aHoleId;
		scorecardId = aScorecardId;
	}
	
	/**
	 * Default no-arg constructor
	 *
	 */
	public ScoreId() {
		// does nothing...
	}
	
	/**
	 * Get the hole ID
	 * @return
	 */
	public int getHoleId() {
		return holeId;
	}

	/**
	 * Set the hole ID
	 * @param aHoleId
	 */
	public void setHoleId(int aHoleId) {
		holeId = aHoleId;
	}
	
	/**
	 * Get the scorecard ID
	 * @return
	 */
	public int getScorecardId() {
		return scorecardId;
	}

	/**
	 * Set the scorecard ID
	 * @param aScorecardId
	 */
	public void setScorecardId(int aScorecardId) {
		scorecardId = aScorecardId;
	}
}
