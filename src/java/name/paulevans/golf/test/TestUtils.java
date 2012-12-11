package name.paulevans.golf.test;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import name.paulevans.golf.BeanFactory;
import name.paulevans.golf.bean.MasterSummary;
import name.paulevans.golf.dao.ScorecardSummaryDAO;

import org.apache.commons.dbutils.DbUtils;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class TestUtils {
	
	// course IDs...
	public static final int COURSE_STADIUM = 0;

	
	/**
	 * Creates a new round
	 * @param aScore
	 * @param aRating
	 * @param aSlope
	 * @param aCourseId
	 * @return
	 */
	public static ScorecardSummaryDAO createRound(int aScore, float aRating,
			int aSlope, String aScoreType) {
		return createRound(aScore, aRating, aSlope, null, aScoreType);
	}
	
	/**
	 * Creates a new round
	 * @param aScore
	 * @param aRating
	 * @param aSlope
	 * @param aCourseId
	 * @return
	 */
	public static ScorecardSummaryDAO createRound(int aScore, float aRating,
			int aSlope, Date aDatePlayed, String aScoreType) {
		
		ScorecardSummaryDAO round;
		
		round = BeanFactory.newScorecardSummaryDAO();
		round.setScore(aScore);
		round.setRating(aRating);
		round.setSlope(aSlope);
		round.setDatePlayed(aDatePlayed);
		round.setScoreType(aScoreType);
		return round;
	}
	
	/**
	 * Creates and returns a new MasterSummary object initialized with the
	 * scorecard count
	 * @param aScorecardCount
	 * @return
	 */
	public static final MasterSummary newSummary(int aScorecardCount) {
		return new MasterSummary(null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, 
				null, null, null, null, aScorecardCount, null);
	}
	
	/**
	 * Returns a connection to the database
	 * @return
	 * @throws SQLException
	 */
	public static final Connection getConnection() throws SQLException {
		DbUtils.loadDriver(System.getProperty("jdbc.driver"));
		return DriverManager.getConnection(
				System.getProperty("jdbc.url"),
				System.getProperty("jdbc.username"),
				System.getProperty("jdbc.password"));
		
	}
	
	/**
	 * Application entry point - creates a full dataset
	 * @param aArgs
	 * @throws Exception
	 */
	public static void main(String aArgs[]) throws Exception {
		
		Connection jdbcConnection;
		IDatabaseConnection connection;
		IDataSet fullDataSet;
		ITableFilter filter;
		
		jdbcConnection = getConnection();
		try {
			// create the connection object...
			connection = new DatabaseConnection(jdbcConnection);
			filter = new DatabaseSequenceFilter(connection);
		
			// do a full database export...
			fullDataSet = new FilteredDataSet(filter, 
					connection.createDataSet());
			FlatXmlDataSet.write(fullDataSet, new FileOutputStream(
        		System.getProperty("dbunit.dataset.file")));
		} finally {
			DbUtils.closeQuietly(jdbcConnection);
		}
	}
}
