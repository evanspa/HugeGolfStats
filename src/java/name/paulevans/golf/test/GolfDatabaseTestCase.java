package name.paulevans.golf.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.dbutils.DbUtils;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class GolfDatabaseTestCase extends DatabaseTestCase {
	
	/**
	 * Returns the data set 
	 */
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSet(new FileInputStream(new File(
				System.getProperty("dbunit.dataset.file"))));
	}
	
	/**
	 * Returns a connection to the test database
	 */
	protected IDatabaseConnection getConnection() throws Exception {
		DbUtils.loadDriver(System.getProperty("jdbc.driver"));
		return new DatabaseConnection(TestUtils.getConnection());
	}
}
